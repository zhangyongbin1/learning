package chapter2_ListProblem;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 将一颗搜索二叉树转换成一个有序的双向链表
 * @author zhangy
 *
 */
public class TreeConvertToDoubleList {
	public class Node{//相当于是一颗二叉树，也相当于是一个双向链表
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	
	
	public Node convert1(Node head){
		Queue<Node> queue = new LinkedList<Node>();
		inOrderToQueue(queue, head);
		head = queue.poll();//双向链表的头节点
		Node pre = head;//为了记录当前节点cur的前一个节点
		Node cur = null;//为了记录当前节点
		pre.left = null;//第一个节点的左节点为null
		while(!queue.isEmpty()){//将队列中的节点构建成双向链表
			cur = queue.poll();
			pre.right = cur;
			cur.left = pre;
			pre = cur;//更新pre节点
		}
		pre.right = null;//此时的pre节点为双向节点的最后一个节点，所以其右子节点为null
		return head;
	}
	
	public void inOrderToQueue(Queue<Node> queue, Node head){//使用中序遍历将搜索二叉树节点放入进队列queue中
		if(head == null){
			return ;
		}
		inOrderToQueue(queue,head.left);
		queue.offer(head);
		inOrderToQueue(queue,head.right);
	}
	public Node convert2(Node head){
		if(head == null){
			return null;
		}
		Node last = process(head);//处理完之后，返回是特殊双向链表的最后一个节点
		head = last.right;//特殊双向链表的最后一个节点的right指针指向的是链表的头节点
		last.right = null;//将特殊双向链表正常化
		return head;//返回头节点
	}
	public Node process(Node head){
		if(head == null){
			return null;
		}
		Node leftE = process(head.left);//处理左子树
		Node rightE = process(head.right);//处理右子树
		Node leftS = leftE != null ? leftE.right : null;//寻找左子树部分的开始位置
		Node rightS = rightE != null ? rightE.right : null;//寻找右子树部分的开始位置
		if(leftE != null && rightE != null){//将左，中，右部分继续拼接，并且返回最后一个节点，且最后这个节点的right指向第一个节点
			leftE.right = head;
			head.left = leftE;
			head.right = rightS;
			rightS.left = head;
			rightE.right = leftS;
			return rightE;
		}else if(leftE != null){
			leftE.right = head;
			head.left = leftE;
			head.right = leftS;
			return head;
		}else if(rightE != null){
			head.right = rightS;
			rightS.left = head;
			rightE.right = head;
			return rightE;
		}else{
			head.right = head;
			return head;
		}
	}
	public void printBSTInOrder(Node head) {
		System.out.print("BST in-order: ");
		if (head != null) {
			printTree(head);
		}
		System.out.println();
	}
	public void printTree(Node head){
		if(head == null){
			return ;
		}
		printTree(head.left);
		System.out.print(head.value + " ");
		printTree(head.right);
	}
	public  void printDoubleLinkedList(Node head) {//打印双向链表
		System.out.print("Double Linked List: ");
		Node end = null;
		while (head != null) {
			System.out.print(head.value + " ");
			end = head;
			head = head.right;
		}
		System.out.print("| ");
		while (end != null) {
			System.out.print(end.value + " ");
			end = end.left;
		}
		System.out.println();
	}
	public static void main(String[] args){
		TreeConvertToDoubleList tctdl = new TreeConvertToDoubleList();
		Node head = tctdl.new Node(4);
		head.left = tctdl.new Node(2);
		head.right = tctdl.new Node(6);
		head.left.left = tctdl.new Node(1);
		head.left.right = tctdl.new Node(3);
		head.right.left = tctdl.new Node(5);
		head.right.right = tctdl.new Node(7);
		
		tctdl.printTree(head);
		//head = tctdl.convert1(head);
		head = tctdl.convert1(head);
		System.out.println();
		tctdl.printDoubleLinkedList(head);
	}

}
