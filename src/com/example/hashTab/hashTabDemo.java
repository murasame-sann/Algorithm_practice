package com.example.hashTab;

import java.util.Scanner;

/**
 * @author hua
 * @create 2021-05-16 9:03
 */
public class hashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        hashTab hashTab = new hashTab(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("delete:删除雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("清输入要添加的雇员id：");
                    int id = scanner.nextInt();
                    System.out.println("请输入要添加雇员的名字：");
                    String name = scanner.next();
                    Employee employee = new Employee(id, name);
                    hashTab.add(employee);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("清输入要查找的雇员id：");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "delete":
                    System.out.println("清输入要删除的雇员id：");
                    id = scanner.nextInt();
                    hashTab.deleteEmpById(id);
                    break;
                case "exit":
                    System.out.println("正在退出。。。");
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }
}

//创建hashTab
class hashTab {
    private EmpLinkedList[] empLinkedLIstArray;
    private int size;//哈希表的大小

    //构造器
    public hashTab(int size) {
        this.size = size;
        //初始化empLinkedLIstArray
        empLinkedLIstArray = new EmpLinkedList[size];
        //要初始化每一条链表，否则会报空指针异常
        for (int i = 0; i < size; i++) {
            empLinkedLIstArray[i] = new EmpLinkedList();
        }
    }

    public void add(Employee employee) {
        //根据id得到该员工要添加到的链表
        int empLinkedListNum = hashFun(employee.id);
        //把employee添加到对应链表中
        empLinkedLIstArray[empLinkedListNum].add(employee);
    }

    //遍历哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLIstArray[i].list(i);
        }
    }

    //根据输入的id，查找雇员
    public void findEmpById(int id) {
        int empLinkedListNum = hashFun(id);
        Employee employee = empLinkedLIstArray[empLinkedListNum].findEmpById(id);
        if (employee == null) {
            System.out.println("没有找到数据！");
        } else {
            System.out.printf("在第%d条链表中找到雇员id=%d\n", empLinkedListNum + 1, id);
        }
    }

    public void deleteEmpById(int id) {
        int empLinkedListNum = hashFun(id);
        empLinkedLIstArray[empLinkedListNum].deleteEmpById(id);
    }

    //编写散列函数，使用取模法
    public int hashFun(int id) {
        return id % size;
    }
}

class Employee {
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList {
    private Employee head; //默认为null

    public void add(Employee employee) {
        //添加第一个雇员
        if (head == null) {
            head = employee;
            return;
        }
        //使用指针，定义到最后
        Employee cursor = head;
        while (true) {
            if (cursor.next == null) {
                break;
            }
            cursor = cursor.next;
        }
        //退出时把Employee加到链表
        cursor.next = employee;
    }

    //遍历链表，并列出
    public void list(int num) {
        if (head == null) {
            System.out.println("第" + num + "条列表为空！");
            return;
        }
        System.out.println("第" + num + "条链表信息为：");
        Employee cursor = head;
        while (true) {
            System.out.printf(" id=%d name=%s", cursor.id, cursor.name);
            if (cursor.next == null) {
                break;
            }
            cursor = cursor.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    public Employee findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空！");
            return null;
        }
        Employee cursor = head;
        while (true) {
            if (cursor.id == id) { //已经找到雇员，直接退出
                break;
            }
            if (cursor.next == null) { //没有找到该雇员
                cursor = null;
                break;
            }
            cursor = cursor.next;
        }
        return cursor;
    }

    public void deleteEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空，无法删除！");
            return;
        }
        Employee cursor = head;
        while (true) {
            if (cursor.id == id) { //已经找到雇员，删除，然后退出
                cursor.id=-1;
                cursor.name=null;
                break;
            }
            if (cursor.next == null) { //没有找到该雇员
                break;
            }
            cursor = cursor.next;
        }
    }
}