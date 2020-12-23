//package com.iotest.msb_zzl_io.myprc;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioSocketChannel;
//
//import java.net.InetSocketAddress;
//import java.util.Random;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @Author: MikeWang
// * @Date: 2020/11/8 6:43 PM
// * @Description:
// * //源于 spark 源码
// */
//public class ClientFactory {
//    int poolSize = 1;
//    NioEventLoopGroup clientWorker;
//    Random rand = new Random();
//
//    private ClientFactory() {
//    }
//
//    private static final MyRPCTest.ClientFactory factory;
//
//    static {
//        factory = new MyRPCTest.ClientFactory();
//    }
//
//    public static MyRPCTest.ClientFactory getFactory() {
//        return factory;
//    }
//
//
//    //一个consumer 可以连接很多的provider，每一个provider都有自己的pool  K,V
//
//    ConcurrentHashMap<InetSocketAddress, MyRPCTest.ClientPool> outboxs = new ConcurrentHashMap<>();
//
//    public synchronized NioSocketChannel getClient(InetSocketAddress address) {
//
//        MyRPCTest.ClientPool clientPool = outboxs.get(address);
//        if (clientPool == null) {
//            outboxs.putIfAbsent(address, new MyRPCTest.ClientPool(poolSize));
//            clientPool = outboxs.get(address);
//        }
//
//        int i = rand.nextInt(poolSize);
//
//        if (clientPool.clients[i] != null && clientPool.clients[i].isActive()) {
//            return clientPool.clients[i];
//        }
//
//        synchronized (clientPool.lock[i]) {
//            return clientPool.clients[i] = create(address);
//        }
//
//    }
//
//    private NioSocketChannel create(InetSocketAddress address) {
//
//        //基于 netty 的客户端创建方式
//        clientWorker = new NioEventLoopGroup(1);
//        Bootstrap bs = new Bootstrap();
//        ChannelFuture connect = bs.group(clientWorker)
//                .channel(NioSocketChannel.class)
//                .handler(new ChannelInitializer<NioSocketChannel>() {
//                    @Override
//                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        ChannelPipeline p = ch.pipeline();
//                        p.addLast(new MyRPCTest.ServerDecode());
//                        p.addLast(new MyRPCTest.ClientResponses());  //解决给谁的？？  requestID..
//                    }
//                }).connect(address);
//        try {
//            NioSocketChannel client = (NioSocketChannel) connect.sync().channel();
//            return client;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return null;
//
//
//    }
//}
