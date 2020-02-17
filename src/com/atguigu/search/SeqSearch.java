package com.atguigu.search;
/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月17日下午7:53:00
 * 线性查找
 */
public class SeqSearch {
	
	public static void main(String[] args) {
		int arr[] = { 1, 9, 11, -1, 34, 89 };
		int index = seqSearch(arr, 11);
		if (index == -1) {
			System.out.println("没有找到到");
		} else {
			System.out.println("找到，下标为=" + index);
		}
	}
	
	// 线性查找是逐一比对，发现有相同值，就返回下标
	public static int seqSearch(int[] arr, int value) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == value) {
				return i;
			}
		}
		return -1;
	}
}
