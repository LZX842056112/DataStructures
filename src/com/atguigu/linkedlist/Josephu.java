package com.atguigu.linkedlist;
/**
 * 
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月6日下午8:15:47
 * 环形的单向链表(约瑟夫问题)
 * 添加小孩节点，构建成一个环形的链表
 * 遍历当前的环形链表
 */
public class Josephu {
	public static void main(String[] args) {
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(20);
		circleSingleLinkedList.showBoy();
	}
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
	private Boy first = null;
	
	// 添加小孩节点，构建成一个环形的链表
	public void addBoy(int nums) {
		if (nums < 1) {
			System.out.println("nums的值不正确");
			return;
		}
		Boy curBoy =first;// 辅助指针，帮助构建环形链表
		for (int i = 1; i <= nums; i++) {
			// 根据编号，创建小孩节点
			Boy boy = new Boy(i);
			// 如果是第一个小孩
			if (i == 1) {
				first = boy;
				first.setNext(first);
				curBoy = first;
			}else {
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy = boy;
			}
		}
	}
	
	// 遍历当前的环形链表
	public void showBoy() {
		if (first == null) {
			System.out.println("没有任何小孩~~");
			return;
		}
		Boy curBoy =first;
		while (true) {
			System.out.printf("小孩的编号 %d \n", curBoy.getNo());
			if (curBoy.getNext() == first) {// 说明已经遍历完毕
				break;
			}
			curBoy = curBoy.getNext();
		}
	}
	
}

//创建一个Boy类，表示一个节点
class Boy {
	private int no;// 编号
	private Boy next; // 指向下一个节点,默认null
	
	public Boy(int no) {
		this.no = no;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Boy getNext() {
		return next;
	}
	public void setNext(Boy next) {
		this.next = next;
	}
}