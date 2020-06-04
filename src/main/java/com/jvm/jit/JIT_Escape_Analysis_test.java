package com.jvm.jit;

/**
 * @Author: MikeWang
 * @Date: 2020/5/29 10:48 AM
 * @Description:
 *
 * Xmx1000m -Xms1000m -XX:-DoEscapeAnalysis -XX:+PrintGC
 * -Xmx1000m -Xms1000m -XX:+DoEscapeAnalysis -XX:+PrintGC
 *
 * 可以说，在 HotSpot 中暂时没有实现这项优化。
 * 随着即时编译器的发展与逃逸分析技术的逐渐成熟，
 * 相信不久的将来 HotSpot 也会实现这项优化功能。
 */
public class JIT_Escape_Analysis_test {
    public static void main(String[] args) {
        for (int i = 0; i < 200000 ; i++) {
            getAge();
        }
    }

    public static int getAge(){
        Student person = new Student(" 小明 ",18);
        return person.getAge();
    }

    static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
