package chapter_8_arrayandmatrix;
/**
 * 自然数数组的排序：给定一个长度为N的整型数组arr,其中有N个互不相等的自然数1～N，请实现arr的排序，但是不要把下标0～N-1
 * 位置上的数通过直接赋值的方式替换成1~N.要求时间复杂度为O(N),额外空间复杂度为O(1)
 * @author zhangy
 *
 */
public class SortNaturalNumberArray {
	/**
	 * 因为是自然数数组，所以只要保证arr[i] = i + 1即可
	 * @param arr
	 */
	public static void sort1(int[] arr){//这种方法只是多使用了一个next变量
		int temp = 0;
		int next = 0;
		for(int i =0; i < arr.length; i++){
			temp = arr[i];
			while(arr[i] != i+1){
				next = arr[temp - 1];
				arr[temp - 1] = temp;
				temp = next;
			}
		}
	}
	
	public static void sort2(int[] arr){
		int temp = 0;
		for(int i = 0; i < arr.length; i++){
			while(arr[i] != i+1){//只要出现arr[i] != i+ 1的情况就不断地进行位置交换
				temp = arr[arr[i] - 1];
				arr[arr[i] -1] = arr[i];
				arr[i] = temp;
			}
		}
	}
	
	public static void main(String[] args){
		int[] arr = {1,4,3,2,5};
		sort1(arr);
		int[] arr2 = {1,4,5,6,2,3};
		sort2(arr2);
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		for(int i = 0; i < arr2.length; i++){
			System.out.print(arr2[i]+" ");
		}
	}

}
