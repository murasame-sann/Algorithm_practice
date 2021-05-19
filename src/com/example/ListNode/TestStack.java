package com.example.ListNode;

import java.util.Stack;

/**
 * @author hua
 * @create 2021-04-24 14:03
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack=new Stack();
        //入栈
        stack.add("a");
        stack.add("b");
        stack.add("c");
        //出栈
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

}
