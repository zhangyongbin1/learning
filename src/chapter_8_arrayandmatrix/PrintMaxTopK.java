package chapter_8_arrayandmatrix;
/**
 * 打印N个数组整体最大的Top K：有N个长度不一的数组，所有的数组都是有序的，请从大到小打印这N个数组整体最大的前K个数。
 * 例如：输入含有N行元素的二维数组可以代表N个一维数组
 *219，405，538，845，971
 *148，558
 *52，99，348，691
 *再输入整数k=5,则打印：
 *Top5:971,845,691,558,538
 * @author zhangy
 *
 */
public class PrintMaxTopK {
	/**
	 * 1.构建一个大小为N的大根堆heap,建堆的过程就是把每一个数组的最后一个值，也就是该数组的最大值，依次加入到堆里，
	 * 这个过程是建堆时的调整过程(heapInsert).
	 * 2.建好堆之后，此时heap堆定的元素是所有数组的最大值中最大的那个，打印堆顶元素
	 * 3.假设堆顶元素来自a数组的i位置，那么接下来就把堆顶的前一个数(a[i-1])放在heap的头部，也就是用a[i-1]替换
	 * 原本的堆顶，然后从堆的头部开始调整堆，使其重新变为大根堆(heapify)过程
	 * 4.这样每次都可以得到一个堆顶元素max,在打印完成后都经历步骤3的调整过程。整体打印k次，就是从大到小的全部Top K
	 * 5.在重复步骤3的过程中，如果max来自的那个数组(仍假设是a数组)已经没有元素。也就是说，max已经是a[0],再往左没有数
	 * 了，那么就把heap中最后一个元素放在heap头部的位置，然后把heap的大小减1(heapSize - 1),最后依然是从堆的头部开始
	 * 调整堆，使其重新变为大根堆
	 * 6.直到打印了k个数，过程结束
	 */
	
	public static void printTopK(int[][] matrix, int topK){
		int heapSize = matrix.length;
		HeapNode[] heap = new HeapNode[heapSize];
		for(int i = 0; i != heapSize; i++){
			int index = matrix[i].length - 1;
			heap[i] = new HeapNode(matrix[i][index],i,index);
			heapInsert(heap,i);
		}
		System.out.println("TOP "+ topK + " : ");
		for(int i = 0; i != topK;i++){
			if(heapSize == 0){
				break;
			}
			System.out.print(heap[0].value+" ");
			if(heap[0].index != 0){
				heap[0].value = matrix[heap[0].arrNum][--heap[0].index];
			}else{
				swap(heap,0,--heapSize);
			}
			heapify(heap,0,heapSize);
		}
	}
	
	public static void heapInsert(HeapNode[] heap, int index){//建堆过程
		while(index != 0){
			int parent = (index - 1) /2;
			if(heap[parent].value < heap[index].value){
				swap(heap,parent,index);
				index = parent;
			}else{
				break;
			}
		}
	}
	
	public static void heapify(HeapNode[] heap, int index, int heapSize){//堆排序的调整过程
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		int largest = index;
		while(left < heapSize){
			if(heap[left].value > heap[index].value){
				largest = left;
			}
			if(right < heapSize && heap[right].value > heap[largest].value){
				largest = right;
			}
			if(largest != index){
				swap(heap,largest,index);
			}else{
				break;
			}
			index = largest;
			left = 2 * index + 1;
			right = 2 * index + 2;
		}
	}
	
	public static void swap(HeapNode[] arr, int i, int j){
		int temp = arr[i].value;
		arr[i].value = arr[j].value;
		arr[j].value = temp;
	}
	
	public  static class HeapNode{
		public int value;//值是什么
		public int arrNum;//来自哪个数组
		public int index;//来自数组的那个位置
		public HeapNode(int value, int arrNum,int index){
			this.value = value;
			this.arrNum = arrNum;
			this.index = index;
		}
	}
	
	public static void main(String[] args){
		int[][] matrix = {{219,405,538,845,971},{148,558},{52,99,348,691}};
		int topK = 5;
		printTopK(matrix,topK);
	}

}
