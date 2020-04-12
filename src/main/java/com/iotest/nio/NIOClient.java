package com.iotest.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author: MikeWang
 * @Date: 2020/4/9 9:55 PM
 * @Description:
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 83);
        OutputStream out = socket.getOutputStream();
        String s = "hello world";
        out.write(s.getBytes());
        out.close();
    }
}
