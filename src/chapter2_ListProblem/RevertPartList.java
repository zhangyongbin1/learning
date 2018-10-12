package chapter2_ListProblem;
/**
 * 给定一个单向链表的头节点head，以及两个整数from和to，在单向链表上把第from个节点和第to个就诶嗲这一部分进行反转
 * 例如： 1->2->3->4->5->null,from=2，to=4
 * 调整结果为：1->4->3->2->5->null
 * @author zhangy
 *
 */
public class RevertPartList {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}

	public Node ReversePart(Node head, int from, int to){
		int len = 0;
		Node tPre = null;
		Node tPos = null;
		Node node1 = head;
		while(node1 != null){
			len++;//循环统计链表的长度
			//不包括tPre和tPos处的节点，即不包含头和尾
			tPre = len == from -1 ? node1 : tPre;//用于定位from之前的一个节点
			tPos = len == to + 1 ? node1 : tPos;//用于定位to之后的一个节点，这样的话tPre----tPos之间的节点是需要反转的
			node1 = node1.next;
		}
		if(from > to || to > len || from < 1){//如果不满足1<=from<=to<=len不用 调整
			return head;//链表不用调整，直接返回了链表的头即可
		}
		node1 = tPre == null ? head : tPre.next;//node1节点为from位置的节点，也就是需要反转的第一个节点
		Node node2 = node1.next;//记住需要反转的第一个节点的下一个节点，以免丢失整个链表
		node1.next = tPos;//开始第一次换位置，即将from位置的next指向tPos
		Node next = null;//定义一个临时节点用于存放next节点
		while(node2 != tPos){//遍历from+1------tPose之间的节点
			next = node2.next;//记住下一个位置
			node2.next = node1;//改变node2的指向，将其指向为node1
			node1 =node2;//更新node1的指向
			node2 = next;//更新Node2从而继续下一次循环
		}
		if(tPre != null){//此时的pre如果不为null,则其仍然指向在第一次换位置时的from节点
			tPre.next = node1;//更新其指向为当前链表的第一个节点从而成为head节点
			return head;
		}
		return node1;//如果tPre == null,那么node1就是新链表的头节点
	}
	
	public void printList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	public static void main(String[] args){
		RevertPartList rl = new RevertPartList();
		Node head = rl.new Node(1);
		head.next = rl.new Node(2);
		head.next.next = rl.new Node(3);
		head.next.next.next = rl.new Node(4);
		head.next.next.next.next = rl.new Node(5);
		rl.printList(head);
		System.out.println();
		rl.printList(rl.ReversePart(head, 2, 4));
	}
}
