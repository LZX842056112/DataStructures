package com.atguigu.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月21日下午8:03:08
 * 堆排序
 */
public class HeapSort {
	public static void main(String[] args) {
//		int arr[] = {4, 6, 8, 5, 9,-1,999,20,-999,3};
		
		int[] arr = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
		}

		System.out.println("排序前");
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前的时间是=" + date1Str);
		
		heapSort(arr);
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序前的时间是=" + date2Str);	
		
//		System.out.println("排序后=" + Arrays.toString(arr));
	}

	//编写一个堆排序的方法
	public static void heapSort(int arr[]) {
		int temp = 0;
		System.out.println("堆排序!!");
		
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		
		for (int j = arr.length - 1; j > 0; j--) {
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
	}

	//将一个数组(二叉树), 调整成一个大顶堆
	/**
	 * 功能： 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
	 * 举例  int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
	 * 如果再次调用  adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
	 * @param arr 待调整的数组
	 * @param i 表示非叶子结点在数组中索引
	 * @param lenght 表示对多少个元素继续调整， length 是在逐渐的减少
	 */
	public  static void adjustHeap(int arr[], int i, int lenght) {
		int temp = arr[i];
		//k = i * 2 + 1 k 是 i结点的左子结点
		for (int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
			//说明左子结点的值小于右子结点的值
			if ((k + 1) < lenght && arr[k] < arr[k + 1]) {
				k++;// k 指向右子结点
			}
			if (arr[k] > temp) {
				arr[i] = arr[k];
				i = k;//i 指向 k,继续循环比较
			}else {
				break;
			}
		}
		//当for 循环结束后，我们已经将以i 为父结点的树的最大值，放在了 最顶(局部)
		arr[i] = temp;//将temp值放到调整后的位置
	}
}
