package com.atguigu.tree;
/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月19日下午8:52:59
 * 二叉树
 * 1.前序中序后序遍历
 */
public class BinaryTreeDemo {
	public static void main(String[] args) {
		//创建一颗二叉树
		BinaryTree binaryTree = new BinaryTree();
		//创建需要的结点
		HeroNode root = new HeroNode(1, "宋江");
		HeroNode node2 = new HeroNode(2, "吴用");
		HeroNode node3 = new HeroNode(3, "卢俊义");
		HeroNode node4 = new HeroNode(4, "林冲");
		HeroNode node5 = new HeroNode(5, "关胜");
		
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
		binaryTree.setRoot(root);
		
		//测试
		System.out.println("前序遍历"); // 1,2,3,5,4
		binaryTree.preOrder();
		System.out.println("中序遍历");
		binaryTree.infixOrder(); // 2,1,5,3,4
		System.out.println("后序遍历");
		binaryTree.postOrder(); // 2,5,4,3,1
	}

}

//定义BinaryTree 二叉树
class BinaryTree {
	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	
	//前序遍历
	public void preOrder() {
		if (this.root != null) {
			this.root.preOrder();
		}else {
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
	//中序遍历
	public void infixOrder() {
		if (this.root != null) {
			this.root.infixOrder();
		}else {
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
	//后序遍历
	public void postOrder() {
		if(this.root != null) {
			this.root.postOrder();
		}else {
			System.out.println("二叉树为空，无法遍历");
		}
	}
}

//先创建HeroNode 结点
class HeroNode {
	private int no;
	private String name;
	private HeroNode left; //默认null
	private HeroNode right; //默认null
	public HeroNode(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HeroNode getLeft() {
		return left;
	}
	public void setLeft(HeroNode left) {
		this.left = left;
	}
	public HeroNode getRight() {
		return right;
	}
	public void setRight(HeroNode right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}
	
	//前序遍历的方法
	public void preOrder() {
		System.out.println(this); //先输出父结点
		if (this.left != null) {//递归向左子树前序遍历
			this.left.preOrder();
		}
		if (this.right != null) {//递归向右子树前序遍历
			this.right.preOrder();
		}
	}
	
	//中序遍历
	public void infixOrder() {
		if (this.left != null) {//递归向左子树前序遍历
			this.left.infixOrder();
		}
		System.out.println(this); //输出父结点
		if (this.right != null) {//递归向右子树前序遍历
			this.right.infixOrder();
		}
	}
	
	//后序遍历
	public void postOrder() {
		if (this.left != null) {//递归向左子树前序遍历
			this.left.postOrder();
		}
		if (this.right != null) {//递归向右子树前序遍历
			this.right.postOrder();
		}
		System.out.println(this); //输出父结点
	}
	
}