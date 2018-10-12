package chapter2_ListProblem;


/**
 * 给定链表的头节点head，实现删除链表的中间节点的函数
 * 例如：
 * 	不删除任何节点
 * 	1->2删除节点1；
 * 	1->2->3 删除节点2
 * 	1->2->3->4 删除节点2
 * 	1->2->3->4->5  删除节点3     规律：也就是每增加两个节点，要删除的节点往后移动一个节点
 * 进阶问题：计算double r = ((double)(a * n))/((double) b),然后向上取整之后的整数值代表该删除的节点是第几个节点
 * @author zhangy
 *
 */
public class RemoveNodeByRatio {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	//链表的长度每增加2，那么需要删除的middle节点就往后移动一个
	public Node removeMiddleNode(Node head){
		if(head ==null || head.next == null){//链表的长度为1，那么链表就不需要调整
			return head;
		}
		if(head.next.next == null){//说明链表只有两个节点，链表的长度为2时，需要删除链表的头节点
			head = head.next;//说明删除了head节点
			return head;
		}
		Node pre = head;//头节点，为了确定删除的节点位置
		Node cur = head.next.next;//是第三个节点,主要是为判断是否右移两个节点后，其是否还能继续右移两个节点
		while(cur.next != null && cur.next.next != null){//保证第四个节点不为空，且第五个节点不为空，如果还可以右移两个节点，就继续右移
			pre = pre.next;//往前增加一个节点，说明需要删除的middle节点需要往前移动一个
			cur = cur.next.next;//那么继续往前增加两个节点
		}
		//应该删除pre.next这个节点
		pre.next = pre.next.next;//删除middle位置处的节点
		return head;
	}
	
	public Node removeNodeByRatio(Node head, int a, int b){
		if(a < 1 || a > b){
			return head;//如果 a > b，也就是 r = a / b >1,根据题意，这时候不删除任何节点，则直接返回头节点
		}
		int length = 0;
		Node cur = head;//最好不要使用头指针直接进行操作，要不然就后面要用到头指针就没有
		while(cur != null){//统计链表的的长度
			length++;
			cur = cur.next;
		}
		//计算需要删除节点的
		length =(int)Math.ceil((double)(a * length) / ((double)b));//向上取整
		if(length == 1){//说明需要删除的是第一个节点
			head = head.next;//所以直接把头节点删除
		}
		if(length > 1){
			cur = head;//准备重新遍历链表
			while(--length != 1){//循环到需要删除节点的前一个节点
				cur = cur.next;
			}
			cur.next = cur.next.next;//直接跳过需要删除的节点即可把需要删除的节点删除
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
		RemoveNodeByRatio rnbr = new RemoveNodeByRatio();
		Node head = rnbr.new Node(1);
		head.next = rnbr.new Node(2);
		head.next.next = rnbr.new Node(3);
		head.next.next.next = rnbr.new Node(4);
		head.next.next.next.next = rnbr.new Node(5);
		rnbr.printList(head);
		System.out.println();
//		rnbr.removeMiddleNode(head);
		head = rnbr.removeNodeByRatio(head, 1, 5);
		System.out.println();
		rnbr.printList(head);
		
		
	}
}
