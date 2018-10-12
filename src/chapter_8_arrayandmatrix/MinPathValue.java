package chapter_8_arrayandmatrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 *求最短通路值：用一个整型矩阵matrix表示一个网络，1代表有路，0代表无路，每一个位置值哟啊不越界，都有
 *上下左右4个方向，求从最左上角到最右下角的最短通路值。例如，matrix为：
 *1    0    1    1    1
 *1    0    1    0    1
 *1    1    1    0    1
 *0    0    0    0    1
 *通路只有一条，由12个1构成，所以返回12
 * @author zhangy
 *
 */
public class MinPathValue {
	/**
	 * 1.开始时生产map矩阵，map[i][j]的含义是从(0,0)位置走到(i,j)位置最短的路径值，然后将左上角位置(0,0)
	 * 的行坐标与列坐标放入行队列rQ,和列队列cQ。
	 * 2.不断从队列弹出一个位置(r,c),然后看这个位置的上下左右四个位置那些在matrix上的值是1，这些都是能走的位置
	 * 3.将那些能走的位置设置好各自在map中的值，即map[r][c]+1.同时将这些位置加入到rQ和cQ中，用队列完成宽度优先遍历
	 * 4.在步骤3中，如果一个位置之前走过，就不要重复走，这个逻辑可以根据一个位置在map中的值来确定，比如map[i][j] != 0.
	 * 就可以知道这个位置之前已经走过
	 * 5.一直重复步骤2～步骤4，直到遇到右下角位置，说明已经找到终点，返回终点在map中的值即可，如果rQ和cQ已经为空都没有
	 * 遇到终点位置，说明不存在这样一条路径，返回0
	 */
	
	public static int getMinPathValue(int[][] matrix){
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix[0][0] !=1
						|| matrix[matrix.length -1][matrix[0].length - 1] != 1){
			return 0;
		}
		int[][] map = new int[matrix.length][matrix[0].length];
		Queue<Integer> rQ = new LinkedList<Integer>();
		Queue<Integer> cQ = new LinkedList<Integer>();
		map[0][0] =1;
		int res = 0;
		rQ.add(0);
		cQ.add(0);
		//使用宽度优先遍历
		while(!rQ.isEmpty()){
			int r = rQ.poll();
			int c = cQ.poll();
			if(r == matrix.length - 1 && c == matrix[0].length -1){//说明走到了最右下角的位置
				return map[r][c];//直接返回map对应位置的值即可
			}
			walkTo(map[r][c],r - 1,c,matrix,map,rQ,cQ);//上
			walkTo(map[r][c],r + 1,c,matrix,map,rQ,cQ);//下
			walkTo(map[r][c],r,c - 1,matrix,map,rQ,cQ);//左
			walkTo(map[r][c],r,c + 1,matrix,map,rQ,cQ);//右
		}
		return res;//如果rQ和cQ已经为空都没有遇到终点位置，说明不存在这样一条路径，返回0
	}
	
	public static void walkTo(int pre, int toR, int toC, int[][] matrix, int[][] map,
							Queue<Integer>rQ, Queue<Integer>cQ){
		if(toR < 0 || toR == matrix.length || toC < 0 || toC == matrix[0].length ||
				matrix[toR][toC] != 1 || map[toR][toC] != 0){//只要角标值无效，且对位置的值不为1，map中对应的值不为0,说明该位置已经走过
			return ;
		}
		map[toR][toC] = pre + 1;
		rQ.add(toR);
		cQ.add(toC);
	}

}
