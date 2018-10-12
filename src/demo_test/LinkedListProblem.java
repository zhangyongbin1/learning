package demo_test;

import java.util.Stack;

import org.junit.Test;

/**
 * 链表问题
 * @author zhangy
 *
 */
public class LinkedListProblem {

	public class Node{
		private int value;
		private Node next;
		public Node(int date){
			this.value = date;
		}
	}
	/**
	 * 约瑟夫问题
	 */
	public Node JospherProblem(Node head, int num){
		if(head == null || head.next == null || num < 1){//说明不用调整
			return head;//直接返回
		}
		Node pre = head;
		Node cur = head;
		while(pre.next != head){
			pre = pre.next;
		}
		int count = 0;
		while(cur != pre){
			if(++count == num){
				pre.next = cur.next;
				count = 0;
			}else{
				pre = pre.next;
			}
			cur = pre.next;
		}
		return cur;
	}
	public Node josephusKill(Node head, int m){
		if(head == null || head.next == head || m < 1){//因为是循环链表，所以head.next == head表面只有一个节点
			return head;//直接返回链表，不用调整
		}
		Node last = head;
		while(last.next != head){//循环遍历使用定义的last节点从头节点位置移动到最后一个节点
			last = last.next;
		}
		int count = 0;//定义变量统计头节点移动的位置，就报数
		while(head != last){//只要不是剩下一个节点，那么就一直遍历
			if(++count == m){//如果头节点的移动的位置数 == 报数的值m,因为是从1开始报数，所以使用++count，就是对初始count先进行加1
				last.next = head.next;//那么跳过当前head节点指向的位置，即删除当前head所指的节点
				count = 0;//统计变量清零
			}else{//如果head移动的位置 ！= 报数的值
				last = last.next;//那么last节点就往下移动
			}
			head = last.next;//无论head移动的位置等不等于m，head节点都往下移动一个位置,head永远比last前进一个节点
		}
		return head;
	}
	/**
	 * 两个有序链表合并成一个链表
	 */
	public Node mergeTwoList(Node head1, Node head2){
		if(head1 == null){
			return head1 == null ? head2 : head1;
		}
		Node head = head1.value > head2.value ? head2 : head1;
		Node cur1 = head == head1 ? head1 : head2;
		Node cur2 = head == head2 ? head1 : head2;
		Node pre = null;
		Node next = null;
		while(cur1 != null && cur2 != null){
			if(cur1.value < cur2.value){
				pre = cur1;
				cur1 = cur1.next;
			}else{
				next = cur2.next;
				pre.next = cur2;
				cur2.next = cur1;
				pre = cur2;
				cur2 = next;
			}
		}
		pre.next = cur1 == null ? cur2 : cur1;
		return head;
	}
	/**
	 * 删除链表中的元素，使用辅助栈的方式
	 */
	public Node removeNode(Node head, int num){
		if(head == null){
			return null;
		}
		Stack<Node> stack = new Stack<Node>();
		while(head != null){
			if(head.value != num){
				stack.push(head);
			}
			head = head.next;
		}
		while(!stack.isEmpty()){
			stack.peek().next = head;
			head = stack.pop();
		}
		return head;
	}
	/**
	 * 删除链表中的重复元素
	 */
	public Node removeRepeatedNodes(Node head){
		if(head == null){
			return head;
		}
		Node pre = null;
		Node next = null;
		Node cur = head;
		while(cur != null){
			pre = cur;
			next = cur.next;
			while(next != null){
				if(cur.value == next.value){
					pre.next = next.next;
				}else{
					pre = next;
				}
				//这个需要拿出来
				next = next.next;
			}
			cur = cur.next;
		}
		return head;
	}
	/**
	 * 删除链表的倒数第K个点
	 */
	public Node removeKNode(Node head, int k){
		if(head == null || k < 1){
			return head;
		}
		Node cur = head;
		while(cur != null){
			k--;
			cur = cur.next;
		}
		if(k == 0){//说明倒数第K个值是头节点，这是需要删除头节点
			head = head.next;
		}
		if(k < 0){//说明链表存在倒数第K个点
			cur = head;
			while(++k != 0){
				cur = cur.next;
			}
			cur.next = cur.next.next;
		}
		return head;
	}
	@Test
	public void test(){
		Node head = new Node(1);
		head.next =new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = head;
		Node leave = JospherProblem(head,2);
		System.out.println(leave.value);
	}
	
}
