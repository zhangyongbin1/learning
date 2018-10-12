package chapter_8_arrayandmatrix;
/**
 * 数组的partition调整：给定一个有序数组arr,调整arr使得这个数组的左半部分没有重复元素，且升序，而不用保证右部分是否有序
 * 例如：arr = [1,2,2,2,3,3,4,5,6,6,7,7,8,8,8,9],调整之后arr=[1,2,3,4,5,6,7,8,9,...].
 * @author zhangy
 *
 */
public class PartitionArray {
	
	public void leftUnique(int[] arr){
		if(arr == null || arr.length < 2){
			return ;
		}
		int u = 0;//表示在arr[0...u]上都是无重复且升序的
		int i = 1;//利用i做从左到右的遍历，在arr[u+1...i]上是不保证没有重复元素且升序的区域
		while(i != arr.length){
			if(arr[i++] != arr[u]){
				swap(arr,++u,i-1);//其实就是把上面arr[i]的值加入到arr[u+1]中
			}
		}
	}
	/**
	 * 补充问题：给定一个数组arr,其中只可能含有0，1，2三个值，请实现arr的排序
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void sort(int[] arr){
		if(arr == null || arr.length < 2){
			return ;
		}
		int left = -1;//arr[0...left]上都是0
		int index = 0;//arr[left+1...index]上都是1
		int right = arr.length;//arr[right...N-1]上都是2
		while(left < right){
			if(arr[index] == 0){
				swap(arr,++left,index);
			}else if(arr[index] == 2){
				swap(arr,index,--right);
			}else{
				index++;
			}
		}
	}
	private static void swap(int[] arr, int i, int j) {
		// TODO Auto-generated method stub
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
