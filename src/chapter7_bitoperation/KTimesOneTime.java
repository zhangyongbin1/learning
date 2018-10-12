package chapter7_bitoperation;
/**
 * 在其他数都出现k次的数组中，找到只出现一次的数：给定一个整数数组arr和一个大于1的整数k.已知arr中只有1个数出现了1次，
 * 其他的数都出现了k次，请返回只出现了1次的数
 * @author zhangy
 *
 */
public class KTimesOneTime {
	//如果k个相同的k进制数进行无进位相加，相加的结果一定是每一位上都是0的k进制数
	
	/**
	 *
	 */

	public static int onceNum(int[] arr, int k) {
		int[] eO = new int[32];
		for (int i = 0; i != arr.length; i++) {
			setExclusiveOr(eO, arr[i], k);//获取只出现一次的数的k进制数
		}
		int res = getNumFromKSysNum(eO, k);//将值出现一次的k进制数转换成十进制数
		return res;
	}

	public static void setExclusiveOr(int[] eO, int value, int k) {
		int[] curKSysNum = getKSysNumFromNum(value, k);
		for (int i = 0; i != eO.length; i++) {
			eO[i] = (eO[i] + curKSysNum[i]) % k;//得到只出现一次的数的k进制数
		}
	}

	public static int[] getKSysNumFromNum(int value, int k) {//获取一个十进制数的k进制数组
		int[] res = new int[32];
		int index = 0;
		while (value != 0) {//将十进制数value转换成k机制数组
			res[index++] = value % k;
			value = value / k;
		}
		return res;
	}

	public static int getNumFromKSysNum(int[] eO, int k) {//将k进制数转换成十进制数
		int res = 0;
		for (int i = eO.length - 1; i != -1; i--) {//从右到左遍历
			res = res * k + eO[i];
		}
		return res;
	}

	public static void main(String[] args) {
		int[] test1 = { 1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9 };
		System.out.println(onceNum(test1, 3));

		int[] test2 = { -1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2 };
		System.out.println(onceNum(test2, 5));

	}



}
