package chapter_4_recursionanddp;
/**
 * 字符串的交错组成：给定三个字符串str1、str2、和aim,如果aim包含且仅包含来自str1和str2的所有字符，而且在aim中属于str1的字符
 * 之间保持原来在str1中的顺序，属于str2的字符之间保持原来在str2中的顺序，那么称aim是str1和str2的交错组成。实现一个函数，判断aim是否是str1和str2交错组成
 * 
 * 例如：str1 = "AB";str2 = "12",那么"AB12"、"A1B2"、"A12B"、"1A2B"和"1AB2"都是str1和str2的交错组成
 * 
 * @author zhangy
 *
 */
public class StringCross {
	//经典动态规划方法
	/**
	 * 生成dp[M][N]的Boolean类型的数组，M = str1.lenght + 1; N = str2.lenght + 1;dp[i][j]的意思就是：aim[0...i+j-1]是否能由str1[0...i-1]和str2[0...j-1]交错组成
	 * 1.dp[0][0] = true;aim为空串时，当然可以被str1为空串和str2为空串交错组成
	 * 2.矩阵dp的第一列dp[i][0]表示aim[0...i-1]能否只被str1[0...i-1]交错组成。如果aim[0...i-1]等于str1[0...i-1]，则令dp[i][0] = true;否则令dp[i][0]=false;
	 * 3.矩阵dp的第一行dp[0][j]表示aim[0...j-1]能否只被str2[0...j-1]交错组成，即如果aim[0...j-1]等于str2[0...j-1]则令dp[0][j] = true;否则令dp[0][j] = false;
	 * 4.对于其他位置(i,j)，dp[i][j]的值由下面的情况决定：
	 * 		(1)dp[i-1][j]代表aim[0...i+j-2]能否被str1[0...i-2]和str2[0...j-1]交错组成，如果可以，那么如果再有str1[i-1]等于aim[i+j-1],说明str1[i-1]又可以
	 * 			作为交错组成aim[0...i+j-1]的最后一个字符。令dp[i][j] = true;
	 * 		(2)dp[i][j-1]代表aim[0...i+j-2]能否被str1[0...i-1]和str2[0...i-2]交错组成，如果可以，那么如果再有str2[j-1] == aim[i+j-1]。说明str2[j-1]又可以
	 * 			作为交错组成aim[0...i+j-1]的最后一个字符。令dp[i][j] = true.
	 * 		(3)如果第1中情况和第2中情况都不满足，令dp[i][j] = false
	 */
 
	public boolean isCross1(String str1, String str2, String aim){
		if(str1 == null || str2 == null || aim == null){
			return false;
		}
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		char[] chaim = aim.toCharArray();
		int m = ch1.length + 1;
		int n = ch2.length + 1;
		if(m + n != chaim.length + 2){
			return false;
		}
		boolean[][] dp = new boolean[m][n];
		dp[0][0] = true;
		//第一列
		for(int i = 1; i < m; i++){
			if(ch1[i - 1] != chaim[i - 1]){
				break;
			}
			dp[i][0] = true;//只有当ch1中的字符全部等于chaim中的字符，则设置dp的对应位置为true
		}
		//第一行
		for(int j = 1; j < n; j++){
			if(ch2[j] != chaim[j]){
				break;
			}
			dp[0][j] = true;
		}
		for(int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				//当ch1的最后一个字符等于chaim中的最后一个字符，且chaim中除最后一个字符之外，可以由ch1[0...i-2]个字符与ch2[0...j-1]字符组成
				if(ch1[i - 1] == chaim[i + j -1] && dp[i - 1][j] || ch2[j - 1] == chaim[ i + j -1] && dp[i][j-1]){
					dp[i][j] = true;
				}
			}
		}
		return dp[m-1][n-1];
	}
	
	public static void main(String[] args){
		StringCross sc = new StringCross();
		String str1 = "AB";
		String str2 = "12";
		String aim = "AB12";
		System.out.println(sc.isCross1(str1, str2, aim));
	}
}
