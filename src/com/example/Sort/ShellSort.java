package com.example.Sort;

import java.util.Arrays;

/**
 * @author hua
 * @create 2021-05-08 14:57
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(array);
        enhanceShellSort(array);
    }

    public static void shellSort(int[] array) {
        int temp = 0;
        int count = 0;
        for (int k = array.length / 2; k > 0; k /= 2) {
            //第count轮排序
            for (int i = k; i < array.length; i++) {
                //步长为k
                for (int j = i - k; j >= 0; j -= k) {
                    //若当前元素大于加上步长后的元素，交换
                    if (array[j] > array[j + k]) {
                        temp = array[j];
                        array[j] = array[j + k];
                        array[j + k] = temp;
                    }
                }
            }
            System.out.printf("第%d轮排序。。。\n", ++count);
            System.out.println(Arrays.toString(array));
        }
        //第二轮排序
//        for (int i = 2; i < array.length; i++) {
//            //步长为2
//            for (int j = i - 2; j >= 0; j -= 2) {
//                //若当前元素大于加上步长后的元素，交换
//                if (array[j] > array[j+2]) {
//                    temp = array[j];
//                    array[j] = array[j+2];
//                    array[j+2] = temp;
//                }
//            }
//        }
//        System.out.println("第二轮排序。。。");
//        System.out.println(Arrays.toString(array));
//
//        //第三轮排序
//        for (int i = 1; i < array.length; i++) {
//            //步长为1
//            for (int j = i - 1; j >= 0; j -= 1) {
//                //若当前元素大于加上步长后的元素，交换
//                if (array[j] > array[j+1]) {
//                    temp = array[j];
//                    array[j] = array[j+1];
//                    array[j+1] = temp;
//                }
//            }
//        }
//        System.out.println("第三轮排序。。。");
//        System.out.println(Arrays.toString(array));
    }

    //优化交换式的希尔排序，改成移动式希尔排序
    public static void enhanceShellSort(int[] array) {
        for (int k = array.length / 2; k > 0; k /= 2) {
            //从第k个元素开始，逐个对其所在的组进行直接插入
            for (int i = k; i < array.length; i++) {
                int temp = array[i]; //要插入的变量
                int j = i; //防止越界
                if (array[i] < array[i - k]) {
                    while (j - k >= 0 && temp < array[j - k]) {
                        //移动
                        array[j] = array[j - k];
                        j -= k;
                    }
                    //退出循环时，就给temp找到插入位置
                    array[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
