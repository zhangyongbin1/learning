package chapter2_ListProblem;

import chapter2_ListProblem.PrintCommonPart.Node;

/**
 * 链表分区问题：将一个链表按照给定的数m进行分区，大于m的节点在右边，等与m的节点在中间，小于m的节点在左边
 * @author zhangy
 *
 */
public class ListPartition {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	
	public Node listPartition1(Node head, int pivot){
		if(head == null){
			return head;
		}
		Node cur = head;
		int len = 0;
		while(cur != null){//获取链表的长度
			len++;
			cur = cur.next;
		}
		Node[] nodeArr = new Node[len];//新建长度为len的节点数组
		cur = head;
		int i = 0;
		for( i = 0; i < nodeArr.length; i++){//将链表的值赋值给节点数组
			nodeArr[i] = cur;
			cur = cur.next;
		}
		arrPartition(nodeArr, pivot);//进行分区
		for( i = 1; i != nodeArr.length; i++){//将节点数组构造成新的链表
			nodeArr[i-1].next = nodeArr[i];
		}
		nodeArr[i-1].next = null;//链表尾部指向null
		return nodeArr[0];//这是新链表的头
	}
	public void arrPartition(Node[] nodeArr, int pivot){//以pivot值为临界值，将数组的顺序排列为：小->等于->大  的排列顺序
		int small = -1;
		int big = nodeArr.length;
		int index = 0;
		while(index != big){//不能保证每一部分都是有序的.如果需要保证与原链表的顺序一致，那么可以将原链表分为small,equal,big三部分，保证这三小部分有序后再一次链接起来
			if(nodeArr[index].value < pivot){
				swap(nodeArr, ++small, index++);
			}else if(nodeArr[index].value == pivot){
				index++;
			}else{
				swap(nodeArr, --big, index++);
			}
		}
	}
	public void swap(Node[] nodeArr, int a, int b){//交换两个node节点的值
		Node tmp = nodeArr[a];
		nodeArr[a] = nodeArr[b];
		nodeArr[b] = tmp;
	}
	
	public Node listPartition2(Node head, int pivot){
		 Node sH = null;//小链表部分的头
		 Node sT = null;//小链表部分的尾
		 Node eH = null;//相等链表的头
		 Node eT = null;//相等链表部分的尾
		 Node bH = null;//大于链表部分的头
		 Node bT = null;//大于链表部分的尾
		Node next = null;//用于保存下一节点
		while(head != null){//遍历链表，将其以pivot为标准，分为小于，等于，大于三部分链表
			next = head.next;//保存下一个节点值
			head.next = null;//将head节点与head.next节点的连接断开
			if(head.value < pivot){//小于部分
				if(sH == null){//如果小于链表部分为null,那么头节点和尾节点都指向head
					sH = head;
					sT = head;
				}else{
					sT.next = head;//尾指针指向当前的head
					sT = head;//并且将尾指针sT移动到当前节点，因为下面head会移动到next节点
				}
			}else if(head.value == pivot){
				if(eH == null){
					eH = head;
					eT = head;
				}else{
					eT.next = head;
					eT = head;
				}
			}else{
				if(bH == null){
					bH = head;
					bT = head;
				}else{
					bT.next = head;
					bT = head;
				}
			}
			head = next;//移动head节点到next节点，继续下一次循环
		}
		if(sT != null){//将小于链表部分与相等链表部分连接起来
			sT.next = eH;
			eT = eT == null ? sT : eT;
		}
		if(eT != null){//将等于链表部分与大于链表部分连接起来
			eT.next = bH;
		}
		
		return sH != null ? sH : eH != null ? eH : bH;//新链表的头
	}
	
	public void printList(Node head){//打印链表
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	public static void main(String[] args){
		ListPartition lp = new ListPartition();
		Node head = lp.new Node(1);
		head.next = lp.new Node(4);
		head.next.next = lp.new Node(3);
		head.next.next.next = lp.new Node(2);
		head.next.next.next.next = lp.new Node(5);
		Node head1 = lp.new Node(7);
		head1.next = lp.new Node(9);
		head1.next.next =lp. new Node(1);
		head1.next.next.next = lp.new Node(8);
		head1.next.next.next.next =lp.new Node(5);
		head1.next.next.next.next.next =lp.new Node(2);
		head1.next.next.next.next.next.next = lp.new Node(5);
		lp.printList(head);
		System.out.println();
		lp.printList(lp.listPartition1(head, 3));
		System.out.println();
		lp.printList(head1);
		System.out.println();
		lp.printList(lp.listPartition2(head1, 5));
	}
}
