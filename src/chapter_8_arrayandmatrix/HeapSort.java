package chapter_8_arrayandmatrix;
/**
 * 堆排序算法
 * @author zhangy
 *
 */
public class HeapSort {
	
	public static void heapSort(int[] arr){
		int len = arr.length - 1;
		for(int i = len/2 - 1; i >=0; i--){//构造堆，只需要扫描一半元素
			heapify(arr,i,len);
		}
		while(len >= 0){
			swap(arr,0,len--);//堆顶与最后一个元素进行交换，并且长度减一
			heapify(arr,0,len);//重新调整剩下元素的大根堆
		}
	}
	
	public static void heapify(int[] arr, int index, int heapSize){
		int left = index * 2 + 1;
		int right = index * 2 + 2;
		int largest = index;
		while(left < heapSize){
			if(arr[left] > arr[largest]){
				largest = left;
			}
			if(right < heapSize && arr[right] > arr[largest]){
				largest = right;
			}
			if(largest != index){
				swap(arr,largest,index);
			}else{
				break;
			}
			index = largest;
			left = index * 2 + 1;
			right = index * 2 + 1;
		}
	}

	private static void swap(int[] arr, int i, int j) {
		// TODO Auto-generated method stub
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args){
		int[] arr = {2,1,6,4,5,3,2,1};
		heapSort(arr);
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]+" ");
		}
	}

}
