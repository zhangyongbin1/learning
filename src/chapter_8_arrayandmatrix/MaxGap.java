package chapter_8_arrayandmatrix;
/**
 * 给定一个整型数组arr,返回排序后的相邻两数的最大差值。
 * arr = [9,3,1,10]如果排序，结果为[1,3,9,10],9和3的差为最大差值，故返回6
 * arr = [5,5,5,5].返回0
 * @author zhangy
 *
 */
public class MaxGap {
	public static int maxGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int len = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);//求数组的最小值
			max = Math.max(max, nums[i]);//求数组的最大值
		}
		if (min == max) {//如果最大值跟最小值相等
			return 0;//说明排序后相邻两数的最大差值为0
		}
		boolean[] hasNum = new boolean[len + 1];//用于判别对应角标的桶中是否有值
		int[] maxs = new int[len + 1];//用于统计每个桶中的最大值
		int[] mins = new int[len + 1];//用于统计每个桶中的最小值
		int bid = 0;
		for (int i = 0; i < len; i++) {
			bid = bucket(nums[i], len, min, max);//计算nums[i]需要放入到哪一个桶中
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];//如果桶中有值，那么取桶中的最小值，如果没有，那么当前值就是桶中的较小值
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];//如果桶中有值，那么取桶中的最大值，如果没有，那么当前值就是桶中的较大值
			hasNum[bid] = true;//将值放入到桶中后，设置标签为true
		}
		int res = 0;
		int lastMax = maxs[0];
		int i = 1;
		for (; i <= len; i++) {
			if (hasNum[i]) {
				res = Math.max(res, mins[i] - lastMax);//相邻两数的最大差值只可能来自某个非空桶的最小值减去前一个非空桶的最大值
				lastMax = maxs[i];
			}
		}
		return res;
	}

	public static int bucket(long num, long len, long min, long max) {
		return (int) ((num - min) * len / (max - min));//当前数该放入到哪一个桶中
	}

	public static void main(String[] args) {
		int[] arr = { 11, 10, 9, 3, 1, 12 };
		System.out.println(maxGap(arr));

	}

}
