package com.example.ListNode;

/**
 * @author hua
 * @create 2021-04-24 19:12
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试");
        HeroNode2 h1=new HeroNode2(1,"宋江","及时雨");
        HeroNode2 h2=new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 h3=new HeroNode2(3,"吴用","智多星");
        HeroNode2 h4=new HeroNode2(4,"林冲","豹子头");
        DoubleLinkedList doubleLinkedList=new DoubleLinkedList();
        doubleLinkedList.addByOrder(h4);
        doubleLinkedList.addByOrder(h2);
        doubleLinkedList.addByOrder(h3);
        doubleLinkedList.addByOrder(h1);
        doubleLinkedList.list();
//        HeroNode2 h4_=new HeroNode2(4,"公孙胜","入云龙");
//        doubleLinkedList.update(h4_);
//        System.out.println("修改后。。。");
//        doubleLinkedList.list();
//        doubleLinkedList.delete(3);
//        System.out.println("修改后。。。");
//        doubleLinkedList.list();
    }
}
class DoubleLinkedList{
    private HeroNode2 head=new HeroNode2(0,"","");
    public HeroNode2 getHead() {
        return head;
    }
    //添加节点
    public void add(HeroNode2 heroNode2){
        //不考虑编号顺序时，先找到最后的节点，把这个节点的next指向新的节点
        HeroNode2 temp=head;
        while (true){
            if (temp.next==null){
                break;
            }
            //没有找到，将temp后移
            temp=temp.next;
        }
        //形成一个双向链表
        temp.next=heroNode2;
        heroNode2.pre=temp;
    }
    //顺序插入
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp=head;//头节点不能动，使用中间变量代替
        boolean flag=false;//flag标志添加的编号是否存在，默认为false
        while (true){
            if (temp.next==null){//temp已经在链表的最后
                break;
            }
            if (temp.next.num>heroNode.num){//位置找到后，就在temp的后面插入
                break;
            }
            else if (temp.next.num==heroNode.num){//编号已存在
                flag=true;
                break;
            }
            temp=temp.next;//temp向下移动，继续寻找
        }
        //判断flag
        if (flag){//编号已存在
            System.out.printf("编号%d已存在，无法添加!\n",heroNode.num);
        }else {
            heroNode.next=temp.next;
            heroNode.pre=temp.pre;
            temp.next=heroNode;
        }
    }
    //修改节点的信息，但num不改
    public void update(HeroNode2 newHeroNode){
        if (head.next==null){
            System.out.println("列表为空！");
            return;
        }
        HeroNode2 temp=head.next;
        boolean flag=false;
        while (true){
            if (temp==null){
                break;
            }
            if (temp.num== newHeroNode.num){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.name=newHeroNode.name;
            temp.nickName=newHeroNode.nickName;
        }else {
            System.out.printf("没有找到%d节点!\n",newHeroNode.num);
        }
    }
    //对于双向链表，可以直接找到要删除的节点，找到后自我删除
    public void delete(int n){
        //判断当前链表是否为空
        if (head.next==null){
            System.out.println("链表为空，无法删除!");
        }
        HeroNode2 temp=head.next;
        boolean flag=false;
        while(true) {
            if (temp == null) {//到了链表的最后
                break;
            }
            if (temp.num == n) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next=temp.next;
            //如果是最后的节点，下一行的代码不需要执行
            if (temp.next!=null) {
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("没有找到%d节点！\n",n);
        }
    }
    public void list(){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //头节点不能动，需要辅助变量进行遍历
        HeroNode2 temp=head.next;
        while (true){
            if (temp==null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            temp=temp.next;
        }
    }
}
class HeroNode2{
    public int num;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;
    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +"}";
    }

    public HeroNode2(int hNum, String hName, String hNickName){
        this.num=hNum;
        this.name=hName;
        this.nickName=hNickName;
    }}