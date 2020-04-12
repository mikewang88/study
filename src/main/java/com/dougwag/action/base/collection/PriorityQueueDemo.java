package com.dougwag.action.base.collection;

import java.util.PriorityQueue;

/**
 * @Author: MikeWang
 * @Date: 2020/3/8 9:01 PM
 * @Description:
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {
//        PriorityQueue<String> q = new PriorityQueue<String>();
//        //入列
//        q.offer("1");
//        q.offer("2");
//        q.offer("5");
//        q.offer("3");
//        q.offer("4");
//
//        //出列
//        System.out.println(q.poll());  //1
//        System.out.println(q.poll());  //2
//        System.out.println(q.poll());  //3
//        System.out.println(q.poll());  //4
//        System.out.println(q.poll());  //5

        //通过改造器指定排序规则
        PriorityQueue<Student> q = new PriorityQueue<>((o1, o2) -> {
            //按照分数低到高，分数相等按名字
            if (o1.getScore() == o2.getScore()) {
                return o1.getName().compareTo(o2.getName());
            }
            return o1.getScore() - o2.getScore();
        });
        //入列
        q.offer(new Student("dafei", 20));
        q.offer(new Student("will", 17));
        q.offer(new Student("setf", 30));
        q.offer(new Student("bunny", 20));

        //出列
        System.out.println(q.poll());  //Student{name='will', score=17}
        System.out.println(q.poll());  //Student{name='bunny', score=20}
        System.out.println(q.poll());  //Student{name='dafei', score=20}
        System.out.println(q.poll());  //Student{name='setf', score=30}
    }
}

class Student {
    private String name;  //名字
    private int score;    //分数

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
