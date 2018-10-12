package chapter_4_recursionanddp;
/**
 * 最小编辑代价：给定两个字符串str1和str2，再给定三个整数ic,dc,和rc，分别代表插入、删除、和替换一个字符的代价，返回将str1编辑成str2的最小代价
 * 例如：str1 = "abc",str2="adc"，从"abc"编辑成"adc"的过程为：将字符‘b’修改为字符‘d’代价最小。所以返回2
 * @author zhangy
 *
 */
public class MinEditCost {
	//使用经典的动态规划方法。复杂度为O(M*N)
	/**
	 * 新建dp[m + 1][n + 1]数组，其中dp[i][j]表示将字符串str1[0...i-1]编辑成str2[0...j-1]的最小代价
	 * 1.dp[0][0]表示将str1空子串编辑成str2空子串的最小代价为0，所以dp[0][0] = 0;
	 * 2.dp数组的第一列即dp[0][j]表示将字符str1[0] = ''编辑成str2[j]的最小代价，所以设置dp[0][j-1] = ic * j,就是插入str1中每个元素
	 * 3.dp数组的第一行即dp[i][0]表示将字符串str1[0...i-1]编辑成str2[0] = ''的最小代价，所以设置dp[i][0] = rc * i，表示删除str2中每个元素
	 * 4.其他位置按照从左到右，再从上到下来计算，dp[i][j]的值只可能来自一下四种情况：
	 * 		(1):str1[0...i-1]可以先编辑成str1[0...i-2],也就是删除字符str1[i-1],然后由str1[0...i-2]编辑成str2[0...j-1],dp[i-1][j]表示
	 * 			str1[0...i-1]编辑成str2[0...j-1]的最小代价，那么dp[i][j]可能等于dc + dp[i-1][j]
	 * 		(2)str1[0...i-1]可以先编辑成str2[0...j-2],然后将str2[0...j-2]插入字符str2[j-1]，编辑成为str2[0...j-1],
	 * 			dp[i][j-1]表示str1[0...i-1]编辑成str2[j-2]的最小代价，那么dp[i][j] = dp[i][j-1] + ic
	 * 		(3)如果str[i-1] != str2[j - 1]。先把str1[0...i-2]编辑成str2[0...j-2]，然后把字符str1[i-1]替换成str2[j-1]。这样str1[0...i-1]
	 * 			就编辑成str2[0...j-1]了。所以dp[i][j] = dp[i-1][j-1] + rc
	 * 		(4)如果str1[i-1] == str2[j-1],dp[i][j] = dp[i-1][j-1]
	 */
	public int getMinCost1(String str1, String str2, int ic, int dc, int rc){
		if(str1 == null || str1.equals("") || str2 == null || str2.equals("")){
			return 0;
		} 
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		int m = ch1.length + 1;
		int n = ch2.length + 1;
		int[][] dp = new int[m][n];
		//第一列
		for(int i = 0; i< m; i++){//
			dp[i][0] = rc * i;//不断从字符串str1中删除字符
		}
		for(int j = 0; j < n; j++){
			dp[0][j] = ic * j;//不断从字符串str1中添加字符
		}
		//dp数组的其他位置
		for(int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				if(ch1[i-1] == ch2[j-1]){
					dp[i][j] = dp[i-1][j-1];//只需将str1[0...i-2]编辑成str2[0....j-2]即可，最后一个字符自然是相等的
				}else{
					dp[i][j] = dp[i-1][j-1] + rc;//不相等那就把str1的字符str1[i-1]变换成str2的str2[j-1]即可
				}
				//否则两个字符串的长度不一样，就是删除和插入的问题了
				dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + ic);//str1的最后一个字符匹配上了str2的倒数第二个字符，所以str2的最后一个字符需要同插入字符来实现
				dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + dc);//str1的倒数第二个字符匹配上了str2的最后一个字符，所以需要将str1的最后一个字符删除来实现str1变成str2
			}
		}
		return dp[m - 1][n -1];
	}
	
	public static void main(String[] args){
		MinEditCost mec = new MinEditCost();
		String str1 = "ab12cd3";
		String str2 = "abcdf";
		int ic = 5;
		int dc = 3;
		int rc = 2;
		System.out.println(mec.getMinCost1(str1, str2, ic, dc, rc));
	}

}
