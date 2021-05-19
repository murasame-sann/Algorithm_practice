package com.example.Tree;

/**
 * @author hua
 * @create 2021-05-18 11:08
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        //说明：这里使用手动创建二叉树
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        System.out.println("前序遍历：");
        binaryTree.setRoot(node1);
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();

        System.out.println("前序遍历寻找节点。。。");
        HeroNode heroNode=binaryTree.preOrderSearch(5);
        if (heroNode!=null){
            System.out.println("该节点信息为："+heroNode);
        }else {
            System.out.println("二叉树中没有找到该节点！");
        }
    }
}

//定义二叉树
class BinaryTree {
    private HeroNode root; //定义根节点

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历！");
        }
    }
    public HeroNode preOrderSearch(int num){
        if (root!=null){
            return root.preOrderSearch(num);
        }else {
            return null;
        }
    }
    public HeroNode infixOrderSearch(int num){
        if (root!=null){
            return root.infixOrderSearch(num);
        }else {
            return null;
        }
    }
    public HeroNode postOrderSearch(int num){
        if (root!=null){
            return root.postOrderSearch(num);
        }else {
            return null;
        }
    }
}


//创建节点
class HeroNode {
    private int num;
    public String name;
    private HeroNode left; //默认为null
    private HeroNode right; //默认为null

    public void setNum(int num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }

    public HeroNode(int num, String name) {
        super();
        this.num = num;
        this.name = name;
    }

    //编写前序遍历
    public void preOrder() {
        System.out.println(this); //先输出父节点
        //递归左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //编写中序遍历
    public void infixOrder() {
        //递归左子树
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this); //输出父节点
        //递归右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        //递归左子树
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归右子树
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this); //输出父节点
    }
    //前序遍历
    public HeroNode preOrderSearch(int num) {
        //比较当前节点
        HeroNode resNode = null;
        if (this.num == num) {
            return this;
        }
        if (this.left != null) {
            resNode = this.left.preOrderSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(num);
        }
        return resNode;
    }
    //中序遍历
    public HeroNode infixOrderSearch(int num) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.num == num) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(num);
        }
        return resNode;
    }
    //后序遍历
    public HeroNode postOrderSearch(int num){
        HeroNode resNode=null;
        if (this.left!=null){
            resNode=this.left.postOrderSearch(num);
        }
        if (resNode!=null){
            return resNode;
        }
        if (this.right!=null){
            resNode=this.right.postOrderSearch(num);
        }
        if (resNode!=null){
            return resNode;
        }
        if (this.num == num) {
            return this;
        }
        return resNode;
    }
}