package chapter_8_arrayandmatrix;
/**
 * 在行列都排好序的矩阵中找到数：给定一个有N × M 的矩阵matrix和一个整数k，matrix的每一行和每一列都是排好序的。实现一个函数，判断K是否在matrix中
 * @author zhangy
 *
 */
public class FindNumInSortedMatrix {
	
	public static boolean ContainsOrNot(int[][] matrix, int k){
		int row = 0;
		int col = matrix[0].length -1;//matrix[row][col]是从矩阵matrix的右上角开始的，也可以从左下角开始
		while(row < matrix.length && col > -1){
			if(matrix[row][col] == k){//如果找到，那么就直接返回true
				return true;
			}else if(matrix[row][col] > k){//如果当前数大于K，因为矩阵的行和列是有序的，所以，k只可能出现在col列的左边
				col--;
			}else{//如果当前数小于K，因为矩阵的行和列是有序的，所以，k只可能出现在下一行中
				row++;
			}
		}
		return false;
	}
	public static void main(String[] args){
		int[][] matrix = {{0,1,2,5},{2,3,4,7},{4,4,4,8},{5,7,7,9}};
		int k = 7;
		boolean flag = ContainsOrNot(matrix,k);
		System.out.println(flag);
	}
}
