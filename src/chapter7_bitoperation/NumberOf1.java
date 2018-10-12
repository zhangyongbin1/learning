package chapter7_bitoperation;
/**
 * 整数二进制表达中有多少个1：给定一个32位整数n,可为0，可为正，也可为负，返回该整数二进制表达中的1的个数
 * @author zhangy
 *
 */
public class NumberOf1 {
	/**
	 * 最简单的解法，整数n每次进行无符号右移一位，检查最右边的bit是否为1来进行统计,最复杂的情况下，需要经过32次循环
	 */
	public int count1(int n){
		int res = 0;
		while(n != 0){
			res += n & 1;//统计n最右边的bit为1的个数
			n >>>= 1;//将n进行无符号右移动一位
		}
		return res;
	}
	/**
	 * 下面这个方法的循环次数只与1的个数有关
	 * @param n
	 * @return
	 */
	public int count2(int n){
		int res = 0;
		while( n != 0){
			n &= n-1;//实在上就是去掉n最右边的1，从而达到统计1的个数的效果
			res++;
		}
		return res;
	}
	public static void main(String[] arg){
		NumberOf1 count = new NumberOf1();
		int n = 2;
		System.out.println(count.count1(n));
		System.out.println(count.count2(n));
	}
}
