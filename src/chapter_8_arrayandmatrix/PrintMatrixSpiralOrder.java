package chapter_8_arrayandmatrix;
/**
 * 转圈打印矩阵：给定一个矩阵matrix，请按照转圈的方式打印它。例如：
 * 1  2  3  4
 * 5  6  7  8
 * 9  10 11 12
 * 13 14 15 16
 * 打印的结果为：1，2，3，4，8，12，16，15，14，13，9，5，6，7，11，10
 * @author zhangy
 *
 */
public class PrintMatrixSpiralOrder {
	
	public static void spiralOrderPrint(int[][] matrix){
		int tR = 0;
		int tC = 0;
		int dR = matrix.length -1;
		int dC = matrix[0].length -1;
		while(tR <= dR && tC <= dC){//只要左上角的坐标跑到了右下角坐标的右方或下面就退出循环
			print(matrix, tR++, tC++, dR--, dC--);
		}
	}
	
	//打印最外层一圈的方法(也就是打印其中一层),tR，tC应该是左上角，dR,dC是右下角
	public static void print(int[][] m, int tR, int tC, int dR, int dC){ 
		if(tR == dR){//左上角的横坐标与右下角的横坐标相等，说明当期矩阵只有一行
			for(int i = 0; i <= tC; i++){
				System.out.print(m[tR][i]+" ");
			}
		}else if(tC == dC){//说明当前矩阵只有一列
			for(int i = 0; i <= tR; i++ ){
				System.out.print(m[i][tC]+" ");
			}
		}else{//一般情况
			//定义两个临时变量方便控制遍历过程
			int curR = tR;
			int curC = tC;
			//下面开始转圈
			while(curC != dC){//相当于先遍历第一行
				System.out.print(m[tR][curC]+" ");
				curC++;
			}
			while(curR != dR){//遍历右边最外层的列
				System.out.print(m[curR][curC]+" ");
				curR++;
			}
			while(curC != tC){//相当于从右到左遍历最后一行
				System.out.print(m[curR][curC]+" ");
				curC--;
			}
			while(curR != tR){//相当于从下到上遍历最左边第一列
				System.out.print(m[curR][curC]+" ");
				curR--;
			}
		}
	}
	
	public static void main(String[] args){
		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		spiralOrderPrint(matrix);
	}

}
