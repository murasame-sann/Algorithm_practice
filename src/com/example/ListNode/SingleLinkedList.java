package com.example.ListNode;

import java.util.Stack;

import static com.example.ListNode.SingleList.findLastNode;
import static com.example.ListNode.SingleList.reversePrint;

/**
 * @author hua
 * @create 2021-04-22 14:45
 */
public class SingleLinkedList {
    public static void main(String[] args) {
        HeroNode h1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode h2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode h3 = new HeroNode(3, "吴用", "智多星");
        HeroNode h4 = new HeroNode(4, "林冲", "豹子头");

        SingleList singleList = new SingleList();
        SingleList list = new SingleList();
//        HeroNode node=new HeroNode(2,"小卢","玉麒麟!!!");
//        singleList.add(h1);  //无序添加
//        singleList.add(h2);
//        singleList.add(h3);
//        singleList.add(h4);
        singleList.addByOrder(h3);  //有序添加
        singleList.addByOrder(h4);
        singleList.addByOrder(h1);
        singleList.addByOrder(h2);
        list.addByOrder(h3);  //有序添加
        list.addByOrder(h4);
        singleList.combineList(singleList.getHead(), list.getHead());
//        singleList.update(node);
//        singleList.delete(3);
//        System.out.println(singleList.getLength(singleList.getHead()));
//        HeroNode res=findLastNode(singleList.getHead(),1);
//        System.out.println("res="+res);
//        System.out.println("逆序打印。。。");
//        reversePrint(singleList.getHead());
//        singleList.list();
    }
}

class SingleList {
    //定义头节点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //查找单链表中倒数第k个节点，index为倒数第index个节点
    public static HeroNode findLastNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength(head);//得到长度
        if (index <= 0 || index > size) {//index不能小于0，也不能大于链表长度
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //添加节点
    public void add(HeroNode heroNode) {
        //不考虑编号顺序时，先找到最后的节点，把这个节点的next指向新的节点
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //没有找到，将temp后移
            temp = temp.next;
        }
        //退出循环时，temp指向链表最后
        temp.next = heroNode;
    }

    //显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //头节点不能动，需要辅助变量进行遍历
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //顺序插入
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;//头节点不能动，使用中间变量代替
        boolean flag = false;//flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//temp已经在链表的最后
                break;
            }
            if (temp.next.num > heroNode.num) {//位置找到后，就在temp的后面插入
                break;
            } else if (temp.next.num == heroNode.num) {//编号已存在
                flag = true;
                break;
            }
            temp = temp.next;//temp向下移动，继续寻找
        }
        //判断flag
        if (flag) {//编号已存在
            System.out.printf("编号%d已存在，无法添加!\n", heroNode.num);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，但num不改
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("列表为空！");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.num == newHeroNode.num) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到%d节点!\n", newHeroNode.num);
        }
    }

    public void delete(int n) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.num == n) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到%d节点！\n", n);
        }
    }

    //获取单链表节点的个数
    public static int getLength(HeroNode head) {
        if (head.next == null) {//空链表
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //反转单链表
    public static void reverseList(HeroNode head) {
        //链表为空，或只有一个节点时，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;  //指向当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (cur != null) {  //遍历原列表，取出节点放入反转列表
            next = cur.next;  //暂时保存当前节点的下一个节点，后面需要使用
            cur.next = reverseHead.next;  //将cur的下一节点指向新链表的前端
            reverseHead.next = cur; //将cur连接到新链表上，赋值
            cur = next;  //让cur后移
        }
        head.next = reverseHead.next;
    }

    //利用栈逆序打印列表
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //把链表所有节点放入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //打印栈的节点
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //栈特点是先进后出
        }
    }

    //作业：合并两个有序的单链表，合并后依然有序
    public void combineList(HeroNode heroNode1, HeroNode heroNode2) {
        HeroNode temp = head;
        while (heroNode1.next != null) {
            temp.next = heroNode1;
        }
        while (heroNode2.next != null) {
            temp.next = heroNode2;
        }
        System.out.println(temp);
    }
}

class HeroNode {
    public int num;
    public String name;
    public String nickName;
    public HeroNode next;

    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' + "}";
    }

    public HeroNode(int hNum, String hName, String hNickName) {
        this.num = hNum;
        this.name = hName;
        this.nickName = hNickName;
    }
}
