package com.example.Sort;

import java.util.Arrays;

/**
 * @author hua
 * @create 2021-05-06 20:44
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {101, 34, 119, 1};
        selectSort(array);
    }

    //选择排序，从要排序的数据中，按指定规则选出某一元素，在依照规定交换位置形成排序，时间复杂度为O(n^2)
    public static void selectSort(int[] array) {
        for (int i=0;i< array.length-1;i++) {

            //第i轮排序，假设第i个数最小
            int minIndex = i;
            int min = array[i];
            for (int j = 1+i; j < array.length; j++) {
                if (min > array[j]) {//假设不成立，重置最小值
                    min = array[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                //交换最小值，放在array[0]
                array[minIndex] = array[i];
                array[i] = min;
            }
            System.out.printf("第%d轮。。。",i+1);
            System.out.println(Arrays.toString(array));
        }
        //第二轮排序
//        int minIndex2 = 1;
//        int min2 = array[0];
//        for (int j = 1 + 1; j < array.length; j++) {
//            if (min2 > array[j]) {//假设不成立，重置最小值
//                min2 = array[j];
//                minIndex2 = j;
//            }
//        }
//        if(minIndex2!=1) {
//            //交换最小值，放在array[0]
//            array[minIndex] = array[0];
//            array[0] = min;
//        }
//        System.out.println("第二轮。。。");
//        System.out.println(Arrays.toString(array));
    }
}
