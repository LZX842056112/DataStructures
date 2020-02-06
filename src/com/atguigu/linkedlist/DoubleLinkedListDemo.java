package com.atguigu.linkedlist;
/**
 * 
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月6日下午5:22:22
 * 双向链表的增删改查
 */
public class DoubleLinkedListDemo {
	public static void main(String[] args) {
		System.out.println("双向链表的测试");
		HeroNode4 hero1 = new HeroNode4(1, "宋江", "及时雨");
		HeroNode4 hero2 = new HeroNode4(2, "卢俊义", "玉麒麟");
		HeroNode4 hero3 = new HeroNode4(3, "吴用", "智多星");
		HeroNode4 hero4 = new HeroNode4(4, "林冲", "豹子头");
		// 创建一个双向链表
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		//插入
		doubleLinkedList.add(hero1);
		doubleLinkedList.add(hero2);
		doubleLinkedList.add(hero3);
		doubleLinkedList.add(hero4);
		
		doubleLinkedList.list();
		//修改
		HeroNode4 newHeroNode = new HeroNode4(4, "公孙胜", "入云龙");
		doubleLinkedList.update(newHeroNode);
		System.out.println("修改后的链表情况");
		doubleLinkedList.list();
		
		//删除
		doubleLinkedList.del(2);
		System.out.println("删除后的链表情况~~");
		doubleLinkedList.list();
	}
}

class DoubleLinkedList{
	private HeroNode4 head = new HeroNode4(0, "", "");
	
	public HeroNode4 getHead() {
		return head;
	}
	// 遍历双向链表的方法
	// 显示链表[遍历]
	public void list() {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode4 temp = head.next;
		while (true) {
			if (temp == null) {
				break;
			}
			System.out.println(temp);
			temp = temp.next;
		}
	}

	// 添加一个节点到双向链表的最后.
	public void add(HeroNode4 heroNode) {
		HeroNode4 temp = head;
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = heroNode;
		heroNode.pre =temp; 
	}

	// 修改一个节点的内容, 可以看到双向链表的节点内容修改和单向链表一样
	public void update(HeroNode4 newHeroNode) {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode4 temp = head.next;
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
	
	// 从双向链表中删除一个节点
	public void del(int no) {
		if (head.next == null) {
			System.out.println("链表为空，无法删除");
			return;
		}
		HeroNode4 temp = head.next;
		boolean flag = false;
		while (true) {
			if (temp == null) {
				break;
			}
			if (temp.no == no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			temp.pre.next = temp.next;
			// 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
			if (temp.next != null) {
				temp.next.pre = temp.pre;
			}
		}else {
			System.out.printf("要删除的 %d 节点不存在\n", no);
		}
	}
}

class HeroNode4 {
	public int no;
	public String name;
	public String nickname;
	public HeroNode4 next; // 指向下一个节点, 默认为null
	public HeroNode4 pre; // 指向前一个节点, 默认为null
	
	
	public HeroNode4(int no, String name, String nickname) {
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