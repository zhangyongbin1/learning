package chapter3_TreeProblem;
/**
 * 比较直观地打印二叉树，得考虑一个32为整数的长度问题。一个32为的整数的长度为11，所以长度为17的空间必然可以放下任何一个32为的整数
 * @author zhangy
 * Binary Tree: 
                                         v6v       
                        v3v       
                                         ^5^       
       H1H       
                   ^-222222222^   
                                                          v7v       
                                         ^4^       
 *
 */
public class PrintBinaryTreeInSpicalWay {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	public static void printTree(Node head){
		System.out.println("Binary Tree: ");
		inOrderPrint(head, 0,"H", 17);
		System.out.println();
	}
	public static void inOrderPrint(Node head, int height, String to, int len){//变相使用二叉树的中序遍历
		if(head == null){
			return ;
		}
		inOrderPrint(head.right, height + 1, "v", len);//新找到二叉树的最右子节点
		String val = to + head.value + to;//将节点中的值与字符串"v"组合，左右各添加一个标记
		int lenM = val.length();//val变量的长度
		int lenL = (len - lenM)/2;//统计左边还剩下多大的空间
		int lenR = len - lenM - lenL;//统计右边还剩下多大的空间
		val = getSpace(lenL) + val + getSpace(lenR);//将拼接好的字符串再拼接上空格以满足len的要求
		System.out.println(getSpace(height * len) + val);//打印拼接后节点的值
		inOrderPrint(head.left, height + 1, "^", len);//递归左子节点
	}
	public static String getSpace(int num){//获取一定数量的" "字符串
		String space = " ";
		StringBuffer sb = new StringBuffer("");
		for(int i = 0; i < num; i++){
			sb.append(space);
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		PrintBinaryTreeInSpicalWay pbtisw = new PrintBinaryTreeInSpicalWay();
		Node head = pbtisw.new Node(1);
		head.left = pbtisw.new Node(-222222222);
		head.right = pbtisw.new Node(3);
		head.left.left = pbtisw.new Node(4);
		head.left.left.right = pbtisw.new Node(7);
		head.right.left = pbtisw.new Node(5);
		head.right.right = pbtisw.new Node(6);
		pbtisw.printTree(head);
		
	}

}
