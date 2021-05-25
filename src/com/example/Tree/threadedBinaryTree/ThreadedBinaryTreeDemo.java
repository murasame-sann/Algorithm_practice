package com.example.Tree.threadedBinaryTree;

import com.sun.source.tree.BinaryTree;

/**
 * @author hua
 * @create 2021-05-21 22:06
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "a");
        HeroNode root1 = new HeroNode(3, "b");
        HeroNode root2 = new HeroNode(6, "c");
        HeroNode root3 = new HeroNode(8, "d");
        HeroNode root4 = new HeroNode(10, "e");
        HeroNode root5 = new HeroNode(14, "f");
        root.setLeft(root1);
        root.setRight(root2);
        root1.setLeft(root3);
        root1.setRight(root4);
        root2.setLeft(root5);

        //测试线索化
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadedNodes();

        HeroNode leftNode = root4.getLeft();
        HeroNode rightNode = root4.getRight();
//        System.out.println(leftNode);
//        System.out.println(rightNode);

        //线索化二叉树后，不能使用原来的遍历方式
        tree.threadedList();
    }
}

//线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root; //定义根节点
    private HeroNode pre = null; //为了实现线索化，需要创建指向前驱节点的指针

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树
    public void threadedList() {
        HeroNode node = root;
        while (node != null) {
            //找到leftType为1的节点，随着遍历node可能会变化
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            //输出后继节点
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    //编写中序线索化的方法
    public void threadedNodes(HeroNode node) {
        //node==null时，无法线索化
        if (node == null) {
            return;
        }
        //先线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前节点，要处理前驱节点
        if (node.getLeft() == null) {
            //左指针指向前驱节点
            node.setLeft(pre);
            //修改左指针的类型,指向前驱节点
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        //线索化右子树
        threadedNodes(node.getRight());
    }

    public void delNode(int num) {
        if (root != null) {
            if (root.getNum() == num) {
                root = null;
            } else {
                root.delNode(num);
            }
        } else {
            System.out.println("该二叉树为空，无法删除！");
        }
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

    public HeroNode preOrderSearch(int num) {
        if (root != null) {
            return root.preOrderSearch(num);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int num) {
        if (root != null) {
            return root.infixOrderSearch(num);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int num) {
        if (root != null) {
            return root.postOrderSearch(num);
        } else {
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
    //说明：leftType==0时表示指向左子树，leftType==1时表示指向前驱节点
    //rightType==0时表示指向右子树，rightType==1时表示指向后继节点
    private int leftType;
    private int rightType;

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getLeftType() {
        return leftType;
    }

    public int getRightType() {
        return rightType;
    }

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

    public void delNode(int num) {
        //若左子节点不为空，且左子节点为要删除的节点，则把左子节点置空，然后返回
        if (this.left != null && this.left.num == num) {
            this.left = null;
            return;
        }
        //若右子节点不为空，且右子节点为要删除的节点，则把右子节点置空，然后返回
        if (this.right != null && this.right.num == num) {
            this.right = null;
            return;
        }
        //左子树递归，寻找要删除的节点
        if (this.left != null) {
            this.left.delNode(num);
        }
        //右子树递归，寻找要删除的节点
        if (this.right != null) {
            this.right.delNode(num);
        }
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
    public HeroNode postOrderSearch(int num) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.num == num) {
            return this;
        }
        return resNode;
    }
}
