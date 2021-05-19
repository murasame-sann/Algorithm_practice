package com.example.Sort;

import java.util.Arrays;

/**
 * @author hua
 * @create 2021-05-10 16:08
 */
class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[] = new int[arr.length]; //归并需要一个额外空间
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    //分+合的方法
    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归分解
            mergeSort(array, left, mid, temp);
            //向右递归分解
            mergeSort(array, mid + 1, right, temp);
            //合并
            merge(array, left, mid, right, temp);
        }
    }

    //合并的方法
    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left; //i表示左边有序序列的初始索引
        int j = mid + 1; //j表示右边有序序列的初始索引
        int t = 0; //指向temp数组的当前索引

        //先把左右两边的数据按规则填充到temp数组
        //直到有一边处理完为止
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[t] = array[i];
                t++;
                i++;
            } else {
                temp[t] = array[j];
                t++;
                j++;
            }
        }

        //把有剩余数据的一边依次填充到temp
        while (i <= mid) {
            temp[t] = array[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = array[j];
            t++;
            j++;
        }
        //将temp数组的元素拷贝到array
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            array[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
