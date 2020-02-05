package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * 
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月5日下午5:03:21
 * 演示栈Stack的基本使用
 */
public class TestStack {
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		// 入栈
		stack.add("1");
		stack.add("2");
		stack.add("3");
		// 出栈
		while (stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}
}
