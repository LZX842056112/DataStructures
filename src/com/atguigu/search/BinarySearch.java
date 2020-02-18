package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月17日下午8:07:35
 * 二分查找算法
 * 完善二分查找算法（查找多个相同的数值）
 * 注意：使用二分查找的前提是 该数组是有序的.
 */
public class BinarySearch {
	
	public static void main(String[] args) {
		int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
//		int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
//		System.out.println("resIndex=" + resIndex);
		
//		int arr[] = {1,2,3,4,5,6,7,8,9,10,10,10,11,12,13,14,15,16,17,18,19,20};
		List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1,1234);
		System.out.println("resIndexList=" + resIndexList);
	}
	
	/**
	 * 
	 * @param arr 数组
	 * @param left 左边的索引
	 * @param right 右边的索引
	 * @param findVal 要查找的值
	 * @return 如果找到就返回下标，如果没有找到，就返回 -1
	 */
	public static int binarySearch(int[] arr, int left, int right, int findVal) {
		// 当 left > right 时，说明递归整个数组，但是没有找到
		if (left > right) {
			return -1;
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];
		
		if (findVal > midVal) {//向右递归
			return binarySearch(arr, mid + 1, right, findVal);
		}else if (findVal < midVal) {//向左递归
			return binarySearch(arr, left, mid - 1, findVal);
		}else {
			return mid;
		}
	}
	
	static int count = 0;
	//有多个相同的数值时，将所有的数值都查找到
	public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
		System.out.println("二分查找次数~~");
		count++;
		// 当 left > right 时，说明递归整个数组，但是没有找到
		if (left > right) {
			return new ArrayList<Integer>();
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];
		
		if (findVal > midVal) {//向右递归
			return binarySearch2(arr, mid + 1, right, findVal);
		}else if (findVal < midVal) {//向左递归
			return binarySearch2(arr, left, mid - 1, findVal);
		}else {
			List<Integer> resIndexlist = new ArrayList<Integer>();
			//向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
			int temp = mid - 1;
			while (true) {
				if (temp < 0 || arr[temp] != findVal) {
					break;
				}
				resIndexlist.add(temp);
				temp -= 1;
			}
			
			resIndexlist.add(mid);
			
			//向mid 索引值的有边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
			temp = mid + 1;
			while (true) {
				if (temp > arr.length - 1 || arr[temp] != findVal) {
					break;
				}
				resIndexlist.add(temp);
				temp += 1;
			}
			System.out.println(count);
			return resIndexlist;
		}
	}
}