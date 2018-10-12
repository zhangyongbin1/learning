package chapter_5_stringproblem;
/**
 * 回文最小分割数：给定一个字符串str,返回把str全部切成回文子串的最小分割数。PS： 回文字符串就是左--右，与 右--左 是一样的
 * 举例：str = "ABA",不需要切割，str本身就是回文串，所以返回0
 * 		str = "ACDCDCDAD",最少需要切2次变成3个回文子串，比如"A"、"CDCDC"和"DAD",所以返回2
 * @author zhangy
 *
 */
public class PalindromeMinCut {
	
	/**
	 * 本题是一个经典的动态规划问题，定义动态规划数组dp,dp[i]的含义是子串str[i...len-1]至少需要切割几次，
	 * 才能把str[i...len-1]全部切成回文子串。那么，dp[0]就是最后的结果。
	 * 从右往左依次计算dp[i]的值，i初始为len-1,具体过程如下：
	 * 1.假设j位置处在i 与len -1位置之间(i<=j<len),如果str[i...j]是一个回文串，那么dp[i]的值可能是
	 * dp[j+1] + 1,其含义是在str[i...len -1]上，既然str[i...j]是一个回文串，那么它可以自己作为一个分割的部分
	 * 剩下的部分(即str[j+1...len-1])继续做最经济的切割，而dp[j+1]的含义正好是str[j+1...len-1]的最少回文分割数。
	 * 2.根据步骤1的方式，让j在i到len位置上枚举，那么所有可能情况中的最小值就是dp[j]的值，即dp[i] = Min{dp[j+1]+1
	 * (i<=j<len,且str[i...j]必须是回文串)}。
	 * 3.如何方便快速地判断str[i...j]是否是回文串呢？具体过程如下：
	 * (1):定义一个二维数组boolean[][]p,如果p[i][j]值为true，说明字符串str[i...j]是回文串，否则不是，在计算
	 * dp数组的过程中，希望能够同步、快速计算出矩阵p
	 * (2):p[i][j]如果为true，一定是一下三种情况：
	 * 		a.str[i...j]由一个字符组成
	 * 		b.str[i...j]由两个字符组成且2个字符相等
	 * 		c.str[i+1...j-1]是一个回文串，即dp[i+1][j-1]为true，且str[i] == str[j],即str[i...j]上首尾两个字符相等
	 * (3)在计算dp数组的过程中，位置i是从右向左依次计算的。而对每一个i 来说，又依次从i位置向右枚举所有的位置j(i<=j<len),
	 * 以此来决策出dp[i]的值，所以对p[i][j]来说，p[i+1][j-1]值一定计算过，这就使判断一个子串是否为回文串变得极为方便。
	 * 4.最终返回dp[0]的值。
	 */
	
	public int getMinCut(String str){
		if(str == null || str.equals("")){
			return 0;
		}
		char[] chas = str.toCharArray();
		int len = chas.length;
		int[] dp = new int[len + 1];
		dp[len] = -1;
		boolean[][] p = new boolean[len][len];//构建Boolean数组p，用于快速判断str[i...j]是否是一个回文串
		for(int i = len -1; i >= 0; i--){//从右到左依次遍历
			dp[i] = Integer.MAX_VALUE;
			for(int j = i; j < len; j++){//从左到右依次遍历判断str[i...j]是否是一个回文串
				if(chas[i] == chas[j] && (j - i < 2 || p[i+1][j-1])){//str[i...j]是回文串的三种情况
					p[i][j] = true;//说明str[i...j]是回文串
					dp[i] = Math.min(dp[i], dp[j+1]+1);//取最小切割数
				}
			}
		}
		return dp[0];//返回从str[0...len-1]的最小切割数
	}
	public static void main(String[] args){
		PalindromeMinCut pdmc = new PalindromeMinCut();
		String str = "ACDCDCDAD";
		System.out.println(pdmc.getMinCut(str));
	}
}
