package demo_test;

import org.junit.Test;

import demo_test.getHeightOfBinaryTree.Tree;

//莫里斯遍历
public class MorrisTraverse {
	public class Tree{
		public int value;
		public Tree left;
		public Tree right;
		public Tree(int data){
			this.value = data;
		}
	}
	
	public void morrisTraverse(Tree head){
		if(head == null){
			return ;
		}
		Tree cur1 = head;
		Tree cur2 = null;
		while(cur1 != null){
			cur2 = cur1.left;
			if(cur2 != null){
				while(cur2.right != null && cur2.right != cur1){
					cur2 = cur2.right;
				}
				if(cur2.right == null){
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				}else{
					cur2.right = null;
				}
			}
			System.out.print(cur1.value);
			cur1 = cur1.right;
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
		morrisTraverse(head);
	}
}
