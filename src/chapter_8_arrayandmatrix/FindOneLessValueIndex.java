package chapter_8_arrayandmatrix;
/**
 * 在数组中找到一个局部最小的位置：定义局部最小的概念。arr长度为1时，arr[0]是局部最小。arr的长度为N(N>1)时，
 * 如果arr[0] < arr[1],那么arr[0]是局部最小；如果arr[N-1] < arr[N-2],那么arr[N-1]是局部最小；
 * 如果0<i<N-1,既有arr[i]<arr[i-1],又有arr[i]<arr[i+1],那么arr[i]是局部最小。
 * 给定无序数组arr,已经arr中任意两个相邻的数都不相等。写一个函数，只需要返回arr中任意一个局部最小出现的位置即可
 * @author zhangy
 *
 */

public class FindOneLessValueIndex {
	public static int getLessValueIndex(int[] arr){
		if(arr == null || arr.length == 0){
			return -1;//不存在
		}
		if(arr.length == 1 || arr[0] < arr[1]){
			return 0;
		}
		if(arr[arr.length -1] < arr[arr.length - 2]){
			return arr.length - 1;
		}
		//剩下的部分可以使用二分查找
		int left = 1;
		int right = arr.length -2;
		int mid = 0;
		while(left < right){
			mid = (left + right)/2;
			if(arr[mid] > arr[mid - 1]){//说明在arr[left...mid - 1]肯定存在局部最小值？？？
				right = mid -1;
			}else if(arr[mid] > arr[mid + 1]){//说明在arr[mid+1...right]肯定存在局部最小值
				left = mid + 1;
			}else{
				return mid;//说明当前mid就是局部最小值位置
			}
		}
		//直到left == right时循环停止，返回left即可，可见二分查找并不是数组有序时才能使用，只要确定二分两侧的某一侧肯定存在你要找的内容就可以使用二分查找
		return left;
	}
	public static void main(String[] args){
		//int[] arr = {6,2,4,3,5};
		int[] arr = {6,2,3,4,5};
		System.out.println(getLessValueIndex(arr));
	}
}
