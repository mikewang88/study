package com.dougwang.algorithm_and_datastructure.ds08;

/**
 * @Author: MikeWang
 * @Date: 2019/12/31 3:38 PM
 * @Description:
 * 整型：
byte:1个字节 8位 -128~127

short ：2个字节 16位

int ：4个字节 32位

long：8个字节 64位

浮点型：

float：4个字节 32 位

double ：8个字节 64位
 */
public class MyBitmap {
    //每个word 是一个long 类型的元素，对应一个64位二进制数据
    private long[] words;
    //Bitmap 的位数大小
    private int size;

    public MyBitmap(int size) {
        this.size = size;
        this.words = new long[getWordIndex(size - 1) + 1];
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
        int worIndex = getWordIndex(bitIndex);
        return (words[worIndex] & (1L << bitIndex)) != 0;
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
        int wordIndex = getWordIndex(bitIndex);
        words[wordIndex] |= (1L << bitIndex);
    }


    /**
     * 定位Bitmap 某一位对应的word
     * @param bitIndex
     * @return
     */
    private int getWordIndex(int bitIndex) {
        //右移6位，相当于除以64
        return bitIndex >> 6;
    }

    public static void main(String[] args) {
        MyBitmap bitmap = new MyBitmap(128);
        bitmap.setBit(1);
        bitmap.setBit(2);
        bitmap.setBit(3);
        bitmap.setBit(4);
        System.out.println(bitmap.getBit(1));
        System.out.println(bitmap.getBit(5));
    }
}
