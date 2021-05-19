package com.example.Recursion;

import java.util.ArrayList;

/**
 * @author hua
 * @create 2021-04-29 20:38
 */
public class Maze {
    //使用递归回溯给小球找路
    public static void main(String[] args) {
        //设计一个数组，表示不同的策略，使用for循环运行这些策略，保存2节点到集合里，比较大小
        Maze maze=new Maze();
        maze.showMap();
    }
    public void showMap(){
        //创建二维数组，模拟迷宫
        int[][] map=new int[8][7];
        //使用1 表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int j = 1; j < 7; j++) {
            map[j][0] = 1;
            map[j][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        setWay1(map,1,1);
        int num1=0;
        //输出新地图，小球走过，并标识过的地图
        System.out.println("小球走过，并标识过的地图\n");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
                if (map[i][j]==2){
                    num1++;
                }
            }
            System.out.println();
        }
        System.out.println("小球走过的部数是："+num1+"\n");
        for (int i=1;i<6;i++){
            for (int j=1;j<7;j++){
                map[1][i]=0;
                map[j][5]=0;
            }
        }
        setWay2(map,1,1);
        int num2=0;
        //输出新地图，小球走过，并标识过的地图
        System.out.println("小球走过，并标识过的地图\n");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
                if (map[i][j]==2){
                    num2++;
                }
            }
            System.out.println();
        }
        System.out.println("小球走过的部数是："+num2+"\n");
    }

    //使用递归回溯，map表示地图，i表示开始找的行，j表示开始找的列
    //找到路则返回true，否则返回false
    public static boolean setWay1(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //找到通路
            return true;
        } else {
            if (map[i][j] == 0) { //如果还没有走过该点
                //按照策略 上-右-下-左 走
                map[i][j] = 2; //假设该点可以走通
                if (setWay1(map, i - 1, j)) { //向上走
                    return true;
                } else if (setWay1(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay1(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay1(map, i, j - 1)) { //向左走
                    return true;
                } else {
                    //该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { //若map[i][j]!=0，可能是 1，2，3
                return false;
            }
        }
    }
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //找到通路
            return true;
        } else {
            if (map[i][j] == 0) { //如果还没有走过该点
                //按照策略 下-右-上-左 走
                map[i][j] = 2; //假设该点可以走通
                if (setWay2(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay2(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay2(map, i - 1, j)) { //向上走
                    return true;
                } else if (setWay2(map, i, j - 1)) { //向左走
                    return true;
                } else {
                    //该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { //若map[i][j]!=0，可能是 1，2，3
                return false;
            }
        }
    }
}
