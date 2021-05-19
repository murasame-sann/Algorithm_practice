package com.example.Sort;

import java.util.Arrays;

/**
 * @author hua
 * @create 2021-05-11 14:08
 */
public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    //基数排序
    public static void radixSort(int[] arr) {
        //得到数组中最大数，假设第一个最大
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大的位数
        int maxLength = (max + "").length();

        //定义二维数组，表示十个桶
        int[][] bucket = new int[10][arr.length];

        //为记录每个桶中实际存放了多少数据，须数组记录
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //桶中有数据才放入原数组中
                if (bucketElementCounts[k] != 0) {
                    //循环该桶，即第k个一维数组
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入arr
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }


        System.out.println("第i+1轮："+Arrays.toString(arr));
    }
}
