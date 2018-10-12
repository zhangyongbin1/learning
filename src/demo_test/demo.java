package demo_test;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class demo {
	
	public static void main(String[] args){
		int[] arr = {3,1,4,9,6,5,3,7,8};
//		heapSort(arr);
//		maopaoSort(arr);
		xuanzheSort(arr);
		for(int i = 0; i < arr.length; i++){
//			System.out.print(arr[i]);
		}
		LinkedHashMap map = new LinkedHashMap();
		map.put("1", "1111");
		map.put("3", "3333");
		map.put("2", "2222");
		Set<Entry> entrySet = map.entrySet();
		for(Entry e : entrySet){
			System.out.print(e.getKey()+"-----------"+e.getValue());
			System.out.println();
		}
//		System.out.println(Integer.MIN_VALUE);
	}
	public static void maopaoSort(int[] arr){
		for(int i = 0; i < arr.length; i++){
			for(int j = i + 1; j < arr.length; j++){
				if(arr[i] > arr[j]){
					swap(arr,i,j);
				}
			}
		}
	}
	public static void xuanzheSort(int[] arr){
		if(arr == null || arr.length < 1){
			return ;
		}
		int minIndex = 0;
		for(int i = 0; i < arr.length; i++){
			minIndex = i;
			for(int j = i + 1; j < arr.length; j++){
				if(arr[minIndex] > arr[j]){
					minIndex = j;
				}
			}
			if(minIndex != i){
				swap(arr,i,minIndex);
			}
		}
	}
	public static void heapSort(int[] arr){
		if(arr.length < 1 || arr == null){
			return;
		}
		int len = arr.length - 1;
		for(int i = len/2 - 1; i >=0; i--){
			heapIfy(arr,i,len);
		}
		while(len >=0){
			swap(arr,0,len);
			heapIfy(arr,0,len--);
		}
	}
	public static void heapIfy(int[] arr, int index, int heapSize){
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
				swap(arr, largest, index);
			}else{
				break;
			}
			index = largest;
			left = 2 * index + 1;
			right = 2 * index + 2;
		}
	}

	private static void swap(int[] arr, int largest, int index) {
		int temp = arr[largest];
		arr[largest] = arr[index];
		arr[index] = temp;
	}

}
