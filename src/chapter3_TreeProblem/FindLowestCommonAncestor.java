package chapter3_TreeProblem;
/**
 * 在二叉树中找到两个节点的最近公共祖先
 * @author zhangy
 *
 */
public class FindLowestCommonAncestor {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	public Node getLowestAncestor(Node head, Node o1, Node o2){//间接使用后序遍历
		if(head == null || head == o1 || head == o2){//每一次递归遍历都会来这里对比，只要找到等于o1或者o2的节点就返回这个节点，否则就返回null
			return head;
		}
		Node left = getLowestAncestor(head.left, o1, o2);//递归遍历左节点
		Node right = getLowestAncestor(head.right,o1,o2);//递归遍历右节点
		if(left != null && right != null){//如果当前节点的左右节点都不为空，说明左、右子树中找到了节点o1,o2，所以当前节点就是o1和o2的最近公共祖先
			 return head;
		}
		
		return left != null ? left : right;//否则就返回null
	}
	
	// for test -- print tree
		public void printTree(Node head) {
			System.out.println("Binary Tree:");
			printInOrder(head, 0, "H", 17);
			System.out.println();
		}

		public void printInOrder(Node head, int height, String to, int len) {
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

		public String getSpace(int num) {
			String space = " ";
			StringBuffer buf = new StringBuffer("");
			for (int i = 0; i < num; i++) {
				buf.append(space);
			}
			return buf.toString();
		}

		public static void main(String[] args) {
			FindLowestCommonAncestor flca = new FindLowestCommonAncestor();
			Node head = flca.new Node(6);
			head.left = flca.new Node(3);
			head.right = flca.new Node(9);
			head.left.left = flca.new Node(1);
			head.left.right = flca.new Node(4);
			head.left.left.right = flca.new Node(2);
			head.left.right.right = flca.new Node(5);
			head.right.left = flca.new Node(8);
			head.right.left.left = flca.new Node(7);
			head.right.right = flca.new Node(10);
			flca.printTree(head);
			Node test = flca.getLowestAncestor(head, head.left.left.right, head.right.left.left);
			System.out.println(test.value);
		}
}
