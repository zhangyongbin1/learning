package chapter_4_recursionanddp;
/**
 * 排成一条线的纸牌博弈问题：给定一个整数数组arr,代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌。玩家A和玩家B都是绝顶聪明。请返回最后获胜的分数
 * @author zhangy
 *
 */
public class CardsInLine {
	//暴力递归的方法
	/**
	 * 定义递归函数f(i,j),表示如果arr[i...j]这个排列上的纸牌被绝顶聪明的人先拿，最终能获得什么分数。定义递归函数s(i,j),表示如果arr[i...j]
	 * 这个排列上的纸牌被决定聪明的人后拿，最终能获得什么分数。
	 * 	首先来分析f(i,j),具体过程如下：
	 * 	1.如果i == j(即arr[i...j])上只剩一张纸牌。当然会被先拿纸牌的人拿走，所以返回arr[i]
	 * 	2.如果i != j。当前拿纸牌的人有两种选择，要么拿走arr[i],要么拿走arr[j]。如果拿走arr[i]，那么排列将剩下arr[i+1...j]。对于当前玩家来说，
	 * 	面对arr[i+1...j]排列的纸牌，他成了后拿的人，所以后续他能获得的分数为s(i+1,j)。如果拿走arr[j]，那么排列将剩下arr[i...j-1]。对于当前玩家
	 * 	来说，面对arr[i...j-1]排列的纸牌，他成了后拿的人，所以后续他能获得的分数为s(i,j-1)。作为绝顶聪明的人，必然会在两种决策中选最优的。所以返回max{arr[i]+s(i+1,j),arr[j]+s(i,j-1)}.
	 * 
	 * 	然后分析s(i,j)，具体过程如下：
	 * 	1.如果i == j(即arr[i...j]上只剩下一种牌)，作为后拿纸牌的人必然什么也得不到，返回0。
	 * 	2.如果 i != j.根据函数s的定义，玩家的对手会先拿牌。对手要么拿走arr[i]，要么拿走arr[j]。如果对手拿走arr[i]，那么排列将剩下arr[i+1...j],然后轮到玩家先拿。
	 * 	如果对手拿走arr[j],那么排列将剩下arr[i...j-1]，然后轮到玩家先拿。对手也是绝顶聪明的人，所以必然会把最差的情况留给玩家。所以返回min{f(i+1,j),f(i,j-1)}
	 */

	public int win1(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	}
	public int f(int[] arr, int i, int j){
		if(i == j){//说明数组中只剩下一张牌，
			return arr[i];//玩家作为先拿的人，只能拿走这一张牌了
		}
		return Math.max(arr[i] + s(arr, i -1, j), arr[j] + s(arr,i,j-1));//如果玩家先拿arr[i],那么对于剩下的arr[i-1...j]张牌就是后拿者
	}
	public int s(int[] arr, int i, int j){
		if(i == j){//说明当前数组中只有一张牌
			return 0;//作为后拿牌的玩家只能没有牌可拿
		}
		return Math.min(f(arr, i-1, j), f(arr, i, j-1));//作为后拿的玩家，对于先拿的玩家拿走牌之后的数列就是先拿的玩家
	}
	
	//动态规划方法
	/**
	 * 如果arr长度为N，生成两个大小为N*N的矩阵f和s,f[i][j]表示函数f(i,j)的返回值，s[i][j]表示函数s(i,j)的返回值。规定一下两个矩阵的计算方向即可
	 */
	public int win2(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		int[][] f = new int[arr.length ][arr.length ];//实际上相当于保存f函数的值
		int[][] s = new int[arr.length][arr.length];//实际上相当于保存s函数的值
		for(int j = 0; j < arr.length; j++){
			f[j][j] = arr[j];
			for(int i = j -1; i >=0; i--){
				f[i][j] = Math.max(arr[i] + s[i+1][j], arr[j] + s[i][j-1]);
				s[i][j] = Math.min(f[i + 1][j], f[i][j-1]);
			}
		}
		return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
	}
}
