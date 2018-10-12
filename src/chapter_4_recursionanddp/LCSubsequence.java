package chapter_4_recursionanddp;

/**
 * 最长公共子序列问题：给定两个字符串str1和str2，返回两个字符串的最长公共子序列 例如：str1 = "1A2C3D4B56"
 * str2="B1D23CA45B6A" 两者的最长公共子序列为："123456"和"12C4B6"，返回二者之一即可。最长公共子序列不需要连续的
 * 
 * @author zhangy 经典的动态规划问题：dp[M][N],其中M为str1的长度，N为str2的长度
 *         dp[i][j]的含义就是：str1[0...i]与str2[0...j]的最长公共子序列的长度，从左到右，从上到下计算矩阵dp
 *         1.矩阵dp的第一列
 *         ，即dp[i][0]表示str1[0...i]与str2[0](也就是str2的第一字符)的最长公共子序列的长度，如果str1[i] ==
 *         str2[0], 那么dp[i][0] =
 *         1;并且一旦dp[i][0]设置为1，那么之后的dp[i+1...M-1][0]也就设置为1。、
 *         2.矩阵dp的第一行，即dp[0][j]表示str1[0]与字符串str2[0...j]的最长公共子序列长度，如果str1[0] ==
 *         str2[j],那么dp[0][j] == 1;
 *         并且一旦dp[0][j]设置为1，那么之后的dp[0][j+1...N-1]也就设置为1；
 *         3.对其他位置(i,j)，dp[i][j]的值只可能来自一下三种情况： (1):可能是dp[i-1][j]，代表str1[0...i-1]
 *         与 str2[0...j]的最长公共子序列长度。也就是说str1[i] != str2[j]
 *         (2):可能是dp[i][j-1],代表str1[0...i] 与str2[0...j-1]的最长公共子序列长度。也说明str1[i]
 *         != str2[j] (3):可能是dp[i-1][j-1] +
 *         1,代表str1[0...i-1]与str2[0...j-1]的最长公共子序列 + 1，说明str1[i] = str2[j]
 */
public class LCSubsequence {
	// 先获取矩阵dp[M][N]
	/**
	 * dp[i][j]的含义就是：str1[0...i]与str2[0...j]的最长公共子序列的长度，从左到右，从上到下计算矩阵dp
	 * 1.矩阵dp的第一列
	 * ，即dp[i][0]表示str1[0...i]与str2[0](也就是str2的第一字符)的最长公共子序列的长度，如果str1[i] ==
	 * str2[0], 那么dp[i][0] = 1;并且一旦dp[i][0]设置为1，那么之后的dp[i+1...M-1][0]也就设置为1。、
	 * 2.矩阵dp的第一行，即dp[0][j]表示str1[0]与字符串str2[0...j]的最长公共子序列长度，如果str1[0] ==
	 * str2[j],那么dp[0][j] == 1; 并且一旦dp[0][j]设置为1，那么之后的dp[0][j+1...N-1]也就设置为1；
	 * 3.对其他位置(i,j)，dp[i][j]的值只可能来自一下三种情况： (1):可能是dp[i-1][j]，代表str1[0...i-1] 与
	 * str2[0...j]的最长公共子序列长度。也就是说str1[i] != str2[j]
	 * (2):可能是dp[i][j-1],代表str1[0...i] 与str2[0...j-1]的最长公共子序列长度。也说明str1[i] !=
	 * str2[j] (3):可能是dp[i-1][j-1] + 1,代表str1[0...i-1]与str2[0...j-1]的最长公共子序列 +
	 * 1，说明str1[i] = str2[j]
	 */

	public int[][] getdp(char[] ch1, char[] ch2){
		int m = ch1.length;
		int n = ch2.length;
		int[][] dp = new int[m][n];
		dp[0][0] = ch1[0] == ch2[0] ? 1 : 0;
		//第一列
		for(int j = 1; j < n; j++){
			dp[0][j-1] = Math.max(dp[0][j-1], ch1[0] == ch2[0] ? 1 : 0);
		}
		//第一行
		for(int i = 1; i < m; i++){
			dp[i][0] = Math.max(dp[i-1][0], ch1[i] == ch2[0] ? 1 : 0);
		}
		//(i,j)其他位置
		for(int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if(ch1[i] == ch2[j]){
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
				}
			}
		}
				return dp;
	}
	//通过获取到的dp数组还原最长公共子序列
	/**
	 * 1.从dp矩阵的右下角开始，有三种移动方法：向上，向左，向左上。假设移动的过程中，i表示此时的行数，j表示此时的列数。同时用一个变量res来表示最长公共子序列
	 * 2.如果dp[i][j] 大于dp[i-1][j]和dp[i][j-1]，说明之前在计算dp[i][j]的时候，一定是选择了决策dp[i-1][j-1] + 1,可以确定str1[i]等于str2[j],
	 * 		并且这个字符一定属于最长公共子序列，把这个字符放进res，然后向左上方移动即dp[i-1][j-1]
	 * 3.如果dp[i][j]等于dp[i-1][j]，说明之前在计算dp[i][j]的时候，dp[i-1][j-1] + 1这个决策不是必须选择的决策，向上方移动即可。
	 * 4.如果dp[i][j]等于dp[i][j-1],同理步骤3.向左方移动即可
	 * 5.如果dp[i][j]同时等于dp[i-1][j]和dp[i][j-1]，向上还是向下无所谓，选择其中一个即可，反正不会错过必选的字符。
	 */
	public String lcse(String str1, String str2){
		if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
			return "";
		}
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		int m = ch1.length-1;
		int n = ch2.length-1;
		int[][] dp = getdp(ch1, ch2);
		char[] res = new char[dp[m][n]];
		int index = res.length - 1;
		while(index >= 0){
			if(n > 0 && dp[m][n] == dp[m][n-1]){//向左移动
				n--;
			}else if( m > 0 && dp[m][n] == dp[m-1][n]){//向上移动
				m--;
			}else {//这时候说明ch1[i] == ch2[j],则向左上移动
				res[index--] = ch1[m];
				m--;
				n--;
			}
		}
		
		return String.valueOf(res);
	}
	
	public static void main(String[] args){
		String str1 = "A1BC2";
		String str2 = "ACB34";
//		String str1 = "A1BC2D3EFGH45I6JK7LMN";
//		String str2 = "12OPQ3RST4U5V6W7XYZ";
		LCSubsequence lcs = new LCSubsequence();
		System.out.println(lcs.lcse(str1, str2).toString());
	}
}
