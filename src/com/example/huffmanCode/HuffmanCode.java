package com.example.huffmanCode;

import java.io.*;
import java.util.*;

/**
 * @author hua
 * @create 2021-05-26 21:38
 * 哈夫曼编码
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length); //长为40
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println(nodes);
//        System.out.println("哈夫曼树...");
//        Node node = createHuffmanTree(nodes);
//        node.preOrder();
//
//        Map<Byte, String> huffmanCodes = getCodes(node);
//        System.out.println("生成的哈夫曼编码表：" + huffmanCodes);
//
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);

        System.out.println("huffmanCodeBytes:" + Arrays.toString(huffmanCodeBytes));

        byte[] unzipBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("解压后：" + new String(unzipBytes));
        zipFile("C:\\Users\\HUA\\Pictures\\Saved Pictures\\咖喱饭.jpg","D:\\For_Java");
        System.out.println("压缩成功！");
    }

    private static byte[] huffmanZip(byte[] bytes) { //封装 main 的方法
        List<Node> nodes = getNodes(bytes);
        Node node = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(node);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    //完成数据解压
    //把 huffmanCodeBytes 转换成二进制字符串
    /*
    flag表示是否需要补高位，若为真，则需要补高位
    byteToBitString 返回 b 对应的二进制字符串，按补码返回
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        //如果是正数，还要补位
        if (flag) {
            temp |= 256; //按位与
        }

        String str = Integer.toBinaryString(temp); //返回补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    //编写方法，压缩一个文件
    /*
    srcFile 为被压缩文件的路径
    dstFile 为压缩后文件的路径
     */
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            //创建大小与 srcFile 一样的数组
            byte[] bytes = new byte[fileInputStream.available()];
            //读取文件
            fileInputStream.read(bytes);

            //获取压缩的文件
            byte[] huffmanZipBytes = huffmanZip(bytes);

            //创建输出流
            outputStream = new FileOutputStream(dstFile);
            //创建与文件输出关联的ObjectOutPutStream
            objectOutputStream = new ObjectOutputStream(outputStream);
            //放入压缩文件
            objectOutputStream.writeObject(huffmanZipBytes);
            //对象写入哈夫曼编码，是为了以后解压使用
            objectOutputStream.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fileInputStream.close();
                outputStream.close();
                objectOutputStream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    //根据哈夫曼编码把二进制字符串转换成原样
/*
huffmanCodes 哈夫曼编码表
huffmanBytes 编码后得到的字节数组
 */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是否为最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        //把字符串按照哈夫曼编码进行解码
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            //取出huffmanCodes的元素，反向插入
            map.put(entry.getValue(), entry.getKey());
        }
        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count); //i不动，count移动
                b = map.get(key);
                if (b == null) {  //还没有匹配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    //编写一个方法，将字符串对应的byte[] 数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的byte[]
    /*
    byte:原始字符串对应的 byte
    huffmanCode：生成哈夫曼编码的 map
    return：返回哈夫曼编码处理后的 byte
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCodes 将 bytes 转成哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //把 "10101000..." 转化成 byte数组
        //统计返回的 byte[] huffmanCodeBytes 的长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的 Byte 数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;  //byte的下标
        for (int i = 0; i < stringBuilder.length(); i += 8) {  // 每 8位对应一个byte
            String strByte;
            if (i + 8 > stringBuilder.length()) {  //不够 8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);  // 拼接字符
            }
            //将 strByte 转换成一个 Byte，放入到 huffmanCodeBytes 中
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //生成哈夫曼树对应的哈夫曼编码
    //思路：
    //1，把哈夫曼编码表放在哈希表Map<Byte,String>里
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //2，在生成哈夫曼编码表时，需要拼接路径，定义一个StringBuilder
    //   用于存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了方便，重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 把传入的node节点的所有叶子节点的哈夫曼编码得到，并放入huffmanCodes中
     * node 传入节点
     * code 路径：左子节点是 0，右子节点是 1
     * stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) { //node为空时不处理
            //判断当前node 是叶子节点还是非叶子节点
            if (node.data == null) {  //非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { //叶子节点
                //表示找到某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //bytes 接收字节数组
    private static List<Node> getNodes(byte[] bytes) {
        //创建ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();

        //遍历bytes，统计每个byte出现的次数 - >map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b); //每个字符的数据
            if (count == null) { //Map还没有该字符的数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每个键值对转成 Node 对象，并加入nodes集合
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过List创建哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);
            //取出最小的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建新的二叉树，权为前两棵二叉树的和
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //添加parent到nodes里
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空！");
        }
    }
}


class Node implements Comparable<Node> {
    Byte data; //存放数据(字符)本身 比如 'a'=> ' ' => 32
    int weight; //权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}