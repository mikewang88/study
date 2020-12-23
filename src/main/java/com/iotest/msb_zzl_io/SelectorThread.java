package com.iotest.msb_zzl_io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * @Author: MikeWang
 * @Date: 2020/11/5 10:33 PM
 * @Description:
 */
public class SelectorThread extends ThreadLocal<LinkedBlockingQueue<Channel>> implements Runnable {
    // 每线程对应一个selector，
    // 多线程情况下，该主机，该程序的并发客户端被分配到多个selector上
    //注意，每个客户端，只绑定到其中一个selector
    //其实不会有交互问题

    Selector selector = null;
    //    LinkedBlockingQueue<Channel> lbq = new LinkedBlockingQueue<>();
    LinkedBlockingQueue<Channel> linkedBlockingQueue = get();  //lbq  在接口或者类中是固定使用方式逻辑写死了。你需要是lbq每个线程持有自己的独立对象
    SelectorThreadGroup selectorThreadGroup;

    @Override
    protected LinkedBlockingQueue<Channel> initialValue() {
        return new LinkedBlockingQueue<>();//你要丰富的是这里！  pool。。。
    }

    SelectorThread(SelectorThreadGroup selectorThreadGroup) {
        try {
            this.selectorThreadGroup = selectorThreadGroup;
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        //Loop
        while (true) {
            try {
                //1,select()
                int nums = selector.select();  //此方法执行阻塞选择操作。只有在选择了至少一个通道、调用了此选择器的wakeup方法或中断了当前线程(以最先出现的方式)之后，它才会返回。

                //2,处理selectedKeys
                System.out.println("In run(), selector.select() 获取到的keys数量为: " + nums);
                if (nums > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    System.out.println("In run(), keys is: " + keys);
                    Iterator<SelectionKey> iter = keys.iterator();
                    int loop = 0;
                    while (iter.hasNext()) {  //线程处理的过程
                        System.out.println("In run(), loop is: " + loop++);
                        SelectionKey key = iter.next();
                        iter.remove();
                        if (key.isAcceptable()) {  //复杂,接受客户端的过程（接收之后，要注册，多线程下，新的客户端，注册到那里呢？）
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHander(key);
                        } else if (key.isWritable()) {

                        }
                    }
                }
                //3,处理一些task :  listen  client
                System.out.println("处理一些task");
                if (!linkedBlockingQueue.isEmpty()) {   //队列是个啥东西啊？ 堆里的对象，线程的栈是独立，堆是共享的
                    System.out.println("linkedBlockingQueue 不是空的，包含 " + linkedBlockingQueue);
                    //只有方法的逻辑，本地变量是线程隔离的
                    Channel c = linkedBlockingQueue.take();
                    if (c instanceof ServerSocketChannel) {
                        ServerSocketChannel server = (ServerSocketChannel) c;
                        server.register(selector, SelectionKey.OP_ACCEPT);
                        System.out.println(Thread.currentThread().getName() + " register server listen");
                    } else if (c instanceof SocketChannel) {
                        SocketChannel client = (SocketChannel) c;
                        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                        client.register(selector, SelectionKey.OP_READ, buffer);
                        System.out.println(Thread.currentThread().getName() + " register client: " + client.getRemoteAddress());
                    } else {
                        System.out.println("c 既不是server，也不是client，c=" + c);
                    }
                } else {
                    System.out.println("linkedBlockingQueue 是空的");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    private void readHander(SelectionKey key) {
        System.out.println(Thread.currentThread().getName() + " readHander......");
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel client = (SocketChannel) key.channel();
        buffer.clear();
        while (true) {
            try {
                int num = client.read(buffer);
                if (num > 0) {
                    buffer.flip();  //将读到的内容翻转，然后直接写出
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (num == 0) {
                    break;
                } else if (num < 0) {
                    //客户端断开了
                    System.out.println("client: " + client.getRemoteAddress() + " closed......");
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName() + "::: acceptHandler Begin");
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        try {
            SocketChannel clientChannel = server.accept();
            clientChannel.configureBlocking(false);

            //选择一个selector，并调用wakeup()完成注册
            selectorThreadGroup.nextSelectorV3(clientChannel);
            System.out.println(Thread.currentThread().getName() + "::: acceptHandler Finish");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWorker(SelectorThreadGroup stgWorker) {
        this.selectorThreadGroup = stgWorker;
    }
}
