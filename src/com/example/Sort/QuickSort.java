package com.example.Sort;

import java.util.Arrays;

/**
 * @author hua
 * @create 2021-05-09 16:05
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {-9, 78, 0, 23, -567, 70};
        quickSort(array, 0, array.length - 1);
        System.out.println("修改后：" + Arrays.toString(array));
    }

    public static void quickSort(int[] array, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        int pivot = array[(left + right) / 2];
        int temp = 0;
        //循环目的：让比 pivot小的放到左边，比 pivot大的放到右边
        while (l < r) {
            //在pivot 左边找，找到大于等于 pivot的值才退出
            while (array[l] < pivot) {
                l += 1;
            }
            //在pivot 右边找，找到小于等于 pivot的值才退出
            while (array[r] > pivot) {
                r -= 1;
            }
            //若条件成立，则 pivot左边的值已经全部小于pivot，右边的值已经全部小于pivot
            if (l >= r) {
                break;
            }
            //交换
            temp = array[r];
            array[r] = array[l];
            array[l] = temp;

            //交换后，若array[l]==pivot ，则前移
            if (array[l] == pivot) {
                r--;
            }
            //交换后，若array[r]==pivot ，则前移
            if (array[r] == pivot) {
                l++;
            }
        }
        //若 l==r，必须l++，r--，否则会溢出
        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            quickSort(array, left, r);
        } if (l < right) {
            quickSort(array, l, right);
        }
    }
}
