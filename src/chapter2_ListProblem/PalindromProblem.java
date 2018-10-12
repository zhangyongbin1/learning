package chapter2_ListProblem;

import java.util.Stack;

/**
 * 回文问题：就是从左到右，与从右到左的值是一样的。例如：1->2->2->1,从右到左：1->2->2->1，所以这是个回文结构
 * @author zhangy
 *
 */
public class PalindromProblem {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	
	public boolean isPalindrome1(Node head){
		boolean flag = true;
		Stack<Node> stack = new Stack<Node>();
		Node cur = head;
		while(cur != null){
			stack.push(cur);
			cur = cur.next;
		}
		while(head != null){
			if(stack.pop().value != head.value){//如果栈中pop出来的值与对应位置的链表中的值不相等，则表示回文结构，直接返回false
				flag = false;
				return flag;
			}
			head = head.next;
		}
		return flag;
	}
	
	public boolean isPalindrome2(Node head){//方法二是使用对折相等的方法，将链表分成两部分，右半部分压入栈中逆序后，再与左半部份比较
		boolean flag = true;
		if(head == null || head.next == null){//如果链表为空或者只有一个节点，那这个链表肯定是回文结构
			flag = true;
		}
		Node right = head.next;
		Node cur = head;
		while(cur.next != null && cur.next.next != null){//为了使right位置第len/2+1的位置上
			right = right.next;
			cur = cur.next.next;
		}
		Stack<Node> stack = new Stack<Node>();
		while(right != null){//right此时位于len/2+1的节点上
			stack.push(right);
			right = right.next;
		}
		while(!stack.isEmpty()){
			if(stack.pop().value != head.value){//右半部分压栈逆序后，与左右部分的值依次比较.如果不相等，那么直接返回false
				flag = false;
				return flag;
			}
			head = head.next;
		}
		return flag;
	}
	
	public boolean isPalindrome3(Node head){
		if(head == null || head.next == null){
			return true;
		}
		Node n1 = head;
		Node n2 = head;
		while(n2.next != null && n2.next.next != null){//查找中间点
			n1 = n1.next;//n1->中部
			n2 = n2.next.next;//n2 -> 结尾
		}
		n2 = n1.next; // n2 -> 右部分第一个节点
		n1.next = null;//mid.next -> null
		Node n3 = null;
		while(n2 != null){//反转右半部分
			n3 = n2.next;//n3 -> 保存下一个节点
			n2.next = n1;//下一个反转点
			n1 = n2;// n1移动
			n2 = n3;//n2移动
		}
		n3 = n1;//n3 -> 保存最后一个节点,此时n1相当于反转部分的头节点，n2为空节点
		n2 = head;//n2 ->左边第一个节点
		Boolean res = true;
		while(n1 != null && n2 != null){//从左边部分的头节点和右半部分的头节点进行遍历比较
			if(n1.value != n2.value){//只要出现值不相等的情况就直接返回false
				res = false;
				break;
			}
			n1 = n1.next;//如果值相等，反转部分往下一个节点移动
			n2 = n2.next;//如果值相等，那么左半部份节点继续往下移动
		}//走到这一步，则n1和n2都为null
		n1 = n3.next;//保存反转部分的头节点的下一个节点
		n3.next = null;
		while(n1 != null){//将反转部分发转回来，保证原链表结果
			n2 = n1.next;
			n1.next = n3;
			n3 = n1;
			n1 = n2;
		}
		return res;
	}
	public void printList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	public static void main(String[] args){
		PalindromProblem pp = new PalindromProblem();
		Node head = pp.new Node(1);
		head.next = pp.new Node(2);
		head.next.next = pp.new Node(3);
		head.next.next.next = pp.new Node(4);
		head.next.next.next.next = pp.new Node(5);
		pp.printList(head);
		System.out.println();
		System.out.println(pp.isPalindrome1(head));
		System.out.println(pp.isPalindrome2(head));
		System.out.println(pp.isPalindrome3(head));
		
	}

}
