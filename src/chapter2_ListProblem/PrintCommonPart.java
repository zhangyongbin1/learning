package chapter2_ListProblem;//链表问题

public class PrintCommonPart {
	public class Node{//定义一个链表
		public int value;//链表节点的值
		public Node next;//链表当前节点的下一个节点
		public Node(int data){//构造函数给链表节点赋值
			this.value = data;
		}
	}
	//处理的是有序链表，如果是非有序链表可以使用排序算法交换节点值的方法来排序，然后在处理
	public void printCommomPart(Node head1, Node head2){
		System.out.print("Common Part: ");
		while(head1.next != null && head2.next != null){//只要链表的下一个节点不为空，那么就去比较两个节点的值
			if(head1.value < head2.value ){//如果节点1比节点2的值小
				head1 = head1.next;//那么节点1往下移动一个节点
			}else if(head1.value > head2.value ){//如果节点1的比节点2的值大
				head2 = head2.next;//那么节点2往下移动一个节点
			}else{//如果两个节点的值相等，那么打印当前节点的值，并且两个节点的值都往下移动
				System.out.print(head1.value);
				head1 = head1.next;
				head2 = head2.next;
			}
		}
	}
	public void printList(Node head){//打印链表
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	public static void main(String[] args){
		PrintCommonPart pcp = new PrintCommonPart();
		Node head1 = pcp.new Node(1);
		Node head2 = pcp.new Node(2);
		Node node3 = pcp.new Node(3);
		Node node4 = pcp.new Node(4);
		Node node5 = pcp.new Node(5);
		Node node6 = pcp.new Node(6);
		Node node7 = pcp.new Node(7);
		Node node7s = pcp.new Node(7);
		Node node9= pcp.new Node(9);
		Node node8 = pcp.new Node(8);
//		head1.next = node3;
//		head2.next = node4;
//		node3.next = node5;
//		node5.next = node7;
//		node4.next = node6;
//		node6.next = node7s;
//		node7.next = node9;
//		node7s.next = node8;
		head1.next = node3;//将链表节点连接起来
		head2.next = node4;
		head1.next.next = node5;
		head1.next.next.next = node7;
		head2.next.next = node6;
		head2.next.next.next = node7s;
		head1.next.next.next.next = node9;
		head2.next.next.next.next = node8;
		pcp.printList(head1);
		System.out.println();
		pcp.printCommomPart(head1, head2);
	}

}
