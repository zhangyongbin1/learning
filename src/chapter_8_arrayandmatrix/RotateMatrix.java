package chapter_8_arrayandmatrix;
/**
 * 将正方形矩阵顺时针转动90度：给定一个N×N的矩阵matrix，把这个矩阵调整成顺时针转动90度后的形式
 * 例如：
 * 1   2   3   4
 * 5   6   7   8
 * 9   10  11  12
 * 13  14  15  16
 * 顺时针转动90度后为：
 * 13  9   5   1
 * 14  10  6   2
 * 15  11  7   3
 * 16  12  8   4
 * @author zhangy
 *
 */
public class RotateMatrix {
	
	public static void rotate(int[][] matrix){
		int tR = 0;
		int tC = 0;
		int dR = matrix.length -1;
		int dC = matrix[0].length - 1;
		while(tR <= dR && tC <= dC){
			rotateEdge(matrix, tR++, tC++, dR--, dC--);
		}
	}
	
	public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC){
		int times = dC - tC;//times就是总的组数,
		int temp = 0;
		for(int i = 0; i != times; i++){
			//到底是哪个角标进行 + i, -i的操作，需要根据当前位置的下一个该转动的位置进行确定
			temp = m[tR][tC + i];//因为下一组开始的位置是m[tR][tC + i]
			m[tR][tC + i] = m[dR - i][tC];
			m[dR - i][tC] = m[dR][dC - i];
			m[dR][dC - i] = m[tR + i][dC];
			m[tR + i][dC] = temp;
		}
	}
	public static void printMatrix(int[][] matrix){
		for(int i = 0; i != matrix.length; i++){
			for(int j = 0; j != matrix[0].length; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args){
		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		printMatrix(matrix);
		System.out.println("----------------------------");
		rotate(matrix);
		printMatrix(matrix);
	}
}
