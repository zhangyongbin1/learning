package chapter3_TreeProblem;

import java.util.HashMap;

/**
 * 已知一个二叉树的所有节点值都不同，给定这颗二叉树正确的先序、中序和后序数组。请分别用三个函数实现任意两种数组结合重构原来的二叉树，
 * 并返回重构二叉树的头节点
 * @author zhangy
 *
 */
public class PreInPosArrayToTree {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	//给定中序数组与先序数组，重构二叉树
	public Node PreInTree(int[] pre, int[] in){
		if(pre == null || in == null){
			return null;
		}
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i = 0; i < in.length; i++){//遍历将中序数组的值与index角标添加进hashmap中
			map.put(in[i], i);
		}
		return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
	}
	public Node preIn(int[] p, int pi, int pj, int[] n, int ni, int nj, HashMap<Integer,Integer>map){
		if(pi > pj){
			return null;
		}
		//先序数组的第一个节点就为整颗树的头节点
		Node head = new Node(p[pi]);
		int index = map.get(p[pi]);//hashmap中存储的是中序遍历数组的值和其位置index
		head.left = preIn(p, pi + 1, pi + index - ni, n, ni, index - 1, map);
		head.right = preIn(p, pi + index - ni + 1, pj, n, index + 1, nj, map);
		return head;
	}
	
	//给定中序与后序数组，重构二叉树
	public Node inPosToTree(int[] in, int[] pos){
		if(in == null || pos == null){
			return null;
		}
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i = 0; i < pos.length; i++){
			map.put(pos[i], i);
		}
		return inPos(in, 0, in.length - 1, pos, 0, pos.length - 1, map);
	}
	public Node inPos(int[] n, int ni, int nj, int[] pos, int posi, int posj, HashMap<Integer,Integer>map){
		if(ni > nj){
			return null;
		}
		//后序数组的的最后一个节点就是整颗树的头节点
		Node head = new Node(pos[posj]);
		int index = map.get(pos[posj]);
		head.left = inPos(n, ni, index -1, pos, posi, posi + index - ni - 1, map);
		head.right = inPos(n, index + 1, nj, pos, posi + index-ni, posj - 1, map);
		return head;
	}
	
	//先序遍历数组与后遍历数组重构二叉树
	//每个节点的孩子树都为0或2二叉树才能被先序和后序重构出来
	public Node prePosToTree(int[] pre, int[] pos){
		if(pre == null || pos == null){
			return null;
		}
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i = 0; i < pos.length; i++){
			map.put(pos[i], i);
		}
		return prePos(pre, 0, pre.length - 1, pos, 0, pos.length -1, map);
	}
	public Node prePos(int[]pre, int pi, int pj, int[] pos, int si, int sj, HashMap<Integer,Integer>map){
		Node head = new Node(pos[sj--]);
		if(pi == pj){//如果先序遍历只有一个值，说明整颗树就一个节点，也就是头节点
			return head;
		}
		//1 2 4 5 3 6 7  先序
		//4 5 2 6 7 3 1 后序
		//index即为后序数组中左子树上的最后一个左节点
		int index = map.get(pre[++pi]);//获取最左节点在后序数组中的index位置，即后序数组中的最左子节点为左半部分的最后一个
		head.left = prePos(pre, pi, pi + index - si, pos, si, index, map );
		head.right = prePos(pre, pi + index - si +1, pj, pos, index + 1, sj, map);
		return head;
	}
	

}
