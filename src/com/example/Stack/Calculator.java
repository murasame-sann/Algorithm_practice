package com.example.Stack;

import java.util.Scanner;

/**
 * @author hua
 * @create 2021-04-27 10:41
 */
public class Calculator {
        public static void main(String[] args) {
            String expression="30+2*6-2";
            ArrayStack2 numStack=new ArrayStack2(10);
            ArrayStack2 operStack=new ArrayStack2(10);

            int index=0; //用于扫描
            int num1=0;
            int num2=0;
            int oper=0;
            int res=0;
            char ch=' ';
            String keepNum = "";
            while (true){
                //依次得到每一个字符
                ch=expression.substring(index,index+1).charAt(0);
                //判断是否为数字或运算符
                if (operStack.isOper(ch)){ //若为运算符
                    if (!operStack.isEmpty()){
                        //处理
                        try {
                            if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                                num1 = numStack.pop();
                                num2 = numStack.pop();
                                oper = operStack.pop();
                                res = numStack.cal(num1,num2,oper);
                                //运算结果入数栈
                                numStack.push(res);
                                operStack.push(ch);
                            }else {
                                //栈空时直接入栈
                                operStack.push(ch);
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }else {
                        //栈空时直接入符号栈
                        operStack.push(ch);
                    }
                }else { //如果是数，直接入数栈，多位数需要拼接
//                    numStack.push(ch-48);
                keepNum+=ch;
                if (index==expression.length()-1){  //判断是否为最后一位，要减一，因为index从0开始
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                //判断后一位是否为数字
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        //要清空keepNum
                        keepNum="";
                    }
                }}
                index++;
                //判断是否扫描到最后
                if (index>=expression.length()){
                    break;
                }
            }
            //表达式扫描完毕，就顺序从数栈和符号栈取出相应的数和符号，并运行
            while (true){
                //若符号栈为空，则已计算出结果
                if (operStack.isEmpty()){
                    break;
                }
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = operStack.pop();
                res = numStack.cal(num1,num2,oper);
                numStack.push(res);
            }
            System.out.printf("表达式为%s=%d",expression,numStack.pop());
        }

        static class ArrayStack2{
            private int maxSize; //栈的大小
            private int[] stack;
            private int top=-1; //栈顶

            public ArrayStack2(int maxSize){
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
            //返回运算符的优先级，数字越大，优先级越高
            public int priority(int oper){
                if (oper=='*'||oper=='/'){
                    return 1;
                }
                else if (oper=='+'||oper=='-'){
                    return 0;
                }
                else {
                    return -1;
                }
            }
            //判断是否为运算符
            public boolean isOper(char val){
                return val=='+'||val=='-'||val=='*'||val=='/';
            }
            //运算方法
            public int cal(int num1,int num2,int oper){
                int res=0; //存放计算结果
                switch (oper){
                    case '+':
                        res=num1+num2;
                        break;
                    case '-':
                        res=num2-num1;
                        break;
                    case '*':
                        res=num1*num2;
                        break;
                    case '/':
                        res=num2/num1;
                        break;
                }
                return res;
            }
            //返回栈顶的值，但不出栈
            public int peek(){
                return stack[top];
            }
        }
    }


