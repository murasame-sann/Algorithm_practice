package com.example.Tree;

import java.util.Arrays;

/**
 * @author hua
 * @create 2021-05-24 17:04
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        //数组进行升序排序
        int arr[] = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int arr[]) {
        int temp=0;
        System.out.println("堆排序");

//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第一次调整后:\n" + Arrays.toString(arr));
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次调整后:\n" + Arrays.toString(arr));
        for (int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i, arr.length);
        }
        for (int j=arr.length-1;j>0;j--){
            //交换
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0, j);
        }
        System.out.println("调整后:\n" + Arrays.toString(arr));
    }

    //把一个数组，调整成一个大顶堆

    /**
     * 功能：将以 i 对应的非叶子节点的树调整成大顶堆
     * arr 待调整的数组
     * i 非叶子节点在数组中的索引
     * length 要调整的元素的长度，逐渐减少
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];
        //k = i * 2 + 1，k 是 i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { //左子节点小于右子节点
                k++;
            }
            if (arr[k] > temp) { //子节点大于父节点
                arr[i] = arr[k]; //把较大的值给当前节点
                i = k; //i指向k，继续循环比较
            } else {
                break;
            }
        }
        //for循环结束后，就已经把以i为父节点的树的最大值，放在了顶部
        arr[i] = temp; //temp值放到调整后的位置
    }
}
