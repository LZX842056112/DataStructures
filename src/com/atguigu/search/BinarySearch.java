package com.atguigu.search;
/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月17日下午8:07:35
 * 二分查找算法
 * 注意：使用二分查找的前提是 该数组是有序的.
 */
public class BinarySearch {
	
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
}
