package com.jvm.zzmAction;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: MikeWang
 * @Date: 2020/2/11 10:25 AM
 * @Description:
 */

/**
 * * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author zzm  */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        System.out.println("1");
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
