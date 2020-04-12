package com.leetcode.concurrency;

/**
 * @Author: MikeWang
 * @Date: 2020/3/24 3:54 PM
 * @Description:
 * 红绿灯
 */
public class leetCode1279 {
}

class TrafficLight {
    public TrafficLight() {
    }

    boolean lightA = true;

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
        synchronized (this) {
            if (roadId == 1) {
                if (!lightA) {
                    turnGreen.run();
                    lightA = true;
                }
                crossCar.run();
            } else {
                if (lightA) {
                    turnGreen.run();
                    lightA = false;
                }
                crossCar.run();
            }
        }
    }
}