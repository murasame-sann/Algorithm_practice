package com.example.ListNode;

/**
 * @author hua
 * @create 2021-04-25 13:58
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList=new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.List();
        System.out.println("出圈。。。");
        circleSingleLinkedList.countBoy(1,2,5);


    }
    //创建环形单向链表
    static class CircleSingleLinkedList{
        private Boy first=null;
        //添加节点构成环形链表
        public void addBoy(int nums){
            if (nums<1){
                System.out.println("nums的值不正确，无法创建！");
                return;
            }
            Boy curBoy=null; //辅助指针
            for (int i=1;i<=nums;i++){
                Boy boy=new Boy(i);
                //第一个节点
                if (i==1){
                    first=boy;
                    first.setNext(first); //构成环
                    curBoy=first;
                }else {
                    curBoy.setNext(boy);
                    boy.setNext(first);
                    curBoy=boy; //辅助指针后移
                }
            }
        }
        //遍历环形链表
        public void List(){
            if (first==null){
                System.out.println("链表为空！");
                return;
            }
            Boy curBoy=first;
            while (true){
                System.out.printf("该节点编号为%d\n",curBoy.getNum());
                if (curBoy.getNext()==first){//遍历完成
                    break;
                }
                curBoy=curBoy.getNext(); //辅助节点后移
            }
        }
        //计算出圈顺序
        public void countBoy(int startNum,int countNum,int nums){
            //先校验数据
            if (first==null || startNum<1 || startNum>nums){
                System.out.println("参数输入有误！");
                return;
            }
            Boy helper=first; //helper事先指向最后的节点
            while (true){
                if (helper.getNext()==first){
                    break;
                }
                helper=helper.getNext();
            }
            //开始前，先让first和helper移动startNum-1次
            for (int j=0;j<startNum-1;j++){
                    first=first.getNext();
                    helper=helper.getNext();
            } //开始报数时，让first和helper同时移动countNum-1次
            while (true){
                if (helper==first){ //圈中只有一个节点
                    break;
                }
                for (int j = 0; j<countNum-1; j++){
                    first=first.getNext();
                    helper=helper.getNext();
                }
                System.out.printf("%d出圈\n",first.getNum());
                first=first.getNext();
                helper.setNext(first);
            }
            System.out.printf("最后的节点为：%d\n",first.getNum());
        }
    }

    static class Boy{
        private int num;
        private Boy next;
        public Boy(int num){
            this.num=num;
        }
        public void setNum(int num) {
            this.num = num;
        }

        public void setNext(Boy next) {
            this.next = next;
        }

        public int getNum() {
            return num;
        }

        public Boy getNext() {
            return next;
        }
    }
}
