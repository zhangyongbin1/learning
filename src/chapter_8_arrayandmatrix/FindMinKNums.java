package chapter_8_arrayandmatrix;
/**
 * 找到无序数组中最小的k个数：给定一个无序的整型数组arr，找到其中最小的k个数。 要求：不能使用先排序的方法，复杂度不满足要求。
 * 复杂度：O(Nlogk)
 * @author zhangy
 *
 */
public class FindMinKNums {
	/**
	 * 一直维护一个有k个数的大根堆，这个堆代表目前选出的k个最小数，在堆里的k个元素中堆顶的元素是最小的k个数里最大的那个。
	 * 接下来遍历整个数组，遍历的过程中看当前数是否比堆顶元素小，如果是，就把堆顶的元素替换成当前的数，然后从堆顶的位置调整
	 * 整个堆，让替换操作后堆的最大元素继续处在堆顶的位置，如果不是，则不进行任何操作，继续遍历下一个数；在遍历完成后，堆中的
	 * k个数就是所有数组中最小的k个数
	 */
	
	public static int[] getMinKNumByHeap(int[] arr, int k){
		if(k < 1 || k > arr.length){
			return arr; 
		}
		int[] heap = new int[k];
		for(int i = 0; i < k; i++){
			heapInsert(heap, arr[i], i);
		}
		for(int i = k; i < arr.length; i++){
			if(arr[i] < heap[0]){
				heap[0] = arr[i];
				heapVerify(heap, 0, k);
			}
		}
		return heap;
	}
	
	//进行建最大堆(大根堆)的操作
	public static void heapInsert(int[] arr, int value, int index){
		arr[index] = value;
		while(index != 0){
			int parent = (index - 1)/2;
			if(arr[parent] < arr[index]){
				swap(arr, parent, index);
				index = parent;//继续往前寻找，即找parent的parent元素进行比较
			}else{
				break;
			}
		}
	}
	
	//进行大根堆的调整
	public static void heapVerify(int[] arr, int index, int heapSize){
		int left = index * 2 + 1;//arr[index]为父节点，则arr[left]为左子节点
		int right = index * 2 + 2;//arr[right]为右子节点
		int largest = index;
		while(left < heapSize){
			if(arr[left] > arr[largest]){//判断父节点与左子节点的大小
				largest = left;
			}
			if(right < heapSize && arr[right] > arr[largest]){//判断上一步之后的父节点的右子节点与父节点的大小
				largest = right;
			}
			if(largest != index){//如果当前的largest不是最开始的index,说明父节点的值不是最大，所以需要交换
				swap(arr, largest, index);
			}else{
				break;
			}
			//继续玩下进行遍历
			index = largest;
			left = index * 2 + 1;
			right = index * 2 + 2;
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static void printArray(int[] matrix){
		for(int i = 0; i < matrix.length; i++){
				System.out.print(matrix[i]+" ");
		}
	}
	public static void main(String[] args) {
		int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
		// sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
		printArray(getMinKNumByHeap(arr, 10));

	}
}
