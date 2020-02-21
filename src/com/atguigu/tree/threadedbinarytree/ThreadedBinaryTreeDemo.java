package com.atguigu.tree.threadedbinarytree;
/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月21日下午2:39:31
 * 二叉树中序线索化
 */
public class ThreadedBinaryTreeDemo {
	public static void main(String[] args) {
		//中序线索二叉树的功能
		HeroNode root = new HeroNode(1, "tom");
		HeroNode node2 = new HeroNode(3, "jack");
		HeroNode node3 = new HeroNode(6, "smith");
		HeroNode node4 = new HeroNode(8, "mary");
		HeroNode node5 = new HeroNode(10, "king");
		HeroNode node6 = new HeroNode(14, "dim");
		
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		
		//测试中序线索化
		ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
		threadedBinaryTree.setRoot(root);
		threadedBinaryTree.threadedNodes();
		//测试: 以10号节点测试
		HeroNode leftNode = node5.getLeft();
		HeroNode rightNode = node5.getRight();
		System.out.println("10号结点的前驱结点是 ="  + leftNode); //3
		System.out.println("10号结点的后继结点是="  + rightNode); //1
	}
}

//定义ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree {
	private HeroNode root;
	//为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
	//在递归进行线索化时，pre 总是保留前一个结点
	private HeroNode pre = null;

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	//重载threadedNodes方法
	public void threadedNodes() {
		this.threadedNodes(root);
	}
	/**
	 * 编写对二叉树进行中序线索化的方法
	 * @param node 就是当前需要线索化的结点
	 */
	public void threadedNodes(HeroNode node) {
		if (node == null) {
			return;
		}
		//(一)先线索化左子树
		threadedNodes(node.getLeft());
		//(二)线索化当前结点
		//处理当前结点的前驱结点
		if (node.getLeft() == null) {
			//让当前结点的左指针指向前驱结点 
			node.setLeft(pre);
			//修改当前结点的左指针的类型,指向前驱结点
			node.setLeftType(1);
		}
		//处理后继结点
		if (pre != null && pre.getRight() == null) {
			pre.setRight(node);
			pre.setRightType(1);
		}
		//每处理一个结点后，让当前结点是下一个结点的前驱结点
		pre = node;
		//(三)在线索化右子树
		threadedNodes(node.getRight());
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
	
	//前序遍历查找
	public HeroNode preOrderSearch(int no) {
		if (this.root != null) {
			return this.root.preOrderSearch(no);
		}else {
			return null;
		}
	}
	
	//中序遍历查找
	public HeroNode infixOrderSearch(int no) {
		if (this.root != null) {
			return this.root.infixOrderSearch(no);
		}else {
			return null;
		}
	}
	
	//后序遍历查找
	public HeroNode postOrderSearch(int no) {
		if (this.root != null) {
			return this.root.postOrderSearch(no);
		}else {
			return null;
		}
	}
	
	//删除结点
	public void delNode(int no) {
		if (root != null) {
			//如果只有一个root结点, 这里立即判断root是不是就是要删除结点
			if(root.getNo() == no) {
				root = null;
			} else {
				root.delNode(no);
			}
		}else {
			System.out.println("空树，不能删除~");
		}
	}
}

//先创建HeroNode 结点
class HeroNode {
	private int no;
	private String name;
	private HeroNode left; //默认null
	private HeroNode right; //默认null
	//1. 如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
	//2. 如果rightType == 0 表示指向是右子树, 如果 1表示指向后继结点
	private int leftType;
	private int rightType;
	
	public int getLeftType() {
		return leftType;
	}
	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}
	public int getRightType() {
		return rightType;
	}
	public void setRightType(int rightType) {
		this.rightType = rightType;
	}
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
	
	//前序遍历
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
	
	//前序遍历查找
	public HeroNode preOrderSearch(int no) {
		System.out.println("进入前序遍历");
		if (this.no == no) {
			return this;
		}
		//1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
		//2.如果左递归前序查找，找到结点，则返回
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if (resNode != null) {//说明左子树找到
			return resNode;
		}
		//1.左递归前序查找，找到结点，则返回，否继续判断，
		//2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
		if (this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}
	
	//中序遍历查找
	public HeroNode infixOrderSearch(int no) {
		//判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if (resNode != null) {//说明左子树找到
			return resNode;
		}
		System.out.println("进入中序查找");
		//如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
		if (this.no == no) {
			return this;
		}
		//否则继续进行右递归的中序查找
		if (this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
	}
	
	//后序遍历查找
	public HeroNode postOrderSearch(int no) {
		//判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.postOrderSearch(no);
		}
		if (resNode != null) {//说明左子树找到
			return resNode;
		}
		//如果左子树没有找到，则向右子树递归进行后序遍历查找
		if (this.right != null) {
			resNode = this.right.postOrderSearch(no);
		}
		if (resNode != null) {//说明右子树找到
			return resNode;
		}
		System.out.println("进入后序查找");
		//如果左右子树都没有找到，就比较当前结点是不是
		if (this.no == no) {
			return this;
		}
		return resNode;
	}
	
	//递归删除结点
	//1.如果删除的节点是叶子节点，则删除该节点
	//2.如果删除的节点是非叶子节点，则删除该子树
	public void delNode(int no) {
		//如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
		if (this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		if (this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		//需要向左子树进行递归删除
		if (this.left != null) {
			this.left.delNode(no);
		}
		if (this.right != null) {
			this.right.delNode(no);
		}
	}
}