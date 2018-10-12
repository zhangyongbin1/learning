package chapter3_TreeProblem;

import java.util.LinkedList;
import java.util.List;


/**
 * 给定一个整数N，如果N < 1，代表空树结构，否则代表中序遍历的结果为{1,2,3,...,N}.请返回可能的二叉树结构有多少？
 * @author zhangy
 * 
 * 分析：如果中序遍历有序且无重复值，则二叉树必为搜索二叉树。假设num(a)代表a个节点的搜索二叉树有多少种可能，再加上序列为{1,...,i,...,N},
 * 如果以1作为头节点，1不可能有左子树，故以1作为头节点有多少中可能的结构，完全取决于1的右子树有多少中可能结构，1的右子树有N-1个节点，所以有num(N-1)中可能。
 * 如果以i作为头节点，i的左子树有i-1个节点，所以可能的结构有num(i-1),右子树有N-i个节点，所以有num(N-i)种可能。故以i为头节点的可能结构有num(i-1)*num(N-i)种
 * 因此把从1到N分别作为头节点时，所有可能的结构加起来就是答案，可以利用动态规划来加速计算的过程，从而做到O(N2)的时间复杂度
 *
 */
public class UniqueBST {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	//使用动态规划法加速计算过程
	public int numTrees(int n){
		if(n < 2){
			return 1;
		}
		int[] num = new int[n + 1];
		num[0] = 1;
		for(int i = 1; i < n + 1; i++){
			for(int j = 1; j < i + 1; j++){
				num[i] += num[j-1] * num[i - j];
			}
		}
		return num[n];
	}
	
	public List<Node> generateTrees(int n){
		return generate(1, n);
	}
	public List<Node> generate(int start, int end){
		List<Node> res = new LinkedList<Node>();
		if(start > end){
			res.add(null);
		}
		Node head = null;
		for(int i = start; i < end + 1; i++){
			head = new Node(i);
			List<Node> lSubs = generate(start, i -1);
			List<Node> rSubs = generate(i + 1, end);
			for(Node l : lSubs){
				for(Node r : rSubs){
					head.left = l;
					head.right = r;
					res.add(cloneTree(head));
				}
			}
		}
		return res;
	}
	public Node cloneTree(Node head){
		if(head == null){
			return null;
		}
		Node res = new Node(head.value);
		res.left = cloneTree(head.left);
		res.right = cloneTree(head.right);
		return res;
	}
	
	// for test -- print tree
		public  void printTree(Node head) {
			System.out.println("Binary Tree:");
			printInOrder(head, 0, "H", 17);
			System.out.println();
		}

		public  void printInOrder(Node head, int height, String to, int len) {
			if (head == null) {
				return;
			}
			printInOrder(head.right, height + 1, "v", len);
			String val = to + head.value + to;
			int lenM = val.length();
			int lenL = (len - lenM) / 2;
			int lenR = len - lenM - lenL;
			val = getSpace(lenL) + val + getSpace(lenR);
			System.out.println(getSpace(height * len) + val);
			printInOrder(head.left, height + 1, "^", len);
		}

		public  String getSpace(int num) {
			String space = " ";
			StringBuffer buf = new StringBuffer("");
			for (int i = 0; i < num; i++) {
				buf.append(space);
			}
			return buf.toString();
		}

		public static void main(String[] args) {
			UniqueBST uBST = new UniqueBST();
			int n = 4;
			System.out.println(uBST.numTrees(n));
			List<Node> res = uBST.generateTrees(n);
			for (Node node : res) {
				uBST.printTree(node);
			}
		}

}
