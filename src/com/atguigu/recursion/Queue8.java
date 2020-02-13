package com.atguigu.recursion;
/**
 * 
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月13日下午4:00:24
 * 八皇后问题
 */
public class Queue8 {
	
	int max = 8;
	int[] array = new int[max];
	static int count = 0;
	static int judgeCount = 0;
	
	public static void main(String[] args) {
		Queue8 queue8 = new Queue8();
		queue8.check(0);
		System.out.printf("一共有%d解法", count);
		System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w
	}
	
	//放置第n个皇后
	private void check(int n) {
		if (n == max) {
			print();
			return;
		}
		//依次放入皇后，并判断是否冲突
		for (int i = 0; i < max; i++) {
			//先把当前这个皇后 n , 放到该行的第1列
			array[n] = i;
			if (judge(n)) {// 不冲突
				//接着放n+1个皇后,即开始递归
				check(n + 1);
			}
			//如果冲突，就继续执行
		}
	}
	
	/**
	 * 查看当我们放置第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突
	 * @param n 表示第n个皇后
	 * @return
	 */
	private boolean judge(int n) {
		judgeCount++;
		for (int i = 0; i < n; i++) {
			//1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
			//2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
			//3. 判断是否在同一行, 没有必要，n 每次都在递增
			if (array[n] == array[i] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}
	
	//写一个方法，可以将皇后摆放的位置输出
	private void print() {
		count++;
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
