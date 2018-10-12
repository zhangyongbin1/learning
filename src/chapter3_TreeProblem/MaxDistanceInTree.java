package chapter3_TreeProblem;
/**
 * 二叉树节点间的最大距离问题
 * @author zhangy
 *
 */
public class MaxDistanceInTree {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	
	public int maxDistance(Node head){
		int[] record = new int[1];
		return posOrder(head,record);
	}
	/**
	 * 以左子树后序遍历为例说明递归过程： 4(左) ——> 5(右) ——>2(头)
	 * @param head
	 * @param record
	 * @return
	 * 一个以h为头的树上，最大距离只可能来自一下三种情况：
	 * 		1.h的左子树上的最大距离
	 * 		2.h的右子树上的最大距离
	 * 		3.h左子树上离h.left最远的距离 + 1 + h右子树上离h.right最远的距离
	 */
	public int posOrder(Node head, int[] record){
		if(head == null){
			 record[0] = 0;
			 return 0;//这里第一次return时 lMax = 0, head =4
		}
		int lMax = posOrder(head.left, record);//一直遍历到整棵树头节点左子树的最左子节点4，那么当前head = 4,head.left = null
		int maxfromLeft = record[0];//if语句return之后就可以往下走这句语句
		int rMax = posOrder(head.right, record);//这是head = 4，head.right = null，所以会走if return返回到这里
		int maxfromRight = record[0];//if return 之后就继续往下走
		int curNodeMax = maxfromLeft + maxfromRight + 1;//当前节点为头节点的最大距离，可能来自于左子树最远距离+1+右子树最远距离
		record[0] = Math.max(maxfromLeft, maxfromRight) + 1;//记录住当前左右子树中其中较大值的距离
		//返回的情况可能来自三中，最大距离来源于左子树，右子树，或左子树+右子树+1的情况
		return Math.max(Math.max(lMax, rMax), curNodeMax);//这里return 之后就回到 head = 2时，head.left执行完的地方，继续往下执行int maxfromLeft = record[0]这句，
													//当执行到head.right时，这时head.right = 5,重新当前头节点分别去执行其左、右子节点，执行完在返回到head.right执行的地方节点继续往下执行，直到到这里再返回上一层头节点即5的头节点2对应的头节点1
	}
	
	public static void main(String[] args){
		MaxDistanceInTree mdit = new MaxDistanceInTree();
		Node head = mdit.new Node(1);
		head.left = mdit.new Node(2);
		head.right = mdit.new Node(3);
		head.left.left = mdit.new Node(4);
		head.left.right = mdit.new Node(5);
		head.right.left = mdit.new Node(6);
		head.right.right = mdit.new Node(7);
		int maxdistance = mdit.maxDistance(head);
		System.out.println(maxdistance);
	}

}
