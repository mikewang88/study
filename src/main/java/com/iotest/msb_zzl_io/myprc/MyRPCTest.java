//package com.iotest.msb_zzl_io.myprc;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.PooledByteBufAllocator;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.ByteToMessageDecoder;
//import org.junit.Test;
//
//import java.io.*;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.net.InetSocketAddress;
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * @Author: MikeWang
// * @Date: 2020/11/8 5:46 PM
// * @Description:
// */
//public class MyRPCTest {
//    @Test
//    public void startServer() {
//        NioEventLoopGroup boss = new NioEventLoopGroup(20);
//        NioEventLoopGroup worker = boss;
//
//        ServerBootstrap sbs = new ServerBootstrap();
//        ChannelFuture bind = sbs.group(boss, worker)
//                .channel(NioServerSocketChannel.class)
//                .childHandler(new ChannelInitializer<NioSocketChannel>() {
//                    @Override
//                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        System.out.println("server accept cliet port: " + ch.remoteAddress().getPort());
//                        ChannelPipeline p = ch.pipeline();
//                        p.addLast(new ServerDecode());
//                        p.addLast(new ServerRequestHandler());
//                    }
//                }).bind(new InetSocketAddress("localhost", 9090));
//        try {
//            bind.sync().channel().closeFuture().sync();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    //模拟comsumer端
//    @Test
//    public void get() {
//        new Thread(() -> {
//            startServer();
//        }).start();
//
//        System.out.println("server started......");
//
//
//        AtomicInteger num = new AtomicInteger(0);
//        int size = 50;
//        Thread[] threads = new Thread[size];
//        for (int i = 0; i < size; i++) {
//            threads[i] = new Thread(() -> {
//                Car car = proxyGet(Car.class);//动态代理实现
//                String arg = "hello" + num.incrementAndGet();
//                String res = car.ooxx(arg);
//                System.out.println("client over msg: " + res + " src arg: " + arg);
//            });
//        }
//
//        for (Thread thread : threads) {
//            thread.start();
//        }
//
//
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // Fly fly = proxyGet(Fly.class);//动态代理实现
//        // fly.xxoo(“hello”);
//    }
//
//
//    public static <T> T proxyGet(Class<T> interfaceInfo) {
//        //实现各个版本的动态代理。。。。
//
//        ClassLoader loader = interfaceInfo.getClassLoader();
//        Class<?>[] methodInfo = {interfaceInfo};
//
//
//        return (T) Proxy.newProxyInstance(loader, methodInfo, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                //如何设计我们的consumer对于provider的调用过程
//
//                //1，调用 服务，方法，参数  ==》 封装成message  [content]
//                String name = interfaceInfo.getName();
//                String methodName = method.getName();
//                Class<?>[] parameterTypes = method.getParameterTypes();
//                MyContent content = new MyContent();
//
//                content.setArgs(args);
//                content.setName(name);
//                content.setMethodName(methodName);
//                content.setParameterTypes(parameterTypes);
//
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                ObjectOutputStream oout = new ObjectOutputStream(out);
//                oout.writeObject(content);
//                byte[] msgBody = out.toByteArray();
//
//                //2，requestID+message  ，本地要缓存
//                //协议：【header<>】【msgBody】
//                Myheader header = createHeader(msgBody);
//
//                out.reset();
//                oout = new ObjectOutputStream(out);
//                oout.writeObject(header);
//                //解决数据decode问题
//                //TODO：Server：： dispatcher  Executor
//                byte[] msgHeader = out.toByteArray();
//                // System.out.println(“msgHeader :”+msgHeader.length);
//
//                //3，连接池：：取得连接
//                ClientFactory factory = ClientFactory.getFactory();
//                NioSocketChannel clientChannel = factory.getClient(new InetSocketAddress("localhost", 9090));
//                //获取连接过程中： 开始-创建，过程-直接取
//
//                //4，发送--> 走IO  out -->走Netty（event 驱动）
//
//                ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(msgHeader.length + msgBody.length);
//                // CountDownLatch countDownLatch = new CountDownLatch(1);
//                long id = header.getRequestID();
//                CompletableFuture res = new CompletableFuture<>();
//                ResponseMappingCallback.addCallBack(id, res);
//                byteBuf.writeBytes(msgHeader);
//                byteBuf.writeBytes(msgBody);
//                ChannelFuture channelFuture = clientChannel.writeAndFlush(byteBuf);
//                channelFuture.sync(); //io是双向的，你看似有个sync，她仅代表out
//
//                // countDownLatch.await();
//                //5，？，如果从IO ，未来回来了，怎么将代码执行到这里
//                //（睡眠/回调，如何让线程停下来？你还能让他继续。。。）
//
//
//                return res.get();//阻塞的
//            }
//        });
//    }
//
//
//    public static Myheader createHeader(byte[] msg) {
//        Myheader header = new Myheader();
//        int size = msg.length;
//        int f = 0x14141414;
//        long requestID = Math.abs(UUID.randomUUID().getLeastSignificantBits());
//        //0x14  0001 0100
//        header.setFlag(f);
//        header.setDataLen(size);
//        header.setRequestID(requestID);
//        return header;
//    }
//
//    class ServerDecode extends ByteToMessageDecoder {
//        //父类里一定有channelread{  前老的拼buf  decode（）；剩余留存 ;对out遍历 } -> bytebuf
////因为你偷懒，自己能不能实现！
//        @Override
//        protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
//
//            while (buf.readableBytes() >= 110) {
//                byte[] bytes = new byte[110];
//                buf.getBytes(buf.readerIndex(), bytes);  //从哪里读取，读多少，但是readindex不变
//                ByteArrayInputStream in = new ByteArrayInputStream(bytes);
//                ObjectInputStream oin = new ObjectInputStream(in);
//                Myheader header = (Myheader) oin.readObject();
//
//
//                //DECODE在2个方向都使用
//                //通信的协议
//                if (buf.readableBytes() >= header.getDataLen()) {
//                    //处理指针
//                    buf.readBytes(110);  //移动指针到body开始的位置
//                    byte[] data = new byte[(int) header.getDataLen()];
//                    buf.readBytes(data);
//                    ByteArrayInputStream din = new ByteArrayInputStream(data);
//                    ObjectInputStream doin = new ObjectInputStream(din);
//
//                    if (header.getFlag() == 0x14141414) {
//                        MyContent content = (MyContent) doin.readObject();
//                        out.add(new Packmsg(header, content));
//
//                    } else if (header.getFlag() == 0x14141424) {
//                        MyContent content = (MyContent) doin.readObject();
//                        out.add(new Packmsg(header, content));
//                    }
//
//
//                } else {
//                    break;
//                }
//
//
//            }
//
//        }
//
//    }
//
//    class ServerRequestHandler extends ChannelInboundHandlerAdapter {
//        //provider:
////思考下解决方法？
////
//        @Override
//        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//            Packmsg requestPkg = (Packmsg) msg;
//
//            // System.out.println(“server handler :”+ requestPkg.content.getArgs()[0]);
//            //如果假设处理完了，要给客户端返回了~！！！
//            //你需要注意哪些环节~！！！！！！！！
//
//            //bytebuf
//            //因为是个RPC吗，你得有requestID！！！！
//            //在client那一侧也要解决解码问题
//
//            //关注rpc通信协议  来的时候flag 0x14141414
//
//            //有新的header+content
//            String ioThreadName = Thread.currentThread().getName();
//            //1,直接在当前方法 处理IO和业务和返回
//
//            //3，自己创建线程池
//            //2,使用netty自己的eventloop来处理业务及返回
//            ctx.executor().execute(new Runnable() {
//                // ctx.executor().parent().next().execute(new Runnable() {
//                @Override
//                public void run() {
//                    String execThreadName = Thread.currentThread().getName();
//                    MyContent content = new MyContent();
//                    String s = "io thread: " + ioThreadName + " exec thread: " + execThreadName + " from args:" + requestPkg.content.getArgs()[0];
//                    content.setRes(s);
//                    byte[] contentByte = SerDerUtil.ser(content);
//
//                    Myheader resHeader = new Myheader();
//                    resHeader.setRequestID(requestPkg.header.getRequestID());
//                    resHeader.setFlag(0x14141424);
//                    resHeader.setDataLen(contentByte.length);
//                    byte[] headerByte = SerDerUtil.ser(resHeader);
//                    ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(headerByte.length + contentByte.length);
//
//                    byteBuf.writeBytes(headerByte);
//                    byteBuf.writeBytes(contentByte);
//                    ctx.writeAndFlush(byteBuf);
//                }
//            });
//
//
//        }
//
//    }
//
//
//
//    class ClientPool {
//        NioSocketChannel[] clients;
//        Object[] lock;
//
//        ClientPool(int size) {
//            clients = new NioSocketChannel[size];//init  连接都是空的
//            lock = new Object[size]; //锁是可以初始化的
//            for (int i = 0; i < size; i++) {
//                lock[i] = new Object();
//            }
//
//        }
//    }
//
//
//
//    class ClientResponses extends ChannelInboundHandlerAdapter {
//        //consumer.....
//        @Override
//        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            Packmsg responsepkg = (Packmsg) msg;
//
//            //曾经我们没考虑返回的事情
//            ResponseMappingCallback.runCallBack(responsepkg);
//
//        }
//
//    }
//
//    class Myheader implements Serializable {
//        //通信上的协议
//        /*
//        1，ooxx值
//        2，UUID:requestID
//        3，DATA_LEN
//         */
//        int flag;  //32bit可以设置很多信息。。。
//        long requestID;
//        long dataLen;
//
//
//        public int getFlag() {
//            return flag;
//        }
//
//        public void setFlag(int flag) {
//            this.flag = flag;
//        }
//
//        public long getRequestID() {
//            return requestID;
//        }
//
//        public void setRequestID(long requestID) {
//            this.requestID = requestID;
//        }
//
//        public long getDataLen() {
//            return dataLen;
//        }
//
//        public void setDataLen(long dataLen) {
//            this.dataLen = dataLen;
//        }
//
//    }
//
//    class MyContent implements Serializable {
//        String name;
//        String methodName;
//        Class<?>[] parameterTypes;
//        Object[] args;
//        String res;
//
//        public String getRes() {
//            return res;
//        }
//
//        public void setRes(String res) {
//            this.res = res;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getMethodName() {
//            return methodName;
//        }
//
//        public void setMethodName(String methodName) {
//            this.methodName = methodName;
//        }
//
//        public Class<?>[] getParameterTypes() {
//            return parameterTypes;
//        }
//
//        public void setParameterTypes(Class<?>[] parameterTypes) {
//            this.parameterTypes = parameterTypes;
//        }
//
//        public Object[] getArgs() {
//            return args;
//        }
//
//        public void setArgs(Object[] args) {
//            this.args = args;
//        }
//
//    }
//
//    interface Car {
//        public String ooxx(String msg);
//    }
//
//    interface Fly {
//        void xxoo(String msg);
//    }
//
//    class Packmsg{
//
//    }
//}
//
//
//
