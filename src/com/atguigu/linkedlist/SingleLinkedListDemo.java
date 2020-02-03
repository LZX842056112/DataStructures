package com.atguigu.linkedlist;
/**
 * 
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月2日下午5:59:54
 * 单链表（当不考虑编号顺序时）
 */
public class SingleLinkedListDemo {
	public static void main(String[] args) {
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
		
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		singleLinkedList.add(hero1);
		singleLinkedList.add(hero2);
		singleLinkedList.add(hero4);
		singleLinkedList.add(hero3);
		
		singleLinkedList.list();
	}
}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList{
	//先初始化一个头节点, 头节点不要动, 不存放具体的数据
	HeroNode head = new HeroNode(0, "", "");
	
	//添加节点到单向链表
	//1. 找到当前链表的最后节点
	//2. 将最后这个节点的next 指向 新的节点
	public void add(HeroNode heroNode) {
		//因为head节点不能动，因此我们需要一个辅助遍历 temp
		HeroNode temp = head;
		while (true) {
			//找到链表的最后
			if (temp.next == null) {
				break;
			}
			//如果没有找到最后, 将temp后移
			temp = temp.next;
		}
		//将最后这个节点的next 指向 新的节点
		temp.next = heroNode;
	}
	//显示链表[遍历]
	public void list() {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//因为头节点，不能动，因此我们需要一个辅助变量来遍历
		HeroNode temp = head.next;
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
class HeroNode {
	public int no;
	public String name;
	public String nickname;
	public HeroNode next; //指向下一个节点
	
	public HeroNode(int no, String name, String nickname) {
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