package chapter2_ListProblem;

import java.util.HashSet;

/**
 * 删除一个无序链表中的重复节点。例如：1->2->3->3->4->4->2->1->1->null,删除重复的节点之后为：1->2->3->4->null
 * @author zhangy
 *
 */
public class RemoveRepeatNode {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	
	public void removeRep1(Node head){
		if(head == null){
			return ;
		}
		HashSet<Integer> set = new HashSet<Integer>();//使用hashSet集合存储对象，与hashMap不一样，hashSet是存储对象的
		Node pre = head;//用于保存前一个节点。因为要删除一个节点，必须要知道其前一个节点和后一个节点
		Node cur = head.next;//用于保存当前节点
		set.add(head.value);//将节点的值放入set集合中
		while(cur != null){
			if(set.contains(cur.value)){//集合中已有该值
				pre.next = cur.next;//那么跳过该节点
			}else{//集合中没有该值
				set.add(cur.value);//将该值加入集合
				pre = cur;//并更新pre值
			}
			cur = cur.next;//再更新cur的值，继续循环
		}
	}
	
	public void removeRep2(Node head){
		if(head == null){
			return ;
		}
		Node cur = head;
		Node pre = null;
		Node next = null;
		while(cur != null){
			pre = cur;//为了记住删除节点的前一个节点
			next = cur.next;//内循环遍历移动的节点
			while(next != null){//内循环，遍历当前节点之后的所有节点，看有否有值相同的节点
				if(cur.value == next.value){
					pre.next = next.next;
				}else{
					pre = next;//pre往下移动一个节点到next；
				}
				next = next.next;//next往下移动一个节点继续遍历
			}
			cur = cur.next;//外循环，移动当前节点到下一个节点
		}
		
	}
	
	public void printList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value + " ");
			head = head.next;
		}
	}
	
	public static void main(String[] args){
		RemoveRepeatNode rrn = new RemoveRepeatNode();
		Node head = rrn.new Node(1);
		head.next = rrn.new Node(2);
		head.next.next = rrn.new Node(3);
		head.next.next.next = rrn.new Node(3);
		head.next.next.next.next = rrn.new Node(4);
		head.next.next.next.next.next = rrn.new Node(4);
		head.next.next.next.next.next.next = rrn.new Node(2);
		head.next.next.next.next.next.next.next = rrn.new Node(1);
		head.next.next.next.next.next.next.next.next = rrn.new Node(1);
		
		rrn.printList(head);
		System.out.println();
		//rrn.removeRep1(head);
		rrn.removeRep2(head);
		rrn.printList(head);
	}

}
