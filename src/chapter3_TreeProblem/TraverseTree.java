package chapter3_TreeProblem;

import java.util.Stack;

/**
 * 使用递归和非递归方式实现二叉树的先序、中序和后序遍历
 * 
 * @author zhangy
 *
 */
public class TraverseTree {
	public class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 使用递归方法的先序遍历
	public void preOrderRecur(Node head) {
		if(head == null){
			return ;
		}
		System.out.print(head.value + " ");
		preOrderRecur(head.left);
		preOrderRecur(head.right);
	}

	// 使用递归方法的中序遍历
	public void InOrderRecur(Node head) {
		if(head == null){
			return ;
		}
		InOrderRecur(head.left);
		System.out.print(head.value + " ");
		InOrderRecur(head.right);
	}

	// 使用递归方法的后序遍历
	public void posOrderRecur(Node head) {
		if(head == null){
			return ;
		}
		posOrderRecur(head.left);
		posOrderRecur(head.right);
		System.out.print(head.value + " ");
	}
	/**
	 * 用递归方法解决的问题都能用非递归的方法实现，这是因为递归方法无非就是利用函数栈来保存信息，如果自己申请的数据结构来代替函数栈，可以实现相同的功能
	 */

	//使用非递归的方法遍历二叉树
	public void preOrderUnRecur(Node head){
		System.out.print("Pre Order UnRecur: ");
		if(head != null){
			Stack<Node> stack = new Stack<Node>();//申请一个辅助栈结构
			stack.push(head);//先将头节点压入栈中
			while(!stack.isEmpty()){//如果栈不为空
				head = stack.pop();//先将头节点弹出
				System.out.print(head.value + " ");//同时打印节点的值
				if(head.right != null){//判断头节点是否有右子节点，因为stack是逆序的
					stack.push(head.right);
				}
				if(head.left != null){//判断头节点是否有左子节点
					stack.push(head.left);
				}
			}
		}
		System.out.println();
	}
	//使用费递归方法对二叉树进行中序遍历
	public void inOrderUnRecur(Node head){
		System.out.print("In Order UnRecur: ");
		if(head != null){
			Stack<Node> stack = new Stack<Node>();
			while(!stack.isEmpty() || head != null){
				if(head != null){//将head节点的左子树不断压入栈中
					stack.push(head);
					head = head.left;
				}else{
					head = stack.pop();//将最后一个左子节点弹出
					System.out.print(head.value + " ");//并打印节点的值
					head = head.right;//然后将弹出的左子节点的右子节点压入栈中
				}
			}
		}
		System.out.println();
	}
	//使用非递归的方法对二叉树进行后序遍历
	public void posOrderUnReucr(Node head){
		System.out.print("Pos Order UnRecur: ");
		if(head != null){
			Stack<Node> s1 = new Stack<Node>();
			Stack<Node> s2 = new Stack<Node>();//用于存放后序遍历的结果
			s1.push(head);
			while(!s1.isEmpty()){
				head = s1.pop();//将头节点从s1中弹出
				s2.push(head);//然后压入s2节点中
				if(head.left != null){//判断当前节点head是否存放左子节点
					s1.push(head.left);//存在左子节点则将其压入到s1中
				}
				if(head.right != null){//判断当钱节点head是否存在右子节点
					s1.push(head.right);//存在则将其压入到s1中
				}
			}
			while(!s2.isEmpty()){//上面循环结束之后，栈s2中从栈顶到栈底的顺序就是后序遍历二叉树的结果
				System.out.print(s2.pop().value+" ");
			}
		}
		System.out.println();
	}
	public void posOrderUnRecur2(Node h){
		System.out.print("pos order UnRecur2： ");
		if( h != null){
			Stack<Node> stack = new Stack<Node>();
			stack.push(h);
			Node c = null;
			while(!stack.isEmpty()){
				c = stack.peek();//每次令c = 栈顶节点
				if(c.left != null && h != c.left && h != c.right){//栈顶节点的左子节点不为null
					stack.push(c.left);
				}else if(c.right != null && h != c.right){//栈顶节点的右子节点不为null
					stack.push(c.right);
				}else{//栈顶节点的左右子树都为null,
					System.out.print(stack.pop().value + " ");//那么弹出当前栈顶节点并打印值
					h = c;//同时将最近一次弹出的栈顶节点变量h更为c
				}
			}
		}
		System.out.println();
	}
	public static void main(String[] args){
		TraverseTree tt = new TraverseTree();
		Node head = tt.new Node(1);
		head.left = tt.new Node(2);
		head.right = tt.new Node(3);
		head.left.left = tt.new Node(4);
		head.left.right = tt.new Node(5);
		head.right.left = tt.new Node(6);
		head.right.right = tt.new Node(7);
		tt.preOrderRecur(head);
		System.out.println();
		tt.preOrderUnRecur(head);
		tt.InOrderRecur(head);
		System.out.println();
		tt.inOrderUnRecur(head);
		tt.posOrderRecur(head);
		System.out.println();
		tt.posOrderUnReucr(head);
		tt.posOrderUnRecur2(head);
	}
}
