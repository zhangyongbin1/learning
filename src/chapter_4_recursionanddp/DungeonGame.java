package chapter_4_recursionanddp;
/**
 * 龙与地下城游戏问题：给定一张二维数组map。含义是一张地图，例如，如下矩阵：
 * 		-2   -3   3
 *      -5   -10  1
 *       0    30  -5
 *       游戏的规则如下：
 *       1.骑士从左上角出发，每次只能向右或向下走，最后到达右下角见到公主。
 *       2.地图中每个位置的值代表骑士要遭遇的事情，如果是负数，说明此处有怪兽，要让其实损失血量。如果是非负数，代表此处有血瓶，能让骑士回血
 *       3.骑士从左上角到右下角的过程中，走到任何一个位置时，血量都不能少于1.
 *       为了保证骑士能见到公主，初始血量至少是多少，根据map,返回初始血量
 * @author zhangy
 *
 */
public class DungeonGame {
	//经典动态规划方法
	/**
	 * 建立和map数组一样大的dp数组，其中dp[i][j]的含义是：如果骑士要走上位置(i,j),并且从该位置选一条最优的路径，最优走到右下角
	 * 骑士起码应该具备的血量。例如map[2][2] = -5, 那么dp[2][2] = 6
	 * 那么其他位置(i,j)的值dp[i][j]怎么求？
	 * 		骑士还要面临向下还是向右的选择，dp[i][j+1]表示骑士选择向右走并最终到达右下角的血量要求。同理，dp[i+1][j]是向下走
	 * 的要求。如果骑士决定向右走，那么骑士在当前位置加完血或者扣完血之后的血量只要等于dp[i][j+1]即可。那么骑士在加血或者扣血之前
	 * 的血量要求(也就是没有踏上位置(i,j)之前的血量要求)，就是dp[i][j+1]-map[i][j]。同时骑士血量随时不少于1，所以向右的要求为：
	 * max{dp[i][j+1]-map[i][j],1},同理如果骑士决定向下走的要求为max{dp[i+1][j],1},骑士可以有这两种选择，当然要选最优的一条
	 * ，所以就是取向右，向下的要求的较小值即可
	 * 
	 */
	public int getMinHP(int[][] map){
		if(map == null || map.length == 0 || map[0] == null || map[0].length == 0){
			return 0;
		}
		int row = map.length;
		int col = map[0].length;
		int[][] dp = new int[row--][col--];//new数组的时候还是使用数组的长度，然后--之后row和col就变成了角标索引
		dp[row][col] = map[row][col] > 0 ? 1 : -map[row][col] + 1;//计算dp矩阵右下角的值
		//开始计算最后一行的值，即骑士选择向右走的情况
		for(int j = col-1; j >= 0; j--){
			dp[row][j] = Math.max(dp[row][j+1] - map[row][j], 1);
		}
		//开始计算从上往下走的情况
		int right = 0;
		int down = 0;
		for(int i = row -1; i >=0; i--){//从倒数第二行开始往上
			dp[i][col] = Math.max(dp[i + 1][col] - map[i][col], 1);//行上的最后一个位置
			//往右选择的情况
			for(int j = col - 1; j >= 0; j--){//行上往左的其他位置
				right = Math.max(dp[i][j + 1] - map[i][j], 1);
				down = Math.max(dp[i + 1][j] - map[i][j], 1);
				dp[i][j] = Math.min(right, down);
			}
		}
		return dp[0][0];//最后返回左上角的值即可
	}
	
	public static void main(String[] args){
		DungeonGame dg = new DungeonGame();
		int[][] map = {{-2,-3,3},{-5,-10,1},{0,30,-5}};
		System.out.println(dg.getMinHP(map));
	}

}
