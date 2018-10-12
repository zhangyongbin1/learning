package chapter1_stackAndqueue;

import java.util.LinkedList;

/**
 * 给定数组arr和整数num，共返回多少个子数组满足如下情况：
 * max(arr[i...j]) - min(arr[i...j]) <=num
 * 
 * @author zhangy
 *
 */
public class AllLessNumSubArray {
	
	public static int getNumCount(int[] arr, int num){
		if(arr == null || arr.length == 0){
			return 0;
		}
		LinkedList<Integer> qMax = new LinkedList<Integer>();//定义双端队列用于更新最大值的角标
		LinkedList<Integer> qMin = new LinkedList<Integer>();//定义双端队列用于更新最小值的角标
		int i = 0;
		int j = 0;
		int res = 0;
		while(i < arr.length){//这层循环用于控制arr[i..j]之间的其实角标控制数组整体向右移动，即以i角标为起始位置
			while(j < arr.length){//这层循环用于控制arr[i...j]之间的尾角标。即：以i角标为起始位置时，满足的条件的数组的个数
				while(!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[j]){
					qMin.pollLast();
				}
				qMin.addLast(j);//保证队列qMin的头部是以i为起始位置到数组arr最后的字数组中的最小值
				while(!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[j]){
					qMax.pollLast();
				}
				qMax.addLast(j);//保证队列qMax的头部是以i为起始位置到数组arr最后的字数组中的最大值
				if(arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num){//1.包含不满足要求的字数组的所有数组也不满足要求；2.满足要求的字数组中的所有字数组也满足要求
					break;//如果不满足条件那么就跳出 j < arr.lenght的循环，说明i...j-1位置之间的数组都满足要求
				}
				j++;
			}
			if(qMin.peekFirst() == i){
				qMin.pollFirst();//说明当前最小值失效，即需要更换起始位置
			}
			if(qMax.peekFirst() == i){
				qMax.pollFirst();//说明当前最大值失效，即需要更换起始位置
			}
			res += j -i;//计算以i为起始位置时，满足条件的字数组的个数
			i++;//更换起始位置
		}
		return res;
	}
	public static void main(String[] args){
		int[] arr = {1,2,3,4,5,6,7,8,9};
		int num = 2;
		int res = getNumCount(arr,num);
		System.out.print(res);
	}

}
