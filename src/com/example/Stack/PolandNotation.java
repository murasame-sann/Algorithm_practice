package com.example.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author hua
 * @create 2021-04-27 19:07
 */
public class PolandNotation {
    public static void main(String[] args) {
        //中缀表达式转后缀表达式
        String expression = "1+((2+3)*4)-5";
        List<String> ex = toInfixExpressionList(expression);
        System.out.println("原式为：" + expression);
        System.out.println("后缀表达式为：" + parseSuffixExpressionList(ex));
        //定义逆波兰表达式 3 4 + 5 * 6 -
//        String suffixExpression = "3 4 + 5 * 6 -";
        //思路：1.把字符串放入ArrayList中
        //     2.把ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
//        List<String> stringList = getListString(suffixExpression);
//        System.out.println(stringList);
//        int res = calculation(stringList);
//        System.out.println("结果为：" + res);
    }

    public static List<String> parseSuffixExpressionList(List<String> list) {
        //定义栈
        Stack<String> s1 = new Stack<String>(); //符号栈
        List<String> s2 = new ArrayList<String>(); //存储中间结果的数组

        for (String s3 : list) {
            //是数字的话，入栈
            if (s3.matches("\\d+")) {
                s2.add(s3);
            } else if (s3.equals("(")) {
                s1.push(s3);
            } else if (s3.equals(")")) {
                // “)”的话，则依次弹出s1栈顶的运算符，并压入s2，直到遇到“(”为止
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); // 将 "(" 弹出s1栈，消除小括号
            } else {
                // 当s3的优先级小于等于s1栈顶运算符
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(s3)) {
                    s2.add(s1.pop());
                }
                s1.push(s3); //还需将s3压入栈
            }
        }
        // 把s1剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    //把中缀表达式转成List
    public static List<String> toInfixExpressionList(String s) {
        //定义List，存放中缀表达式
        List<String> List = new ArrayList<String>();
        int i = 0; //用于遍历
        String str; //对多位数拼接
        char c; //每遍历一个字符，便放入c中
        do {
            //非数字加到List里
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                List.add("" + c);
                i++;
            } else {
                str = "";
                //判断是否未越界和是否为数字
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c; //拼接
                    i++;
                }
                List.add(str);
            }
        } while (i < s.length());
        return List;
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //分割字符串
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String element : split) {
            list.add(element);
        }
        return list;
    }

    //完成逆波兰表达式的运算
    public static int calculation(List<String> list) {
        Stack<String> stack = new Stack<String>();
        for (String str : list) {
            // 使用正则表达式来取数，匹配多位数
            if (str.matches("\\d+")) {
                stack.push(str);
            } else {// 取出两个数并运算
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                int res = 0;
                if (str.equals("+")) {
                    res = a + b;
                } else if (str.equals("-")) {
                    res = b - a;
                } else if (str.equals("*")) {
                    res = a * b;
                } else if (str.equals("/")) {
                    res = b / a;
                } else {
                    throw new RuntimeException("运算符有误！");
                }
                stack.push(res + "");
            }
        }
        //最后留在栈的就是结果
        return Integer.parseInt(stack.pop());
    }
}

// 编写类，返回一个运算符的优先级
class Operation {
    private static int Add = 1;
    private static int Sub = 1;
    private static int Mul = 2;
    private static int Div = 2;

    public static int getValue(String oper) {
        int res = 0;
        switch (oper) {
            case "+":
                res = Add;
                break;
            case "-":
                res = Sub;
                break;
            case "*":
                res = Mul;
                break;
            case "/":
                res = Div;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }
}