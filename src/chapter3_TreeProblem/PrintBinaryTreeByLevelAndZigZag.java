package chapter3_TreeProblem;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 二叉树按层和zigzag方式打印
 * @author zhangy
 *
 */
public class PrintBinaryTreeByLevelAndZigZag {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	public void printBinaryTreeByLevel(Node head){
		if(head == null){
			return ;
		}
		int level = 1;
		Node last = head;//这个变量是为了判断当前从队列弹出的节点是否是每一层的最后一个节点
		Node nLast = null;//这个变量是为了记住当前加入队列的最后一个节点
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		System.out.print("Level "+(level++) + " : ");//第一层的字符串先打印出来
		while(!queue.isEmpty()){
			head = queue.poll();
			System.out.print(head.value + " ");//打印当前节点的值
			if(head.left != null){
				queue.add(head.left);
				nLast = head.left;
			}
			if(head.right != null){
				queue.add(head.right);
				nLast = head.right;
			}
			if(head == last && !queue.isEmpty()){//打印完第一层之后，下面要遍历第二层的节点，此时就需要换行并将第二层的字符串先打印出来
				System.out.print("\nLevel "+(level++)+" : ");
				last = nLast;//如果有有子节点，那么nLast就是右子节点。如果没有右子节点，那么nLast就是左子节点
			}
			
		}
		System.out.println();//所有的节点都打印完了后再换一行
	}
	public void printBinaryTreeByZigZag(Node head){
		if(head == null){
			return ;
		}
		Deque<Node> dq = new LinkedList<Node>();
		Node last = head;
		Node nLast = null;
		int level = 1;
		boolean lr = true;//开始默认 from left to right
		dq.offerFirst(head);//from left to right ,从头部进队列，从尾部弹出来
		printLevelAndOrientation(level++,lr);
		while(!dq.isEmpty()){
			if(lr){//如果是from left to right
				head = dq.pollFirst();//从头部弹出
				if(head.left != null){
					nLast = nLast == null ? head.left : nLast;
					dq.offerLast(head.left);//如果当前节点存在左子节点，那么从尾部压入
				}
				if(head.right != null){
					nLast = nLast == null ? head.right : nLast;
					dq.offerLast(head.right);//如果当前节点存在右子节点，那么从尾部压入
				}
			}else{//如果是from right to left
				head = dq.pollLast();//先从尾部弹出
				if(head.right != null){//如果当前节点存在右子节点
					nLast = nLast == null ? head.right : nLast;
					dq.offerFirst(head.right);//那么从头部先将右子节点压入，与From left to right不同
				}
				if(head.left != null){//如果当前节点的左子节点不为空，那么就头不压入到双端队列中
					nLast = nLast == null ? head.left : nLast;
					dq.offerFirst(head.left);
				}
			}
			System.out.print(head.value + " ");
			if(head == last && !dq.isEmpty()){
				lr = !lr;//逐层改变打印的方向
				last = nLast;
				nLast = null;
				System.out.println();
				printLevelAndOrientation(level++,lr);
			}
		}
		System.out.println();
	}
	public void printLevelAndOrientation(int level, boolean lr){//拼凑需要打印的level和方向的字符串
		System.out.print("Level "+level+" from ");
		System.out.print(lr ? "left to right: " : "right to left: ");
	}
	public static void main(String[] args){
		PrintBinaryTreeByLevelAndZigZag ptblazz = new PrintBinaryTreeByLevelAndZigZag();
		Node head = ptblazz.new Node(1);
		head.left = ptblazz.new Node(2);
		head.left.left = ptblazz.new Node(4);
		head.right = ptblazz.new Node(3);
		head.right.right = ptblazz.new Node(6);
		head.right.left = ptblazz.new Node(5);
		head.right.left.left = ptblazz.new Node(7);
		head.right.left.right = ptblazz.new Node(8);
		ptblazz.printBinaryTreeByLevel(head);
		ptblazz.printBinaryTreeByZigZag(head);
	}

}
