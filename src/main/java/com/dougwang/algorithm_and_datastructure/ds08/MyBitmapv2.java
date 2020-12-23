package com.dougwang.algorithm_and_datastructure.ds08;

/**
 * @Author: MikeWang
 * @Date: 2020/1/2 11:27 AM
 * @Description:
 */
public class MyBitmapv2 {

    //对应一个32位二进制数据
    private int words;
    //Bitmap 的位数大小
    private int size;

    public MyBitmapv2(int size) {
        this.size = size;
        this.words = 0;
    }

    /**
     * 把Bitmap 某一位设置为true
     *
     * @param bitIndex 位图的第bitIndex 位
     */
    public void setBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过Bitmap 有效范围");
        }
        words |= (1L << bitIndex);
    }

    /**
     * 判断Bitmap 某一位的状态
     *
     * @param bitIndex 位图的第bitIndex 位
     * @return
     */
    public boolean getBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过Bitmap 有效范围");
        }
        return (words & (1L << bitIndex)) != 0;
    }

    public static void main(String[] args) {
        MyBitmapv2 bitmap = new MyBitmapv2(32);
        bitmap.setBit(1);
        bitmap.setBit(2);
        bitmap.setBit(3);
        bitmap.setBit(4);
        System.out.println(bitmap.getBit(2));
        System.out.println(bitmap.getBit(5));
    }

}
