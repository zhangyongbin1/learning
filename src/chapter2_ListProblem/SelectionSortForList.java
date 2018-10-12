package chapter2_ListProblem;
/**
 * 给定义无序的单链表，实现链表的选择排序
 * @author zhangy
 *
 */
public class SelectionSortForList {
	
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	public Node selectionSort(Node head){
		Node tail = null;//排序部分尾部
		Node cur = head;//为排序部分头部
		Node smallPre = null;//最小节点的前一个节点
		Node small = null; //最小的节点
		while(cur != null){
			small = cur;
			smallPre = getSmallestPreNode(cur);//以当前节点cur为头节点的为排序部分的最小值
			if(smallPre != null){//将small节点删除
				small = smallPre.next;
				smallPre.next = small.next;
			}
			cur = cur == small ? cur.next : cur;//cur移动到下一个节点继续循环寻找小值
			if(tail == null){
				head = small;//说明small是第一个小值，就是最小值，所以为新排序好的连表的头
			}else{
				tail.next = small;//将值小的节点放在排序好的链表部分的尾部
			}
			tail = small;//更新排序部分的尾节点
		}
		return head;
	}
	public Node getSmallestPreNode(Node head){//获取未排序部分的最小值的前一个节点
		Node smallpre = null;
		Node small = head;
		Node cur = head.next;
		Node pre = head;
		while(cur != null){
			if(cur.value < small.value){//small用于固定当前的最小值，cur用不断移动到下一个节点进行链表的遍历
				smallpre = pre;
				small = cur;
			}
			pre = cur;
			cur = cur.next;
		}
		return smallpre;
	}
	
	public void printList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value + " ");
			head = head.next;
		}
	}
	
	public static void main(String[] args){
		SelectionSortForList ssfl = new SelectionSortForList();
		Node head = ssfl.new Node(4);
		head.next = ssfl.new Node(1);
		head.next.next = ssfl.new Node(3);
		head.next.next.next = ssfl.new Node(2);
		head.next.next.next.next = ssfl.new Node(5);
		ssfl.printList(head);
		System.out.println();
//		ssfl.selectionSort(head);
		System.out.println();
		ssfl.printList(ssfl.selectionSort(head));
	}

}
