package com.dougwang.algorithm_and_datastructure.ds03;

import java.util.Stack;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 9:11 PM
 * @Description:
 * 括号的匹配问题
 * https://juejin.im/post/5db2cfad518825647a3d1ae8
 */
public class Stack05 {
}
class Solution {
    public boolean isValid(String s) {
        if (s.length() == 0)
            return true;
        if ((s.length() & 1) == 1)
            return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                case '[':
                case '{':
                    stack.push(s.charAt(i));
                    continue;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(')
                        return false;
                    continue;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[')
                        return false;
                    continue;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{')
                        return false;
                    continue;
            }
        }
        return stack.isEmpty();
    }
}
