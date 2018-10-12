package chapter7_bitoperation;
/**
 * 在其他数都出现偶数次的数组中找到出现奇数次的数: 给定一个整型数组arr,其中只有一个数出现奇数次，其他的数都出现
 * 来偶数次，打印这个数。
 * @author zhangy
 *
 */
public class EvenTimesOddTimes {
	//整数n 与 0 异或的结果是 n, 整数n 与 n 异或的结果是0，并且异或满足交换律
	public static int printOddTimeNum(int[] arr){
		int e0 = 0;
		for(int i =0; i < arr.length; i++){
			e0 = e0 ^ arr[i];
		}
		return e0;
	}
	/**
	 * 进阶问题：有两个数出现了奇数次，其他的数都出现了偶数次，打印这两个数
	 * 
	 * 如果是两个奇数，那么最后的e0结果就是这两个奇数异或的结果，且一定不等于0，那么肯定能在32位整数e0上找到
	 * 一个不等0的bit位，假设是第k位不等于0，e0在第k为不等于0.说明a和b的第k位肯定一个是1另外一个是0.
	 * 接下来再设置一个遍历记为e0hasOne,然后再遍历一次数组。在这次遍历时，e0hasOne只与第k位上是1的整数异或，
	 * 其他的数忽略。那么在第二次遍历结束后，e0hasOne就是a或者b中的一个，而e0^e0hasOne就是另外一个出现奇数次的数
	 */
	public static void printTwoOdds(int[] arr){
		int e0 = 0;
		int e0hasOne = 0;
		for(int cur : arr){
			e0 ^= cur;//e0最后的结果就是两个奇数的异或结果
		}
		int rightOne = e0 & (~e0 + 1);//找到e0中其中一个为1的位置，这个位置为1说明，两个奇数中一个是1，一个是0
		for(int cur : arr){
			if((cur & rightOne) != 0){//比较rightOne这个位置的数
				e0hasOne ^= cur;//最后e0hasOne的结果就是两个奇数中的一个
			}
		}
		System.out.println(e0hasOne +"; "+ (e0^e0hasOne));
	}
	
	public static void main(String[] args) {
		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		System.out.println(printOddTimeNum(arr1));

		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printTwoOdds(arr2);

	}
}
