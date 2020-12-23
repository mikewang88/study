package com.dougwang.algorithm_and_datastructure.ds08;

/**
 * @Author: MikeWang
 * @Date: 2020/1/2 11:27 AM
 * @Description:
 */
public class MyBitmapv3 {

    //对应一个32位二进制数据
    private long words;
    //Bitmap 的位数大小
    private long size;

    public MyBitmapv3(long size) {
        this.size = size;
        this.words = 0;
    }

    /**
     * 把Bitmap 某一位设置为true
     *
     * @param bitIndex 位图的第bitIndex 位
     */
    public void setBit(long bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过Bitmap 有效范围");
        }
        printInfo(bitIndex);
        long temp = (1L << bitIndex);
        printInfo(temp);
        printInfo(words);
        words |= (1L << bitIndex);
        printInfo(words);
    }

    /**
     * 判断Bitmap 某一位的状态
     *
     * @param bitIndex 位图的第bitIndex 位
     * @return
     */
    public boolean getBit(long bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过Bitmap 有效范围");
        }
        return (words & (1L << bitIndex)) != 0;
    }

    public static void main(String[] args) {
        MyBitmapv3 bitmap = new MyBitmapv3(99999999999l);
        bitmap.setBit(15825502028l);
//        bitmap.setBit(2);
//        bitmap.setBit(3);
//        bitmap.setBit(4);
//        System.out.println(bitmap.getBit(2));
//        System.out.println(bitmap.getBit(5));
//        System.out.println(bitmap.getBit(15825502028l));
//
//        System.out.println(bitmap.size);
//        System.out.println(bitmap.words);
    }

    /**
     * 输出一个int的二进制数
     *
     * @param num
     */
    public static void printInfo(long num) {
        System.out.println(num + " 二进制为" + Long.toBinaryString(num));

    }

}
