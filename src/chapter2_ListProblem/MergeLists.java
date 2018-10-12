package chapter2_ListProblem;

import chapter2_ListProblem.RemoveValue.Node;

/**
 * 给定两个有序的链表的头节点head1,head2，请合并两个有序链表，合并后的链表依然有序，并返回合并后链表的头节点，例如：
 * 0->2->3->7->null,  1->3->4->5->7->9->null
 * 合并后的链表为：0->1->2->3->3->5->7->7->9->null
 * @author zhangy
 *
 */
public class MergeLists {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	
	public Node merge(Node head1, Node head2){
		if(head1 == null || head2 == null){//只要其中一个链表为空，那么就不需要进行合并，直接返回另一个链表的头节点
			return head1 != null ? head1 : head2;
		}
		Node head = head1.value < head2.value ? head1 : head2;//新链表的头肯定是合并前两个链表的头节点中较小的一个
		//确定两个链表的遍历的头节点cur1和cur2
		Node cur1 = head == head1 ? head1 : head2;
		Node cur2 = head == head1 ? head2 : head1;
//		Node cur1 = head1 ;
//		Node cur2 = head2 ;//会报空指针
		Node pre = null;//记录新合并和且的最后一个较小值
		Node next = null;
		while(cur1 != null && cur2 != null){
			if(cur1.value <= cur2.value){
				pre = cur1;//如果cur1是较小值，那么将prez指向cur1,
				cur1 = cur1.next;//然后cur1链表往下移动一个节点继续跟cur2的节点比较
			}else{//如果是cur2的值较小
				next = cur2.next;//记下链表2中cur2的下一个节点
				pre.next = cur2;//将链表2中的cur2当前节点插入到链表1中、
				cur2.next = cur1;//cur2的next指针指向cur1,这样就把链表2中cur2节点插入到了链表1中节点cur1的前一个节点
				pre = cur2;//更新pre为新插入的节点cur2
				cur2 = next;//将cur2重新指向链表2的节点
			}
			
		}//执行到这一步说明两个链表中其中一个为空
		pre.next = cur1 == null ? cur2 : cur1;
		return head;
		
	}
	public void printList(Node head){
		Node cur = head;
		while(cur != null){
			System.out.print(cur.value+" ");
			cur = cur.next;
		}
	}
	public static void main(String[] args){
		MergeLists mls = new MergeLists();
		Node head1 = mls.new Node(1);
		head1.next = mls.new Node(3);
		head1.next.next = mls.new Node(5);
		head1.next.next.next = mls.new Node(7);
		head1.next.next.next.next = mls.new Node(9);
		
		Node head2 = mls.new Node(0);
		head2.next = mls.new Node(2);
		head2.next.next = mls.new Node(3);
		head2.next.next.next = mls.new Node(7);
		
		System.out.print("Print List1: ");
		mls.printList(head1);
		System.out.println();
		System.out.print("Print List2: ");
		mls.printList(head2);
		System.out.println();
		System.out.print("Merge List1AndList2: ");
		Node head3 = mls.merge(head1, head2);
		mls.printList(head3);
	}

}
