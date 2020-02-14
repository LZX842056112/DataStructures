package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月14日下午4:15:40
 * 冒泡排序
 * 冒泡排序的优化
 */
public class BubbleSort {
	public static void main(String[] args) {
	
//		int arr[] = {3, 9, -1, 10, 20};
//		System.out.println("排序前");
//		System.out.println(Arrays.toString(arr));
	
		//测试冒泡排序的速度O(n^2), 80000个数据
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int)(Math.random() * 800000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间是=" + date1Str);

		//测试冒泡排序
		bubbleSort(arr);
		
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序前的时间是=" + date2Str);
	
//		System.out.println("排序后");
//		System.out.println(Arrays.toString(arr));
	
	}
	
	// 将前面额冒泡排序算法，封装成一个方法
	public static void bubbleSort(int[] arr) {
		int temp = 0;
		boolean flag = false; // 标识变量，表示是否进行过交换
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					flag = true;
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			
//			System.out.println("第" + (i + 1) + "趟排序后的数组");
//			System.out.println(Arrays.toString(arr));
			
			if (!flag) { // 在一趟排序中，一次交换都没有发生过
				break;
			} else {
				flag = false;
			}
		}
	}
	
}
