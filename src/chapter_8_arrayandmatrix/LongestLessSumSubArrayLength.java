package chapter_8_arrayandmatrix;
/**
 * 未排序的数组中累加和小于或等于给定值的最长子数组长度：给定一个无序数组arr,其中元素可正，可负、可0，给定一个整数k,求arr所有的
 * 子数组中累加和小于或者等于k的最长子数组长度。
 * 例如：arr = [3,-2,-4,0,6], k = -2, 相加和小于或等于-2的最长子数组为{3,-2,-4,0},所以结果返回4
 * @author zhangy
 *
 */
public class LongestLessSumSubArrayLength {
	/**
	 * 如果从0位置到j位置的累加和为sum[0...j],此时想求以j位置结尾的相加和小于或等于k的最长子数组长度，
	 * 那么只要知道大于或等于sum[0...j]-k这个值的累加和最早出现在j之前的什么位置就可以，假设那个位置
	 * 是i位置，那么arr[i+1...j]就是在以j位置结尾的相加和小于或等于k的最长子数组
	 */
	
	public static int maxLength(int[] arr, int k){
		if(arr == null || arr.length == 0){
			return 0;
		}
		int sum = 0;
		int[] helpArr = new int[arr.length + 1];
		helpArr[0] = sum;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
			helpArr[i+1] = Math.max(sum, helpArr[i]);//生成sumArr左侧最大值数组，用于判断大于或等于sum[0...j]-k这个值的累加和最早出现在j之前的什么位置
		}
		int pre = 0;
		int len = 0;
		int res = 0;
		sum = 0;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];//sum就表示arr[0...i]子数组的累加上
			pre = getLessIndex(helpArr,sum - k);//如果没找到sum - k, 那么pre = -1
			len = pre == -1 ? 0 : i - pre + 1;
			res = Math.max(res, len);
		}
		return res;
	}
	
	public static int getLessIndex(int[] arr, int num){//因为饿arr数组是有序的，所以使用二分查找法查找num的位置
		int low = 0;
		int high = arr.length -1;
		int res = -1;
		int mid = 0;
		while(low <= high){
			mid = (low + high)/2;
			if(arr[mid] >= num){//如果一直没进入这个循环，那么res = -1被return回去
				res = mid;
				high = mid -1;
			}else{
				low = mid + 1;
			}
		}
		return res;
	}
	public static void main(String[] args){
		int[] arr = {3,-2,-4,0,6};
		int k = -2;
		System.out.println(maxLength(arr,k));
	}
}
