package chapter_8_arrayandmatrix;
/**
 * 子矩阵的最大累加和问题：给定一个矩阵matrix，其中的值有正、有负、有0，返回子矩阵的最大累加和，
 * 例如，矩阵matrix为：
 * 			-90   48   78
 * 			64    -40  64
 * 			-81   -7   66
 * 其中，最大累加和的子矩阵为：
 * 48    78
 * -40   64
 * -7    66
 * 所以返回累加和209
 * @author zhangy
 *
 */
public class SubMatrixMaxSum {
	/**
	 * 如果一个矩阵一共有k行且限定必须含有k行元素的情况下，我们只要把矩阵中每一列的k个元素累加成一个累加数组，
	 * 然后求出这个数组的最大累加和，这个最大累加和就是必须含有k行元素的子矩阵的最大累加和
	 */
	
	public static int getSubMatrixSum(int[][] matrix){
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int[] s = null;
		int cur = 0;
		for(int i = 0; i < matrix.length; i++){//外层循环控制从矩阵的那一行开始往下遍历
			s = new int[matrix[0].length];//累加和数组
			for(int j = i; j < matrix.length; j++){//内层循环控制从开始行到最后一行的遍历
				cur = 0;
				for(int k = 0; k < s.length;k++){//这个循环控制每一行上求累加和数组的最大值
					s[k] += matrix[j][k];
					cur += s[k];
					max = Math.max(max, cur);//求得每一行累加和数组的最大值
					cur = cur < 0 ? 0 : cur;//如果cur为负，那么就从下一个元素开始累加
				}
			}
		}
		return max;
	}
	
	public static void main(String[] args){
		int[][] matrix = {{-90,48,78},{64,-40,64},{-81,-7,66}};
		System.out.println(getSubMatrixSum(matrix));
	}

}
