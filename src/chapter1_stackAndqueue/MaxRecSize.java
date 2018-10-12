package chapter1_stackAndqueue;

import java.util.Stack;

/**
 * 求最大矩形的面积其实就是求height数组中的每个位置向左向右最远能扩展到的位置面积和，其中height数组就是每一列累加之和，也就是求最大子矩阵。
 * 1 0 1 1
 * 1 1 1 1
 * 1 1 1 0
 * 这个矩形的最大面积为6个1，为6。第一行的heigt={1, 0, 1, 1};第二行的heigt={2, 2, 2, 2}；第三行的heigt={3, 2, 3, 0}
 * @author zhangy
 *
 */
public class MaxRecSize {

	public static int getMaxRecSize(int[][] arr){
		if(arr == null || arr.length == 0|| arr[0].length == 0){
			return 0;
		}
		int MaxArea = 0;
		int[] height = new int[arr[0].length];
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[0].length;j++){
				height[j] = arr[i][j] == 0 ? 0 : height[j] + 1;//每一行换行时更新height数组,只要中间遇到0，那么Height数组中的值就置为0
			}
			MaxArea = Math.max(MaxArea, getMaxArea(height));
		}
		return MaxArea;
	}
	public static int getMaxArea(int[] height){//获取义一行值为底时，最大的矩形面积
		if(height == null || height.length == 0){
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		int MaxArea = 0;
		for(int i=0;i<height.length;i++){
			while(!stack.isEmpty() && height[i] <= height[stack.peek()]){
				int j = stack.pop();
				int k = stack.isEmpty() ? -1 : stack.peek();//如果当前的栈是空的，那么就将向左最远能扩的位置k赋值为-1
				int curArea = (i-k-1) * height[j];//当前栈顶位置j对应的值height[j]向左向右能扩展的最大面积
				MaxArea = Math.max(MaxArea, curArea);
			}
			stack.push(i);
		}
		
		while(!stack.isEmpty()){//遍历结束，stack中仍有位置没有经历扩的过程，此时因为height数组在往右不能扩出去，所以认为i==height.lenght == 6且越界之后的极值很小，然后开始弹出留在栈中的位置
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length - k -1) * height[j];
			MaxArea = Math.max(MaxArea, curArea);
		}
		return MaxArea;
	}
	
	public static void main(String[] args){
		int[][] map = {{1,0,1,1},{1,1,1,1},{1,1,1,0}};
		System.out.print(getMaxRecSize(map));
	}
}
