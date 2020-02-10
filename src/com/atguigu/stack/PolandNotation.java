package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 * 
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月10日下午12:16:44
 * 逆波兰计算器（后缀表达式）
 */
public class PolandNotation {
	public static void main(String[] args) {
		//先定义给逆波兰表达式
		//(30+4)×5-6  => 30 4 + 5 × 6 - => 164
//		String suffixExpression = "30 4 + 5 * 6 -";	
		String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76
		List<String> list = getListString(suffixExpression);
		System.out.println("rpnList=" + list);
		int res = calculate(list);
		System.out.println("计算的结果是=" + res);
	}
	
	//将一个逆波兰表达式， 依次将数据和运算符 放入到 ArrayList中
	public static List<String> getListString(String suffixExpression) {
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for (String ele : split) {
			list.add(ele);
		}
		return list;
	}
	
	//完成对逆波兰表达式的运算
	/*
	 * 1)从左至右扫描，将3和4压入堆栈；
		2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
		3)将5入栈；
		4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
		5)将6入栈；
		6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
	 */
	public static int calculate(List<String> ls) {
		// 创建给栈, 只需要一个栈即可
		Stack<String> stack = new Stack<String>();
		for (String item : ls) {
			// 这里使用正则表达式来取出数
			if (item.matches("\\d+")) { // 匹配的是数
				// 入栈
				stack.push(item);
			}else {
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;
				if (item.equals("+")) {
					res = num1 + num2;
				}else if (item.equals("-")) {
					res = num1 - num2;
				}else if (item.equals("*")) {
					res = num1 * num2;
				}else if (item.equals("/")) {
					res = num1 / num2;
				}else {
					throw new RuntimeException("运算符有误");
				}
				//把res 入栈
				stack.push("" + res);
			}
		}
		//最后留在stack中的数据是运算结果
		return Integer.parseInt(stack.pop());
	}
}
