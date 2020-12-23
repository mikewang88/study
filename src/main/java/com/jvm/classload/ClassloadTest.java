package com.jvm.classload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: MikeWang
 * @Date: 2020/7/1 5:40 PM
 * @Description:
 */
public class ClassloadTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String s) throws ClassNotFoundException {
                try {
                    if (s.equals("com.mythsman.test.Hello")) {
                        byte[] classBytes = Files.readAllBytes(Paths.get("/home/myths/Desktop/test/Hello.class"));
                        return defineClass(s, classBytes, 0, classBytes.length);
                    } else {
                        return super.loadClass(s);
                    }
                } catch (IOException e) {
                    throw new ClassNotFoundException(s);
                }
            }
        };
        Father outside = (Father) classLoader.loadClass("com.mythsman.test.Hello").newInstance();
        Hello inside = new Hello();
        outside.say();
        inside.say();
    }
}
