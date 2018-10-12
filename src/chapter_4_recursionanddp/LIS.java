package chapter_4_recursionanddp;

/**
 * 最长递增子序列问题：给定数组arr,返回arr的最长递增子序列
 * 
 * @author zhangy
 *
 */
public class LIS {
	// 时间复杂度为O(N^2)的动态规划法
	/**
	 * 1.生成长度为N的数组dp,dp[i]表示在以arr[i]这个数结尾的情况下，arr[0...i]中的最大递增子序列长度，arr[i]
	 * 应该是这个子序列的最大值
	 * 2.对第一个数arr[0]来说，dp[0]表示以arr[0]结尾的情况情况下，arr[0]中的最大递增子序列长度应该为1，所以dp[0] = 1;
	 * 3.
	 * 假设计算到位置i,求以arr[i]结尾情况下的最长递增子序列长度，即dp[i].如果最长递增子序列以arr[i]结尾，那么在arr[0...i-
	 * 1]中所有比
	 * arr[i]小的数都可以作为倒数第二个数。在这么多倒数第二个数的选择中，以那一个结尾的最大递增子序列更大，就选哪个数作为倒数第二个数，所以：
	 * dp[i] = max{dp[j] + 1}(0<=j<i, arr[j] <
	 * arr[i])。如果arr[0...i-1]中所有的数都不比arr[i]小，那令dp[i] = 1即可，说明以arr[i]结尾的
	 * 最大递增子序列(LIS)只包含arr[i] 按照步骤1～3即可计算出dp数组
	 */
	public int[] getdp1(int[] arr) {
		int[] dp = new int[arr.length];// 第一步：新建一个长度为N的dp数组
		for (int i = 0; i < arr.length; i++) {// 循环遍历给dp数组赋值
			dp[i] = 1;// dp[0] = 1.且arr[0...i-1]中所有的数都不比arr[i]小时，dp[i]也为1
			for (int j = 0; j < i; j++) {// 在arr[0...i-1]中遍历寻比arr[i]小的数
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);// 并使用其较大的dp[j]值加1来构成dp[i]的值。表示以arr[i]结尾，以为较大dp[j]对应的arr[j]结尾的最大递增子序列
				}
			}
		}
		return dp;
	}

	/**
	 * 求出dp数组之后，怎么得到最大递增子序列？例如：arr=[2,1,5,3,6,4,8,9,7], dp=[1,1,2,2,3,3,4,5,4]
	 * 1：遍历dp数组，找到最大值已经位置，在本例中最大值为5，位置为7，说明最终的最大递增子序列的长度为5，并且应该以arr[7]=9这个数结尾
	 * 2:从arr数组的位置7开始从右向左遍历。如果对某一个位置i,既有arr[i] < arr[7],又有dp[i] == dp[7]
	 * -1;说明arr[i]可以作为最长递增子序列的倒数第二个数。 在本例中，arr[6] < arr[7],并且dp[6] == dp[7] -
	 * 1,所以8应该作为增长递增子序列的倒数第二个数
	 * 3：从arr数组的位置6开始继续向左遍历，按照同样的过找到倒数第三个数。在本例中，位置5满足arr[5] < arr[6],并且dp[5] =
	 * dp[6] -1;同时位置4也满足，选arr[5]或者arr[4]作为倒数第三个数都可以 4：重复这样的过程，知道所有的数都找出来。
	 */
	public int[] generateLIS(int[] arr, int[] dp) {
		int len = 0;
		int index = 0;
		for (int i = 0; i < dp.length; i++) {// 寻找到dp数组中最大的值，并记录其角标
			if (dp[i] > len) {
				len = dp[i];
				index = i;
			}
		}

		int[] lis = new int[len];
		lis[--len] = arr[index];// 以arr[index]结尾的最大子序列的最后一个数
		for (int i = index; i >= 0; i--) {// 从数组中最大子序列最后一个数对应的位置开始，向左遍历
			if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {// 满足arr[i] <
																// arr[index]且dp[i]
																// = dp[index]
																// -1
				lis[--len] = arr[i];// 则arr[i]可以作为倒数第二个数
				index = i;// 继续以i为起始位置继续向左遍历
			}
		}
		return lis;
	}

	public int[] lis1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		int[] dp = getdp1(arr);
		return generateLIS(arr, dp);
	}

	// 使用二分法获取dp数组，使复杂度降低为O(NlogN)
	public int[] getdp2(int[] arr) {
		int[] dp = new int[arr.length];
		int[] ends = new int[arr.length];
		ends[0] = arr[0];
		dp[0] = 1;
		int right = 0;
		int l = 0;
		int r = 0;
		int m = 0;
		for (int i = 1; i < arr.length; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = arr[i];
			dp[i] = l + 1;
		}
		return dp;
	}

	public int[] lis2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		int[] dp = getdp2(arr);
		return generateLIS(arr, dp);
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		LIS lis = new LIS();
		int[] arr = { 2, 1, 5, 3, 6, 4, 8, 9, 7 };
		printArray(lis.getdp2(arr));
		printArray(lis.lis1(arr));
		printArray(lis.lis2(arr));

	}

}
