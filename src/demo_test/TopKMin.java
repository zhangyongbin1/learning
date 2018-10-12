package demo_test;
/**
 * 从一个无序的数组中找最小的k个数，不能使用排序算法
 * @author zhangy
 *
 */
public class TopKMin {
	
	public static void printArray(int[] matrix){
		for(int i = 0; i < matrix.length; i++){
				System.out.print(matrix[i]+" ");
		}
	}
	public static void main(String[] args) {
		int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
		// sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
		printArray(TopMinK(arr, 10));

	}
	public static int[] TopMinK(int[] arr, int k){
		int[] heap = new int[k];
		for(int i = 0; i < k; i++){
			createMinHeap(heap,i,arr[i]);
		}
		for(int i = k; i < arr.length; i++){
			if(arr[i] > heap[0]){
				heap[0] = arr[i];
				heapIfy(heap,0,k);
			}
		}
		return heap;
	}
	public static void createMinHeap(int[] arr, int index, int value){
		arr[index] = value;
		while(index != 0){
			int parent = (index - 1)/2;
			if(arr[parent] > arr[index]){
				swap(arr,parent,index);
				index = parent;
			}else{
				break;
			}
		}
	}
	
	public static void heapIfy(int[] arr, int parent, int heapsize){
		int left = 2 * parent + 1;
		int right = 2 * parent + 2;
		int minest = parent;
		while(left < heapsize){
			if(arr[left] < arr[minest]){
				minest = left;
			}
			if(right < heapsize && arr[right] < arr[minest]){
				minest = right;
			}
			if(minest != parent){
				swap(arr,minest,parent);
			}else{
				break;
			}
			parent = minest;
			left = 2 * parent + 1;
			right = 2 * parent + 2;
		}
	}

	private static void swap(int[] arr, int i, int j) {
		// TODO Auto-generated method stub
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	

}
