package com.iotest.msb_zzl_io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * @Author: MikeWang
 * @Date: 2020/11/5 10:32 PM
 * @Description:
 */
public class SelectorThreadGroup {
    SelectorThread[] selectorThreadArray;
    ServerSocketChannel serverSocketChannel = null;
    AtomicInteger xid = new AtomicInteger(0);

    SelectorThreadGroup selectorThreadGroup = this;

    public void setWorker(SelectorThreadGroup selectorThreadGroup) {
        this.selectorThreadGroup = selectorThreadGroup;
    }

    SelectorThreadGroup(int num) {
        //num  线程数
        selectorThreadArray = new SelectorThread[num];
        for (int i = 0; i < num; i++) {
            selectorThreadArray[i] = new SelectorThread(this);
            new Thread(selectorThreadArray[i]).start();
        }
    }


    public void bind(int port) {
        try {
            serverSocketChannel = ServerSocketChannel.open();// 打开服务器-套接字通道
            serverSocketChannel.configureBlocking(false);//设置非阻塞
            serverSocketChannel.bind(new InetSocketAddress(port));//绑定端口

            //注册到那个selector上呢？
//            nextSelectorV2(server);
            nextSelectorV3(serverSocketChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 负载均衡的方式选择一个selector
     * @param channel
     */
    public void nextSelectorV3(Channel channel) {
        try {
            if (channel instanceof ServerSocketChannel) {
                SelectorThread selectorThread = next();  //listen 选择了 boss组selectorThreadArray中的一个线程后，要更新这个线程的work组
                selectorThread.linkedBlockingQueue.put(channel);
                selectorThread.setWorker(selectorThreadGroup);
                selectorThread.selector.wakeup();
            } else {
                SelectorThread selectorThread = nextV3();  //在main线程（当前SelectorThreadGroup）中，取到堆里的selectorThread对象
                //1,通过队列传递数据 消息
                selectorThread.linkedBlockingQueue.add(channel);
                //2,通过打断阻塞，让对应的线程去自己在打断后完成注册selector
                selectorThread.selector.wakeup();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void nextSelectorV2(Channel c) {

        try {
            if (c instanceof ServerSocketChannel) {
                selectorThreadArray[0].linkedBlockingQueue.put(c);
                selectorThreadArray[0].selector.wakeup();
            } else {
                SelectorThread st = nextV2();  //在 main线程种，取到堆里的selectorThread对象

                //1,通过队列传递数据 消息
                st.linkedBlockingQueue.add(c);
                //2,通过打断阻塞，让对应的线程去自己在打断后完成注册selector
                st.selector.wakeup();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void nextSelector(Channel c) {
        SelectorThread st = next();  //在 main线程种，取到堆里的selectorThread对象

        //1,通过队列传递数据 消息
        st.linkedBlockingQueue.add(c);
        //2,通过打断阻塞，让对应的线程去自己在打断后完成注册selector
        st.selector.wakeup();

//    public void nextSelector(Channel c) {
//        SelectorThread st = next();  //在 main线程种，取到堆里的selectorThread对象
//
//        //1,通过队列传递数据 消息
//        st.lbq.add(c);
//        //2,通过打断阻塞，让对应的线程去自己在打断后完成注册selector
//        st.selector.wakeup();


        //重点：  c有可能是 server  有可能是client
//        ServerSocketChannel s = (ServerSocketChannel) c;
        //呼应上， int nums = selector.select();  //阻塞  wakeup()
//        try {
//            s.register(st.selector, SelectionKey.OP_ACCEPT);  //会被阻塞的!!!!!
//            st.selector.wakeup();  //功能是让 selector的select（）方法，立刻返回，不阻塞！
//            System.out.println("aaaaa");
//        } catch (ClosedChannelException e) {
//            e.printStackTrace();
//        }

    }


    //无论 serversocket  socket  都复用这个方法
    private SelectorThread next() {
        int index = xid.incrementAndGet() % selectorThreadArray.length;  //轮询就会很尴尬，倾斜
        return selectorThreadArray[index];
    }

    private SelectorThread nextV2() {
        int index = xid.incrementAndGet() % (selectorThreadArray.length - 1);  //轮询就会很尴尬，倾斜
        return selectorThreadArray[index + 1];
    }

    private SelectorThread nextV3() {
        int index = xid.incrementAndGet() % selectorThreadGroup.selectorThreadArray.length;  //动用worker的线程分配
        return selectorThreadGroup.selectorThreadArray[index];
    }
}
