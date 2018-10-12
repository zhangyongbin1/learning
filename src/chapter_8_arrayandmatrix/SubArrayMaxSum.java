package chapter_8_arrayandmatrix;
/**
 * 子数组的最大累加和问题：给定一个数组arr,返回子数组的最大累加和。例如，arr = [1,-2,3,5,-2,6,-1]，所有子数组中，
 * [3,5,-2,6]可以累加出最大的和12，所以返回12
 * @author zhangy
 *
 */
public class SubArrayMaxSum {
	
	public static int getMaxSum(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		int cur = 0;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++){
				cur +=  arr[i];//记录每一步的累加和
				max = Math.max(max, cur);
				//cur 重置为0，表示从下一个数开始累加
				cur = cur < 0 ? 0 : cur;//当cur < 0时，说明累积到当前数出现了小于0的结果，那么累加的这一部分肯定不能作为产生最大累加和的子数组的左边部分。令cur=0
			}
		return max;
	}
	public static void main(String[] args){
		int[] arr = {1,-2,3,5,-2,6,-1};
		System.out.println(getMaxSum(arr));
	}

}
