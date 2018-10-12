package demo_test;
/**
 * 
 * @author zhangy
 *
 */
public class DPproblems {
	
	/**
	 * 计算三角形的最小和
	 */
	public int MinSumTriangle(int[][] Triangle){
		for(int i = Triangle.length - 2; i >=0; i++){
			for(int j = 0; j < Triangle[i].length; j++){
				Triangle[i][j] += Math.min(Triangle[i+1][j], Triangle[i+1][j+1]);
			}
		}
		return Triangle[0][0];
	}
	
	/**
	 * 求最长公共字符子串，要求连续dp[i][j]表示必须以str1[i]和str2[j]结尾的情况，最长公共子串长度
	 */
	public int[][] getDpArr(char[] chas1, char[] chas2){
		int m = chas1.length;
		int n = chas2.length;
		int[][] dp = new int[m][n];
		//第一行
		for(int j = 0; j < n; j++){
			dp[0][j] = chas1[0] == chas2[j] ? 1 : 0;
		}
		//第一列
		for(int i = 0; i < m; i++){
			dp[i][0] = chas1[i] == chas2[0] ? 1 : 0;
		}
		for(int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				if(chas1[i] == chas2[j]){
					dp[i][j] = dp[i-1][j-1]+1;
				}
			}
		}
		return dp;
	}
	public String getCommonSubStr(String str1, String str2){
		char[] chas1 = str1.toCharArray();
		char[] chas2 = str2.toCharArray();
		int[][] dp = getDpArr(chas1,chas2);
		int max = 0;
		int maxIndex = 0;
		for(int i = 0; i < dp.length; i++){
			for(int j = 0; j < dp[i].length; j++){
				if(dp[i][j] > max){
					max = dp[i][j];
					maxIndex = i;
				}
			}
		}
		return str1.substring(maxIndex - max + 1, maxIndex + 1);
	}
	
	/**
	 * 求最大公共子序列问题
	 * 
	 */
	
	public int[][] getLastCommonSubSequence(char[] chars1, char[] chars2){
		int m = chars1.length;
		int n = chars2.length;
		int[][] dp = new int[m][n];
		dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
		//第一行
		for(int j = 1; j < n; j++){
			dp[0][j] = Math.max(dp[0][j-1], chars1[0] == chars2[j] ? 1 : 0);
		}
		//第一行
		for(int i = 0; i < m; i++){
			dp[i][0] = Math.max(dp[i-1][0], chars1[i] == chars2[0] ? 1 : 0);
		}
		for(int i  = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if(chars1[i] == chars2[j]){
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
				}
			}
		}
		return dp;
	}
	public String getCommonSubSequence(String str1, String str2){
		char[] chas1 = str1.toCharArray();
		char[] chas2 = str2.toCharArray();
		int[][] dp = getLastCommonSubSequence(chas1,chas2);
		int m = chas1.length -1;
		int n = chas2.length -1;
		int maxlength = dp[m][n];
		char[] res = new char[maxlength];
		int index = res.length - 1;
		while(index >= 0){
			if(n > 0 && dp[m][n] == dp[m][n-1]){
				n--;
			}else if(m > 0 && dp[m][n] == dp[m-1][n]){
				m--;
			}else{
				res[index--] = chas1[m];
				m--;
				n--;
			}
		}
		
		return String.valueOf(res);
	}

}
