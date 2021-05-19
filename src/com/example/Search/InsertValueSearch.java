package com.example.Search;

/**
 * @author hua
 * @create 2021-05-13 22:49
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 50);
        System.out.println(index);
    }

    //差值查找算法，需要有序数组(从小到大)
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        //注意：findVal < arr[0] || findVal > arr[arr.length - 1]可以防止 mid 越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {//没有找到，返回-1
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) { //向右递归查找（左指针向右）
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { //向左递归查找（右指针向左）
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
