package demo_test;

public class PartitionArray {
	
	public static void partition(int[] arr, int pivot){
		int small = -1;
		int big = arr.length;
		int index = 0;
		while(index != big){
			if(arr[index] < pivot){
				swap(arr, ++small, index++);
			}else if(arr[index] == pivot){
				index++;
			}else{
				swap(arr, --big, index);
			}
		}
	}
	
	public static void main(String[] args){
		int[] arr = {3,4,5,2,3,1,6};
		partition(arr,2);
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		// TODO Auto-generated method stub
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
