package chapter2_ListProblem;

import java.util.Stack;

/**
 * 将两个代码整数的链表相加的和形成新的链表。例如：9->3->7代表整数937，而6->3代表整数63，937+63的和为1000；生成新链表1->0->0->0
 * @author zhangy
 *
 */
public class AddList {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	
	public Node addList1(Node head1, Node head2){
		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		while(head1 != null){//因为head1节点的变化对后面的处理链表没有影响。所以直接移动头节点
			stack1.push(head1.value);
			head1 = head1.next;
		}
		while(head2 != null){
			stack2.push(head2.value);
			head2 = head2.next;
		}
		//这些变量定义在这里是因为while之外的地方也需要用到这几个变量，所以定义在while语句的外面
		int ca = 0;
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		Node pre = null;
		Node node = null;
		while(!stack1.isEmpty() || !stack2.isEmpty()){
			n1 = stack1.isEmpty() ? 0 : stack1.pop();
			n2 = stack2.isEmpty() ? 0 : stack2.pop();
			n = n1 + n2 + ca;//ca为记录是否有进位情况
			pre = node;//将pre用来记录node
			node = new Node(n % 10);//然后node赋值为新建的节点
			node.next = pre;//然后将新建的节点与前一次生成的node节点进行连接
			ca = n / 10;//计算进位的情况
		}//这里例子情况生成的链表为：0->0->0
		if(ca == 1){//如果最后还有进位，说明最高位的和也有进位，所以需要多新建一个节点
			pre = node;
			node = new Node(1);
			node.next = pre;//因为最高位有进位，所以需要生成新的头节点：1，构成新链表：1->0->0->0
		}
		return node;
	}
	
	public Node addList2(Node head1, Node head2){
		Node node = null;
		head1 = reverse(head1);
		head2 = reverse(head2);
		int ca = 0;
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		Node c1 = head1;//因为后面需要使用到这个头节点，所以不能直接移动这个链表的头
		Node c2 = head2;
		Node pre = null;
		while(c1 != null || c2 != null){//只要c1或c2其中一个不为null即可
			 n1 = c1 == null ? 0 : c1.value;//有可能c1为null
			 n2 = c2 == null ? 0 : c2.value;//有可能c2为null
			 n = n1 + n2 + ca;
			 pre = node;
			 node = new Node(n % 10);
			 node.next = pre;
			 ca = n / 10;
			 c1 = c1 != null ? c1.next : null;//移动到下一个节点继续循环处理
			 c2 = c2 != null ? c2.next : null;
		}
		if( ca == 1){
			pre = node;
			node = new Node(1);
			node.next = pre;
		}
		//复原原始链表
		reverse(head1);
		reverse(head2);
		return node;
	}
	public Node reverse(Node head){//将链表逆序
		Node rHead = null;
		Node next = null;
		while(head != null){
			next = head.next;
			head.next = rHead;
			rHead = head;
			head = next;
		}
		return rHead;
	}
	
	public void printList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	
	public static void main(String[] args){
		AddList al = new AddList();
		Node head1 = al.new Node(9);
		head1.next = al.new Node(3);
		head1.next.next = al.new Node(7);
		
		Node head2 = al.new Node(6);
		head2.next = al.new Node(3);
		
		al.printList(head1);
		System.out.println();
		al.printList(head2);
		System.out.println();
		al.printList(al.addList1(head1, head2));
		System.out.println();
		al.printList(al.addList2(head1, head2));
	}

}
