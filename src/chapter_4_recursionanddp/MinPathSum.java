package chapter_4_recursionanddp;
/**
 * 矩阵的最小路径和问题：动态规划法
 * @author zhangy
 *给定一个矩阵m，从左上角开始每次只能向下或者向右走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和。返回所有路径中最小的路径和
 */
public class MinPathSum {
	
	public int minPathSum1(int[][] m){
		if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){
			return 0;
		}
		int row = m.length;//二维数组的行数
		int col = m[0].length;//二维数组的列数
		int[][] dp = new int[row][col];
		dp[0][0] = m[0][0];//dp矩阵的第一个值与m矩阵的第一个值相同
		for(int j = 1; j < col; j++){
			dp[0][j] = dp[0][j-1] + m[0][j]; 
		}
		for(int i = 1; i < row; i++){
			dp[i][0] =dp[i-1][0] + m[i][0];
		} 
		
		for(int i = 1; i < row; i++){
			for(int j = 1; j < col; j++){
				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + m[i][j];
			}
		}
		return dp[row-1][col - 1];
	}
	//时间复杂度为O(M*N)，空间复杂度为O(m矩阵的行与列的较小值);//但这种方法不适合用于需要记录最小路径经过的位置的值，因为会被覆盖
	public int minPathSum2(int[][] m){
		if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){
			return 0;
		}
		int more = Math.max(m.length, m[0].length);//行数与列数较大的为more
		int less = Math.min(m.length, m[0].length);//行数与列数较小的为less
		boolean rowmore = more == less ? true : false;
		int[] arr = new int[less];//辅助数组的长度仅为行数与列数中的最小值
		arr[0] = m[0][0];
		for(int i = 1; i < less; i++){//将arr数组的值设置为dp[][]数组的第一行(列)的值，即m数组第一行(列)每个位置的值相加的对应的结果
			 arr[i] = arr[i-1] + (rowmore ? m[0][i] : m[i][0]);//要么是m数组第一行或者第一列的每个位置的值相加的结果，如果rowmore为true，则是第行，如果rowmore为false则是
		}
		for(int i = 1; i < more; i++){//开始更新arr数组的值
			arr[0] = arr[0] + (rowmore ? m[i][0] : m[0][i]);//rowmore为true是为第计算(0,0)-->(1,0),rowmore为false时说明是计算(0,0)-->(0,1)的值
			for(int j = 1; j < less; j++){//开始更新每一行其它位置的值，j = 1,2,3...
				arr[j] = Math.min(arr[j -1], arr[j]) + (rowmore ? m[i][j] : m[j][i]);//arr[j -1], arr[j]可能为当前位置的左边，上面或者是上面，左面
			}
		}
		return arr[less - 1];
	}
	
	public static void main(String[] args){
		int[][] m = {{1,3,5,9},{8,1,3,4},{5,0,6,1},{8,8,4,0}};
		MinPathSum mps = new MinPathSum();
		System.out.println(mps.minPathSum1(m));
		System.out.println(mps.minPathSum2(m));
	}

}
