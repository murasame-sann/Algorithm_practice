package com.example.Sort;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author hua
 * @create 2021-05-05 19:09
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] array = {3, 9, -1, 10, 20};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
        int[] array=new int[80000];
        for (int i=0;i<80000;i++){
            array[i]=(int)(Math.random()*8000000);
        }
        Date date1=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str=dateFormat.format(date1);
        System.out.println("排序前的时间："+date1Str);
        bubble(array);
        Date date2=new Date();
        String date2Str=dateFormat.format(date2);
        System.out.println("排序后的时间："+date2Str);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(array));
        }
//        //第二次排序，排第二大的数
//        for (int i = 0; i < array.length - 1 - 1; i++) {
//            //前面的数比后面的数大，交换
//            if (array[i + 1] < array[i]) {
//                temp = array[i];
//                array[i] = array[i + 1];
//                array[i + 1] = temp;
//            }
//        }
//        System.out.println("第二次排序后的数组：");
//        System.out.println(Arrays.toString(array));
//        //第三次排序，排第三大的数
//        for (int i = 0; i < array.length - 1 - 2; i++) {
//            //前面的数比后面的数大，交换
//            if (array[i + 1] < array[i]) {
//                temp = array[i];
//                array[i] = array[i + 1];
//                array[i + 1] = temp;
//            }
//        }
//        System.out.println("第四次排序后的数组：");
//        System.out.println(Arrays.toString(array));
//        //第四次排序，排第四大的数
//        for (int i = 0; i < array.length - 1 - 3; i++) {
//            //前面的数比后面的数大，交换
//            if (array[i + 1] < array[i]) {
//                temp = array[i];
//                array[i] = array[i + 1];
//                array[i + 1] = temp;
//            }
//        }
//        System.out.println("第四次排序后的数组：");
//        System.out.println(Arrays.toString(array));
public static void bubble(int[] array){
    int temp = 0;
    boolean flag=false; //标识变量，表示是否进行过交换
    for (int j=0;j<array.length-1;j++) {
        for (int i = 0; i < array.length - 1 - j; i++) {
            //前面的数比后面的数大，交换
            if (array[i + 1] < array[i]) {
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                flag=true;
            }
        }
//        System.out.printf("第%d次排序后的数组：",j+1);
//        System.out.println(Arrays.toString(array));
        if (!flag){
            break;
        }else {
            flag=false; //重置flag，进行下次判断
        }
    }
}
    }

