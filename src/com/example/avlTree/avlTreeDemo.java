package com.example.avlTree;

/**
 * @author hua
 * @create 2021-06-04 20:29
 * 平衡二叉树
 */
public class avlTreeDemo {
    public static void main(String[] args) {
        int arr[] = {4,3,6,5,7,8};
        avlTree avlTree=new avlTree();
        for (int i=0;i<arr.length;i++){
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历：");
        avlTree.infixOrder();
        System.out.println("树高："+avlTree.getRoot().height());
    }
}

class avlTree {
    private Node root;

    public Node getRoot(){
        return root;
    }
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("该树为空，无法遍历！");
        }
    }

    //查找节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /*
        node 为二叉排序树的根节点
        函数返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node temp = node;
        //查找左子节点，直到找到最小值
        while (temp.left != null) {
            temp = temp.left;
        }
        //删除最小节点
        deleteNode(temp.value);
        return temp.value;
    }

    //删除节点
    public void deleteNode(int value) {
        if (root == null) {
            return;
        } else {
            //先找到删除的节点
            Node targetNode = search(value);
            //没有找到删除节点
            if (targetNode == null) {
                return;
            }
            //如果只有targetNode一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //查找targetNode的父节点
            Node parent = searchParent(value);
            //若删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) { //是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) { //是右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //删除有两颗子树的节点
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;
            } else {  //删除有一颗子树的节点
                //子树为左子树
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {  //targetNode为parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {  //子树为右子树
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    //返回左子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.height();
        }
    }

    //返回当前节点的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //查找删除节点，value为要删除节点的值
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {//查找的值小于当前节点，向左递归查找
            if (this.left != null) {
                return this.left.search(value);
            } else {
                return null;
            }
        } else {  //查找的值大于当前节点，向右递归查找
            if (this.right != null) {
                return this.right.search(value);
            } else {
                return null;
            }
        }
    }

    //查找删除节点的父节点，value为要删除节点的值
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {  //如果查找的值小于当前的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null; //没有找到父节点
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //递归添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入节点的值和当前子树的根节点的值的关系
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else { //添加节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                //递归向右子树添加
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}