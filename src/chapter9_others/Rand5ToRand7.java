package chapter9_others;

public class Rand5ToRand7 {
	
	public static int rand1To5(){
		return (int)(Math.random()*5 + 1);//会产生1～5的随机数
	}
	/**
	 * 使用上面等概率产生的1～5的数，实现等概率产生(1~7)的函数功能
	 * 1.rand1To5()等概率随机产生1，2，3，4，5
	 * 2.rand1To5() - 1等概率随机产生0，1，2，3，4
	 * 3.(rand1To5() - 1) * 5等概率随机产生0，5，10，15，20
	 * 4.(rand1To5()-1) * 5 + (rand1To5() - 1)等概率随机产生0，1，2，3，4，5...23,24.注意，这两个rand1To5()
	 * 是指独立的两次调用结果，请不要简化，这是插空儿的过程
	 * 5.如果步骤4产生的结果大于20，则重复进行步骤4，直到产生的结果在0～20之间。同时可以轻易知道出现21～24的概率，
	 * 会平均分配到0～20上。这是“筛”的过程
	 * 6.步骤5会等概率随机产生0～20，所以步骤5的结果再进行%7操作，就会等概率的随机产生0～6.
	 * 7.步骤6的结果再加1，就会等概率地随机产生1～7
	 * @return
	 */
	public static int rand1To7(){
		int num = 0;
		do{
			num = (rand1To5() - 1) * 5 + rand1To5() -1;
		}while(num > 20);
			return num % 7 + 1;
	}
	
	public static int rand01P(){
		//可随意改变p
		double p = 0.83;
		return Math.random() < p ? 0 : 1;
	}
	
	public static void main(String[] args){
		System.out.println(rand1To5());
		System.out.println(rand1To7());
		
	}

}
