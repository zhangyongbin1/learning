package chapter7_bitoperation;
/**
 * 只用位运算，不用算术运算实现整数的加减乘除运算：给定两个32位整数a和b,可正、可负、可0。不能使用算术运算符，分别实现a和b的加减乘除运算
 * 
 * @author zhangy
 *
 */
public class AdddeleteMultiDivideByBit {
	
	/**
	 * 加法运算，在不考虑进位的情况，a+b = a ^ b, 在只算进位的情况下，也就是只考虑a加b的过程中进位产生的值是什么，
	 * 结果就是(a & b) << 1,因为在第i位上只有1与1相加才会产生i-1位的进位。知道(a & b) << 1 == 0时说明所有的过程都加完了。
	 */
	
	public static int add(int a, int b){
		int sum = a;
		while( b != 0 ){
			sum = a ^ b;
			b = (a & b) << 1;//b在这个循环里面表示进位的变化情况
			a = sum;//a这个循环里面表示每一步中两个值异或的变化情况
		}
		return sum;
	}
	
	/**
	 * 两个数的减法就是：a + (-b),-b根据二进制数在机器中表达的规则，得到一个数的相反数，就是这个数的二进制表达取反加1(补码)的结果
	 * @param args
	 */
	public static int delete(int a, int b){//去两个数相减就是求a + b的补码
		return add(a, negNum(b));
	}
	//求一个数的补码结果
	public static int negNum(int n){
		return add(~n, 1);//~n + 1，表示n的反码加1
	}
	
	/**
	 * 位运算求两个数相乘的结果
	 * @param args
	 */
	public static int multi(int a, int b){
		int res = 0;
		while( b != 0){//如果b为0，那么整个过程停止
			if((b & 1) != 0){//如果b的最左侧为1，那么res = res + a;
				res = add(res, a);
			}
			a <<= 1;//a左移一位
			b >>>= 1;//b右移一位
		}
		return res;
	}
	/**
	 * 
	 * @param args
	 */
	public static boolean isNeg(int n) {
		return n < 0;
	}

	public static int div(int a, int b) {
		int x = isNeg(a) ? negNum(a) : a;
		int y = isNeg(b) ? negNum(b) : b;
		int res = 0;
		for (int i = 31; i > -1; i = delete(i, 1)) {
			if ((x >> i) >= y) {
				res |= (1 << i);
				x = delete(x, y << i);
			}
		}
		return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
	}
	public static int divide(int a, int b) {
		if (b == 0) {
			throw new RuntimeException("divisor is 0");
		}
		if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
			return 1;
		} else if (b == Integer.MIN_VALUE) {
			return 0;
		} else if (a == Integer.MIN_VALUE) {
			int res = div(add(a, 1), b);
			return add(res, div(delete(a, multi(res, b)), b));
		} else {
			return div(a, b);
		}
	}
	
	public static void main(String[] args){
		int a = 3;
		int b = 4;
		System.out.println(add(a,b));
		System.out.println(delete(a,b));
		System.out.println(multi(a,b));
		System.out.println(divide(a,b));
	}

}
