package com.example.Stack;

import java.util.Scanner;

/**
 * @author hua
 * @create 2021-04-26 18:12
 */
public class ArrayStackDemo {
    public static void main(String[] args) {

        ArrayStack arrayStack=new ArrayStack(4);
        String key="";
        boolean flag=true;
        Scanner scanner=new Scanner(System.in);

        while (flag){
            System.out.println("show:显示栈");
            System.out.println("exit:退出栈");
            System.out.println("push:添加数据到栈");
            System.out.println("pop:取出栈数据");
            System.out.println("请输入你的选择：");
            key=scanner.next();
            switch (key){
                case "show":
                    arrayStack.list();
                    break;
                case "exit":
                    scanner.close();
                    flag=false;
                    System.out.println("正在退出。。。");
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value=scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res=arrayStack.pop();
                        System.out.printf("出栈的数据为：%d",res);
                        break;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
            }
        }
    }
    static class ArrayStack{
        private int maxSize; //栈的大小
        private int[] stack;
        private int top=-1; //栈顶

        public ArrayStack(int maxSize){
            this.maxSize=maxSize;
            stack=new int[this.maxSize];
        }

        public boolean isFull(){
            return top==maxSize-1;
        }

        public boolean isEmpty(){
            return top==-1;
        }
        //入栈
        public void push(int value){
            if (isFull()){
                System.out.println("栈已满！");
                return;
            }
            top++;
            stack[top]=value;
        }

        public int pop(){
            if (isEmpty()){
                throw new RuntimeException("栈为空！");
            }
            int value=stack[top];
            top--;
            return value;
        }
        //遍历时从栈顶开时遍历
        public void list(){
            if (isEmpty()){
                System.out.println("栈为空！");
                return;
            }
            for (int i=top;i>=0;i--){
                System.out.printf("%d\n",stack[i]);
            }
        }
    }
}
