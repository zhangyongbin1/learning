package chapter3_TreeProblem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将一个二叉树序列化与反序列化
 * @author zhangy
 *
 */
public class SerialAndDeserialOfBinaryTree {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}
	
	public String serialByPre(Node head){//使用先序遍历的结果进行二叉树的序列化
		if(head == null){
			return "#!";//#代表当前节点的值为null
		}
		String res = head.value + "!";//加一个！作为一个节点值结束的标志
		res += serialByPre(head.left);//只有每次return了值之后才会走下一个语句
		res += serialByPre(head.right);
		return res;
	}
	
	public Node deserialByPreString(String str){
		String[] values = str.split("!");
		Queue<String> queue = new LinkedList<String>();
		for(int i =0; i != values.length; i++){
			queue.offer(values[i]);
		}
		return reconPreOrder(queue);
	}
	
	public Node reconPreOrder(Queue<String> queue){
		String value = queue.poll();
		if(value.equals("#")){
			return null;
		}
		Node head = new Node(Integer.valueOf(value));
		head.left = reconPreOrder(queue);//使用递归的方法重构左子树
		head.right = reconPreOrder(queue);//使用递归的方法重构右子树
		return head;
	}
	public void preOrder(Node head){
		if(head == null){
			return ;
		}
		System.out.print(head.value + " ");
		preOrder(head.left);
		preOrder(head.right);
	}
	//使用按层遍历的方式序列化二叉树,使用队列的方式来按层遍历二叉树
	public String serialByLevel(Node head){
		if(head == null){
			return "#!";
		}
		Queue<Node> queue = new LinkedList<Node>();//与传统的三种遍历方式使用stack不同，按层遍历使用的是队列，先进先出
		String res = head.value + "!";//现将头节点进行拼接
		queue.offer(head);//将头节点添加到队列中
		while(!queue.isEmpty()){//如果队列不为空
			head = queue.poll();//将队列中的节点弹出，但不打印
			if(head.left != null){//判断当前节点的左子节点是否为空
				res += head.left.value + "!";//拼接当前左子节点的值
				queue.offer(head.left);//并将当前左子节点添加进队列
			}else{
				 res +="#!";
			}
			if(head.right != null){//判断当前节点的右子节点是否为空
				res += head.right.value + "!";
				queue.offer(head.right);
			}else{
				 res +="#!";
			}
		}
		return res;
	}
	public Node reconByLevelString(String levelStr){
		String[] values = levelStr.split("!");
		int index = 0;
		Queue<Node> queue = new LinkedList<Node>();
		Node head = generateNodeByString(values[index++]);
		if(head != null){
			queue.add(head);
		}
		Node node = null;
		while(!queue.isEmpty()){
			node = queue.poll();
			node.left = generateNodeByString(values[index++]);//先构造左子节点
			node.right = generateNodeByString(values[index++]);//后构造右子节点
			if(node.left != null){
				queue.add(node.left);//把左子树节点加入到队列中
			}
			if(node.right != null){
				queue.add(node.right);//把右子树节点加入到队列中
			}
		}
		return head;//返回树的头节点
	}
	public Node generateNodeByString(String str){
		if(str.equals("#")){
			return null;
		}
		return new Node(Integer.valueOf(str));
	}
	public static void main(String[] args){
		SerialAndDeserialOfBinaryTree sdob = new SerialAndDeserialOfBinaryTree();
		Node head = sdob.new Node(1);
		head.left = sdob.new Node(2);
		head.right = sdob.new Node(3);
		head.left.left = sdob.new Node(4);
		head.left.right = sdob.new Node(5);
		head.right.left = sdob.new Node(6);
		head.right.right = sdob.new Node(7);
		String str = sdob.serialByPre(head);
		System.out.println("Serial: "+str);
		Node node = sdob.deserialByPreString(str);
		sdob.preOrder(node);
		System.out.println();
		Node head2 = sdob.new Node(1);
		head2.left = sdob.new Node(2);
		head2.right = sdob.new Node(3);
		head2.left.left = sdob.new Node(4);
		head2.right.right = sdob.new Node(5);
		String str2 = sdob.serialByLevel(head2);
		System.out.println("Serial By Level: "+str2);
		Node head3 = sdob.reconByLevelString(str2);
		sdob.preOrder(head3);
	}

}
