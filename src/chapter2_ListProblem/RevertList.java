package chapter2_ListProblem;
/**
 *分别实现反转单向链表和反转双向链表的函数
 * @author zhangy
 *
 */
public class RevertList {
	public class Node{//定义单向链表
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	public class DoubleNode{//定义双向链表
		public int value;
		public DoubleNode next;
		public DoubleNode pre;
		public DoubleNode(int data){
			this.value = data;
		}
	}
	public Node reverseList(Node head){//1->2->3->4->5
		Node pre = null;
		Node next = null;
		while(head != null){
			next = head.next;//使用next临时变量存放头节点的下一个节点的1->next=2->3->4->5
			head.next = pre;//将头节点的下一个节点与头节点端口连接，并连接到一个临时的空节点1=head   pre<-2->3->4->5
			pre = head;//将头节点赋值给临时空节点， pre = head=1,所以：pre=head=1<-2->3->4->5
			head = next;//将头节点移动到移动到下一个节点pre=1<-head=2->3->4->5
		}
		return pre;//此时pre节点为逆向链表的头节点，head节点为null节点，所以返回pre节点
	}
	
	public DoubleNode reverseDoubleList(DoubleNode head){
		DoubleNode pre = null;
		DoubleNode next = null;
		while(head != null){
			next = head.next;
			head.next = pre;
			head.pre = next;//让左指针等next，这样最后head.pre就是最后面的null
			pre = head;
			head = next;
		}
		return pre;
	}

	public void printList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	public void printDoubleList(DoubleNode head){
		System.out.print("Print DoubleList: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	public static void main(String[] args){
		RevertList rl = new RevertList();
		Node head = rl.new Node(1);
		DoubleNode dhead = rl.new DoubleNode(1);
		head.next = rl.new Node(2);
		head.next.next = rl.new Node(3);
		head.next.next.next = rl.new Node(4);
		head.next.next.next.next = rl.new Node(5);
		
		dhead.next = rl.new DoubleNode(2);
		dhead.pre = null;
		dhead.next.pre = dhead;
		dhead.next.next = rl.new DoubleNode(3);
		dhead.next.next.pre = dhead.next;
		dhead.next.next.next = rl.new DoubleNode(4);
		dhead.next.next.next.pre = dhead.next.next;
		dhead.next.next.next.next = rl.new DoubleNode(5);
		dhead.next.next.next.next.pre = dhead.next.next.next;
		rl.printList(head);
		System.out.println();
//		rl.reverseList(head);
		rl.printList(rl.reverseList(head));
		System.out.println();
		rl.printDoubleList(dhead);
		System.out.println();
//		rl.reverseDoubleList(dhead);
		rl.printDoubleList(rl.reverseDoubleList(dhead));
	}
}
