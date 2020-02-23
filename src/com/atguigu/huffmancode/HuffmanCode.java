package com.atguigu.huffmancode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月22日下午8:16:14
 * 数据压缩
 * 1.生成字节数组对应的赫夫曼树
 * 2.生成赫夫曼树对应的赫夫曼编码
 * 3.根据赫夫曼编码生成赫夫曼编码表
 * 4.通过赫夫曼编码表，返回赫夫曼编码 压缩后的byte[]
 */
public class HuffmanCode {
	public static void main(String[] args) {
		String content = "i like like like java do you like a java";
		byte[] contentBytes = content.getBytes();
		System.out.println(contentBytes.length);//40
		//将数据进行解压(解码)  
		//分步过程
		List<Node> nodes = getNodes(contentBytes);
		//nodes=[Node [data=32, weight=9], Node [data=97, weight=5],...]	
		System.out.println("nodes=" + nodes);
		//测试创建赫夫曼树
		System.out.println("赫夫曼树");
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		System.out.println("前序遍历");
		huffmanTreeRoot.preOrder();
		//测试生成对应的赫夫曼编码
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		System.out.println("~生成的赫夫曼编码表= " + huffmanCodes);
		//测试返回一个赫夫曼编码 压缩后的byte[]
		byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
		System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));//17
	}
	
	//将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
	/**
	 * @param bytes 这时原始的字符串对应的 byte[]
	 * @param huffmanCodes 生成的赫夫曼编码map
	 * @return 返回赫夫曼编码处理后的 byte[] 
	 * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
	 * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
	 * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
	 * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
	 * huffmanCodeBytes[1] = -88
	 */
	private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
		//1.利用 huffmanCodes 将  bytes 转成  赫夫曼编码对应的字符串
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}
//		System.out.println("测试 stringBuilder~~~=" + stringBuilder.toString());
		//统计返回  byte[] huffmanCodeBytes 长度
		//等价 int len = (stringBuilder.length() + 7) / 8;
		int len;
		if (stringBuilder.length() % 8 == 0) {
			len = stringBuilder.length() / 8;
		}else {
			len = stringBuilder.length() / 8 + 1;
		}
		//创建 存储压缩后的 byte数组
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;//记录是第几个byte
		for (int i = 0; i < stringBuilder.length(); i += 8) {//因为是每8位对应一个byte,所以步长 +8
			String strByte;
			if (i+8 > stringBuilder.length()) {//不够8位
				strByte = stringBuilder.substring(i);
			}else {
				strByte = stringBuilder.substring(i, i + 8);
			}
			//将strByte 转成一个byte,放入到 huffmanCodeBytes
			huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
			index++;
		}
		return huffmanCodeBytes;
	}
	
	//生成赫夫曼树对应的赫夫曼编码
	//1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
	//   生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
	static Map<Byte, String> huffmanCodes = new HashMap<Byte,String>();
	//2. 在生成赫夫曼编码表时，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
	static StringBuilder stringBuilder = new StringBuilder();
	
	
	//为了调用方便，我们重载 getCodes
	private static Map<Byte, String> getCodes(Node root) {
		if (root == null) {
			return null;
		}
		//处理root的左子树
		getCodes(root.left, "0", stringBuilder);
		//处理root的右子树
		getCodes(root.right, "1", stringBuilder);
		return huffmanCodes;
	}
	
	/**
	 * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
	 * @param node  传入结点
	 * @param code  路径： 左子结点是 0, 右子结点 1
	 * @param stringBuilder 用于拼接路径
	 */
	private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		//将code 加入到 stringBuilder2
		stringBuilder2.append(code);
		if(node != null) { //如果node == null不处理
			//判断当前node 是叶子结点还是非叶子结点
			if (node.data == null) {//非叶子结点
				//递归处理
				//向左递归
				getCodes(node.left, "0", stringBuilder2);
				//向右递归
				getCodes(node.right, "1", stringBuilder2);
			}else {//叶子结点
				//就表示找到某个叶子结点的最后
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
		}
	}
	
	//前序遍历的方法
	private static void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		}else {
			System.out.println("赫夫曼树为空");
		}
	}
	
	/**
	 * @param bytes 接收字节数组
	 * @return 返回的就是 List 形式   [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......],
	 */
	private static List<Node> getNodes(byte[] bytes) {
		//1创建一个ArrayList
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		//遍历 bytes , 统计 每一个byte出现的次数->map[key,value]
		Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) {//Map还没有这个字符数据,第一次
				counts.put(b, 1);
			}else {
				counts.put(b, count + 1);
			}
		}
		//把每一个键值对转成一个Node 对象，并加入到nodes集合
		//遍历map
		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}
	
	//可以通过List 创建对应的赫夫曼树
	private static Node createHuffmanTree(List<Node> nodes) {
		while (nodes.size() > 1) {
			//排序, 从小到大
			Collections.sort(nodes);
			//取出第一颗最小的二叉树
			Node leftNode = nodes.get(0);
			//取出第二颗最小的二叉树
			Node rightNode = nodes.get(1);
			//创建一颗新的二叉树,它的根节点 没有data, 只有权值
			Node parent = new Node(null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			//将已经处理的两颗二叉树从nodes删除
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//将新的二叉树，加入到nodes
			nodes.add(parent);
		}
		//nodes 最后的结点，就是赫夫曼树的根结点
		return nodes.get(0);
	}
}

//创建Node ,待数据和权值
class Node implements Comparable<Node>  {
	Byte data; // 存放数据(字符)本身，比如'a' => 97 ' ' => 32
	int weight; //权值, 表示字符出现的次数
	Node left;
	Node right;
	public Node(Byte data, int weight) {
		super();
		this.data = data;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}
	@Override
	public int compareTo(Node o) {
		//从小到大排序
		return this.weight - o.weight;
	}

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
}
