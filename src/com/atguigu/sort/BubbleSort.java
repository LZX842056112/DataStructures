package com.atguigu.sort;

import java.util.Arrays;
/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月14日下午4:15:40
 * 冒泡排序
 */
public class BubbleSort {
	public static void main(String[] args) {
		int arr[] = {3, 9, -1, 10, -2};
		int temp = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			System.out.println("第" + (i + 1) + "趟排序后的数组");
			System.out.println(Arrays.toString(arr));
		}
	}
	
}
