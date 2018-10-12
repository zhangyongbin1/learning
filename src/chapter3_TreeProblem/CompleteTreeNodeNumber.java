package chapter3_TreeProblem;
/**
 * 统计一个完全二叉树的节点数
 * @author zhangy
 *
 */
public class CompleteTreeNodeNumber {
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data){
			this.value = data;
		}
	}

	public int nodeNum(Node head){
		if(head == null){//如果是空树，直接返回节点数为0
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head,1));
	}
	public int bs(Node node, int l, int h){//node为当前节点，l为当前节点所在的层数，h为整颗树的高度
		if( l == h){//说明当前节点就是最后一层节点
			return 1;
		}
		if(mostLeftLevel(node.right, l + 1) == h){//说明整个左子树是满二叉树，则其节点数 + 头节点数为： 2^(h-1) -1 + 1 = 2^(h-1)个节点
			return (1 << (h -l)) + bs(node.right, l + 1, h);//1向左移动一位变成2，然后再移动(h-1)位，变成2的h-1次幂，最后再加上右子树上节点的个数
		}else{//说明node节点的右子树是满二叉树，且层数为h - l -1
			return (1 << (h - l -1)) + bs(node.left, l + 1, h);
		}
		
	}
	public int mostLeftLevel(Node node, int level){//寻找最左节点所在的层数。level为当前节点所在的层数
		while(node != null){
			level++;
			node = node.left;
		}
		return level - 1;//因为是level++最后导致的结果就是level层数多加了1，所以这需要减掉1
	}
}
