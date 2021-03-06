package com.atguigu.search;

import java.util.Arrays;

/**
 * @Description
 * @author liZongXiao
 * @version
 * @date 2020年2月18日下午9:01:56
 * 斐波那契查找算法
 */
public class FibonacciSearch {
	public static int maxSize = 20;
	
	public static void main(String[] args) {
		int [] arr = {1,8, 10, 89, 1000, 1234};
		
		System.out.println("index=" + fibSearch(arr, 8));
	}
	
	//非递归方法得到一个斐波那契数列
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}
	
	/**
	 * @param a  数组
	 * @param key 我们需要查找的关键码(值)
	 * @return 返回对应的下标，如果没有-1
	 */
	public static int fibSearch(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		int k = 0; //表示斐波那契分割数值的下标
		int mid = 0; //存放mid值
		int f[] = fib();//获取到斐波那契数列
		//获取到斐波那契分割数值的下标
		while (high > f[k] - 1) {
			k++;
		}
		//因为 f[k] 值 可能大于 a 的 长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
		//不足的部分会使用0填充
		int[] temp = Arrays.copyOf(a, f[k]);
		//实际上需求使用a数组最后的数填充 temp
		//temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
		for (int i = high + 1; i < temp.length; i++) {
			temp[i] = a[high];
		}
		// 使用while来循环处理，找到我们的数 key
		while (low <= high) {// 只要这个条件满足，就可以找
			mid = low + f[k - 1] - 1;
			if (key < temp[mid]) {//继续向数组的前面查找(左边)
				high = mid - 1;
				//1. 全部元素 = 前面的元素 + 后边元素
				//2. f[k] = f[k-1] + f[k-2]
				//因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
				//即 在 f[k-1] 的前面继续查找 k--
				//即下次循环 mid = f[k-1-1]-1
				k--;
			}else if (key > temp[mid]) {//继续向数组的后面查找(右边)
				low = mid + 1;
				//1. 全部元素 = 前面的元素 + 后边元素
				//2. f[k] = f[k-1] + f[k-2]
				//3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
				//4. 即在f[k-2] 的前面进行查找 k -=2
				//5. 即下次循环 mid = f[k - 1 - 2] - 1
				k -= 2;
			}else {
				//需要确定，返回的是哪个下标
				if (mid <= high) {
					return mid;
				}else {
					return high;
				}
			}
		}
		return -1;
	}
}
