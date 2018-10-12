package chapter_8_arrayandmatrix;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 最长的可整合子数组的长度：先给出可整合数组的定义，如果一个数组在排序之后，每相邻两个数差的绝对值都为1，则该数组为可整合数组。
 * 例如，[5,3,4,6,2]排序之后为[2,3,4,5,6],符合每相邻两个数差的绝对值为1，所以这个数组为可整合数组。
 * 给定一个整型数组arr，请返回其中最大可整合子数组的长度。例如，[5,5,3,2,6,4,3]的最大可整合数组为[5,3,2,6,4],所以返回5
 * @author zhangy
 *
 */
public class LongestIntegratedLength {
	
	//时间复杂度比较高的解法
	public static int getMaxIntegerArrLength(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		int len = 0;
		//开始遍历每一组子数组。判断其是否符合整合子数组
		for(int i = 0; i < arr.length; i++){
			for(int j = i; j < arr.length; j++ ){//arr[i][j]是数组arr的子数组
				if(IntegerArrOrNot(arr, i, j)){
					len = Math.max(len, j-i+1);
				}
			}
		}
		return len;
	}
	public static boolean IntegerArrOrNot(int[] arr, int left, int right){
		int[] newArr = Arrays.copyOfRange(arr, left, right + 1);//包含头不包含尾
		Arrays.sort(newArr);//对新数组进行排序
		for(int i = 1; i < newArr.length; i++){
			if(newArr[i -1] != newArr[i] -1){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 实际上，判断一个数组是否是整个数组不用IntegerArrOrNot方法这么复杂，有一个规律：
	 * 一个数组中如果没有充分元素，并且如果最大值减去最小值，再加1的结果等于元素个数(max-min + 1 == 元素个数)，那么这个数组
	 * 就是可整合数组。比如[3,2,5,6,4],max - min + 1 = 6-2 + 1 = 5 == 元素个数，所以这个数组是可整合数组
	 */
	public static int getMaxIntegeredLength2(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		 int max = 0;
		 int min = 0;
		 int len = 0;
		 HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < arr.length; i++){//遍历arr数组的每个子数组
			 min = Integer.MAX_VALUE;
			 max = Integer.MIN_VALUE;
			for(int j = i; j < arr.length; j++){
				if(set.contains(arr[j])){//去重复
					break;
				}
				set.add(arr[i]);
				min = Math.min(min, arr[j]);//找当前子数组中的最小值
				max = Math.max(max, arr[j]);//找当前子数组的最大值
				if(max - min == j -i){//判断是否符合整合数组的条件
					len = Math.max(len, j - i + 1);
				}
			}
			set.clear();
		}
		return len;
	}
	public static void main(String[] args){
		int[] arr = {5,3,4,6,2};
		System.out.println(getMaxIntegeredLength2(arr));
		System.out.println(getMaxIntegerArrLength(arr));
	}
}
