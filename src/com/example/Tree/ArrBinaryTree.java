package com.example.Tree;

/**
 * @author hua
 * @create 2021-05-20 21:44
 * 顺序存储二叉树
 */
public class ArrBinaryTree {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrayBinaryTree BinaryTree=new ArrayBinaryTree(arr);
        BinaryTree.preOrder(); //顺序应为1，2，4，5，3，6，7
    }
}

class ArrayBinaryTree{
    public int[] arr; //存储数据

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载方法
    public void preOrder(){
        this.preOrder(0);
    }
    //编写方法，完成顺序存储二叉树的前序遍历，index为数组下标
    public void preOrder(int index){
        //若数组为空，或arr.length=0
        if (arr==null||arr.length==0){
            System.out.println("数组为空，不能遍历");
        }
        //数组当前元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index*2+1)<arr.length){
            preOrder(2*index+1);
        }
        //向右递归遍历
        if ((2*index+2)<arr.length){
            preOrder(2*index+2);
        }
    }
}