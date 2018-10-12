package demo_test;

import java.util.LinkedList;
import java.util.Stack;

import org.junit.Test;

//判断一颗数是否是平衡二叉树：要么是一个空树，要么两个左右两个子树之间的高度差不超过1
public class TreeProblem {
	public class Tree{
		public int value;
		public Tree left;
		public Tree right;
		public Tree(int data){
			this.value = data;
		}
	}
	
	public boolean isBalancedTree(Tree head){
		if(head == null){
			return true;
		}
		boolean[] res = new boolean[1];
		res[0] = true;//初始化为true
		getHeight(head,1,res);
		return res[0];
	}
	//使用递归函数按层遍历
	public int getHeight(Tree head, int level, boolean[] res){
		if(head == null){
			return level;
		}
		int lH = getHeight(head.left,level+1,res);
		if(!res[0]){
			return level;
		}
		int rH = getHeight(head.right,level+1,res);
		if(!res[0]){
			return level;
		}
		if(Math.abs(lH - rH) > 1){
			res[0] = false;
		}
		
		return Math.max(lH, rH);
	}
	public int getHeight2(Tree head){
		if(head == null){
			return 0;
		}
		LinkedList<Tree> queue = new LinkedList<Tree>();
		Tree last = head;
		Tree nLast = null;
		int height = 1;
		queue.add(head);
		while(!queue.isEmpty()){
			head = queue.remove();
			System.out.print(head.value);//按层打印
			if(head.left != null){
				queue.add(head.left);
				nLast = head.left;
			}
			if(head.right != null){
				queue.add(head.right);
				nLast = head.right;
			}
			if(last == head && !queue.isEmpty()){
				height++;
				last = nLast;
			}
		}
		System.out.println();
		return height;
	}
	/**
	 * 判断一个树是否是搜索二叉树:中序遍历是递增的
	 */
	public boolean isBST(Tree head){
		if(head == null){
			return true;
		}
		boolean flag = true;
		int last = Integer.MIN_VALUE;
		if(head.left != null && flag){
			isBST(head.left);
		}
		if(head.value < last){
			flag = false;
		}
		last = head.value;
		if(head.right != null && flag){
			isBST(head.right);
		}
		return flag;
	}
	/**
	 * 获取一个二叉的最近公共父节点
	 */
	public Tree getLastCommonNode(Tree head, Tree node1, Tree node2){
		if(head == null || head == node1 || head == node2){
			return head;
		}
		Tree left = getLastCommonNode(head.left, node1,node2);
		Tree right = getLastCommonNode(head.right,node1,node2);
		if(left != null && right != null){
			return head;
		}
		return left == null ?right : left; 
	}
	/**
	 * 打印二叉树的路径
	 */
	public void printPath(Tree head,LinkedList<Integer> queue){
		if(head == null){
			return ;
		}
//		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(head.value);
		if(head.left == null && head.right == null){
			for(int value : queue){
				System.out.print(value);
			}
			System.out.println();
			queue.removeLast();
		}else{
			printPath(head.left,queue);
			printPath(head.right,queue);
			queue.removeLast();
		}
	}
	/**
	 * 非递归的先序遍历
	 */
	public void preOrderWithStack(Tree head){
		if(head == null){
			return ;
		}
		Stack<Tree> stack = new Stack<Tree>();
		stack.push(head);
		while(!stack.isEmpty()){
			head = stack.pop();
			System.out.print(head.value);
			if(head.right != null){
				stack.push(head.right);
			}
			if(head.left != null){
				stack.push(head.left);
			}
		}
	}
	/**
	 *非递归的中序遍历
	 */
	public void inOrder(Tree head){
		if(head == null){
			return ;
		}
		Stack<Tree> stack = new Stack<Tree>();
		while(!stack.isEmpty() || head != null){
			if(head != null){
				stack.push(head);
				head = head.left;
			}else{
				head = stack.pop();
				System.out.print(head.value);
				head = head.right;
			}
		}
	}
	/**
	 * 二叉数的非递归后续遍历
	 */
	public void posOrder(Tree head){
		if(head == null){
			return ;
		}
		Stack<Tree> stack1 = new Stack<Tree>();
		Stack<Tree> stack2 = new Stack<Tree>();
		stack1.push(head);
		while(!stack1.isEmpty()){
			head = stack1.pop();
			if(head.left != null){
				stack1.push(head.left);
			}
			if(head.right != null){
				stack1.push(head.right);
			}
			stack2.push(head);
		}
		while(!stack2.isEmpty()){
			System.out.print(stack2.pop().value);
		}
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
//		System.out.println(isBalancedTree(head));
		boolean[] res = new boolean[1];
		res[0] = true;
//		System.out.println(getHeight(head,1,res));
//		System.out.println(getHeight2(head));
//		System.out.println(isBST(head));
//		System.out.println(getLastCommonNode(head,head.left,head.right).value);
		LinkedList<Integer> queue = new LinkedList<Integer>();
//		printPath(head,queue);
//		preOrderWithStack(head);
//		inOrder(head);
		posOrder(head);
	}

}
