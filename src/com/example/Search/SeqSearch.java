package com.example.Search;

/**
 * @author hua
 * @create 2021-05-12 16:52
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 89, 34};
        int index = seqSearch(arr, 11);
        if (index == -1) {
            System.out.println("没有找到相关数据！");
        } else {
            System.out.println("下标为：" + index);
        }
    }

    //线性查找是逐一比对，发现有相同值时返回下标(找到一个就返回)
    public static int seqSearch(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
