package chapter3_TreeProblem;


/**
 * 找到二叉树中的最大搜索二叉树：给定一个二叉树的头节点head，已经其中所有 节点值都不一样，找到含有节点最多的搜索二叉子树，并返回这颗子树的头节点
 * @author zhangy
 * 最大搜索二叉子树的情况：
 * 		1.如果来自node左子树上的最大搜索二叉子树是以node.left为头的；来自node右子树上的最大搜索二叉子树是以node.right为头的；node左子树
 * 上的最大搜索二叉子树的最大值小于node.value;node右子树上的最大搜索二叉子树的最小值大于node.value.那么以节点node为头的整颗树都是二叉树。
 * 		2.如果不满足第一种情况，说明以node为头的树整体不能连成搜索二叉树。这种情况下，以node为头的树上的最大搜索二叉子树是来自node的左子树
 * 上的的最大搜索二叉子树和来自node的右子树上的最大搜索二叉子树之间。节点数较多的那个
 */
public class BiggestSubBSTInTree {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	public Node biggestSubBST(Node head){
		int[] record = new int[3];
		return posOrder(head, record);
	}
	
	public Node posOrder(Node head, int[] record){//record数组是一个全局变量
		if(head == null){
			record[0] = 0;
			record[1] = Integer.MAX_VALUE;//最大值  (左最小)
			record[2] = Integer.MIN_VALUE;//最小值   (右最大)
			return null;
		}
		int value = head.value;
		Node left = head.left;
		Node right = head.right;
		Node lBST = posOrder(left, record);//return head走到这里，继续执行int lSize = record[0]及后面的语句
		int lSize = record[0];
		int lMin = record[1];//如果left == null,那么lMin = Integer.MAX_VALUE，最大整数值,然后再取下面的record数组赋值结果
		int lMax = record[2];//如果left == null,那么 lMax = Integer.MIN_VALUE，最小整数值
		Node rBST = posOrder(right, record);
		int rSize = record[0];//如果right == null,那么rSize = 0;
		int rMin = record[1];//如果right == null,那么rMin = Integer.MAX_VALUE
		int rMax = record[2];//如果right == null,那么rMin = Integer.MIN_VALUE
		//这里才是record数组的最终赋值结果
		record[1] = Math.min(lMin, value);//记录的是左子树的最小值，但有时候会被遍历右子树时临时借用该变量
		record[2] = Math.max(rMax, value);//记录的是右子树的最大值，
		if(left == lBST && right == rBST && lMax < value && value < rMin){//如果左子节点中的最大值小于其父节点的value,右子节点中的最小值大于其父节点的值，那么以父节点为头的二叉树都是搜索二叉树
			record[0] = lSize + rSize + 1;//最大搜索二叉树的节点个数为左子树节点数加上右子树节点数加上父节点
			return head;//返回head节点，这样就可以返回到递归函数出进行上一层节点的进一步计算
		}
		record[0] = Math.max(lSize, rSize);//如果整颗树不是搜索二叉树，那么就最大搜索二叉树肯定在头节点的左、右子树中，则取两者节点数较多的一个搜索二叉树
		return lSize > rSize ? lBST : rBST;
	}
	
	public static void printTree(Node head){
		System.out.println("Binary Tree: ");
		inOrderPrint(head, 0,"H", 17);
		System.out.println();
	}
	public static void inOrderPrint(Node head, int height, String to, int len){//变相使用二叉树的中序遍历
		if(head == null){
			return ;
		}
		inOrderPrint(head.right, height + 1, "v", len);//新找到二叉树的最右子节点
		String val = to + head.value + to;//将节点中的值与字符串"v"组合，左右各添加一个标记
		int lenM = val.length();//val变量的长度
		int lenL = (len - lenM)/2;//统计左边还剩下多大的空间
		int lenR = len - lenM - lenL;//统计右边还剩下多大的空间
		val = getSpace(lenL) + val + getSpace(lenR);//将拼接好的字符串再拼接上空格以满足len的要求
		System.out.println(getSpace(height * len) + val);//打印拼接后节点的值
		inOrderPrint(head.left, height + 1, "^", len);//递归左子节点
	}
	public static String getSpace(int num){//获取一定数量的" "字符串
		String space = " ";
		StringBuffer sb = new StringBuffer("");
		for(int i = 0; i < num; i++){
			sb.append(space);
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		BiggestSubBSTInTree bsbit = new BiggestSubBSTInTree();
		Node head = bsbit.new Node(6);
		head.left = bsbit.new Node(1);
		head.left.left = bsbit.new Node(0);
		head.left.right = bsbit.new Node(3);
		head.right = bsbit.new Node(12);
		head.right.left = bsbit.new Node(10);
		head.right.left.left = bsbit.new Node(4);
		head.right.left.left.left = bsbit.new Node(2);
		head.right.left.left.right = bsbit.new Node(5);
		head.right.left.right = bsbit.new Node(14);
		head.right.left.right.left = bsbit.new Node(11);
		head.right.left.right.right = bsbit.new Node(15);
		head.right.right = bsbit.new Node(13);
		head.right.right.left = bsbit.new Node(20);
		head.right.right.right = bsbit.new Node(16);
		Node newHead = bsbit.biggestSubBST(head);
		printTree(newHead);
	}

}
