package chapter_4_recursionanddp;
/**
 * 斐波纳切数列问题
 * @author zhangy
 *
 */
public class FibonacciProblem {
	//斐波纳切数列求第n个数问题
	public int f1(int n){//递归方法求解，复杂度为O(2^n)
		if(n < 1){
			return 0;
		}
		if(n == 1 || n == 2){
			return 1;
		}
		return f1(n-1) + f1(n-2);
	}
	public int f2(int n){//使用循环的方法直接求出第n个数的值。时间复杂度为O(n)
		if(n < 1){
			return 0;
		}
		if(n ==1 || n== 2){
			return 1;
		}
		int res = 1;
		int pre = 1;
		int tmp = 0;
		for(int i = 3; i <= n; i++){
			tmp = res;//暂存前面一个数的值
			res = res + pre;
			pre = tmp;//pre存放前两个数的值
		}
		return res;
	}
	/**
	 * 如果台阶只有1级，方法只有1中，如果台阶有2级，方法有2种，如果台阶有N级，最后跳第N级的情况：要么是从N-2级台阶直接跨2级台阶。要么是
	 * 从第N-1级跨一级台阶。所以台阶有N级的方法数为：t(n) = t(n-1) + t(n-2);
	 * @param n
	 * @return
	 */
	//类似斐波纳切问题的跳台阶问题：一次可以跨一个台阶或两个台阶
	public int t1(int n){
		if(n < 1){
			return 0;
		}
		if(n == 1){
			return 1;
		}
		if(n == 2){
			return 2;
		}
		return t1(n-1) + t1(n-2);
	}
	
	public int t2(int n){
		if(n < 1){
			return 0;
		}
		if(n == 1 || n == 2){
			return n;
		}
		int res = 2;
		int pre = 1;
		int tmp = 0;
		for(int i = 3; i <= n; i++){
			tmp = res;
			res = res + pre;
			pre = tmp;
		}
		return res;
	}

	/**
	 * 所有的牛都不会死，所以第N-1年的牛会毫无损失第活到第N年。同时所哟成熟的牛都会生1头新的牛，那么成熟牛的数量如何估计？就是第N-3年
	 * 的所有牛，到第N年肯定都是成熟的牛，期间出生的牛肯定都是没哟成熟。所以c(n) = c(n-3) + c(n -1);
	 */
	public int c1(int n){
		if(n < 1){
			return 0;
		}
		if(n == 1 || n == 2 || n == 3 ){
			return n;
		}
		return c1(n-1) + c1(n-3);
	}
	public int c2(int n){
		if(n < 1){
			return 0;
		}
		if(n == 1 || n == 2 || n == 3){
			return n;
		}
		int res = 3;
		int pre = 2;
		int prePre = 1;
		int temp = 0;
		int temp2 = 0;
		for(int i = 4; i <= n; i++){
			temp = res;
			temp2 = pre;
			res = res + pre + prePre;
			pre = temp;
			prePre = temp2;
		}
		return res;
	}
	public static void main(String[] args) {
		int n = 20;
		FibonacciProblem fp = new FibonacciProblem();
		System.out.println(fp.f1(n));
		System.out.println(fp.f2(n));
		System.out.println("===");

		System.out.println(fp.t1(n));
		System.out.println(fp.t2(n));
		System.out.println("===");

		System.out.println(fp.c1(n));
		System.out.println(fp.c2(n));
		System.out.println("===");

	}
}
