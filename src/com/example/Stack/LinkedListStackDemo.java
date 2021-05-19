package com.example.Stack;



import org.w3c.dom.Node;

/**
 * @author hua
 * @create 2021-04-26 21:18
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
    }
    static class LinkedListStack{
        ListNode head=new ListNode(0,"");
        int maxSize=5;
        ListNode top=new ListNode(-1,"");
        public boolean isFull(){
            return top.num==maxSize;
        }

        public boolean isEmpty(){
            return top.next==null;
        }
        //入栈
        public void add(ListNode listNode){
            //不考虑编号顺序时，先找到最后的节点，把这个节点的next指向新的节点
            ListNode temp=head;
            while (true){
                if (temp.next==null){
                    break;
                }
                //没有找到，将temp后移
                temp=temp.next;
            }
            //退出循环时，temp指向链表最后
            temp.next=listNode;
        }
        public void list(){
            if (head.next==null){
                System.out.println("栈为空！");
                return;
            }
            //头节点不能动，需要辅助变量进行遍历
            ListNode temp=head.next;
            while (true){
                if (temp==null){
                    break;
                }
                //输出节点信息
                System.out.println(temp);
                temp=temp.next;
            }
        }
        //从最上面取出节点
        public ListNode pop(){
            if (isEmpty()){
                throw new RuntimeException("栈为空！");
            }
            ListNode temp=head.next;
            for (int i=0;i<maxSize;i++){
                temp=temp.next;
            }
            return temp;

        }
    }
    static class ListNode{
        int num;
        String data;
        ListNode next;
        public ListNode(int num,String data){
            this.num=num;
            this.data=data;
        }
    }
}
