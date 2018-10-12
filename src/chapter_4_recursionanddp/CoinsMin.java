package chapter_4_recursionanddp;

public class CoinsMin {
	public int minCoins1(int[] arr, int aim){
		if(arr == null || arr.length == 0 || aim < 0){
			return -1;//表示钱aim不能找开的情况，直接返回-1
		}
		int n = arr.length;
		int max = Integer.MAX_VALUE;
		//dp[i][j]表示只使用arr[0...i]这些钱的时候能找出钱数j所需要的钱数的最小张数
		int [][] dp = new int [n][aim + 1];//初始化默认的值就是0,数组的行使用arr数组的长度，数组的列使用找钱数aim + 1
		//dp第一列的的值为默认值0
		for(int j = 1; j <= aim; j++){//所以这里只需要设置第一行的值即可
			dp[0][j] = max;//第一行使用arr[0]值找不开的钱数对应的位置使用max最大值
			if(j - arr[0] >= 0 && dp[0][j - arr[0]] != max){//如果使用arr[0]能找开j-arr[0]钱，
				dp[0][j] = dp[0][j - arr[0]] + 1;//那么就是找开j-arr[0]的钱数 + 1即可
			}
		}
		int left = 0;
		for(int i = 1; i < n; i++){
			for(int j = 1; j <= aim; j++){
				left = max;
				if(j - arr[i] >=0 && dp[i][j-arr[i]] != max){
					left = dp[i][j - arr[i]] + 1;//相当于dp[i-1][j - y*arr[i]] + y (y >=0),因为题意是可以使用任意张的钱数
				}
				dp[i][j] = Math.min(left, dp[i-1][j]);//取使用当前货币arr[i] 与不使用当前货币arr[i]使用的较小值
			}
		}
		return dp[n-1][aim] != max ? dp[n-1][aim] : -1;//返回dp数组的最后一个元素即为解，如果为max说明找不开，直接返回-1
	}
	
	//补充问题为：arr数组中重复的值，但是每一张货币只能使用一次，不能任意使用
	public int minCoins3(int[] arr, int aim){
		if(arr == null || arr.length == 0 || aim < 0){
			return -1;//表示钱aim不能找开的情况，直接返回-1
		}
		int n = arr.length;
		int max = Integer.MAX_VALUE;
		//dp[i][j]表示只使用arr[0...i]这些钱的时候能找出钱数j所需要的钱数的最小张数
		int [][] dp = new int [n][aim + 1];//初始化默认的值就是0,数组的行使用arr数组的长度，数组的列使用找钱数aim + 1
		//dp第一列的的值为默认值0

		for(int j = 1; j <= aim; j++){//先把第一行所有的值赋值为max
			 dp[0][j] = max;
		}
		if(arr[0] <= aim){//表示只使用一张arr[0]所能找出的钱数，当然只能找出钱数aim == arr[0]的情况
			dp[0][arr[0]] = 1;
		}
		int leftup = 0;
		for(int i = 1; i < n; i++){
			for(int j = 1; j <= aim; j++){
				leftup = max;
				if(j - arr[i] >=0 && dp[i-1][j-arr[i]] != max){
					leftup = dp[i-1][j - arr[i]] + 1;//使用a[0...i-1]这些货币的情况下能找出j-arr[i]钱数所需要的最小的张数 + 1 张当前货币的钱arr[i]
				}
				dp[i][j] = Math.min(leftup, dp[i-1][j]);//取使用当前货币arr[i] 与不使用当前货币arr[i]使用的较小值
			}
		}
		return dp[n-1][aim] != max ? dp[n-1][aim] : -1;//返回dp数组的最后一个元素即为解，如果为max说明找不开，直接返回-1
	}
	
	public static void main(String[] args) {
		CoinsMin cm = new CoinsMin();
		//int[] arr1 = { 100, 20, 5, 10, 2, 50, 1 };
		//int aim1 = 17019;
		int[] arr1 = { 5,  2, 3 };
		int aim1 = 20;
		System.out.println(cm.minCoins1(arr1, aim1));

		//int[] arr2 = { 10, 100, 2, 5, 5, 5, 10, 1, 1, 1, 2, 100 };
		//int aim2 = 223;
		int[] arr2 = { 5, 2, 5, 3 };
		int aim2 = 15;
		System.out.println(cm.minCoins3(arr2, aim2));

	}

}
