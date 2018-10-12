package demo_test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 使用递归的方法打印二叉树的所有路径
 * @author zhangy
 *
 */
public class PrintTreePath {
	public class Tree{
		public int value;
		public Tree left;
		public Tree right;
		public Tree(int data){
			this.value = data;
		}
	}
	public void printTreePath(Tree head, int n){
		//n应该是最长路径的的长度
		int[] arr = new int[n];
		function(head,arr,0);
	}
	public void function(Tree head, int[] path, int pathLen){
		if(head == null){
			return ;
		}
		path[pathLen++] = head.value;
		if(head.left == null && head.right == null){
			printArray(path);
			System.out.println("pathSUm:"+pathSum(path));
		}else{
			function(head.left,path,pathLen);
			function(head.right,path,pathLen);
		}
	}
	
	public void printArray(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]+ " ");
		}
		System.out.println();
	}
	public int pathSum(int[] arr){
		int sum = 0;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
		}
		return sum;
	}
	
	public void printTreePath2(Tree head){
		//n应该是最长路径的的长度
//		int[] arr = new int[n];
		LinkedList<Integer> list = new LinkedList<Integer>();
		function2(head,list);
	}
	public void function2(Tree head, LinkedList<Integer> list){
		if(head == null){
			return ;
		}
		list.add(head.value);
		if(head.left == null && head.right == null){
			printArray2(list);
			System.out.println("pathSUm:"+pathSum2(list));
			//每一次返回的上一层递归的时候都要把最后一次加入到队列中的数据给删除了，以免被重复使用
			//如果使用数组的就不需要，因为返回上一层是数组默认就是当时的赋值情况，而不记录已执行完的层加入的值
			list.removeLast();
		}else{
			function2(head.left,list);
			function2(head.right,list);
			list.removeLast();
		}
	}
	
	public void printArray2(LinkedList<Integer> list){
		for(int i = 0; i < list.size(); i++){
			System.out.print(list.get(i)+ " ");
		}
		System.out.println();
	}
	public int pathSum2(LinkedList<Integer> list){
		int sum = 0;
		for(int i = 0; i < list.size(); i++){
			sum += list.get(i);
		}
		return sum;
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
		head.right.right.left = new Tree(8);
		printTreePath2(head);
	}

}
