package demo_test;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import demo_test.TreeProblem.Tree;

//使用递归和非递归的方法获取二叉树的高度
public class getHeightOfBinaryTree {
	public class Tree{
		public int value;
		public Tree left;
		public Tree right;
		public Tree(int data){
			this.value = data;
		}
	}
	
	//使用递归的方法获取二叉数的高度
	public int getHeightByRecursive(Tree head){
		if(head != null){
			return Math.max(getHeightByRecursive(head.left), getHeightByRecursive(head.right)) + 1;
		}
		return 0;
	}
	//使用层级遍历的方式来统计二叉树的高度
	public int getHeightByQueue(Tree head){
		if(head == null){
			return 0;
		}
		Queue<Tree> queue = new LinkedList<Tree>();
		queue.add(head);
		int height = 1;
		Tree nLast = null;
		Tree last = head;
		while(!queue.isEmpty()){
			head = queue.remove();
			System.out.print(head.value);
			if(head.left != null){
				queue.add(head.left);
				nLast = head.left;
			}
			if(head.right != null){
				queue.add(head.right);
				nLast = head.right;
			}
			if(head == last && !queue.isEmpty()){
				height++;
				last = nLast;//last记录的是每一层的最后一个节点
			}
		}
		System.out.println();
		return height;
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
		System.out.println("-----------------------------");
		System.out.println(getHeightByRecursive(head));
		System.out.println(getHeightByQueue(head));
	}
}
