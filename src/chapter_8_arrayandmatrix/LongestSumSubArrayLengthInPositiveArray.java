package chapter_8_arrayandmatrix;
/**
 * 未排序正数数组中累加和为给定值的最长子数组长度：给定一个数组arr,该数组无序，但每个值均为正数，再给定一个正数k,
 * 求arr的所有子数组中所有元素相加和为k的最长子数组长度
 * 例如：arr = [1,2,1,1,1], k =3,累加和为3的最长子数组为[1,1,1],所以结果返回3
 * @author zhangy
 *
 */
public class LongestSumSubArrayLengthInPositiveArray {
	/**
	 * 最优解可以做到时间复杂度为O(N),额外空间复杂度为O(1)。首先用两个位置来标记子数组的左右两头，记为left和right,
	 * 开始时都在数组的最左边(left = 0, right = 0)。整体过程如下：
	 * 1.开始时变量left = 0, right =0,代表子数组arr[left...right]
	 * 2.变量sum始终表示子数组arr[left...right]的和。开始时sum = arr[0],即arr[0...0]的和
	 * 3.变量len一直记录累加和为k的所有子数组中最大子数组的长度。开始时，len = 0
	 * 4.根据sum与k的比较结果决定是left移动还是right移动具体如下：
	 * 		a.如果sum == k,说明arr[left...right]累加和为k,如果arr[left...right]长度大于len,则更新len,
	 * 		此时因为数组中所有的值都为正数，那么所有从left位置开始，在right之后的位置结束的子数组，即arr[left...i(i>right)],
	 * 		累加和一定大于k.所以，令left加+1，这表示我们开始考察以left之后的位置开始的子数组，同时令sum -=arr[left],
	 * 		sum此时开始表示arr[left+1...right]的累加和
	 * 		b.如果sum小于k,说明arr[left...right]还需要加上right后面的值，其和才能达到k,所以，令right加1,sum +=arr[right]
	 * 		需要注意的是，right 加1后是否越界
	 * 		c.如果sum大于k,说明所有从left位置开始，在right之后的位置结束的子数组，即arr[left...i(i>right)],累加和一定大于k
	 * 		所以，令left加1，这表示我们开始考查以left之后的位置开始的子数组，同时令sum -=arr[left], sum此时表示arr[left+1...right]的累加和
	 * 5.如果right < arr.length,重复步骤4，否则直接返回Len，全部过程结束
	 */
	
	public static int getLongestSumSubArray(int[] arr, int k){
		if(arr == null || arr.length == 0 || k < 0){
			return 0;
		}
		int left = 0;
		int right = 0;
		int len = 0;
		int sum = arr[0];
		while(right < arr.length){
			if(sum == k){
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			}else if(sum > k){
				sum -= arr[left++];
			}else{
				right++;
				if(right == arr.length){
					break;
				}
				sum +=arr[right];
			}
		}
		return len;
	}
	
	public static void main(String[] args){
		int[] arr = {1,2,1,1,1};
		int k = 3;
		System.out.println(getLongestSumSubArray(arr,k));
	}

}
