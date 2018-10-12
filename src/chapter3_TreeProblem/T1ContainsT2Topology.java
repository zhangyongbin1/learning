package chapter3_TreeProblem;
/**
 * 判断树结构中t1中是否全部包括t2结构：就是同时移动两个数的节点进行比较，同时t1每一次节点构成的二叉树都需要需要遍历到
 * @author zhangy
 *
 */
public class T1ContainsT2Topology {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	
	public boolean contains(Node t1, Node t2){
		if (t2 == null) {//t1肯定包含空子树拓扑结构
			return true;
		}
		if (t1 == null) {//t1为空肯定不包含拓扑结构t2
			return false;
		}
		return check(t1,t2) || contains(t1.left, t2) || contains(t1.right, t2);//先检查以t1头节点为头的整颗二叉树，然后再检查t1的左子树的节点，最后检查t1右子树的节点为头的拓扑情况 
	}
	
	public boolean check(Node head, Node t2){//使用先序遍历的方式，先check一个节点为头节点的数是否包含t2拓扑结构
		if(t2 == null){
			return true;
		}
		if(head == null || head.value != t2.value){//一旦节点值不相等，那么肯定不包含，返回false
			return false;
		}
		return check(head.left, t2.left) && check(head.right, t2.right);//同时向左，向右移动
	}
	
	public static void main(String[] args){
		T1ContainsT2Topology tct = new T1ContainsT2Topology();
		Node head = tct.new Node(1);
		head.left = tct.new Node(2);
		head.right = tct.new Node(3);
		head.left.left = tct.new Node(4);
		head.left.right = tct.new Node(5);
		head.left.left.left = tct.new Node(8);
		head.left.left.right = tct.new Node(9);
		head.left.right.left = tct.new Node(10);
		head.right.left = tct.new Node(6);
		head.right.right = tct.new Node(7);
		
		Node head2 = tct.new Node(2);
		head2.left = tct.new Node(4);
		head2.left.left = tct.new Node(8);
		head2.right = tct.new Node(5);
		boolean flag = tct.contains(head, head2);
		System.out.println("head is containing head2: "+ flag);
	}

}
