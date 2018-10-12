package chapter2_ListProblem;

import chapter2_ListProblem.RemoveValue.Node;

/**
 * 将一个链表按要求分成左右两半，如果链表的长度N为偶数那么，前N/2为左半去，后N/2为右半区；如果链表的长度N为奇数，那么前N/2为左半区，后N/2+1为右半区。然后进行合并。
 * 例如：1->2->3->4->5->6->null,N=6, 左半区的节点个数为6/2=3，1->2->3；右半区的节点个数6/2=3,4->5->6;合并后的新链表为：1->4->2->5->3->6->null;
 * 
 * 奇数：1->2->3->4->5->null,N=5,左半区的节点个数为N/2=2,1->2,右区的个数为N/2+1=3，3->4->5,所以合并后的新链表为：1->3->2->4->5
 * @author zhangy
 *
 */
public class Relocate {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	
	public void relocate(Node head){
		if(head == null || head.next == null){
			return ;
		}
		Node mid = head;
		Node right = head.next;
		while(right.next !=null && right.next.next != null){//寻找整条链表的中间节点，或者说左半区的最后一个节点，观察规律可以发现，每增加两个节点，mid节点会向下移动一个节点。
			mid = mid.next;//每增加两个节点，mid会向下移动一个节点
			right = right.next.next;//增加两个节点
		}
		right = mid.next;//将right为右半区的第一个节点，即为中间节点mid的next节点
		mid.next = null;//将左，右半区分开，即将左半区最后一个节点指向null
		mergeLR(head , right);//然后将左，右半区合起来
	}
	
	public void mergeLR(Node left, Node right){
		Node next = null;
		while(left.next != null){//只要左半区未到达最后一个节点就继续循环
			next =right.next;//保存右半区的下一个节点
			right.next = left.next;//先将right值指向left的next值，如果在后面再写这一步的话，left的next指针可能丢失
			left.next = right;//再将left的next指针指向right,结合上一步就形成了left -> right->原先left.next的情况，而原left.next变成现在的right.next;
			left = right.next;//这里left更新到right,即原链表中的left.next
			right = next;//最后更新right指针
		}
		//这个时候left左半区的节点全部遍历完，left为左半区的最后一个节点指向右半区的最后一个节点，完成左右半区合并
		left.next = right;
	}
	public void printList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	
	public static void main(String[] args){
		Relocate r = new Relocate();
		Node head = r.new Node(1);
		head.next = r.new Node(2);
		head.next.next = r.new Node(3);
		head.next.next.next = r.new Node(4);
		head.next.next.next.next = r.new Node(5);
		head.next.next.next.next.next = r.new Node(6);
		
		r.printList(head);
		System.out.println();
		r.relocate(head);
		r.printList(head);
	}

}
