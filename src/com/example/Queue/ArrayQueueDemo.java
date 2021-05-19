package com.example.Queue;

import java.util.Scanner;

/**
 * @author hua
 * @create 2021-04-21 13:00
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
     ArrayQueue arrayQueue=new ArrayQueue(3);
     char key=' ';
     Scanner scanner=new Scanner(System.in);
     boolean loop=true;
     while (loop){
         System.out.println("s(show):显示队列");
         System.out.println("e(exit):退出程序");
         System.out.println("a(add):添加队列数据");
         System.out.println("g(get):从队列取出数据");
         System.out.println("h(head):查看队列头的数据");
         key=scanner.next().charAt(0);
         switch (key){
             case 's':
                 arrayQueue.showQueue();
                 break;
             case 'e':
                 scanner.close();
                 loop=false;
                 System.out.println("程序退出。。。");
                 break;
             case 'a':
                 System.out.println("输入一个数：");
                 int value=scanner.nextInt();
                 arrayQueue.addQueue(value);
                 break;
             case 'g':
                 try {
                     int res=arrayQueue.getQueue();
                     System.out.printf("取出的数据是%d\n",res);
                 }catch (Exception e){
                     System.out.println(e.getMessage());
                 }
                 break;
             case 'h':
                 try {
                     int res=arrayQueue.headQueue();
                     System.out.printf("队列头数据是%d\n",res);
                 }catch (Exception e){
                     System.out.println(e.getMessage());
                 }
                 break;
         }


     }
    }
}

//使用数组模拟队列
class ArrayQueue{
    private int maxSize;
    private int front;//队列头部
    private int rear;//队列尾部
    private int[] array;

    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        array=new int[maxSize];
        front=-1;//未添加数据时，指向队头前一个位置
        rear=-1;//未添加数据时，指向队尾
    }
    //判断队列是否满
    public boolean isFull(){
        return rear==maxSize-1;
    }
    //判断队列是否空
    public boolean isEmpty(){
        return front==rear;
    }
    //添加数据
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满！");
            return;
        }
        rear++;
        array[rear]=n;
    }
    //获取数据
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        front++;//front后移
        return array[front];
    }
    //显示队列
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空！");
            return;
        }
        for (int i=0;i<array.length;i++){
            System.out.printf("array[%d]=%d\n",i,array[i]);
        }
        //显示队列的头数据
    }
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        return array[front+1];
    }
}
