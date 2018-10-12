package demo_test;

import org.junit.Test;

/**
 * 排序算法
 * @author zhangy
 *
 */
public class SortedProblems {

	public void maopaoSort(int[] arr){
		if(arr == null || arr.length < 1){
			return ;
		}
		for(int i = 0; i < arr.length; i++){
			for(int j = i+1; j < arr.length; j++){
				if(arr[i] > arr[j] ){
					swap(arr, i, j);
				}
			}
		}
	}
	/**
	 * 选择排序
	 * @param arr
	 */
	public void xuanzheSort(int[] arr){
		if(arr == null || arr.length < 1){
			return ;
		}
		for(int i = 0; i < arr.length; i++){
			int minIndex = i;
			for(int j = i + 1; j < arr.length; j++){
				if(arr[minIndex] > arr[j]){
					minIndex = j;
				}
			}
			if(minIndex != i){
				swap(arr, minIndex, i);
			}
		}
		
	}
	/**
	 * 归并排序
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	public void guipingSort(int[] arr){
		if(arr == null || arr.length < 1){
			return ;
		}
		guipingFunction(arr,0,arr.length-1);
	}
	public void guipingFunction(int[] arr, int left, int right){
		if(left == right){
			return ;
		}
		int mid = (left + right)/2;
		guipingFunction(arr, left, mid);
		guipingFunction(arr,mid + 1, right);
		merge(arr, left, mid, right);
		return ;
	}

	private void merge(int[] arr, int left, int mid, int right) {
		// TODO Auto-generated method stub
		int i = left;
		int j = mid + 1;
		int[] help = new int[right - left + 1];
		int k = 0;
		while(i <= mid && j <= right){
			if(arr[i] < arr[j]){
				help[k++] = arr[i++];
			}else{
				help[k++] = arr[j++];
			}
		}
		for( ; i < mid + 1 || j < right + 1; i++,j++){
			help[k++] = i > mid ? arr[j] : arr[i];
		}
		for(int m = 0; m < help.length; m++){
			arr[left++] = help[m];//这里arr使用的left角标
		}
	}
	/**
	 * 插入排序
	 * @param arr
	 * @param i
	 * @param j
	 */
	public void charuSort(int[] arr){
		if(arr == null || arr.length < 1){
			return ;
		}
		for(int i = 1; i < arr.length; i++){
			if(arr[i - 1] > arr[i]){
				int temp = arr[i];
				int j = i - 1;
				while(j >= 0 && temp < arr[j]){
					arr[j+1] = arr[j];
					j--;
				}
				arr[++j] = temp;
			}
		}
	}
	/**
	 * 快速排序
	 * @param arr
	 * @param i
	 * @param j
	 */
	public void kuaishuSort(int[] arr, int left, int right){
		if(left >= right || arr == null || arr.length < 1){
			return ;
		}
	    int i = left;
	    int j = right;
	    int pivot = (left + right)/2;
	    while(i <= j){
	    	while(arr[i] < arr[pivot]){
	    		i++;
	    	}
	    	while(arr[j] > pivot){
	    		j--;
	    	}
	    	if(i <= j){
	    		swap(arr,i,j);
	    		i++;
	    		j--;
	    	}else{
	    		i++;
	    	}
	    }
	    kuaishuSort(arr,left,j);
	    kuaishuSort(arr,i,right);
	}
	/**
	 * 堆排序
	 * @param arr
	 * @param i
	 * @param j
	 */
	public void duiSort(int[] arr){
		if(arr == null || arr.length < 1){
			return ;
		}
		int len = arr.length -1;
		for(int i = len/2-1; i>=0; i--){
			heapIfy(arr,i,len);
		}
		while(len >=0){
			swap(arr,0,len);
			heapIfy(arr,0,len--);
		}
	}
	private void heapIfy(int[] arr, int index, int heapSize) {
		// TODO Auto-generated method stub
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		int largest = index;
		while(left < heapSize){
			if(arr[left] > arr[largest]){
				largest = left;
			}
			if(right < heapSize && arr[right] > arr[largest]){
				largest = right;
			}
			if(largest != index){
				swap(arr,index,largest);
			}else{
				break;
			}
			index = largest;
			left = 2 * index + 1;
			right = 2 * index + 2;
		}
	}
	private void swap(int[] arr, int i, int j) {
		// TODO Auto-generated method stub
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public void printArr(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]);
		}
		System.out.println();
	}
	@Test
	public void test(){
		int[] arr = {4,8,2,2,4,1,6,7,9,0};
//		maopaoSort(arr);
//		xuanzheSort(arr);
//		guipingSort(arr);
//		charuSort(arr);
//		kuaishuSort(arr,0,arr.length-1);
		duiSort(arr);
		printArr(arr);
	}
}
