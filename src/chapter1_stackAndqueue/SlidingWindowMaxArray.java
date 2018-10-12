package chapter1_stackAndqueue;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowMaxArray {
	public static int[] getMaxWindow(int[] arr, int w){
		if(arr == null || w <1 || arr.length < w){
			return null;
		}
		//利用双向队列跟踪每个长度为w的矩形窗口中的最大值的角标，
		LinkedList<Integer> qMax = new LinkedList<Integer>();
		int[] res = new int [arr.length-w+1];//新建一个数组用于保存最后获得的所有最大值
		int index = 0;
		for(int i=0;i<arr.length;i++){
			while(!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]){//如果队列中的最后一个角标对应的值小于当前遍历到的值
				qMax.pollLast();//将较小值的角标从队列中剔除
			}
			qMax.addLast(i);//在队列尾加入较大值的角标
			if(qMax.peekFirst() == i-w){//队列头部的角标已经失效
				qMax.pollFirst();
			}
			if(i >=w-1){//从窗口记录每一个最大值,队列头对应值对应的数组值即为窗口最大值
				res[index++] = arr[qMax.peekFirst()];
			}
		}
		return res;
	}
	public static void main(String[] args){
		int[] arr = {4,3,5,4,3,3,6,7};
		int[] newArr = getMaxWindow(arr,3);
		for(int i=0; i<newArr.length;i++){
			System.out.println(newArr[i]);
		}
	}

}
