package chapter3_TreeProblem;

import chapter3_TreeProblem.IsBSTAndCBT.Node;

/**
 * 在函数父节点的二叉树中寻找一个节点的后继节点
 * 
 * @author zhangy
 *
 */
public class FindDescendantNode {
	public class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	public Node getDescendantNode(Node node) {
		if (node == null) {
			return null;
		}
		if (node.right != null) {//如果node节点的右孩子不为空
			return getMostLeftNode(node.right);//那么右子树上最左的节点就是node的后继节点
		} else {//如果node节点的右子节点为null
			Node parent = node.parent;//那么往上寻找node的父节点
			while (parent != null && parent.left != node) {//如果node节点的父节点不为null且node节点不是其父节点的左子节点，那么就继续往上寻找
				node = parent;//node节点往上移动
				parent = node.parent;//继续寻找父节点
			}
			return parent;//如果当前node节点是其父节点的左子节点，那么其父节点就是后继节点
		}
	}

	public Node getMostLeftNode(Node node) {//获取到当前节点node的最左节点
		if (node == null) {
			return null;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
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
		FindDescendantNode fdn = new FindDescendantNode();
		Node head = fdn.new Node(6);
		head.parent = null;
		head.left = fdn.new Node(3);
		head.left.parent = head;
		head.right = fdn.new Node(9);
		head.right.parent = head;
		head.left.left = fdn.new Node(1);
		head.left.left.parent = head.left;
		head.left.right = fdn.new Node(4);
		head.left.right.parent = head.left;
		head.left.left.right = fdn.new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right.right = fdn.new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right.left = fdn.new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = fdn.new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = fdn.new Node(10);
		head.right.right.parent = head.right;
		fdn.printTree(head);
		Node test = head.left.right.right;
		Node descaendantNode = fdn.getDescendantNode(test);
		System.out.println(descaendantNode.value);
	}
}
