package com.iotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @Author: MikeWang
 * @Date: 2020/4/7 5:30 PM
 * @Description:
 */
public class Test {
//    著作权归https://www.pdai.tech所有。
//    链接：https://www.pdai.tech/md/java/io/java-io-basic-usage.html

    public static void main(String[] args) throws IOException {

        URL url = new URL("http://www.baidu.com");

        /* 字节流 */
        InputStream is = url.openStream();

        /* 字符流 */
        InputStreamReader isr = new InputStreamReader(is, "utf-8");

        /* 提供缓存功能 */
        BufferedReader br = new BufferedReader(isr);

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }
}
