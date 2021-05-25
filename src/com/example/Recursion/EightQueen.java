package com.example.Recursion;

/**
 * @author hua
 * @create 2021-05-03 15:12
 */
public class EightQueen {
    //定义max表示共有几个皇后
    int max = 8;
    //定义数组，保存皇后的位置
    int[] array = new int[max];
    public static void main(String[] args) {
        EightQueen queens = new EightQueen();
        queens.check(0);
    }

    //编写方法放置第n个皇后
    private void check(int n) {
        if (n == max) {  //n=8时，其实8个皇后已经放好
            Print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judgeLocation(n)) { //若为真，则接着放n+1个皇后，即开始递归
                check(n + 1);
            }
            //若冲突，则继续执行array[n]=i; 即将第n个皇后放在本行的后移一个位置
        }
    }

    //查看我们放入第n个皇后时，就检测该皇后是否和前面已摆放的皇后冲突
    private boolean judgeLocation(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false; //皇后不能在同一列，也不能在同一斜线上，abs求绝对值，
                // 可以看成判断斜率 K=Y/X
            }
        }
        return true;
    }

    //输出皇后的位置
    private void Print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
