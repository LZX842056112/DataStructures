package com.atguigu.search;
/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月18日下午2:29:56
 * 插值查找算法
 */
public class InsertValueSearch {
	
	public static void main(String[] args) {
//		int [] arr = new int[100];
//		for(int i = 0; i < 100; i++) {
//			arr[i] = i + 1;
//		}
		
		int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
		int index = insertValueSearch(arr, 0, arr.length - 1,1234);
		
		System.out.println("index = " + index);
	}

	/**
	 * @param arr 数组
	 * @param left 左边索引
	 * @param right 右边索引
	 * @param findVal 查找值
	 * @return 如果找到，就返回对应的下标，如果没有找到，返回-1
	 */
	static int count = 0;
	public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
		count++;
		System.out.println("插值查找次数~~");
		if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
			return -1;
		}
		int mid =left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
		int midVal = arr[mid];
		if (findVal < midVal) {//应该向左递归
			return insertValueSearch(arr, left, mid - 1, findVal);
		}else if (findVal > midVal) {//向右边递归
			return insertValueSearch(arr, mid + 1, right, findVal);
		}else {
			System.out.println(count);
			return mid;
		}
	}
}
