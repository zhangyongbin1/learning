package chapter3_TreeProblem;

import java.util.Stack;


/**
 * 调整搜索二叉树中两个错误的节点。总结：对二叉树进行中序遍历时，第一个错误节点为第一次降序时较大的节点，第二个错误节点为最后一次降序时较小的节点
 * @author zhangy
 *
 */
public class RecoverBST {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	
	public Node[] getTwoErrorNodes(Node head){//使用非递归方法的中序遍历的改写
		Node[] errs = new Node[2];
		if(head == null){
			return errs;
		}
		Stack<Node> stack = new Stack<Node>();
		Node pre = null;//这个变量用于记录前一个节点
		while(!stack.isEmpty() || head != null){
			if(head != null){//如果当前节点不为null
				stack.push(head);//那么将当前节点加入到栈中
				head = head.left;//即不断把左子树的左节点加入栈中
			}else{//当然当前遍历到的节点为空
				head = stack.pop();//从栈中弹出一个节点，这个节点肯定是二叉树的左子树的最左子节点
				//这个pre节点有可能比当前head节点高好几层
				if(pre != null && pre.value > head.value){//然后判断弹出节点的value与前一个前一个节点的value比较
					errs[0] = errs[0] == null ? pre : errs[0];//第一个错误节点是第一次降序时较大的节点
					errs[1] = head;//第二个错误节点是第二次降序时较小的节点。
				}
				pre = head;//更新pre变量记录的节点
				head = head.right;//走到当前head节点的右子树去判断
			}
		}
		return errs;
	}
	
	public Node[] getTwoErrorNodesParent(Node head, Node err1, Node err2){//获取错误节点的父节点
		Node[] parents = new Node[2];
		if(head == null){
			return parents;
		}
		Stack<Node> stack = new Stack<Node>();
		while(!stack.isEmpty() || head != null){
			if(head != null){
				stack.push(head);
				head = head.left;
			}else{
				head = stack.pop();
				if(head.left == err1 || head.right == err1){
					parents[0] = head;
				}
				if(head.left == err2 || head.right == err2){
					parents[1] = head;
				}
				head = head.right;//继续遍历右子树
			}
		}
		return parents;
	}
	
	public Node recoverTree(Node head){

		Node[] errs = getTwoErrorNodes(head);
		Node[] parents = getTwoErrorNodesParent(head, errs[0], errs[1]);
		Node e1 = errs[0];
		Node e1P = parents[0];
		Node e1L = e1.left;
		Node e1R = e1.right;
		Node e2 = errs[1];
		Node e2P = parents[1];
		Node e2L = e2.left;
		Node e2R = e2.right;
		if (e1 == head) {
			if (e1 == e2P) { // 情况1：err1是头，err1是err2的父，此时err2只可能是err1的右孩子
				e1.left = e2L;
				e1.right = e2R;
				e2.right = e1;
				e2.left = e1L;
			} else if (e2P.left == e2) { // 情况2：err1是头，err1不是err2的父，err2是err2P的左孩子
				e2P.left = e1;
				e2.left = e1L;
				e2.right = e1R;
				e1.left = e2L;
				e1.right = e2R;
			} else { // 情况3：err1是头，err1不是err2的父，err2是err2P的右孩子
				e2P.right = e1;
				e2.left = e1L;
				e2.right = e1R;
				e1.left = e2L;
				e1.right = e2R;
			}
			head = e2;
		} else if (e2 == head) {
			if (e2 == e1P) { // 情况4：err2是头，err2不是err1的父，此时err1只可能是err2的左孩子
				e2.left = e1L;
				e2.right = e1R;
				e1.left = e2;
				e1.right = e2R;
			} else if (e1P.left == e1) { // 情况5：err2是头，err2不是err1的父，err1是err1P的左孩子
				e1P.left = e2;
				e1.left = e2L;
				e1.right = e2R;
				e2.left = e1L;
				e2.right = e1R;
			} else { // 情况6：err2是头，err2不是err1的父，err1是err1P的右孩子
				e1P.right = e2;
				e1.left = e2L;
				e1.right = e2R;
				e2.left = e1L;
				e2.right = e1R;
			}
			head = e1;
		} else {
			if (e1 == e2P) {
				if (e1P.left == e1) { // 情况7：err1和err2都不是头，err1是err2的父，此时err2只可能是err1的右孩子，err1是err1P的左孩子
					e1P.left = e2;
					e1.left = e2L;
					e1.right = e2R;
					e2.left = e1L;
					e2.right = e1;
				} else { // 情况8：err1和err2都不是头，err1是err2的父，此时err2只可能是err1的右孩子，err1是err1P的右孩子
					e1P.right = e2;
					e1.left = e2L;
					e1.right = e2R;
					e2.left = e1L;
					e2.right = e1;
				}
			} else if (e2 == e1P) {
				if (e2P.left == e2) { // 情况9：err1和err2都不是头，err2是err1的父，此时err1只可能是err2的左孩子，err2是err2P的左孩子
					e2P.left = e1;
					e2.left = e1L;
					e2.right = e1R;
					e1.left = e2;
					e1.right = e2R;
				} else { // 情况10：err1和err2都不是头，err2是err1的父，此时err1只可能是err2的左孩子，err2是err2P的右孩子
					e2P.right = e1;
					e2.left = e1L;
					e2.right = e1R;
					e1.left = e2;
					e1.right = e2R;
				}
			} else {
				if (e1P.left == e1) {
					if (e2P.left == e2) {// 情况11：err1和err2都不是头，谁也不是谁的父节点，err1是err1P的左孩子，err2是err2P的左孩子
						e1.left = e2L;
						e1.right = e2R;
						e2.left = e1L;
						e2.right = e1R;
						e1P.left = e2;
						e2P.left = e1;
					} else { // 情况12：err1和err2都不是头，谁也不是谁的父节点，err1是err1P的左孩子，err2是err2P的右孩子
						e1.left = e2L;
						e1.right = e2R;
						e2.left = e1L;
						e2.right = e1R;
						e1P.left = e2;
						e2P.right = e1;
					}
				} else {
					if (e2P.left == e2) { // 情况13：err1和err2都不是头，谁也不是谁的父节点，err1是err1P的右孩子，err2是err2P的左孩子
						e1.left = e2L;
						e1.right = e2R;
						e2.left = e1L;
						e2.right = e1R;
						e1P.right = e2;
						e2P.left = e1;
					} else {// 情况14：err1和err2都不是头，谁也不是谁的父节点，err1是err1P的右孩子，err2是err2P的右孩子
						e1.left = e2L;
						e1.right = e2R;
						e2.left = e1L;
						e2.right = e1R;
						e1P.right = e2;
						e2P.right = e1;
					}
				}
			}
		}
		 return head;
	}

}
