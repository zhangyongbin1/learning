package chapter3_TreeProblem;
/**
 * 遍历二叉树的神级方法：使用morris遍历，就是利用二叉树中那些指向null的指针(即空闲指针)。中序遍历的过程如下：
 * 	1.假设当前子树的头节点为h，让h的左子树中最右节点的right指针指向h，然后h的左子树继续步骤1的处理过程，直到遇到某一个节点没有左子树时记为node，进入步骤2.
 * 	2.从node开始通过每个节点的right指针进行移动，并依次打印，假设移动到的节点cur。对每一个cur节点都判断cur节点的左子树中最右节点是否指向cur:
 * 			a:如果是，那么让cur节点的左子树中最右节点的right指针指向空，也就是把步骤1的调整后再逐渐调整回来，然后打印cur。继续通过cur的right指针移动到下一个节点，重复步骤2.
 * 			b:如果不是，以cur为头的子树重回步骤1执行
 * 	3.步骤2最终移动到null，整个过程结束
 * @author zhangy
 *
 */
public class MorrisForTraverseTree {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	
	public void morrisIn(Node head){
		if(head == null){
			return ;
		}
		Node cur1 = head;
		Node cur2 = null;
		while(cur1 != null){
			cur2 = cur1.left;//寻找cur1节点的左子树
			if(cur2 != null){
				while(cur2.right != null && cur2.right != cur1){//如果cur1节点的左子树的最节点的right指针指向不为null，且其指向不等cur1,即满足步骤2的条件b
					cur2 = cur2.right;//移动cur2节点到下一个节点，重复步骤1操作
				}
				if(cur2.right == null){//cur1的左子树的右节点为空
					cur2.right = cur1;//就把cur2的right指针指向cur1节点
					cur1 = cur1.left;//继续寻找cur1节点的左子树
					continue ;//继续while(cur1 ！= null)的判断
				}else{
					cur2.right = null;//cur1的左子树的右节点不为空，说明此时已经是步骤2的a条件满足，那就把其还原，指向空
				}
			}
			System.out.print(cur1.value + " ");//打印cur1节点的值,在移动cur1节点之前打印cur1节点的值
			cur1 = cur1.right;//cur1移动到right指针指向的节点(即指回上一层头节点)
		}
		System.out.println();
	}

	public void morrisPre(Node head){
		if(head == null){
			return ;
		}
		Node cur1 = head;
		Node cur2 = null;
		while(cur1 != null){
			cur2 = cur1.left;
			if(cur2 != null){
				while(cur2.right != null && cur2.right != cur1){
					cur2 = cur2.right;
				}
				if(cur2.right == null){
					cur2.right = cur1;
					System.out.print(cur1.value + " ");//在寻找当前节点左子树的最右节点的时候打印当前节点的值，这是先序遍历与中序遍历的区别
					cur1 = cur1.left;
					continue;
				}else{
					cur2.right = null;
				}
			}else{
				System.out.print(cur1.value + " ");
			}
			cur1 = cur1.right;
		}
		System.out.println();
	}
	
	public void morrisPos(Node head){
		if(head == null){
			return ;
		}
		Node cur1 = head;
		Node cur2 = null;
		while(cur1 != null){
			cur2 = cur1.left;
			if(cur2 != null){
				while(cur2.right != null && cur2.right != cur1){
					cur2 = cur2.right;
				}
				if(cur2.right == null){
					cur2.right = cur1;
					cur1 = cur1.left;
					continue ;
				}else{
					cur2.right = null;
					printEdge(cur1.left);//这里面其实是打印cur1节点的左子树的右节点边界
				}
			}
			cur1 = cur1.right;
		}
		printEdge(head);//看图就能发现其实上面打印的结果不包括头节点右子树的右节点边界，所以需要单独打印
		System.out.println();
	}
	public void printEdge(Node head){//打印边界节点
		Node tail = reverseEdge(head);
		Node cur = tail;
		while(cur != null){
			System.out.print(cur.value + " ");
			cur = cur.right;//右子节点
		}
		reverseEdge(tail);
	}
	public Node reverseEdge(Node from){//逆序
		Node pre = null;
		Node next = null;
		while(from != null){
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}
	public static void main(String[] args){
		MorrisForTraverseTree mftt = new MorrisForTraverseTree();
		Node head = mftt.new Node(4);
		head.left = mftt.new Node(2);
		head.right = mftt.new Node(6);
		head.left.left = mftt.new Node(1);
		head.left.right = mftt.new Node(3);
		head.right.left = mftt.new Node(5);
		head.right.right = mftt.new Node(7);
		System.out.print("Pre Tree: ");
		mftt.morrisPre(head);
		System.out.println();
		
		System.out.print("In Tree: ");
		mftt.morrisIn(head);
		System.out.println();
		
		System.out.print("Pos Tree: ");
		mftt.morrisPos(head);
		System.out.println();
	}
}
