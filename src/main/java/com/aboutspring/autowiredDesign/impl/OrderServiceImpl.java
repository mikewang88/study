package com.aboutspring.autowiredDesign.impl;

import com.aboutspring.autowiredDesign.OrderService;
import com.aboutspring.autowiredDesign.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: MikeWang
 * @Date: 2020/4/28 10:47 AM
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private UserService userService;

    @Override
    public void query() {
        System.out.println(userService);
    }
}
