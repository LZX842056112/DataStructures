package com.atguigu.linkedlist;
/**
 * 
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月3日下午3:51:06
 * 单链表（考虑编号顺序时）
 */
public class SingleLinkedListDemo2 {
	public static void main(String[] args) {
		HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
		HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
		HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
		HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
		
		SingleLinkedList2 singleLinkedList = new SingleLinkedList2();
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);
		
		singleLinkedList.list();
		
		//测试修改节点的代码
		HeroNode2 newHeroNode = new HeroNode2(2, "小卢", "玉麒麟~~");
		singleLinkedList.update(newHeroNode);
		System.out.println("修改后的链表情况~~");
		singleLinkedList.list();
	}
}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList2{
	//先初始化一个头节点, 头节点不要动, 不存放具体的数据
	HeroNode2 head = new HeroNode2(0, "", "");
	
	//第二种方式在添加英雄时，根据排名将英雄插入到指定位置
	//(如果有这个排名，则添加失败，并给出提示)
	public void addByOrder(HeroNode2 heroNode) {
		//因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
		//因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
		HeroNode2 temp = head;
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
	public void update(HeroNode2 newHeroNode) {
		if (head.next == null) {
			System.out.println("链表为空~");
			return;
		}
		//找到需要修改的节点, 根据no编号
		//定义一个辅助变量
		HeroNode2 temp = head.next;
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

	//显示链表[遍历]
	public void list() {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//因为头节点，不能动，因此我们需要一个辅助变量来遍历
		HeroNode2 temp = head.next;
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
class HeroNode2 {
	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next; //指向下一个节点
	
	public HeroNode2(int no, String name, String nickname) {
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