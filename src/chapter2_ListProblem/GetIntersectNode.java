package chapter2_ListProblem;
/**
 * 判断两个链表相交的情况：
 * 		1.先判断两个链表是否有环，有则还回第一个相交的点，无则直接返回null
 * 		2.判断无环链表的的相交情况
 * 		3.判断有环链表的相交情况
 * @author zhangy
 *
 */
public class GetIntersectNode {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	
	public Node getLoopNode(Node head){//判断一个链表是否有环
		if(head == null || head.next == null || head.next.next == null){//链表长度为0，1，2的都不可能有环
			return null;//直接返回null
		}
		Node slow = head.next;
		Node fast = head.next.next;
		while(fast != slow){
			if(fast.next == null || fast.next.next == null){//说明链表无环
				return null;//直接返回null
			}
			fast = fast.next.next;
			slow = slow.next;
		}//到这里说明有环
		fast = head;//将fast重置为head节点
		while(fast != slow){
			fast = fast.next;
			slow = slow.next;
		}//如果fast 与 slow节点再次相遇，那么相遇点就是如果的第一个节点
		return fast;
	}
	//判断两个无环链表的相交情况
	public Node noLoopIntersect(Node head1, Node head2){
		if(head1 == null || head2 == null){
			return null;
		}
		Node cur1 = head1;
		Node cur2 = head2;
		int len1 = 1;
		int len2 = 1;
		while(cur1.next != null){
			len1++;
			cur1 = cur1.next;
		}
		while(cur2.next != null){
			len2++;
			cur2 = cur2.next;
		}
		
		if(cur1 != cur2){//两个链表的尾节点不相等。说明两个链表肯定不相交
			return null;
		}
		//否则说明两个链表相交，则需要寻找第一个相交点
		cur1 = head1;
		cur2 = head2;
		if(len1 > len2){
			int n = len1 - len2;
			while(n != 0){
				n--;
				cur1 = cur1.next;
			}
			while(cur1 != cur2){
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
		}else{
			int n = len2 - len1;
			while(n != 0){
				n--;
				cur2 = cur2.next;
			}
			while(cur1 != cur2){
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
		}
		return cur1;
	}
	
	public Node bothLoop(Node head1, Node loop1, Node head2, Node loop2){
		 Node cur1 = null;
		 Node cur2 = null;
		 if(loop1 == loop2){//那么安装无环链表的情况找head1--->loop1;head2--->loop2两个链表的相交情况
			 cur1 = head1;
			 cur2 = head2;
			 int n = 0;
			 while(cur1 != loop1){
				 n++;
				 cur1 = cur1.next;
			 }
			 while(cur2 != loop2){
				 n--;
				 cur2 = cur2.next;
			 }
			 cur1 = n > 0 ? head1 : head2;
			 cur2 = cur1 == head1 ? head2 : head1;
			 n = Math.abs(n);
			 while(n != 0){
				 n--;
				 cur1 = cur1.next;
			 }
			 while(cur1 != cur2){
				 cur1 = cur1.next;
				 cur2 = cur2.next;
			 }
			 return cur1;
		 }else{
			 cur1 = loop1.next;
			 while(cur1.next != loop1){//链表在环里遍历
				 if(cur1 == loop2){//遇到loop2说明两个环相加，随便返回loop1或loop2都可以
					 return loop1;
				 }
				 cur1 = cur1.next;
			 }
			 //链表1遍历完了环也没遇到loop2,说明两个链表没相交
			 return null;
		 }
	}
	
	public Node getIntersectNode(Node head1, Node head2){
		if(head1 == null || head2 == null){
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		if(loop1 == null && loop2 == null){//两个无环链表的相加情况
			return noLoopIntersect(head1,head2);
		}
		if(loop1 != null && loop2 != null){//两个有环链表的相交情况
			return bothLoop(head1,loop1,head2,loop2);
		}
		
		return null;
	}
	public void printLoopList(Node head){
		
	}
	
	public static void main(String[] args){
		GetIntersectNode gin = new GetIntersectNode();
		Node head1 = gin.new Node(1);
		head1.next = gin.new Node(2);
		head1.next.next = gin.new Node(3);
		head1.next.next.next = gin.new Node(4);
		head1.next.next.next.next = gin.new Node(5);
		//head1.next.next.next.next.next = head1.next.next;
		
		Node head2 = gin.new Node(1);
		head2.next = gin.new Node(2);
		head2.next.next = gin.new Node(3);
		head2.next.next.next = gin.new Node(4);
		head2.next.next.next.next = gin.new Node(5);
		head2.next.next.next.next.next = head1.next.next;//1->2->3->4->5->3->4->5->null
		System.out.println(gin.getIntersectNode(head1, head2).value);//两个无环的第一个交点
		
		Node head3 = gin.new Node(1);
		head3.next = gin.new Node(2);
		head3.next.next = gin.new Node(3);
		head3.next.next.next = gin.new Node(4);
		head3.next.next.next.next = gin.new Node(5);
		head1.next.next.next.next.next = head1.next.next;//5->3
		
		Node head4 = gin.new Node(1);
		head4.next = gin.new Node(2);
		head4.next.next = gin.new Node(3);
		head4.next.next.next = gin.new Node(4);
		head4.next.next.next.next = gin.new Node(5);
		head4.next.next.next.next.next = head3.next.next;//1->2->3->4->5->3->4->5-> 3,4,5循环
		System.out.println(gin.getIntersectNode(head3, head4).value);//两个有环的链表的第一个交点
		
	}

}
