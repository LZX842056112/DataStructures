package com.atguigu.linkedlist;
/**
 * 
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月4日下午1:11:57
 * 求单链表中有效节点的个数
 * 查找单链表中的倒数第k个结点 【新浪面试题】
 */
public class SingleLinkedListDemo3 {
	public static void main(String[] args) {
		HeroNode3 hero1 = new HeroNode3(1, "宋江", "及时雨");
		HeroNode3 hero2 = new HeroNode3(2, "卢俊义", "玉麒麟");
		HeroNode3 hero3 = new HeroNode3(3, "吴用", "智多星");
		HeroNode3 hero4 = new HeroNode3(4, "林冲", "豹子头");
		
		//插入
		SingleLinkedList3 singleLinkedList = new SingleLinkedList3();
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);
		singleLinkedList.list();
		
		//修改
		HeroNode3 newHeroNode = new HeroNode3(2, "小卢", "玉麒麟~~");
		singleLinkedList.update(newHeroNode);
		System.out.println("修改后的链表情况~~");
		singleLinkedList.list();
		
		//删除
		singleLinkedList.del(3);
		singleLinkedList.del(1);
		System.out.println("删除后的链表情况~~");
		singleLinkedList.list();
		
		//测试：求单链表中有效节点的个数
		System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));
		
		//测试：查找单链表中的倒数第k个结点 【新浪面试题】
		System.out.println("" + findLastIndexNode(singleLinkedList.getHead(), 1));
	}
	
	//查找单链表中的倒数第k个结点 【新浪面试题】
	public static HeroNode3 findLastIndexNode(HeroNode3 head, int index) {
		if (head.next == null) {
			return null;
		}
		int size = getLength(head);
		if (index > size || index <= 0) {
			return null;
		}
		HeroNode3 cur = head.next;
		for (int i = 0; i < size - index; i++) {
			cur = cur.next;
		}
		return cur;
	}
	
	//方法：求单链表中有效节点的个数(如果是带头结点的链表，需求不统计头节点)
	public static int getLength(HeroNode3 head){
		if (head.next == null) {
			return 0;
		}
		int length = 0;
		HeroNode3 cur = head.next;
		while (cur != null) {
			length++;
			cur = cur.next;
		}
		return length;
	}
}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList3{
	//先初始化一个头节点, 头节点不要动, 不存放具体的数据
	HeroNode3 head = new HeroNode3(0, "", "");
	
	//返回头节点
	public HeroNode3 getHead() {
		return head;
	}

	//第二种方式在添加英雄时，根据排名将HeroNode插入到指定位置
	//(如果有这个排名，则添加失败，并给出提示)
	public void addByOrder(HeroNode3 heroNode) {
		//因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
		//因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
		HeroNode3 temp = head;
		boolean flag = false;
		while (true) {
			if (temp.next == null) {//说明temp已经在链表的最后
				break;
			}
			if (temp.next.no > heroNode.no) {//位置找到，就在temp的后面插入
				break;
			} else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
		}else {
			//插入到链表中, temp的后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}
	
	//修改节点的信息, 根据no编号来修改，即no编号不能改.
	public void update(HeroNode3 newHeroNode) {
		if (head.next == null) {
			System.out.println("链表为空~");
			return;
		}
		//找到需要修改的节点, 根据no编号
		//定义一个辅助变量
		HeroNode3 temp = head.next;
		boolean flag = false;
		while (true) {
			if (temp == null) {
				break;
			}
			if (temp.no == newHeroNode.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		}else {
			System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
		}
	}

	//删除节点
	public void del(int no) {
		HeroNode3 temp = head;
		boolean flag = false;
		while (true) {
			if (temp.next == null) {
				break;
			}
			if (temp.next.no == no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			temp.next = temp.next.next;
		}else {
			System.out.printf("要删除的 %d 节点不存在\n", no);
		}
	}
	
	//显示链表[遍历]
	public void list() {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//因为头节点，不能动，因此我们需要一个辅助变量来遍历
		HeroNode3 temp = head.next;
		while (true) {
			//判断是否到链表最后
			if (temp == null) {
				break;
			}
			System.out.println(temp);
			//将temp后移
			temp = temp.next;
		}
	}
	
}
//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode3 {
	public int no;
	public String name;
	public String nickname;
	public HeroNode3 next; //指向下一个节点
	
	public HeroNode3(int no, String name, String nickname) {
		super();
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
}