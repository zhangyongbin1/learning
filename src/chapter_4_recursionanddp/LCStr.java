package chapter_4_recursionanddp;
/**
 * 最长公共子串问题：例如 str1 = "1AB2345CD"  str2 = "12345EF" 返回 "2345"
 * @author zhangy
 *
 */
public class LCStr {
	//使用经典的动态规划解法可以做到时间复杂度为O(M*N) 空间复杂度为O(M*N)
	/**
	 * 生成行为str1.length = M，列为str2.length = N的二维数组： dp[M][N].
	 * dp[i][j]的含义就是：必须以字符str1[i] 和 str2[j]当作最后一个字符的情况下，公共子串最长能有多长。下面是怎么求解dp[i][j]的值：
	 * 1.矩阵dp的第一列，即dp[M][0]表示 对于某个位置(i,0)，如果有str1(i) == str2(0),那么令dp[i][0] = 1,否则就令dp[i][0] = 0
	 * 2.矩阵dp的第一行，即dp[0][N]表示对于某个位置(0,j)，如果str1(0) == str2(j),那么就令dp[0][j] = 1,否则就令dp[0][j] = 0
	 * 3.其他位置按照从左到右，再从上到下来计算，dp[i][j]的值只可能有两种情况
	 * 		(1):如果str1[i] != str2[j],说明在必须把str1[i]和str2[j]当作公共子串最后一个字符是不可能的，令dp[i][j] = 0
	 * 		(2):如果str1[i] == str2[j],说明str1[i]和str2[j]当作公共子串最后一个字符，从最后一个字符向左能扩多长的长度呢？
	 * 			就是dp[i-1][j-1]的值，所以令dp[i][j] = dp[i-1][j-1] + 1
	 */
	
	//获取dp数组
	public int[][] getdp(char[] ch1, char[] ch2){
		int m = ch1.length;
		int n = ch2.length;
		int[][] dp = new int[m][n];
		//第一列
		for(int i = 0; i < m; i++){
			if(ch1[i] == ch2[0]){
				dp[i][0] = 1;
			}
		}
		//第一行
		for(int j = 0; j < n; j++){
			if(ch1[0] == ch2[j]){
				dp[0][j] = 1;
			}
		}
		//dp[i][j]的其他位置
		for(int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				if(ch1[i] == ch2[j]){//如果可以以ch1[i] 和 ch2[j]作为结尾的公共子串的情况下下
					dp[i][j] = dp[i-1][j-1] + 1;//dp[i][j]的值
				}
			}
		}
		return dp;
	}
	
	public String getLCStr(String str1, String str2){
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		int[][] dp = getdp(ch1, ch2);
		//遍历dp数组，寻找其最大值
		int max = 0;//最大值变量
		int endIndex = 0;//最大值所处于的位置角标
		for(int i = 0; i < ch1.length; i++){
			for(int j = 0; j < ch2.length; j++){
				if(dp[i][j] > max){
					endIndex = i;
					max = dp[i][j];
				}
			}
		}
		return str1.substring(endIndex - max +1, endIndex + 1);//截取子串并返回，不包含endIndex + 1这个字符
	}
	//空间复杂度可以减小至O(1)
	public String getLCStr2(String str1, String str2){
		if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
			return "";
		}
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		int row = 0;//斜线开始位置的行
		int col = ch2.length - 1;//斜线开始位置的列
		int max = 0;//记录最大长度
		int end = 0;//最大长度更新时，记录子串的结尾位置
		while(row < ch1.length){
			int i = row;
			int j = col;
			int len = 0;
			//从(i,j)开始向右下方遍历
			while(i < ch1.length && j < ch2.length){
				if(ch1[i] != ch2[j]){
					len = 0;
				}else{
					len ++;
				}
				//记录最大值，以及结束字符的位置
				if(len > max){
					end = i;
					max = len;
				}
				//移动到斜线上的另一个元素
				i++;
				j++;
			}
			//移动到下一条斜线
			if(col > 0){//斜线开始位置的列先向左移动，也就是先遍历玩右上部分，
				col--;
			}else{//列移动到最左之后，行向下移动//再遍历右下部分
				row++;
			}
		}
		return str1.substring(end - max + 1, end + 1 );
	}
	
	public static void main(String[] args){
		LCStr lcstr = new LCStr();
		String str1 = "abcde";
		String str2 = "bebcd";
		System.out.println(lcstr.getLCStr2(str1, str2));
	}

}
