package chapter3_TreeProblem;
/**
 * 搜索二叉树的性质：左子树所有节点的值肯定小于head节点的值，右子树上所有的节点的值肯定都大于head节点的值。并且可以head节点的没有左子树，或者没哟右子树
 * @author zhangy
 *
 */
public class PosArrayToBST {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	
	public boolean isPosArry(int[] arr){
		if(arr == null || arr.length == 0){
			return false;
		}
		return isPosBST(arr, 0, arr.length - 1);
	}
	
	public boolean isPosBST(int[] arr, int start, int end){
		if(arr == null){
			return true;
		}
		int less = -1;
		int more = end;
		for(int i = start; i < end; i++){//这个for循环是将小于head节点的值和大于head节点的值分开
			if(arr[end] > arr[i]){
				less = i;//less代表的是小于head值的所有值中最后一个值在数组arr中的位置
			}else{
				more = more == end ? i : more;//more代表大于head值的所有值中最后一个值在数组中的位置
			}
			if(less == -1 || more == end){//less = -1 表示head头节点的左子树全为空，more = end 代表右子树全为空
				return isPosBST(arr, start, end -1);//继续递归其中一个字符去判断其是否符合搜索二叉树的性质
			}
			if(less != more -1){//如果左子树的最后一个节点在数组中的位置与右子树的第一个节点在数组中的位置不是是紧挨着的
				return false;//说明满足搜索二叉树的性质，直接返回false退出
			}
		}
		return isPosBST(arr, start, less) && isPosBST(arr, more, end -1);
	}
	
	public Node posArrayToBSTImpl(int[] arr){
		if(arr == null){
			return null;
		}
		return posArrayToBST(arr, 0 , arr.length -1);
	}
	
	public Node posArrayToBST(int[] arr, int start, int end){
		if(start > end){
			return null;
		}
		Node head = new Node(arr[end]);
		int less = -1;
		int more = end;
		for(int i = start; i < end; i++){
			if(arr[end] > arr[i]){
				less = i;
			}else{
				more = more == end ? i : more; 
			}
		}
		head.left = posArrayToBST(arr, start, less);
		head.right = posArrayToBST(arr, more, end - 1);
		return head;
	}

}
