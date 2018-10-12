package chapter2_ListProblem;

import java.util.Stack;

/**
 * 将链表中节点值为num的节点全部删除。例如：1->2->3->4->null,num=3,则删除之后的链表为:1->2->4->null
 * @author zhangy
 *
 */
public class RemoveValue {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	
	public Node removeValue1(Node head, int num){
		Stack<Node> stack = new Stack<Node>();//借助使用栈结构存放不等与num的节点，然后再将stack中的节点连接成链表
		while(head != null){
			if(head.value != num){
				stack.push(head);
			}
			head = head.next;
		}
		while(!stack.isEmpty()){//此时的head为null
			stack.peek().next = head;//将peek指向head节点
			head = stack.pop();//更新head节点为当前弹出的节点，其实也就是stack.peek()节点
		}
		return head;
	}
	
	public Node removeValue2(Node head, int num){
		while(head != null){//删除节点在头尾部的情况
			if(head.value != num){
				break;
			}
			head = head.next;
		}
		Node pre = head;//为了记录要删除节点的前一个节点
		Node cur = head;//当前需要删除的节点
		Node cur1 = null;
		while(cur != null){//遍历链表
			if(cur.value == num){//如果当前节点值=num
				pre.next = cur.next;//删除当前节点
				//cur1 = cur;
			}else{
				pre = cur;//记录当前节点
			}
			cur = cur.next;//当前节点往下移动
		}
		
		return head;
	}

	public void printList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	public static void main(String[] args){
		RemoveValue rv = new RemoveValue();
		Node head = rv.new Node(1);
		head.next = rv.new Node(2);
		head.next.next = rv.new Node(3);
		head.next.next.next = rv.new Node(4);
		head.next.next.next.next = rv.new Node(5);
		
		rv.printList(head);
		System.out.println();
		//rv.printList(rv.removeValue1(head, 3));
		rv.printList(rv.removeValue2(head, 3));
		
	}
}
