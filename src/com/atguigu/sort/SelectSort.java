package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月15日下午6:14:10
 * 选择排序
 */
public class SelectSort {
	
	public static void main(String[] args) {
//		int [] arr = {101, 34, 119, 1, -1, 90, 123};
		
//		System.out.println("排序前");
//		System.out.println(Arrays.toString(arr));
		
		//创建要给80000个的随机的数组
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
		}
		
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前的时间是=" + date1Str);
		
		selectSort(arr);
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序后的时间是=" + date2Str);
		
//		System.out.println("排序后");
//		System.out.println(Arrays.toString(arr));
		
	}
	
	//选择排序
	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			int min = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) {// 说明假定的最小值，并不是最小
					min = arr[j];
					minIndex = j;
				}
			}
			// 将最小值，放在arr[i], 即交换
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
		}
	}

}
