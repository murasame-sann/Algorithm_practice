package com.example.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author hua
 * @create 2021-05-25 15:06
 * 赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree huffmanTree = new HuffmanTree();
        Node root=huffmanTree.createHuffmanTree(arr);
        preOrder(root);
    }
//前序遍历方法
    public static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("空树无法遍历！");
        }
    }
    //创建赫夫曼树
    public Node createHuffmanTree(int[] arr) {
        //第一步，遍历arr
        //第二步，将arr的每个元素构建成一个Node
        //第三步，把Node放入ArrayList
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //规律总结：循环处理
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
//            System.out.println("nodes = " + nodes);

            //取出根节点最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //构建新的二叉树，根节点为两叶子节点之和
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //添加处理得到的新二叉树
            nodes.add(parent);
            Collections.sort(nodes);
//            System.out.println("处理后：" + nodes);
        }
        return nodes.get(0);
    }
}

//引入方法比较
class Node implements Comparable<Node> {
    int value; //权值
    Node left; //指向左子节点
    Node right; //指向右子节点

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }

    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}