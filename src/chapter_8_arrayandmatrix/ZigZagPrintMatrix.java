package chapter_8_arrayandmatrix;
/**
 * "之"字形打印矩阵：给定一个矩阵matrix，按照"之"字形的方式打印这个矩阵，例如：
 * 1     2     3     4
 * 5     6     7     8
 * 9     10    11    12
 * "之"字形打印的结果为：1，    2， 5，      9， 6，3，       4，7，10，    11，8，      12
 * @author zhangy
 *
 */
public class ZigZagPrintMatrix {
	
	/**
	 * 1.上坐标(tR,tC)初始为(0,0),先沿着矩阵第一行移动(tC++),当到达第一行最右边的元素后，再沿着矩阵最后一列移动(tR++)
	 * 2.下坐标(dR,dC)初始为(0,0),先沿着矩阵第一列移动(dR++),当到达第一列最下边的元素时，再沿着矩阵最后一行移动(dC++)
	 * 3.上坐标与下坐标同步移动，每次移动后的上坐标与下坐标的连线就是矩阵中的一条斜线，打印斜线上的元素即可
	 * 4.如果上次斜线是从左下向右上打印的，这次一定是从右上向左下打印，反之亦然。总之，可以把打印的方向用Boolean值表示，每次取反即可
	 * 
	 */
	
	public static void printMatrixZigZag(int[][] matrix){
		int tR = 0;
		int tC = 0;
		int dR = 0;
		int dC = 0;
		int endR = matrix.length -1;
		int endC = matrix[0].length -1;
		boolean fromUp = false;//从上往下，或者从下往上
		while(tR != endR + 1){//只要tR不超过矩阵的行数即可遍历
			printLevel(matrix, tR, tC, dR,dC,fromUp);//根据fromUp的标志，看是打印左下斜线还是打印右上斜线
			tR = tC == endC ? tR + 1 : tR;
			tC = tC == endC ? tC : tC + 1;
			//dR的在printLevel中改变后没有返回来，所以dR还是等于传进去的值
			dC = dR == endR ? dC + 1 : dC;
			dR = dR == endR ? dR  : dR + 1;
			fromUp = !fromUp;
		}
	}

	public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f){
		if(f){
			while(tR != dR + 1){//往左下斜线打印
				System.out.print(m[tR++][tC--]+" ");
			}
		}else{
			while(dR != tR -1){//往右上斜线打印
				System.out.print(m[dR--][dC++]+" ");
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
