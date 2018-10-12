package demo_test;

import java.util.Stack;

import org.junit.Test;

public class MirrorBinaryTree {
	public class Tree{
		public int value;
		public Tree left;
		public Tree right;
		public Tree(int data){
			this.value = data;
		}
	}
	
	public Tree MirrorOfTree(Tree head){
		if(head == null){
			return null;
		}
		if(head.left == null && head.right == null){
			return head;
		}
		Stack<Tree> stack = new Stack<Tree>();
		Tree cur = head;
		while(!stack.isEmpty() || cur != null){
			while(cur != null){//用中序进行两个节点之间的交换，注意交换节点时是将其子节点也交换过来了
				Tree temp = cur.left;
				cur.left = cur.right;
				cur.right = temp;
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			cur = cur.right;
		}
		return head;
	}
	
	@Test
	public void test(){
		Tree head = new Tree(1);
		head.left = new Tree(2);
		head.right = new Tree(3);
		head.left.left = new Tree(4);
		head.left.right = new Tree(5);
		head.right.left = new Tree(6);
		head.right.right = new Tree(7);
		preOrder(head);
		System.out.println("-----------------------------");
		Tree newHead = MirrorOfTree(head);
		preOrder(newHead);
	}
	
	public void preOrder(Tree head){
		if(head == null){
			return ;
		}
		System.out.print(head.value);
		preOrder(head.left);
		preOrder(head.right);
	}

}
