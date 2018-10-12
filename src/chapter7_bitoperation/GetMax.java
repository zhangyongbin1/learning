package chapter7_bitoperation;
/**
 * 不用任何比较判断就找出两个数中较大的数：给定两个32位整数a和b,返回a和b中较大的值
 * @author zhangy
 *
 */
public class GetMax {
	
	//第一种方法就是：得到a-b的值的符号，就可以知道是返回a还是返回b
	
	public static int flip(int n){//如果n为1，则返回0，如果n为0，则返回1，由于最高位0表示正数，1表示负数，所以需要这一步
		return n ^ 1;
	}
	public static int sign(int n){//这个函数用于判断n的符号位，0表示正数，1表示负数
		return flip((n >> 31) & 1);//将n进行有符号右移动31位，最后一位就是符号位
	}

	public static int getMax1(int a, int b){
		int c = a-b;//这种方法的问题是：如果a-b出现溢出的情况，那么这种方法就不对
		int scA = sign(c);//判断c的符号，c为负数，那么scA = 0,c为正数，那么scA=1
		int scB = flip(scA);//c为负数，那么scA = 0,scB = 1; c为正数，那么scA=1,scB = 0
		return a * scA + b * scB;//返回较大数
	}
	
	public static int getMax2(int a, int b){
		int c = a-b;
		int sa = sign(a);
		int sb = sign(b);
		int sc = sign(c);
		int diffSab = sa ^ sb;//判断a 与 b的符号是否不同
		int sameSab = flip(diffSab);//判断a 与 b的符号是否相同
		int returnA = sa * diffSab + sameSab * sc;//如果符号相同，那么就跟getMax1方法一样，如果不相同，则returnA就a符号
		int returnB = flip(returnA);//b符号不同
		return a * returnA + b * returnB;
	}
	
	public static void main(String[] args){
		int a = 3;
		int b = 4;
		System.out.println(getMax1(a,b));
		System.out.println(getMax2(a,b));
	}
	

}
