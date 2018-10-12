package chapter3_TreeProblem;
/**
 * 打印二叉树的边界节点，边界点的定义有两个标准：
 * 	标准一	1：头节点为边界节点； 2：叶节点为边界节点； 3：如果节点在其所在的层中是最左或最右的，那么也是边界节点
 *  标准二       1：头节点为边界节点； 2：叶节点为边界节点  3：树左边边界延伸下去的路径为边界节点； 4：树右边界延伸下去的路径为边界节点
 * @author zhangy
 *
 */
public class PrintEdgeOfTree {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}

	public void printEdge1(Node head){
		if(head == null){
			return ;
		}
		int height = getHeight(head, 0);
		Node[][] edgeMap = new Node[height][2];
		setEdgeMap(head, 0 , edgeMap);
		//打印左边界
		for(int i= 0; i != edgeMap.length; i++){
			System.out.print(edgeMap[i][0].value + " ");
		}
		//打印既不是左边界，也不是右边界的叶子节点
		printLeafNotInMap(head, 0, edgeMap);
		//打印右边界，但不是左边界的节点
		for(int i = edgeMap.length - 1; i != -1; i--){
			if(edgeMap[i][0] != edgeMap[i][1]){
				System.out.print(edgeMap[i][1].value + " ");
			}
		}
	}
	public void printEdge2(Node head){
		if(head == null){
			return ;
		}
		System.out.print(head.value + " ");
		if(head.left != null && head.right != null){
			printLeftEdge(head.left, true);
			printRightEdge(head.right, true);
		}else{
			printEdge2(head.left != null ? head.left : head.right);
		}
		System.out.println();
	}
	public int getHeight(Node head, int l){//使用递归的方法求解二叉树的高度
		if(head == null){
			return l;
		}
		return Math.max(getHeight(head.left,l+1), getHeight(head.right,l+1));//取左右子树的较大高度的值
	}
	public void setEdgeMap(Node h, int l, Node[][] edgeMap){//遍历最左和最右的节点
		if(h == null){
			return ;
		}
		edgeMap[l][0] = edgeMap[l][0] == null ? h : edgeMap[l][0];
		edgeMap[l][1] = h;
		setEdgeMap(h.left, l+1, edgeMap);//使用递归方法将最左子节点加入到二维数组edgeMap中的第一列中
		setEdgeMap(h.right, l+1,edgeMap);//使用递归方法将最左子节点加入到二维数组edgeMap中的第二列中
	}
	
	public void printLeafNotInMap(Node h, int l, Node[][] m){//打印未在Map中的叶子节点
		if(h == null){
			return;
		}
		if(h.left == null && h.right == null && h != m[l][0] && h != m[l][1]){//将叶节点且不再Map数组中的叶节点打印出来
			System.out.print(h.value + " ");
		}
		printLeafNotInMap(h.left, l+1, m);
		printLeafNotInMap(h.right, l+1, m);
	}
	public void printLeftEdge(Node h, boolean print){//打印左叶子节点
		if(h == null){
			return ;
		}
		if(print || (h.left == null && h.right == null)){
			System.out.print(h.value + " ");
		}
		printLeftEdge(h.left, print);
		printLeftEdge(h.right, print && h.left == null ? true : false);//打印以右节点为头节点的左叶子节点
	}
	public void printRightEdge(Node h, boolean print){//打印右叶子节点
		if(h == null){
			return ;
		}
		printRightEdge(h.left, print && h.right == null ? true : false);
		printRightEdge(h.right, print);
		if(print || (h.left == null && h.right == null)){//左右子节点都为null,则该节点为叶节点
			System.out.print(h.value + " ");
		}
	}
	
	public static void main(String[] args){
		PrintEdgeOfTree peot = new PrintEdgeOfTree();
		Node head = peot.new Node(1);
		head.left = peot.new Node(2);
		head.right = peot.new Node(3);
		head.left.right = peot.new Node(4);
		head.left.right.left = peot.new Node(7);
		head.left.right.right = peot.new Node(8);
		head.left.right.right.right = peot.new Node(11);
		head.left.right.right.right.left = peot.new Node(13);
		head.left.right.right.right.right = peot.new Node(14);
		head.right.left = peot.new Node(5);
		head.right.right = peot.new Node(6);
		head.right.left.left = peot.new Node(9);
		head.right.left.right = peot.new Node(10);
		head.right.left.left.left = peot.new Node(12);
		head.right.left.left.left.left = peot.new Node(15);
		head.right.left.left.left.right = peot.new Node(16);
		//peot.printEdge1(head);
		peot.printEdge2(head);
	}
}
