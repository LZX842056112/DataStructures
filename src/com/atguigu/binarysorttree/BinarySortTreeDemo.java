package com.atguigu.binarysorttree;

/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月24日下午5:39:18
 * 二叉排序树(BST)
 * 遍历和添加
 */
public class BinarySortTreeDemo {
	public static void main(String[] args) {
		int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
		BinarySortTree binarySortTree = new BinarySortTree();
		//循环的添加结点到二叉排序树
		for (int i = 0; i < arr.length; i++) {
			binarySortTree.add(new Node(arr[i]));
		}
		//中序遍历二叉排序树
		System.out.println("中序遍历二叉排序树~");
		binarySortTree.infixOrder(); // 1,2,3,5,7,9,10,12
	}
}

//创建二叉排序树
class BinarySortTree {
	private Node root;
	
	public Node getRoot() {
		return root;
	}
	
	//添加结点的方法
	public void add(Node node) {
		if (root == null) {
			root = node;
		}else {
			root.add(node);
		}
	}
	
	//中序遍历
	public void infixOrder() {
		if (root != null) {
			root.infixOrder();
		}else {
			System.out.println("二叉排序树为空，不能遍历");
		}
	}
}

//创建Node结点
class Node {
	int value;
	Node left;
	Node right;
	public Node(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	//添加结点的方法
	//递归的形式添加结点，注意需要满足二叉排序树的要求
	public void add(Node node) {
		if (node == null) {
			return;
		}
		//判断传入的结点的值，和当前子树的根结点的值关系
		if(node.value < this.value) {
			//如果当前结点左子结点为null
			if(this.left == null) {
				this.left = node;
			} else {
				//递归的向左子树添加
				this.left.add(node);
			}
		} else { //添加的结点的值大于 当前结点的值
			if(this.right == null) {
				this.right = node;
			} else {
				//递归的向右子树添加
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