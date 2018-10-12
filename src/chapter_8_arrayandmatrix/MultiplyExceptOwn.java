package chapter_8_arrayandmatrix;
/**
 * 不包含本位置值的累乘数组：给定一个整型数组arr，返回不包含本位置值的累乘数组
 * 例如：arr=[2,3,1,4],返回[12,8,24,6],即除自己外，其他位置上的累乘
 * @author zhangy
 *
 */
public class MultiplyExceptOwn {
	/**
	 * 如果可以使用除法，那么当前的累乘数组结果就是 res[i] = All/arr[i],对于数组有一个0的情况，就是等于0的位置
	 * 的res值设置为All，其他位置都为0，对于0的个数大于1个，那么所有的位置都设置为0即可
	 */
	
	public static int[] product1(int[] arr){
		if(arr == null || arr.length < 2){//因为要除掉当前位置
			return null;
		}
		int count = 0;
		int all = 1;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] != 0){
				all = all * arr[i];
			}else{
				count++;
			}
		}
		int[] res = new int[arr.length];
		for(int i = 0; i < arr.length; i++){
			if(count == 0){
				res[i] = all/arr[i];
			}else if(count == 1){
				if(arr[i] == 0){
					res[i] = all;
				}else{
					res[i] = 0;
				}
			}else{
				res[i] = 0;
			}
		}
		return res;
	}

	public static void printArr(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]+" ");
		}
	}
	public static void main(String[] args){
		int[] arr = {2,3,1,4};
		printArr(product1(arr));
	}
}
