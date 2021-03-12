package com.leetcode.day;

import java.net.Inet6Address;
import java.net.InetAddress;

/**
 * @Author: MikeWang
 * @Date: 2021/3/12 9:11 PM
 * @Description:
 */
public class L468 {
    public String validIPAddress(String IP) {
        try {
            return (InetAddress.getByName(IP) instanceof Inet6Address) ? "IPv6": "IPv4";
        } catch(Exception e) {}
        return "Neither";
    }
}
