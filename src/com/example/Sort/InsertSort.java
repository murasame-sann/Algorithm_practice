package com.example.Sort;

import java.util.Arrays;

/**
 * @author hua
 * @create 2021-05-07 22:09
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = {101, 24, 119, 1};
        insertSort(array);
    }

    //插入排序
    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //定义待插入数
            int insertValue = arr[i + 1];
            int insertIndex = i;

            //给insertValue找到插入位置
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {  //保证给insertValue找插入位置时不越界
                arr[insertIndex + 1] = arr[insertIndex]; //insertIndex后移
                insertIndex--;
            }
            //退出循环时，说明插入的数大于传入数组里的数，就放在该数前面
            arr[insertIndex + 1] = insertValue;
            System.out.printf("第%d轮插入后", i + 1);
            System.out.println(Arrays.toString(arr));
        }
    }
}
