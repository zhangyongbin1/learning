package chapter9_others;
/**
 * 一行代码求两个数的最大公约数：给定两个等于0的整数M和N，求M和N的最大公约数
 * @author zhangy
 *
 */
public class GCD {
	
	/**
	 * 一个很简单的求两个数最大公约数的算法是欧几里德在其《几何原本》中提出的欧几里德算法，又称为辗转相除法
	 * 具体做法为：如果q和r分别是m除以n的商及余数，即m = nq + r,那么m和n的最大公约数等于n和r的最大公约数。
	 */
	public static int gcd(int m, int n){
		return n == 0 ? m : gcd(n, m % n);
	}
	public static void main(String[] args){
		int m = 10;
		int n = 2;
		System.out.print(gcd(m,n));
	}
}
