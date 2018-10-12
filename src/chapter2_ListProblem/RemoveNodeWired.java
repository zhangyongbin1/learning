package chapter2_ListProblem;
/**
 * 一种怪异的节点删除方式，例如：1->2->3->null,如果需要删除节点2，那么把节点2变成节点3，然后在链表中删除节点3即可，但这种方法根本无法删除最后一个节点，
 * 而且这种方式的办法根本就不是删除了节点2，而是把node节点的值改变，然后删除node节点的下一个节点，在实际工程中可能会带来很大的问题。比如：工程上的一个节点
 * 可能代表很复杂的结构，节点值的复制会想到复杂，或者可能改变该节点值，这个操作都是被禁止的；再如：工程上的一个节点代表提供服务的一个服务器，外界对每个节点
 * 都有很多依赖，如果示例中删除节点2时，其实影响力节点3对外提供的服务
 * @author zhangy
 *
 */
public class RemoveNodeWired {
	public class Node{
		public int value;
		public Node next;
		public Node(int data){
			this.value = data;
		}
	}

	public void removeNodeWired(Node node){
		if(node == null){
			return;
		}
		Node next = node.next;
		if(next == null){//最后一个节点的next为null，所以这种方法不能删除最后一个节点
			throw new RuntimeException("can not remove last node");
		}
		node.value = next.value;//将后一个节点的值赋值给当前需要删除掉的节点
		node.next = next.next;//然后删除当前需要删除节点的下一节点，从而间接地删除了需要删除掉的节点
	}
	public void PrintList(Node head){
		System.out.print("Print List: ");
		while(head != null){
			System.out.print(head.value+" ");
			head = head.next;
		}
	}

	public static void main(String[] args){
		RemoveNodeWired rnw = new RemoveNodeWired();
		Node head = rnw.new Node(1);
		head.next = rnw.new Node(2);
		head.next.next = rnw.new Node(3);
		rnw.PrintList(head);
		System.out.println();
		rnw.removeNodeWired(head.next);
		rnw.PrintList(head);
	}
}
