package chapter_4_recursionanddp;
/**
 * 换钱的方法数：给定数组arr,arr中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面的货币可以使用任意张，给定一个整数aim代表要找的钱数，求换钱有多少种方法
 * @author zhangy
 *
 */
public class CoinsWay {
	//暴力递归的方法，时间复杂度较高
	public int coins1(int[]arr, int aim){
		if(arr == null || arr.length == 0 || aim < 0){
			return 0;
		}
		return process1(arr, 0, aim);
	}
	public int process1(int[] arr, int index, int aim){//表示使用arr[index...N-1]这些面值的钱组成aim，返回总的方法数
		int res = 0;
		if(index == arr.length){
			res = aim == 0 ? 1 : 0;
		}else{
			for(int i = 0; arr[index] * i <= aim; i++){//递归过程有大量重复计算：例如process1(arr,2,990):2 * 5 + 0 *10 +[25,1]组成990和0 * 5 + 1 *10 +[25,1]组成990的递归过程
				res += process1(arr, index + 1, aim - arr[index]*i);
			}
		}
		return res;
	}

	public int coins2(int[] arr, int aim){
		if(arr == null || arr.length == 0 || aim < 0){
			return 0;
		}
		int[][] map = new int[arr.length + 1][aim + 1];//map数组的大小
		return process2(arr, 0, aim, map);
	}
	//记忆搜索方法，在递归方法的基础上增加一个map集合，存放已经计算的递归过程放回的结果
	//map[i][j] = 0表示递归过程p(i,j)从来没有计算过
	//map[i][j] = -1表示递归过程p(i,j)计算过，但返回值为0
	//map[i][j] = a表示递归过程p(i,j)计算过，返回值为a
	public int process2(int[] arr, int index, int aim, int[][] map){
		int res = 0;
		if(index == arr.length){
			res = aim == 0 ? 1 : 0;
		}else{
			int mapValue = 0;
			for(int i = 0; arr[index] * i <= aim; i++){
				mapValue = map[index + 1][aim - arr[index] * i];// 拿出map中对应的下一次递归计算结果出来
				if(mapValue != 0){//说明当前这个process2过程计算过
					res += mapValue == -1 ? 0 : mapValue;
				}else{//说明当前过程没有计算过，那么就继续进行递归计算
					res += process2(arr,index + 1, aim - arr[index] * i, map);
				}
			}
		}
		map[index][aim] = res == 0 ? -1 : res;//给map数组赋值,每一次递归的结果
		return res;
	}
	//动态规划方法
	/**
	 * dp[i][j]矩阵表示在使用arr[0...i]这些货币的情况下，组成钱数j有多少种方法：
	 * 	1：对于矩阵dp的第一行的值dp[0][j]，表示在只适用arr[0]的情况下，组成钱数j有多少种方法，因为可是使用任意数量的货币，所以可以组成的钱为arr[0]的值的倍数
	 * 		dp[0][k * arr[0]]= 1 (k>=0)
	 * 	2：对于矩阵dp的第一列的值dp[i][0].表示在使用arr[0...i]的情况下，组成钱数0有多少中方法，很显然是1中,也就是不使用任何货币。所以矩阵dp第一列的值都为1
	 * 
	 * 	3:除第一行和第一列的其他位置，记为位置(i,j).dp[i][j]的值是以下几个值的累加
	 * 		(1):完全不用货币arr[i],只使用arr[0...i-1]货币是，方法数为dp[i-1][j]
	 * 		(2):使用1张arr[i]货币是，剩余钱数用arr[0...i-1]组成是，方法数为dp[i-1][j-arr[i]]
	 * 		(3):使用2张arr[i]货币是，剩余钱数用arr[0...i-1]组成是，方法数为dp[i-1][j- 2 *arr[i]]
	 * 			.
	 * 			.
	 * 			.
	 * 		(k+1):使用k张arr[i]货币是，剩余钱数用arr[0...i-1]组成是，方法数为dp[i-1][j- k *arr[i]]
	 * 	4:最终dp[N-1][aim]的值就是最终结果
	 */
	public int coins3(int[] arr, int aim){
		if(arr == null || arr.length == 0 || aim < 0){
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N][aim + 1];
		for(int i = 0; i < N; i++){//dp矩阵的第一列都为1，即组成钱数为0的方法都是1种，就是不使用任何货币
			dp[i][0] = 1;
		}
		for(int j = 1; j * arr[0] <= aim; j++){//dp矩阵的第一行为组成钱数为arr[0]的倍数的时候，其位置上的值为1，表示有一种方法
			dp[0][j * arr[0]] = 1;
		}
		int num = 0;
		//遍历矩阵dp,给矩阵dp其他位置进行赋值
		for(int i = 1; i < N; i++){
			for(int j = 1; j <= aim; j++){
				num = 0;
				for(int k = 0; j- arr[i] * k >= 0; k++){//表示使用k张当前arr[i]货币时，组成aim的总方法数
					num += dp[i-1][j - arr[i] * k];//
				}
				dp[i][j] = num;
			}
		}
		return dp[N-1][aim];
	}
	
	//动态规划方法
		/**
		 * dp[i][j]矩阵表示在使用arr[0...i]这些货币的情况下，组成钱数j有多少种方法：
		 * 	1：对于矩阵dp的第一行的值dp[0][j]，表示在只适用arr[0]的情况下，组成钱数j有多少种方法，因为可是使用任意数量的货币，所以可以组成的钱为arr[0]的值的倍数
		 * 		dp[0][k * arr[0]]= 1 (k>=0)
		 * 	2：对于矩阵dp的第一列的值dp[i][0].表示在使用arr[0...i]的情况下，组成钱数0有多少中方法，很显然是1中,也就是不使用任何货币。所以矩阵dp第一列的值都为1
		 * 
		 * 	3:除第一行和第一列的其他位置，记为位置(i,j).dp[i][j]的值是以下几个值的累加
		 * 		(1):完全不用货币arr[i],只使用arr[0...i-1]货币是，方法数为dp[i-1][j]
		 * 		(2):使用1张arr[i]货币是，剩余钱数用arr[0...i-1]组成是，方法数为dp[i-1][j-arr[i]]
		 * 		(3):使用2张arr[i]货币是，剩余钱数用arr[0...i-1]组成是，方法数为dp[i-1][j- 2 *arr[i]]
		 * 			.
		 * 			.
		 * 			.
		 * 		(k+1):使用k张arr[i]货币是，剩余钱数用arr[0...i-1]组成是，方法数为dp[i-1][j- k *arr[i]]
		 * 	4:最终dp[N-1][aim]的值就是最终结果
		 * Ps:其实第三步骤的结果就等于dp[i][j-arr[i]],因为是可以使用任意数量的货币
		 */
	public int coins4(int[] arr, int aim){
		if(arr == null || arr.length == 0 || aim < 0){
			return 0;
		}
		int[][] dp = new int[arr.length ][aim + 1];
		for(int i = 0; i < arr.length; i++){
			dp[i][0] = 1;
		}
		for(int j = 1; j * arr[0] <= aim; j++){
			dp[0][arr[0] * j] = 1; 
		}
		for(int i = 1; i < arr.length; i++){
			for(int j = 1; j <= aim; j++){
				dp[i][j] = dp[i - 1][j];
				dp[i][j] += j - arr[i] >= 0 ? dp[i][j-arr[i]] : 0;//其实第三步最后得到的值就是dp[i][j-arr[i]]
			}
		}
		return dp[arr.length  - 1][aim];
	}
	
	
	public static void main(String[] args){
		CoinsWay cw = new CoinsWay();
		int[] coins = { 10, 5, 1, 25 };
		int aim = 2000;
		
		long start = 0;
		long end = 0;
		System.out.println("===========暴力递归¨===========");
		start = System.currentTimeMillis();
		System.out.println(cw.coins1(coins, aim));
		end = System.currentTimeMillis();
		System.out.println("cost time : " + (end - start) + "(ms)");
		
		aim = 20000;
		
		System.out.println("===========记忆搜索方法===========");
		start = System.currentTimeMillis();
		System.out.println(cw.coins2(coins, aim));
		end = System.currentTimeMillis();
		System.out.println("cost time : " + (end - start) + "(ms)");
		
		System.out.println("=====动态规划1=====");
		start = System.currentTimeMillis();
		System.out.println(cw.coins3(coins, aim));
		end = System.currentTimeMillis();
		System.out.println("cost time : " + (end - start) + "(ms)");
		
		System.out.println("=======动态规划2=======");
		start = System.currentTimeMillis();
		System.out.println(cw.coins4(coins, aim));
		end = System.currentTimeMillis();
		System.out.println("cost time : " + (end - start) + "(ms)");
	}
}
