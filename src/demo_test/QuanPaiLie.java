package demo_test;
/**
 * 使用递归法进行全排列计算
 * @author zhangy
 *
 */
public class QuanPaiLie {
	public static void main(String[] args){
		int[] arr = {1,2,3};
		function(arr,0);
	}
	
	public static void function(int[] arr, int start){
		if(start == arr.length){
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i]);
			}
			System.out.println();
		}else{
			for(int i = start; i < arr.length;i++){
				swap(arr,start,i);
				function(arr,start+1);
				swap(arr,start,i);
			}
		}
	}
	public static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
