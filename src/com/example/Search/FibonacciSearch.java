package com.example.Search;

import java.util.Arrays;

/**
 * @author hua
 * @create 2021-05-14 11:08
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(FibSearch(arr,8));
    }

    //构建斐波那契数列
    public static int[] Fibonacci() {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    //编写斐波那契查找算法，key为需要查找的值，非递归方式编写
    public static int FibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0; //存放mid值
        int[] fib = Fibonacci();
        //获取斐波那契分割数值的下标
        while (high > fib[k] - 1) {
            k++;
        }
        //因为fib[k]可能大于数组 a 的长度，所以需要用Arrays类构建新数组，并指向temp[]，不足的部分用0填充
        int[] temp = Arrays.copyOf(a, fib[k]);
        //需要使用a数组最后的数填充temp
        //例：int[] arr = {1, 8, 10, 89, 1000, 1234，0，0} ==》int[] arr = {1, 8, 10, 89, 1000, 1234，1234，1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        //使用while循环找到key
        while (low <= high) {
            mid = low + fib[k - 1] - 1;
            if (key < temp[mid]) { //应该继续向数组的前面查找(左边)
                high = mid - 1;
                //说明：全部元素=前面元素+后面元素
                //f[k]=f[k-1]+f[k-2]
                //因为前面有f[k-1]个元素，所以可以继续拆分成f[k-1]=f[k-2]+f[k-3]
                //即在f[k-1]的前面继续查找
                k--;
            } else if (key > temp[mid]) { //应该继续向数组的后面查找(右边)
                low = mid + 1;
                //说明：全部元素=前面元素+后面元素
                //f[k]=f[k-1]+f[k-2]
                //因为后面有f[k-2]个元素，所以可以继续拆分成f[k-1]=f[k-3]+f[k-4]
                //即在f[k-2]的前面继续查找
                k -= 2;
            }else { //需要确定返回的下标
                if (mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
