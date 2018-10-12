package chapter2_ListProblem;

import java.util.HashMap;

import chapter2_ListProblem.ListPartition.Node;

/**
 * 将特殊的链表复制一份，保证其节点之间的关系一样
 * @author zhangy
 *
 */
public class CopyListWithRandom {
	public class Node{//带有rand随机指针的特殊链表
		public int value;
		public Node next;
		public Node rand;
		public Node (int data){
			this.value = data;
		}
	}

	public Node copyListWithRand(Node head){
		HashMap<Node,Node> map = new HashMap<Node,Node>();//新建一个hasmap用于存放原链表的节点与复制后对应的节点，保存节点与复制节点的关系
		Node cur = head;
		while(cur != null){
			map.put(cur, new Node(cur.value));//遍历链表，单个节点进行复制，每个节点的next和rand都指向null
			cur = cur.next;
		}
		cur = head;
		Node rand = null;
		while(cur != null){//再次遍历链表，将每个节点之间的关系进行复制
			rand = cur.rand;
			map.get(cur).rand = map.get(rand);//当前节点的复制节点的rand节点执向当钱节点rand的指向的复制节点
			map.get(cur).next = map.get(cur.next);//当前节点的复制节点的next节点指向当前节点的next节点的复制节点
			cur = cur.next;//将当前节点移动到下一个节点
		}
		return map.get(head);//返回新链表的头节点
	}
	
	public Node copyListWithRand2(Node head){
		if(head == null){
			return null;
		}
		Node cur = head;
		Node next = null;
		while(cur != null){//将当前节点复制一个，放置在当前节点的next位置，
			next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next=next;//复制的节点指向当前节点的真实next节点
			cur = next;//继续下一次循环
		}
		cur = head;
		Node curCopy = null;
		while(cur != null){//形成rand指针之间的关系
			next = cur.next.next;//当前节点的真实下一个节点
			curCopy = cur.next;//当前节点的复制节点
			curCopy.rand = cur.rand != null ? cur.rand.next : null;//复制节点的rand指向为当前节点的rand指向的下一个节点
			cur = next;//继续下一次循环
		}
		cur = head;
		Node res = head.next;
		while(cur != null){//拆分
			next = cur.next.next;//当前节点的真实下一个节点
			curCopy = cur.next;//当前节点的复制节点
			cur.next = next;//还原原链表
			curCopy.next = next != null ? next.next : null;//构建新的链表关系
			cur = next;//继续下一次循环
		}
		return res;
	}
	public  void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}
	public static void main(String[] args){
		CopyListWithRandom clwr = new CopyListWithRandom();
		Node head = clwr.new Node(1);
		head.next = clwr.new Node(4);
		head.next.next = clwr.new Node(3);
		head.next.next.next = clwr.new Node(2);
		head.next.next.next.next = clwr.new Node(5);
		head.rand = head.next.next.next.next;
		head.next.rand = head.next.next.next;
		head.next.next.rand = head.next.next;
		clwr.printRandLinkedList(head);
		System.out.println();
		clwr.printRandLinkedList(clwr.copyListWithRand(head));
		System.out.println();
		clwr.printRandLinkedList(clwr.copyListWithRand2(head));
	}
}
