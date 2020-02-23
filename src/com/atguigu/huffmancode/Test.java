package com.atguigu.huffmancode;

import java.util.HashSet;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//huffmanCodeBytes[0] =  10101000(补码) => byte  
		//[推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000(源码)= -88 ]
		//huffmanCodeBytes[1] = -88
		String strBye = "10101000";
		System.out.println((byte)Integer.parseInt(strBye, 2));//-88
	}
}
