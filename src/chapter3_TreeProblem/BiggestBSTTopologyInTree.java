package chapter3_TreeProblem;
/**
 * 找到二叉树中符合搜索二叉树条件的最大拓扑结构
 * @author zhangy
 *
 */
public class BiggestBSTTopologyInTree {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	public int bstTopoSize(Node head){
		if(head == null){
			return 0;
		}
		//以下都是遍历二叉树每个节点，寻找最大拓扑结构情况
		int max = maxTopo(head,head);//以头节点为头的整个二叉树中满足搜索二叉树条件的最大拓扑结构
		max = Math.max(bstTopoSize(head.left), max);
		max = Math.max(bstTopoSize(head.right), max);
		return max;
	}
	public int maxTopo(Node h, Node n){//以h节点为头节点，n为子节点的二叉树中满足搜索二叉树条件的最大拓扑结构
		if(h != null && n != null && isBSTNode(h, n, n.value)){
			return maxTopo(h, n.left)+ maxTopo(h, n.right) + 1;
		}
		return 0;
	}
	/**
	 * 先考察头节点h的孩子节点，根据孩子节点的值从h开始按照二叉搜索的方式移动，如果最后能移动到同一个孩子节点，说明这个孩子节点可以作为这个拓扑的一部分
	 * 并继续考察这个孩子节点的孩子节点，一直延伸下去
	 * @param h：二叉树头节点
	 * @param n：头节点的孩子节点
	 * @param value
	 * @return
	 */
	public boolean isBSTNode(Node h, Node n, int value){//寻找符合二叉搜索条件的节点
		if(h == null){//如果父节点为null,那么不符合二叉搜索树的条件
			return false;
		}
		if(h == n){//如果在二叉树中递归找到了当前节点h，且其符合二叉搜索树条件即： h == n，
			return true;
		}
		//h是当前二叉树中遍历到的节点，n是需要寻找的节点，value是节点n的值
		return isBSTNode(h.value > value ? h.left : h.right, n, value);//如果当前节点值小于其父节点的值，那么遍历二叉树时应该往左走
	}
	public void printTreeInSpicalWay(Node head){
		System.out.print("Print Tree: ");
		inOrderPrint(head,0,"H",17);
		System.out.println();
	}
	//使用中序遍历二叉树
	public void inOrderPrint(Node head, int height, String to, int len){
		if(head == null){
			return ;
		}
		inOrderPrint(head.left, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM)/2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);//打印拼接后节点的值
		inOrderPrint(head.right, height + 1, "^", len);
	}
	
	public String getSpace(int num){
		String space = " ";
		StringBuffer sb = new StringBuffer("");
		for(int i = 0; i < num; i++){
			sb.append(space);
		}
		return sb.toString();
	}
	public static void main(String[] args){
		BiggestBSTTopologyInTree bbsttit = new BiggestBSTTopologyInTree();
		Node head = bbsttit.new Node(6);
		head.left = bbsttit.new Node(1);
		head.left.left = bbsttit.new Node(0);
		head.left.right = bbsttit.new Node(3);
		head.right = bbsttit.new Node(12);
		head.right.left = bbsttit.new Node(10);
		head.right.left.left = bbsttit.new Node(4);
		head.right.left.right = bbsttit.new Node(14);
		head.right.left.left.left = bbsttit.new Node(2);
		head.right.left.left.right = bbsttit.new Node(5);
		head.right.left.right.left = bbsttit.new Node(11);
		head.right.left.right.right = bbsttit.new Node(15);
		head.right.right = bbsttit.new Node(13);
		head.right.right.left = bbsttit.new Node(20);
		head.right.right.right = bbsttit.new Node(16);
		System.out.print(bbsttit.bstTopoSize(head));
	}

}
