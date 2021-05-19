package com.example.Queue;

import java.util.Scanner;

/**
 * @author hua
 * @create 2021-04-21 21:55
 * 环形队列
 */
public class CircleArrayQueueDemo {

        public static void main(String[] args) {
            System.out.println("环形队列案例。。。");
            CircleArray circleArray=new CircleArray(4);//最大的有效位置为3
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
                        circleArray.showQueue();
                        break;
                    case 'e':
                        scanner.close();
                        loop=false;
                        System.out.println("程序退出。。。");
                        break;
                    case 'a':
                        System.out.println("输入一个数：");
                        int value=scanner.nextInt();
                        circleArray.addQueue(value);
                        break;
                    case 'g':
                        try {
                            int res=circleArray.getQueue();
                            System.out.printf("取出的数据是%d\n",res);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 'h':
                        try {
                            int res=circleArray.headQueue();
                            System.out.printf("队列头数据是%d\n",res);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                }


            }
        }
    }
class CircleArray{
    private int maxSize;
    private int front;//队列头部
    private int rear;//队列尾部
    private int[] array;

    public CircleArray(int arrayMaxSize){
        maxSize=arrayMaxSize;
        front=0;
        rear=0;
        array=new int[maxSize];
    }
    public boolean isFull(){
        return (rear+1)%maxSize==front;
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
        array[rear]=n;
        //取模，防止溢出
        rear=(rear+1)%maxSize;
    }
    //获取数据
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        int value=array[front];
        front=(front+1)%maxSize;
        return value;
    }
    //显示队列
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空！");
            return;
        }
        for (int i=front;i<front+size();i++){
            System.out.printf("array[%d]=%d\n",i%maxSize,array[i%maxSize]);
        }
        //显示队列的头数据
    }
    //求出当前队列的有效个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        return array[front];
    }
}





