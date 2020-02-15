package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月15日下午7:00:50
 * 插入排序
 */
public class InsertSort {
	
	public static void main(String[] args) {
//		int[] arr = {101, 34, 119, 1, -1, 89}; 
//		System.out.println("插入排序前");
//		System.out.println(Arrays.toString(arr));
		
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
		}
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前的时间是=" + date1Str);
		
		insertSort(arr);
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序后的时间是=" + date2Str);
		
//		System.out.println("插入排序后");
//		System.out.println(Arrays.toString(arr));
		
	}

	//插入排序
	public static void insertSort(int[] arr) {
		int insertVal = 0;
		int insertIndex = 0; 
		for (int i = 1; i < arr.length; i++) {
			insertVal = arr[i];
			insertIndex = i - 1;
			//insertIndex >= 0 保证在给insertVal 找插入位置，不越界
			//insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
			while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex];
				insertIndex--;
			}
			if (insertIndex + 1 != i) {
				arr[insertIndex + 1] = insertVal;
			}
		}
	}
}
