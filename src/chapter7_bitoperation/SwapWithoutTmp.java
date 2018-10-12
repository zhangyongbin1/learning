package chapter7_bitoperation;
/**
 * 不使用额外的变量交换两个整数的值：如何不用额外变量交换两个整数的值？
 * @author zhangy
 *
 */
public class SwapWithoutTmp {
	/**
	 * 假设a异或b的结果记为c,c就是a整数位信息和b整数位信息的所有不同信息，比如，a = 4=100,b=3=011,a ^ b = 000.
	 * a ^ c的结果就是b,比如 a = 4=100,c=000,a^c=011 = 3 = 6
	 * b ^ c的结果就是a,比如 b = 3 = 011, c= 000, b^c=100=a=4
	 * 也就是： a = a^b^b,同一个数a异或b两次的结果还是这个值，这个性质可以用在加密中
	 * 异或就是相同的位为0，不同的位为1
	 */
	public static void main(String[] args){
		int a = 4;
		int b = 3;
		a = a ^ b;
		b = a ^ b;//这时的a^b应该是a的值，现在赋值给b,所以能达到交换a,b的值的效果
		a = a ^ b;//这时的b应该是a原本的值3，而这时的a应该还是a^b的结果，所以这时的a 应该是原本b = 3的值
		System.out.println("a = "+a+"; b = "+b);
	}

}
