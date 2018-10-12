package chapter2_ListProblem;


/**
 * 约瑟夫问题：39个人排成一个圈，有第一个人开始报数，报数到3的人就自杀，然后再由下一个人重新报1，报数到3的人再自杀，这样依次下去，直到剩下最后一个人。
 * @author zhangy
 *
 */
public class JosephusProblems {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}
	
	public Node josephusKill(Node head, int m){
		if(head == null || head.next == head || m < 1){//因为是循环链表，所以head.next == head表面只有一个节点
			return head;//直接返回链表，不用调整
		}
		Node last = head;
		while(last.next != head){//循环遍历使用定义的last节点从头节点位置移动到最后一个节点
			last = last.next;
		}
		int count = 0;//定义变量统计头节点移动的位置，就报数
		while(head != last){//只要不是剩下一个节点，那么就一直遍历
			if(++count == m){//如果头节点的移动的位置数 == 报数的值m,因为是从1开始报数，所以使用++count，就是对初始count先进行加1
				last.next = head.next;//那么跳过当前head节点指向的位置，即删除当前head所指的节点
				count = 0;//统计变量清零
			}else{//如果head移动的位置 ！= 报数的值
				last = last.next;//那么last节点就往下移动
			}
			head = last.next;//无论head移动的位置等不等于m，head节点都往下移动一个位置,head永远比last前进一个节点
		}
		return head;
	}
	public Node josephusKill2(Node head, int m){
		if(head == null || head.next == head || m < 1){
			return head;
		}
		Node cur = head.next;//说明循环链表头节点的下一个节点很重要
		int tmp = 1; //tmp记录list的size
		while(cur != head){
			tmp++;
			cur = cur.next;
		}
		tmp = getLive(tmp,m);//获取到存活的节点的编号
		while(--tmp != 0){
			head = head.next;//在原链表中寻找存活的节点
		}
		head.next = head;//当前head就是存活的节点，这句是为了形成循环链表
		return head;
	}
	public int getLive(int i, int m){//根据分析等到的表达式：Num(i) = [Num(i-1)+ m -1] % i + 1 求得最后存活的节点
		if(i == 1){//最后只剩下一个节点，直接返回编号1
			return 1;
		}
		return (getLive(i-1, m) + m - 1) % i + 1; 
	}
	public void printList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}
	public void printCircularList(Node head){
		if(head == null){
			return ;
		}
		System.out.print("Circular List: "+head.value +" ");
		Node cur = head.next;
		while(cur != head){
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println("-> "+head.value);
	}
	public static void main(String[] args){
		JosephusProblems JP = new JosephusProblems();
		Node head = JP.new Node(1);
		head.next = JP.new Node(2);
		head.next.next = JP.new Node(3);
		head.next.next.next = JP.new Node(4);
		head.next.next.next.next = JP.new Node(5);
		head.next.next.next.next.next = head;//循环链表，将尾节点与head节点连接起来
		Node head2 = JP.new Node(1);
		head2.next = JP.new Node(2);
		head2.next.next = JP.new Node(3);
		head2.next.next.next = JP.new Node(4);
		head2.next.next.next.next = JP.new Node(5);
		head2.next.next.next.next.next = head2;//循环链表，将尾节点与head节点连接起来
		
		JP.printCircularList(head);
		System.out.println();
		JP.printCircularList(JP.josephusKill(head, 2));
		System.out.println();
		JP.printCircularList(JP.josephusKill2(head2, 3));
	}

}
