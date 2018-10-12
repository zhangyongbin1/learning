package chapter3_TreeProblem;
/**
 * 通过有序数组生成平衡搜索二叉树：搜索二叉树中序遍历的结果就是升序排列的，所以只要找到有序数组的中间值即为树的头节点
 * @author zhangy
 *
 */
public class SortedArrayToBalancedBST {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	public Node generateBSTBinaryTree(int[] arr){
		if(arr == null){
			return null;
		}
		return generate(arr, 0, arr.length -1);
	}
	public Node generate(int[] arr, int start, int end){
		if(start > end){
			return null;
		}
		int mid = (start + end)/2;
		Node head = new Node(arr[mid]);
		head.left = generate(arr, start, mid - 1);
		head.right = generate(arr, mid + 1, end);
		return head;
	}
	//for test
	public void printTree(Node head){
		System.out.println("Binary Tree:");
		inOrderPrint(head, 0, "H", 17);
		System.out.println();
	}
	public void inOrderPrint(Node head, int height, String to, int len){
		if(head == null){
			return ;
		}
		inOrderPrint(head.right, height + 1, "v", 17);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM)/2;
		int lenR = len - lenL - lenM;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len)+val);
		inOrderPrint(head.left, height + 1, "^", 17);
	}
	public String getSpace(int num){
		String space = " ";
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < num; i++){
			sb.append(space);
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		SortedArrayToBalancedBST satbbst = new SortedArrayToBalancedBST();
		int[] arr = {1,2,3,4,5,6,7};
		Node head = satbbst.generateBSTBinaryTree(arr);
		satbbst.printTree(head);
	}

}
