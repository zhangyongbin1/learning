package chapter3_TreeProblem;

/**
 * 判断一个数是否为平衡二叉树，平衡二叉树的性质为：要么是一颗空树，要么任何一个节点的左右子树高度差的绝对值不超过1
 * @author zhangy
 *
 */
public class IsBalancedTree {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	
	public Boolean iSBalanceBinaryTree(Node head){
		boolean[] res = new boolean[1];
		res[0] = true;//初始值设置设置为true
		if(head == null){//说明二叉树为一颗空树
			res[0] = true;//说明空树是一颗平衡二叉树
		}
		getHeight(head, 1, res);//判断当前二叉树是否是一颗平衡二叉树
		return res[0];//返回getHeight方法里给res赋的值
	}
	public int getHeight(Node head, int level, boolean[] res){//使用后序遍历获取二叉树的高度即集合res
		if(head == null){//如果当前节点为null
			return level;//返回当前节点的所在的层数
		}
		int lH = getHeight(head.left, level + 1, res);//先遍历左子树上的左子节点，并且没遍历一次高度加1
		if(!res[0]){//如果一当前节点head为头的二叉树不符合平衡二叉树的条件
			return level;//那么直接返回当前节点所在的层数
		}
		int rH = getHeight(head.right, level + 1, res);//继续变量当前节点的右子节点
		if(!res[0]){//如果不满足平衡二叉树的条件
			return level;//那么也直接返回当前节点所在的层数
		}
		if(Math.abs(lH - rH) > 1){//如果当前节点的head的左右子节点的的高度差大于1
			res[0] = false;//那么说明以当前节点head为头的二叉的不满足平衡二叉树的条件，所以直接返回false
		}
		
		return Math.max(lH, rH);//最后返回层二叉树的高度
	}
	public static void main(String[] args) {
		IsBalancedTree bt = new IsBalancedTree();
		Node head = bt.new Node(1);
		head.left = bt.new Node(2);
		head.right = bt.new Node(3);
		head.left.left = bt.new Node(4);
		head.left.right = bt.new Node(5);
		head.right.left = bt.new Node(6);
		head.right.right = bt.new Node(7);
		System.out.println(bt.iSBalanceBinaryTree(head));
		boolean[] res = new boolean[1];
		res[0] = true;
		System.out.println(bt.getHeight(head,1,res));

	}

}
