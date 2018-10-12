package chapter2_ListProblem;

import java.util.Stack;

/**
 * 将一个链表的每K个节点进行翻转,例如：1->2->3->4->5->6->7->8->null,翻转后的结果为：3->2->1->6->5->4->7->8->null
 * @author zhangy
 *
 */
public class ReverseKNodes {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	
	public Node reverseKNode1(Node head, int k){
		if(k < 2 || head == null){//如果k == 1,只逆序一个节点，完全没有意义，还是一样的
			return head;
		}
		Stack<Node> stack = new Stack<Node>();//使用栈将每k个节点逆序
		Node newHead = head;//用于记录新链表的头
		Node cur = head;//用于遍历链表
		Node pre = null;//用于记录逆序的起始位置
		Node next = null;//用于记录逆序的下一个位置
		while(cur != null){//遍历链表
			next = cur.next;
			stack.push(cur);//将节点压入栈中
			if(stack.size() == k){//如果栈中的节点数刚好为k个，那么这k个就是要逆序的一组
				pre = resignl(stack, pre, next);//重新组合链表,逆序第一组时，next为k+1个节点
				newHead = newHead == head ? cur : newHead;//记录新链表的头
			}
			cur = next;//移动到下一个节点继续遍历
		}
		return newHead;//返回新链表的头
	}

	public Node reverseKNode2(Node head, int k){
		if(k < 2){
			return head;
		}
		Node cur = head;//为了遍历链表
		Node start = null;//为了记录每一组需要逆序的部分的开始节点
		Node pre = null;//为了记录每一组需要逆序的部分的开始节点的前一个节点
		Node next = null;//为了记录每一组需要逆序的部分的最后一个节点的下一个节点
		int count = 1;//为了记录遍历的节点数量
		while(cur != null){//遍历链表
			next = cur.next;//记录下一个节点
			if(k == count){//说明遍历了k个节点
				start = pre == null ? head : pre.next;//如果开始的前一个节点为null，说明start应该是head节点，pre != null,start = pre.next
				head = pre == null ? cur : head;//新链表的头节点
				resign2(pre, start, cur, next);//逆序k个节点并且重连到原链表从而形成新的链表
				pre = start;//更新pre指向，start经过逆序后从开始节点变成了最后一个节点
				count = 0;//重置count值
			}
			count++;//记录遍历了多少个节点
			cur = next;//更新cur，继续循环变量
		}
		return head;
	}
	public Node resignl(Stack<Node> stack, Node left, Node right){//将栈中的节点重新连接起来
		Node cur = stack.pop();
		if(left != null){
			left.next = cur;
		}
		Node next = null;
		while(!stack.isEmpty()){
			next = stack.pop();
			cur.next = next;
			cur = next;
		}
		cur.next = right;
		return cur;//返回的是stack中的栈底元素
	}
	
	public void resign2(Node left, Node start, Node end, Node right){//不使用stack结构逆序且重连，而使用辅助变量逆序并重连
		Node pre = start;//逆序开始的节点
		Node cur = start.next;//开始的下一个节点
		Node next = null;
		while(cur != right){
			next = cur.next;//逆序的下一个节点的下一个节点
			cur.next = pre;//断开start.next 与 start.next.next连接,使start.next -> start从而逆向
			pre = cur;//移动更新pre指针
			cur = next;//移动更新cur指针
		}
		if(left != null){//end节点为逆序前，需要逆序的部分的最后一个节点，逆序后，end节点就成为了第一个节点
			left.next = end;
		}
		start.next = right;//逆序好的最后一个节点连接剩下未逆序的第一个节点
	}
	
	public void printList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value + " ");
			head = head.next;
		}
	}
	public static void main(String[] args){
		ReverseKNodes rkn = new ReverseKNodes();
		Node head = rkn.new Node(1);
		head.next = rkn.new Node(2);
		head.next.next = rkn.new Node(3);
		head.next.next.next = rkn.new Node(4);
		head.next.next.next.next = rkn.new Node(5);
		head.next.next.next.next.next = rkn.new Node(6);
		head.next.next.next.next.next.next = rkn.new Node(7);
		head.next.next.next.next.next.next.next = rkn.new Node(8);
		
		Node head2 = rkn.new Node(1);
		head2.next = rkn.new Node(2);
		head2.next.next = rkn.new Node(3);
		head2.next.next.next = rkn.new Node(4);
		head2.next.next.next.next = rkn.new Node(5);
		head2.next.next.next.next.next = rkn.new Node(6);
		head2.next.next.next.next.next.next = rkn.new Node(7);
		head2.next.next.next.next.next.next.next = rkn.new Node(8);
		
		rkn.printList(head);
		System.out.println();
		rkn.printList(rkn.reverseKNode1(head, 3));
		System.out.println();
		rkn.printList(rkn.reverseKNode2(head2, 3));
	}
}
