package com.bitmap;

/**
 * @Author: MikeWang
 * @Date: 2020/12/16 2:27 PM
 * @Description:
 */
public class BitMap {
    //用于记录数据是否存在(类似记录表)
    private byte[] bits;

    //容量,即要对多少对象进行bitmap操作
    private int capacity;


    public BitMap(int capacity){
        this.capacity = capacity;
        //1个byte包含8个bit位，那么capacity数据容量需要多少个byte[]来记录数据呢？答案是：capacity/8+1(非8倍数+1操作)
        bits = new byte[(capacity >>3 )+1];
    }

    /**
     * description: 对象的添加（即标记）.
     * @param objHashCode 需要标记对象的hashcode值(乐观认为不同对象不重复)
     */
    public void add(int objHashCode){
        // objHashCode/8得到byte[]的index（可以看作在第几行）
        int arrayIndex = objHashCode >> 3;
        // objHashCode%8得到在byte[index]的第position位置上的bit位值为1
        int position = objHashCode & 0x07;
        //将1左移position后，那个位置自然就是1，然后和以前的数据做|，这样，那个位置就替换成1了。
        bits[arrayIndex] |= (1 << position);
    }

    public boolean contain(int objHashCode){
        // objHashCode/8得到byte[]的index
        int arrayIndex = objHashCode >> 3;

        // objHashCode%8得到在byte[index]的位置
        int position = objHashCode & 0x07;

        //将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可
        return (bits[arrayIndex] & (1 << position)) != 0;
    }

    public void clear(int objHashCode){
        // objHashCode/8得到byte[]的index
        int arrayIndex = objHashCode >> 3;

        // objHashCode%8得到在byte[index]的位置
        int position = objHashCode & 0x07;

        //将1左移position后，那个位置自然就是1，然后对取反，再与当前值做&，即可清除当前的位置了.
        bits[arrayIndex] &= ~(1 << position);

    }

    public static void main(String[] args) {
        BitMap bitmap = new BitMap(100);
        bitmap.add(7);
        System.out.println("插入7成功");

        boolean isexsit = bitmap.contain(7);
        System.out.println("7是否存在:"+isexsit);

        bitmap.clear(7);
        isexsit = bitmap.contain(7);
        System.out.println("7是否存在:" + isexsit);

    }
}
