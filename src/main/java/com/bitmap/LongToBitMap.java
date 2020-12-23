package com.bitmap;


import java.util.ArrayList;

/**
 * @Author: MikeWang
 * @Date: 2020/12/22 9:56 PM
 * @Description: Long 转化为bitMap
 */
public class LongToBitMap {
    /* recond longMax to bit*/
    private ArrayList<Long> bit = new ArrayList<>();

    /* recond String to bit value */
    private  ArrayList<Long> bitMap = new ArrayList<>();

    /* get bitMap size*/
    public int getLongSize() {
        return bitMap.size();
    }

    public LongToBitMap() {}

    public LongToBitMap(Long l) {
        this.addBitMap(l);
    }

    public LongToBitMap(Long[] ls) {
        this.addBitMap(ls);
    }

    /* add Long to bit type */
    private void addLongToBit(Long l) {
        /* get index while need to insert */
        int index = (int) (l >> 6);

        if(index == bit.size()) {
            bit.add(1l);
        }else {
            Long value = bit.get(index);
            bit.set(index, value | (1l << ((l - 1) & 0x3f)));
        }
    }

    /* delete Long to bit type*/
    private void delLongToBit(Long l) {
        /* get index while need to insert */
        int index = (int) (l >> 6);

        if(index >= bit.size()) {
            return;
        }else {
            Long value = bit.get(index);
            if(l == 0l) {
                bit.set(index, value & (Long.MAX_VALUE -1));
            }else {
                bit.set(index, value & (~(1l << ((l - 1) & 0x3f))));
            }
        }
    }

    /* add str to bitMap and update longMax */
    public boolean addBitMap(Long l) {
        Long[] ls = {l};
        return this.addBitMap(ls);
    }
    /* add strs to bitMap and update bitMax */
    public boolean addBitMap(Long[] ls) {
        if(ls.length == 0) {
            return false;
        }

        for (Long l : ls) {
            if(bitMap.isEmpty()) {
                bitMap.add(l);
                continue;
            }
            if(!bitMap.contains(l)) {
                bitMap.add(l);
                addLongToBit(l);
            }else {
                System.out.println("Long: " + l + " exsist in bitMap!");
            }
        }
        return true;
    }

    /* delete str to bitMap */
    public boolean delBitMap(Long l) {
        Long[] ls = {l};
        return this.delBitMap(ls);
    }

    /* delete strs to bitMap */
    public boolean delBitMap(Long[] ls) {
        if(ls.length == 0) {
            return false;
        }
        for (Long l : ls) {
            if(bitMap.isEmpty()) {
                return false;
            }else if(bitMap.contains(l)) {
                bitMap.remove(l);
                delLongToBit(l);
                continue;
            }else {
                continue;
            }
        }
        return true;
    }

    public ArrayList<Long> getBit() {
        return bit;
    }

    public ArrayList<Long> getBitMap() {
        return bitMap;
    }

}
