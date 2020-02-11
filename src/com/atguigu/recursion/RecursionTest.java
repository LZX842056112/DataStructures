package com.atguigu.recursion;
/**
 * 
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月11日下午4:16:54
 * 递归调用机制
 * 打印问题
 * 阶乘问题
 */
public class RecursionTest {
	
	public static void main(String[] args) {
		test(4);
		System.out.println(factorial(4));
	}

	//打印问题
	public static void test(int n) {
		if (n > 2) {
			test(n - 1);
		}
		System.out.println("n = " + n);
	}
	
	//阶乘问题
	public static int factorial(int n) {
		if (n == 1) {
			return 1;
		}else {
			return factorial(n - 1) * n;
		}
	}
}
