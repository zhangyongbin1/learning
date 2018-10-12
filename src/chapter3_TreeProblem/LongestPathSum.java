package chapter3_TreeProblem;

import java.util.HashMap;

/**
 * 在二叉树中找到累加和为指定值的最长路径长度：给定一个二叉树的头节点head和一个32位整数sum，二叉树节点值类型为整型，求累加和为sum的最长路径长度。
 * 路径是指从某个节点往下，每次最多选择一个孩子节点或者不选择所形成的节点链
 * @author zhangy
 *
 */
public class LongestPathSum {
	public class Node{//构建二叉树
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	public int getMaxLength(Node head, int sum){
		HashMap<Integer,Integer> sumMap = new HashMap<Integer, Integer>();
		sumMap.put(0, 0);//相当于满足sum = 0时,最长路径的长度
		return preOrder(head, sum, 0, 1, 0, sumMap);
	}
	//按照二叉树先序遍历的方式遍历节点
	public int preOrder(Node head, int sum, int preSum, int level, int maxLen, HashMap<Integer, Integer> sumMap){
		if(head == null){
			return maxLen;
		}
		int curSum = preSum + head.value;
		if(!sumMap.containsKey(curSum)){//如果map中不包含curSum值，那么就把它添加进map中
			sumMap.put(curSum, level);
		}
		if(sumMap.containsKey(curSum - sum)){//应该判断有负数的情况
			maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
		}
		maxLen = preOrder(head.left, sum, preSum, level+1, maxLen, sumMap);
		maxLen = preOrder(head.right, sum, preSum, level+1, maxLen, sumMap);
		if(level == sumMap.get(curSum)){//如果遍历到每个子树最后一个节点时，如果curSum包含当前节点，那么需要删除这条记录
			sumMap.remove(curSum);//因为下一次的preSum要使用的是不包含当前节点的value值，也就相当于数组中sum-curnum，left=left + 1的情况
		}
		return maxLen;
	}
	public static void main(String[] args){
		LongestPathSum lps = new LongestPathSum();
		Node head = lps.new Node(-3);
		head.left = lps.new Node(3);
		head.left.left = lps.new Node(1);
		head.left.right = lps.new Node(0);
		head.left.right.left = lps.new Node(1);
		head.left.right.right = lps.new Node(6);
		head.right = lps.new Node(-9);
		head.right.left = lps.new Node(2);
		head.right.right = lps.new Node(1);
		int len = lps.getMaxLength(head, 6);
		System.out.println("max len: "+len);
	}

}
