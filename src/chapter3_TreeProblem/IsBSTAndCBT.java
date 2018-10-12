package chapter3_TreeProblem;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 判断一个二叉树是否为搜索二叉树和完全二叉树
 * 
 * @author zhangy
 *
 */
public class IsBSTAndCBT {
	public class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public boolean isBST(Node head) {// 使用morris中序遍历二叉树
		if (head == null) {
			return true;
		}
		boolean res = true;
		Node pre = null;
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				if (cur2.right != null && cur2.right != cur1) {// 如果左子树的右节点不为空，但又不等当前节点cur1
					cur2 = cur2.right;// 那么cur2继续寻找右节点
				} else if (cur2.right == null) {
					cur2.right = cur1;// 如果放在这里打印就是先序遍历
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;// 将有节点还原成指向空的状态
				}
			}
			if (pre != null && pre.value > cur1.value) {// 如果放在这里打印节点，就是中序遍历
				res = false;// 不能直接返回false，如果直接返回false可能会跳过恢复阶段，从而破坏二叉树的结构
			}
			pre = head;
			cur1 = cur1.right;
		}
		return res;
	}

	public boolean isCBT(Node head) {
		if (head == null) {
			return true;
		}
		Queue<Node> queue = new LinkedList<Node>();// 按层打印树的节点使用的是队列
		boolean leaf = false;// 判断当前节点是否是叶子节点
		Node left = null;
		Node right = null;
		queue.add(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			left = head.left;
			right = head.right;
			// 如果当前节点head有右孩子，但是没有左孩子，则不是一颗平衡二叉树，如果当前节点并不是左右孩子都有，那么之后的节点必须都为叶节点，否则返回false
			if ((leaf && (left != null || right != null))
					|| (left == null && right != null)) {
				return false;
			}
			if (left != null) {// 先将左节点添加进队列中
				queue.offer(left);
			}
			if (right != null) {// 然后将右节点添加进队列中
				queue.offer(right);
			} else {
				leaf = true;// 如果左、右子节点都为空，那么就是叶子节点
			}

		}

		return true;// 整个循环过程没有返回false，最后则返回true
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
		IsBSTAndCBT bct = new IsBSTAndCBT();
		Node head = bct.new Node(4);
		head.left = bct.new Node(2);
		head.right = bct.new Node(6);
		head.left.left = bct.new Node(1);
		head.left.right = bct.new Node(3);
		head.right.left = bct.new Node(5);

		bct.printTree(head);
		System.out.println(bct.isBST(head));
		System.out.println(bct.isCBT(head));

	}

}
