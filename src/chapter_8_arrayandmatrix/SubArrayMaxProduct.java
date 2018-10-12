package chapter_8_arrayandmatrix;
/**
 * 数组中子数组的最大累乘积：给定一个double类型的数组arr,其中的元素可正、可负、可0，返回的子数组累乘的最大乘积。
 * 例如：arr = [-2.5,4,0,3,0.5,8,-1],子数组[3,0.5,8]累乘可以收获得最大的乘积12，所以返回12
 * @author zhangy
 *
 */
public class SubArrayMaxProduct {
	/**
	 * 本题可以做到时间复杂度为O(N)、额外空间复杂度为O(1)。所有的子数组都会以某一个位置结束，所以，如果求出以
	 * 每一个位置结尾的子数组最大的累乘积，在这么多最大累乘积中最大的那个就是最终的结果，也就是说，结果={
	 * 以arr[0]结尾的所有子数组的最大累乘积，以arr[1]结尾的所有子数组的最大累乘积...以arr[arr.length-1]
	 * 结尾的所有子数组的最大累乘积}.
	 * 如何快速求出所有以i位置结尾(arr[i])的子数组的最大乘积呢？假设以arr[i-1]结尾的最小累乘积为min,
	 * 以arr[i-1]结尾的最大累乘积为max.那么，以arr[i]结尾的最大累乘积只有以下三种可能：
	 * 1.可能是max*arr[i]。max既然表示以arr[i-1]结尾的最大累乘积，那么当然有可能以arr[i]结尾的最大累乘积
	 * 是max*arr[i]。例如，[3,4,5]在算到5的时候
	 * 2.可能是min*arr[i]。min既然表示以arr[i-1]结尾的最小累乘积，当然有可能min是负数，而如果arr[i]也是负数，
	 * 两个负数相乘的结果也可能很大。例如，[-2,3,-4]在算到-4的时候。
	 * 3.可能仅是arr[i]的值。以arr[i]结尾的最大累乘积并不一定非要包含arr[i]之前的数，例如，[0.1,0.1,100]在算到100的时候
	 */
	
	public static double getSubArrayMaxProduct(double[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		double min = arr[0];
		double max = arr[0];
		double maxEnd = 0;
		double minEnd = 0;
		double res = arr[0];
		for(int i = 1; i < arr.length; ++i){//上面已经赋值数组第一个数了，所以这里从第二个数开始遍历
			maxEnd = max * arr[i];
			minEnd = min * arr[i];
			max = Math.max(Math.max(maxEnd, minEnd), arr[i]);//max是以arr[i-1]结尾的最大子数组累乘积
			min = Math.min(Math.min(maxEnd, minEnd), arr[i]);//min是以arr[i-1]结尾的最小子数组累乘积
			res = Math.max(res, max);//加这个原因就是：如果res = arr[0]最大的话，就直接用arr[0]了，否则会漏掉
		}
		return res;
	}
	public static void main(String[] args){
		double[] arr = {-2.5,4,0,3,0.5,8,-1};
		System.out.println(getSubArrayMaxProduct(arr));
	}
}
