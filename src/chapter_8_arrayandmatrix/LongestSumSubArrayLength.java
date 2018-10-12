package chapter_8_arrayandmatrix;

import java.util.HashMap;

/**
 * 未排序数组中累加和为给定值的最长子数组序列问题：给定一个无序数组arr,其中元素可正，可负，可0，给定一个整数k。求arr所有的子数组
 * 中累加和为k的最长子数组长度。
 * @author zhangy
 *
 */
public class LongestSumSubArrayLength {
	
	/**
	 * 为了说明解法，先定义s的概念，s(i)代表子数组arr[0...i]所有元素的累加和。那么子数组arr[j...i](0<=j<=i<arr.lenght)
	 * 的累加和为s(i)-s(j-1),因为根据定义，s(i)=arr[0...i]的累加和 = arr[0...j-1]的累加和 + arr[j...i]的累加和，又有
	 * arr[0...j-1]的累加和为s(j-1)。所以，arr[j...i]的累加和为s(i)-s(j-1),这个结论是求解这道题的核心
	 * 
	 * 原问题解法只遍历一次arr,具体过程为：
	 * 1.设置变量sum = 0, 表示从0位置开始一直加到i位置所有元素的和。设置变量len = 0，表示累加和为k的最长子数组长度。设置
	 * 哈希表map,其中，key表示从arr最左边开始累加的过程中出现过的sum值，对应value值则表示sum值最早出现的位置
	 * ......
	 * 
	 */
	
	public static int getMaxLength(int[] arr, int k){
		if(arr == null || arr.length == 0){
			return 0;
		}
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(0, -1);//根据arr[j+1...i]的累加和为s(i) - s(j),所以如果从0位置开始累加，会导致j+1>=1.也就说从0位置开始的子数组都没有考虑，所以初始化是需要这上(0,-1)这个值
		int sum = 0;
		int len =0;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
			if(map.containsKey(sum-k)){//判断sum-k这个值之前是否已经出现过，出现过，说明s(i)-s(j)这部分值的累加和就k
				len = Math.max(i - map.get(sum - k), len);//直接更新len
			}
			if(!map.containsKey(sum)){//如果之前没出现过sum,那么就直接添加，出现过就不用做任何记录
				map.put(sum, i);
			}
		}
		return len;
	}
	public static void main(String[] args){
		int[] arr = {1,2,3,3};
		int k = 6;
		System.out.println(getMaxLength(arr,k));
	}

}
