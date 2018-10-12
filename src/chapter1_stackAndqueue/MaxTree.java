package chapter1_stackAndqueue;

import java.util.HashMap;
import java.util.Stack;

/**
 * 构建最大树的原则：1.每一个树的父节点是它左边第一个比它大的树和它右边第一个比它大的数中较小的那个
 * 				  2.如果一个数左边没有比它大的数，右边也没有。也就是说，这个数是整个数组的的最大值，那么这个数是MaxTree的头节点
 * @author zhangy
 *
 */
public class MaxTree {
	public class Node{ //构建一个树的节点
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	
	public Node getMaxTree(int[] arr){
		Node[] nodes = new Node[arr.length];//构建数组长度相同的节点数
		for(int i=0; i<arr.length; i++){
			nodes[i] = new Node(arr[i]);//以数组元素的值当作value新建每个节点
		}
		Stack<Node> stack = new Stack<Node>();//新建一个栈用于获取当前元素左边第一个比它大的数和右边第一个比它大的数
		HashMap<Node,Node> lBigMap = new HashMap<Node,Node>();//用于存放当前数和左边一个比它大的数
		HashMap<Node,Node> rBigMap = new HashMap<Node,Node>();//用于存放当前数和右边一个比它大的数
		for(int i=0; i !=nodes.length; i++){
			Node curNode = nodes[i];
			while(!stack.isEmpty() && stack.peek().value < curNode.value){
				popStackSetMap(stack,lBigMap);
			}
			stack.push(curNode);//如果当前节点比栈顶元素大于当前元素，那就直接压入栈中
		}
		while(!stack.isEmpty()){//如果当前是最大值，那么说明他会最后留在栈中，所以需要pop出来，且其左边无第一个最大值
			popStackSetMap(stack,lBigMap);
		}
		
		for(int i=nodes.length -1; i !=-1; i--){//统计当前元素右边第一个比它大的值
			Node curNode = nodes[i];
			while(!stack.isEmpty() && stack.peek().value < curNode.value){
				popStackSetMap(stack,rBigMap);
			}
			stack.push(curNode);//如果当前节点比栈顶元素大于当前元素，那就直接压入栈中
		}
		while(!stack.isEmpty()){//如果当前是最大值，那么说明他会最后留在栈中，所以需要pop出来，且其右边无第一个最大值
			popStackSetMap(stack,rBigMap);
		}
		//上面的工作准好之后就可以开始构建最大树了
		Node head = null;
		for(int i=0; i != nodes.length; i++){
			Node curNode = nodes[i];//当前节点
			Node left = lBigMap.get(curNode);//当前节点左边第一个比它大的节点
			Node right = rBigMap.get(curNode);//当前节点右边第一个比它大的节点
			if(left == null && right == null){
				head = curNode;//当前节点就是maxTree的父节点
			}else if(left == null){//如果左边第一个最大值为null，右边第一个最大值right不为null,那当前节点的父节点就是right这个节点
				if(right.left == null){//当前节点的父节点的左子节点为空，那么直接将当前节点作为right的左子节点
					right.left = curNode;
				}else{
					right.right = curNode;//当前节点的父节点的左子节点不为空，那么直接将当前节点作为right的右子节点
				}
			}else if(right == null){//如果右边第一个最大值为null，左边第一个最大值right不为null,那当前节点的父节点就是left这个节点
				if(left.left == null){
					left.left = curNode;//当前节点的父节点的左子节点为空，那么直接将当前节点作为left的左子节点
				}else{
					left.right = curNode;//当前节点的父节点的右子节点为空，那么直接将当前节点作为left的右子节点
				}
			}else{
				Node parent = left.value < right.value ? left : right;//如果当前节点的左边第一个最大值和右边第一个最大值都不为null，那么选择二者中较小的作为当前节点的父节点
				if(parent.left == null){
					parent.left = curNode;
				}else{
					parent.right = curNode;
				}
			}
		}
		return head;//返回MaxTree
	}

	private void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map) {
		Node popData = stack.pop();
		if(stack.isEmpty()){
			map.put(popData, null);//说明当前节点popData的左边或右边最大值没有
		}else{
			map.put(popData, stack.peek());//说明当前节点popData的左边最大或右边最大为stack.peek();
		}
	}
	public static void printPreOrder(Node head){
		if(head == null){
			return;
		}
		System.out.print(head.value+" ");
		printPreOrder(head.left);
		printPreOrder(head.right);
	}
	public static void printInOrder(Node head){
		if(head == null){
			return;
		}
		printPreOrder(head.left);
		System.out.print(head.value+" ");
		printPreOrder(head.right);
	}
	public static void main(String[] args){
		int[] arr = {3,4,5,1,2};
		MaxTree mt = new MaxTree();
		Node head = mt.getMaxTree(arr);
		printPreOrder(head);
		System.out.println();
		printInOrder(head);
	}

}
