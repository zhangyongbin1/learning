package chapter2_ListProblem;
/**
 * 将给点的整数值num，插入到有序的环形链表中使得新的环形链表也是有序的
 * @author zhangy
 *
 */
public class InsertNumToList {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	public Node insertNum(Node head, int num){
		Node node = new Node(num);
		if(head == null){
			node.next = node;
			return node;
		}
		Node pre = head;
		Node cur = head.next;
		while(cur != head){//因为是环形链表，所以需要用头节点head作为判断循环的条件
			if(pre.value <= num && cur.value >= num){
				 break;//找到了num需要插入的位置，所以跳出到while循环之后进行节点的插入处理
			}
			pre = cur;
			cur = cur.next;
		}//到这一步说明对链表遍历了一圈也没找到符合条件的位置。说明num值要么是最大值，要么是最小值，但是无论是最大值还是最小值，都是将其放置在pre之后，cur之前
		pre.next = node;
		node.next = cur;
		return head.value > num ? node : head;//如果num是最大值，那么则返回原链表头节点即可，如果num是最小值，则返回node节点即可
	}
	
	public void printList(Node head){
		System.out.print("Print Cycle List: ");
		System.out.print(head.value + " -> ");
		Node cur = head.next;
		while(cur != head){
			System.out.print(cur.value + " -> ");
			cur = cur.next;
		}
		System.out.print(cur.value);
	}
	
	public static void main(String[] args){
		InsertNumToList intl = new InsertNumToList();
		Node head = intl.new Node(1);
		head.next = intl.new Node(3);
		head.next.next = intl.new Node(4);
		head.next.next.next = intl.new Node(5);
		head.next.next.next.next = head;
		intl.printList(head);
		System.out.println();
		int num = 2;
		intl.printList(intl.insertNum(head, num));
	}

}
