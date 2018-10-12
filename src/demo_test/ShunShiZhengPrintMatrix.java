package demo_test;

import org.junit.Test;

/**
 * 顺时针从外到内打印矩阵
 * @author zhangy
 *
 */
public class ShunShiZhengPrintMatrix {
	
	public void printMatrix(int[][] matrix,int tR, int tC, int dR, int dC){
		if(tR == dR){//说明当前只有一行
			for(int i = 0; i <= dC; i++){
				System.out.print(matrix[tR][i]);
			}
		}else if(tC == dC){//说明当前只有一列
			for(int i = 0; i <= dR; i++){
				System.out.print(matrix[i][tC]);
			}
		}else{//一个正常的二维矩阵，开始转圈打印
			//定两个变量方便遍历
			int curR = tR;
			int curC = tC;
			while(curC != dC){
				System.out.print(matrix[curR][curC++]);
			}
			while(curR != dR){
				System.out.print(matrix[curR++][curC]);
			}
			while(curC != tC){
				System.out.print(matrix[curR][curC--]);
			}
			while(curR != tR){
				System.out.print(matrix[curR--][curC]);
			}
		}
	}
	
	@Test
	public void test(){
		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		int tR =0;
		int tC = 0;
		int dR = matrix.length-1;
		int dC = matrix[0].length-1;
		while(tR <= dR && tC <= dC){
			printMatrix(matrix,tR++,tC++,dR--,dC--);
			//1，2，3，4，8，12，16，15，14，13，9，5，6，7，11，10
		}
		
	}

}
