package chapter_5_stringproblem;
/**
 * 添加最少字符是字符串整体都是回文字符串：给定一个字符串str，如果可以在str的任意位置添加字符，请返回在添加字符最少的情况下，
 * 让str整体都是回文字符串的一种结果
 * 举例：str="ABA".str本身就是回文串，不需要添加字符，所有返回"ABA"
 * 		str="AB".可以在'A'之前添加'B'，使str整体都是回文串，故可以返回"BAB"。也可以在'B'之后添加'A',使str整体都是回文串，
 * 		故也可以返回"ABA".总之，只要添加的字符数最少，只返回一种结果即可。
 * @author zhangy
 *
 */
public class PalindromeString {//回文字符串问题
	/**
	 * 在求解问题之前，我们先来解决下面这个问题，如果可以在str的任意位置添加字符，最少需要添加几个字符可以让str整体都是回文字符串？
	 * 这个问题可以使用动态规划的方法求解。如果str的长度为N，动态规划表是一个N*N的矩阵即为dp[][]。dp[i][j]值的含义代表子串str[i...j]
	 * 最少需要添加几个字符可以使用str[i...j]整体都是回文串。那么，如何求dp[i][j]的值呢？有如下三种情况：
	 * 1.如果字符串str[i...j]只有一个字符，此时dp[i][j] = 0,这是很明显的，如果str[i...j]只有一个字符，那么str[i...j]已经是回文了，
	 * 自然不必添加任何字符。
	 * 2.如果字符串str[i...j]只有两个字符。如果两个字符相等，那么dp[i][j] = 0。比如，如果str[i...j]为"AA",两个字符相等，说明str[i...j]
	 * 已经是回文串，自然不必添加任何字符。如果两个字符不相等，那么dp[i][j] = 1. 比如，如果str[i...j]为"AB",只用添加一个字符就可以令str[i...j]
	 * 变成回文串，所以dp[i][j] = 1
	 * 3.如果字符串str[i...j]多于两个字符。如果str[i] == str[j], 那么dp[i][j] = dp[i+1][j-1]。比如，如果str[i...j]为"A124521A",str[i...j]
	 * 需要添加的字符数与str[i+1..j-1](即"124521")需要添加的字符数相等，因为只要能把"124521"整体变成回文串，然后再左右两头加上字符'A'，就是str[i...j]
	 * 整体变成回文串的结果。如果str[i] != str[j],要让str[i...j]整体变为回文串有两种方法，一种方法是让str[i...j-1]先变成回文串，然后再左边加上字符str[j],
	 * 就是str[i...j]整体变成回文串的结果。另一种方法是让str[i+1...j]先变成回文串，然后再右边加上字符str[i],就是str[i...j]整体变成回文串的结果。两种方法
	 * 中哪个个代价最下就选择哪个，即dp[i][j] = min{dp[i][j-1],dp[i+1][j]} + 1.
	 * 
	 * 既然dp[i][j]值代表子串str[i...j]最少添加几个字符可以使用str[i...j]整体都是回文串，所有根据上面的方法求出整体dp矩阵之后，我们就得到了str中任何以恶搞子串
	 * 添加几个字符后可以变成回文串。
	 */
	//如果可以在str任意位置添加字符，最少需要添加几个字符可以使得str整体都是回文串的dp数组
	public int[][] getdp(char[] ch){
		int [][] dp = new int[ch.length][ch.length];
		for(int j = 1; j < ch.length; j++){
			//这是字符个数小于等于2的情况
			dp[j-1][j] = ch[j-1] == ch[j] ? 0 : 1;
			for(int i = j-2; i > -1; i--){//这是字符大于2的情况
				if(ch[i] == ch[j]){//如果str的第一个字符和最后一个字符相等，那么dp[i][j]的值就等于dp[i+1][j]
					dp[i][j] = dp[i+1][j-1];
				}else{//表示str的第一个字符和最后一个字符不相等
					//那么dp[i][j]的值就等于dp[i+1][j]与dp[i][j-1]中较小的值，然后加上需要添加的一个字符
					dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
				}
			}
		}
		return dp;
	}
	//根据上面获取到的dp数组进行问题的求解
	public String getPalindromeString(String str){
		if(str == null || str.length() < 2){
			return str;
		}
		char[] chas = str.toCharArray();
		int[][] dp = getdp(chas);//先获取dp数组
		//res作为保存新的字符串的字符串数组
		char[] res = new char[chas.length+ dp[0][chas.length - 1]];
		int i = 0;//旧数组的左角标
		int j = chas.length -1;//旧数组的右角标
		int resl = 0;//新数组的左角标
		int resr = res.length -1;//新数组的右角标
		while(i <= j){
			if(chas[i] == chas[j]){
				res[resl++] = chas[i++];
				res[resr--] = chas[j--];
			}else if(dp[i+1][j] > dp[i][j-1]){//先把str[i...j-1]变成回文串，然后再在左边添加str[j]
				//res = str[j] + str[i...j-1]的回文串 + str[j], 不断递归下去
				res[resl++] = chas[j];
				res[resr--] = chas[j--];
			}else{//先把str[i+1...j]变成回文串，然后再在末尾加上str[i]字符
				//res = str[i] + str[i+1...j]的回文串 + str[i]
				res[resl++] = chas[i];
				res[resr--] = chas[i++];//i++要放在后面，否则不行
			}
		}
		return String.valueOf(res);
	}
	
	public static void main(String[] args){
		PalindromeString plds = new PalindromeString();
		String str = "AB";
		System.out.println(plds.getPalindromeString(str));
	}
	/**
	 * 动态规划问题：就是先将小范围的问题满足要求，然后再在这基础上不断扩大范围
	 */
}
