package chapter2_ListProblem;
/**
 * 分别实现两个函数，一个可以删除单链表中倒数第k个节点，另一个可以删除双链表中倒数第k个节点
 * @author zhangy
 *
 */
public class RemoveLastKthNode {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	public Node removeSingleListLastKthNode(Node head, int k){
		if(head == null || k < 1){
			return head;
		}
		/*Node head1 = head;
		Node head2 = head;*/
		Node cur = head;
		while(cur != null){//为了验证k值与链表长度的关系，k > 0说明k的值大于链表的长度，此时应该是没有倒数第k个值，可以直接返回head
			k--;
			cur = cur.next;
		}
		if(k == 0){//k = 0说明k与链表长度相同，所以倒数第k个值即head头
			head = head.next;//将表头向前移动一个位置，即删除倒数第k个数
		}
		if(k < 0){//k < 0说明链表有倒数第k个位置，因为此时的k值应该是head离倒数第k+1一个位置的距离的负数
			cur = head;//重新遍历链表
			while(++k != 0){//如果k值为负数，则继续往前一直到倒数第k+1个数
				cur = cur.next;
			}
			cur.next = cur.next.next;//删除倒数第k个位置处的数
		}
		return head;
		/*while(head1 != null || head2 != null){
			for(int i = 0; i <= k; i++){
				head1 = head1.next;
			}
			head1 = head1.next;
			head2 = head2.next;
		}//此时head2节点已经执行到倒数第k+1个位置，下面需要删除倒数第k个节点
		head2.next = head2.next.next;//head2.next跳过倒数第k个节点
		return head;//这时候head头节点所代表的链表的倒数第k个位置已经被删除了
*/	}
	public class DoubleNode{
		public int value;
		public DoubleNode last;//双向链表就是多了一个指针,相当于head指针可以定位next一个节点和head的前一个节点
		public DoubleNode next;
		public DoubleNode(int data){
			this.value = data;
		}
	}
	//双向链表就是要记住把last指针也需要重置
	public DoubleNode removeDoubleListLastKthNode(DoubleNode head, int k){
		if(head == null || k < 1){
			return head;
		}
		DoubleNode cur = head;
		while(cur != null){//k的负数值的绝对值就是，倒数第k+1个数的角标位置
			k--;
			cur = cur.next;
		}
		if(k == 0){
			head = head.next;//跳过当前头节点，即删除倒数第k个节点
			head.last = null;//新的头节点的前一个节点为空
		}
		if(k < 0){
			cur = head;//重新开始遍历链表
			while(++k != 0){
				cur = cur.next;
			}
			DoubleNode newNext = cur.next.next;
			cur.next = newNext;//删除倒数第k个节点
			if(newNext != null){//更新last节点
				newNext.last =cur;//新的倒数第k个节点的前一个节点为cur,为了更新last节点
			}
		}
		return head;
	}
	public void prinList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	public void prinDoubleList(DoubleNode head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	public static void main(String[] args){
		RemoveLastKthNode rlkn = new RemoveLastKthNode();
		Node head = rlkn.new Node(1);
		DoubleNode dhead = rlkn.new DoubleNode(1);
		head.next = rlkn.new Node(2);
		head.next.next = rlkn.new Node(3);
		head.next.next.next = rlkn.new Node(4);
		head.next.next.next.next = rlkn.new Node(5);
		
		dhead.next = rlkn.new DoubleNode(2);
		dhead.last = null;
		dhead.next.last = dhead;
		dhead.next.next = rlkn.new DoubleNode(3);
		dhead.next.next.last = dhead.next;
		dhead.next.next.next = rlkn.new DoubleNode(4);
		dhead.next.next.next.last = dhead.next.next;
		dhead.next.next.next.next = rlkn.new DoubleNode(5);
		dhead.next.next.next.next.last = dhead.next.next.next;
		
		rlkn.prinList(head);
		rlkn.removeSingleListLastKthNode(head, 2);
		System.out.println();
		rlkn.prinList(head);
		
		System.out.println();
		
		rlkn.prinDoubleList(dhead);
		rlkn.removeDoubleListLastKthNode(dhead, 2);
		System.out.println();
		rlkn.prinDoubleList(dhead);
		System.out.println();
		System.out.println(dhead.next.last.value);
		
	}
}
