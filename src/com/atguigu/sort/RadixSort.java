package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月17日下午6:20:09
 * 基数排序
 */
public class RadixSort {
	
	public static void main(String[] args) {
//		int arr[] = { 53, 3, 542, 748, 14, 214};
//		System.out.println("排序前" + Arrays.toString(arr));
		
		int[] arr = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
		}
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前的时间是=" + date1Str);
		
		radixSort(arr);
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序前的时间是=" + date2Str);
		
//		System.out.println("基数排序后 " + Arrays.toString(arr));
	}
	
	//基数排序方法
	public static void radixSort(int[] arr) {
		//得到数组中最大的数的位数
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		//得到最大数是几位数
		int maxLength = (max + "").length();
		
		//定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
		int[][] bucket = new int[10][arr.length];
		
		//为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
		int[] bucketElementCounts = new int[10];
		
		for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
			//(针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
			for (int j = 0; j < arr.length; j++) {
				//取出每个元素的对应位的值
				int digitOfElement = arr[j] / n % 10;
				//放入到对应的桶中
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
				bucketElementCounts[digitOfElement]++;
			}
			//按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
			int index = 0;
			//遍历每一桶，并将桶中是数据，放入到原数组
			for (int k = 0; k < bucketElementCounts.length; k++) {
				if (bucketElementCounts[k] != 0) {
					//循环该桶即第k个桶(即第k个一维数组), 放入
					for (int l = 0; l < bucketElementCounts[k]; l++) {
						arr[index++] = bucket[k][l];
					}
				}
				//第i+1轮处理后，需要将每个 bucketElementCounts[k] = 0 
				bucketElementCounts[k] = 0;
			}
		}
	}
}
