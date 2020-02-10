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
 * 中缀转后缀表达式
 */
public class PolandNotation {
	public static void main(String[] args) {
	/*  //先定义给逆波兰表达式
		//(30+4)×5-6  => 30 4 + 5 × 6 - => 164
		//String suffixExpression = "30 4 + 5 * 6 -";	
		String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76
		List<String> list = getListString(suffixExpression);
		System.out.println("rpnList=" + list);
		int res = calculate(list);
		System.out.println("计算的结果是=" + res);
	*/
		String expression = "1+((2+3)*4)-5";
		List<String> infixExpressionList = toInfixExpressionList(expression);
		System.out.println("中缀表达式对应的List=" + infixExpressionList); 
		List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
		System.out.println("后缀表达式对应的List" + suffixExpreesionList);

		System.out.printf("expression=%d", calculate(suffixExpreesionList));
	}
	
	//方法：将 中缀表达式转成对应的List
	public static List<String> toInfixExpressionList(String s) {
		//定义一个List,存放中缀表达式 对应的内容
		List<String> ls = new ArrayList<String>();
		int i = 0; //这时是一个指针，用于遍历 中缀表达式字符串
		String str; // 对多位数的拼接
		char c; // 每遍历到一个字符，就放入到c
		do {
			//如果c是一个非数字，我需要加入到ls
			if ((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57) {
				ls.add("" + c);
				i++;
			}else {//如果是一个数，需要考虑多位数
				str = "";
				 while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
					str += c;
					i++;
				}
				ls.add(str);
			}
		} while (i < s.length());
		return ls;
	}
	
	//方法：将得到的中缀表达式对应的List => 后缀表达式对应的List
	public static List<String> parseSuffixExpreesionList(List<String> ls) {
		Stack<String> s1 = new Stack<String>(); // 符号栈
		List<String> s2 = new ArrayList<String>(); // 储存中间结果的Lists2
		for (String item : ls) {
			//如果是一个数，加入s2
			if (item.matches("\\d+")) {
				s2.add(item);
			}else if (item.equals("(")) {
				s1.push(item);
			}else if (item.equals(")")) {
				//如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
				while (!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop();//将 ( 弹出 s1栈， 消除小括号
			}else {
				//当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
				while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
					s2.add(s1.pop());
				}
				//还需要将item压入栈
				s1.push(item);
			}
		}
		//将s1中剩余的运算符依次弹出并加入s2
		while (s1.size() != 0) {
			s2.add(s1.pop());
		}
		return s2;
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

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation{
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;
	//写一个方法，返回对应的优先级数字
	public static int getValue(String operation) {
		int result = 0;
		switch (operation) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			System.out.println("不存在该运算符" + operation);
			break;
		}
		return result;
	}
}