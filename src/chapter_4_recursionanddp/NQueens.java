package chapter_4_recursionanddp;
/**
 * N皇后问题：指在N×N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，也不在同一条斜线上。给定一个整数n，返回n皇后的摆法有多少种？？
 * 例如：n = 1,返回1；n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0；n = 8,返回92
 * @author zhangy
 *
 */
public class NQueens {
	//暴力递归的方法
	public int num1(int n){
		if(n < 1){
			return 0;
		}
		int[] record = new int[n];
		return process1(0, record, n);
	}
	public int process1(int i, int[] record, int n){//递归函数，按行进行递归
		if( i == n){//这是递归函数的出口，说明此时已经递归到最后一行了，肯定只要一种排放方法
			return 1;
		}
		int res = 0;//记录总共有多少种摆放的方法
		for(int j = 0; j < n; j++){//对每列进递归
			if(isValid(record, i , j)){//先判断是否同一列和斜线有皇后
				record[i] = j;//记录当前行的第j列摆放了皇后
				res += process1(i + 1, record, n);//换行继续递归
			} 
		}
		return res;//返回统计的总方法数
	}
	public boolean isValid(int[] record, int i, int j){
		for(int k = 0; k < i; k++){//k值的范围：0<k<i
			if(record[k] == j || Math.abs(record[k] -j) == Math.abs(i - k)){//说明当前位置(i,j)的同一列或斜线上有皇后
				 return false;
			}
		}
		return true;
	}
	//因为本方法中位运算的载体是int型变量，所以该方法只能算1～32皇后问题
	//如果想计算更多的皇后问题，需使用包含更多位的变量
	public  int num2(int n) {
		if (n < 1 || n > 32) {
			return 0;
		}
		//八皇后问题的upperLim == 00000000000000000000000011111111
	   //32皇后的问题的upperLim== 11111111111111111111111111111111
		int upperLim = n == 32 ? -1 : (1 << n) - 1;
		return process2(upperLim, 0, 0, 0);
	}

	public  int process2(int upperLim, int colLim, int leftDiaLim,
			int rightDiaLim) {
		if (colLim == upperLim) {//colLim表示递归到上一行为止，在那些列上已经放置了皇后，1代表已经放置，0代表没有放置
			return 1;
		}
		int pos = 0;
		int mostRightOne = 0;//代表在pos中，最右边的1是在什么位置。然后从右到左依次筛选出pos中选择的位置进行递归尝试
		//变量pos代表当期行在colLim,leftDiaLim和rightDiaLim这三个状态的影响下。还有那些位置可供选择的，1代表能选择，0代表不能选择
		pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
		int res = 0;
		while (pos != 0) {
			//拷贝pos最后边为1的bit，其余bit置0，也就是取得可以放置皇后的最右边的列
			mostRightOne = pos & (~pos + 1);
			//将pos最右边为1的bit清零，也就是为获取下一次的最右可用列使用做准备，程序将回溯到这个位置继续试探
			pos = pos - mostRightOne;
			/**
			 * colLim | mostRightOne 表示将当前列置1，表示记录这次皇后放置的列
			 * (leftDiaLim | mostRightOne) << 1 标记当前皇后左边相邻的列不允许下一个皇后放置
			 * (rightDiaLim | mostRightOne) >>> 1 标记当前皇后右边的相邻的列不允许下一个皇后放置
			 */
			res += process2(upperLim, colLim | mostRightOne,
					(leftDiaLim | mostRightOne) << 1,
					(rightDiaLim | mostRightOne) >>> 1);
		}
		return res;
	}
	public static void main(String[] args) {
		NQueens nq = new NQueens();
		int n = 16;
		long start = System.currentTimeMillis();
		//System.out.println(nq.num1(n));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");
		
		//14772512
		//cost time: 17107ms
		start = System.currentTimeMillis();
		System.out.println(nq.num2(n));
		end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");
	}
}
