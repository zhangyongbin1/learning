package chapter_8_arrayandmatrix;
/**
 * 奇数下标都是奇数或者偶数下标都是偶数：给顶一个长度不小于2的数组arr,实现一个函数调整arr,要么让所有的偶数下标都是偶数，要么让所有的
 * 奇数下标都是奇数。要求：如果arr的长度为N，函数要求时间复杂度为O(N),额外空间复杂度为O(1)
 * @author zhangy
 *
 */
public class EvenInEvenOddInOdd {
	
	/**
	 * 1.设置变量even，表示目前arr最左边的偶数下标，初始时even=0
	 * 2.设置变量odd,表示目前arr最左边的奇数下标，初始是odd = 1
	 * 3.不断检查arr 的最后一个数，即arr[N-1].如果arr[N-1]是偶数，交换arr[N-1]和arr[even],然后令even = even + 2;
	 * 如果arr[N-1]是奇数，交换arr[N-1]和arr[odd],然后令odd = odd + 2
	 * 4.如果even或odd大于或者等于N，过程停止
	 */
	public static void modifyEvenAndOdd(int[] arr){
		if(arr == null || arr.length < 2 || (arr.length %2 != 0)){
			return ;
		}
		int even = 0;
		int odd = 1;
		int end = arr.length -1;
		while(even <= end && odd <= end){//如果even或odd大于或者等于N，过程停止
			if(arr[end] % 2 == 0){
				swap(arr,even,end);
				even = even + 2;
			}else{
				swap(arr,odd,end);
				odd = odd + 2;
			}
		}
	}
	
	public static void swap(int[] arr, int i, int j){
		int temp = 0;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args){
		int[] arr = {1,2,4,3,5,6};
		modifyEvenAndOdd(arr);
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
	}

}
