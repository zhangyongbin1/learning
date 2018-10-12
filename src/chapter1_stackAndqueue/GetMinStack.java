package chapter1_stackAndqueue;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作
 * 要求：pop、push、getMin操作的时间复杂度都是O（1）；设计的栈的类型可以使用现成的栈结构
 * 思路：主要是进行push操作时，判断number值是否应该压入到第二个辅助栈stackMin中
 * 
 * @author zhangy
 *
 */
public class GetMinStack {
	public static class MyStack1 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;//使用一个辅助栈结构，存放原始栈中的最小值

		public MyStack1() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		public void push(int newNum) {
			if (stackMin.isEmpty()) {//压入辅助栈中时需要判断一下，只有当前要压入的值小于辅助栈的栈顶值时才将其压入到辅助栈中
				stackMin.push(newNum);
			} else {
				if (stackMin.peek() >= newNum) {
					stackMin.push(newNum);
				}
			}
			stackData.push(newNum);//原始栈中肯定是要放入的
		}

		public int pop() {
			if (stackData.isEmpty()) {
				throw new RuntimeException("Your stackData is Empty");
			}
			int value = stackData.pop();
			if (value == stackMin.pop()) {
				this.stackMin.pop();
			}
			return value;
		}

		public int getMin() {//辅助栈中的栈顶就是原始栈的最小值
			if (stackMin.isEmpty()) {
				throw new RuntimeException("Your stackMin is Empty!");
			}
			return stackMin.peek();
		}
	}

	// 第二种方法:stackMin中的元素个数与stackData中的元素个数一样
	public static class MyStack2 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack2() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		public void push(int newNum) {
			if (stackMin.isEmpty()) {
				stackMin.push(newNum);
			} else if (newNum < stackMin.peek()){
				stackMin.push(newNum);
			}else{
				stackMin.push(stackMin.peek());//为保证两个栈中的元素个数一样，stackMin中重复压力较小的值
			}
			stackData.push(newNum);
		}

		public int pop() {
			if (stackData.isEmpty()) {
				throw new RuntimeException("Your stackData is Empty");
			}
			this.stackMin.pop();
			return stackData.pop();
		}

		public int getMin() {
			if (stackMin.isEmpty()) {
				throw new RuntimeException("Your stackMin is Empty");
			}
			return stackMin.peek();
		}
	}
	
	public static void main(String[] args){
		MyStack1 stack1 = new MyStack1();
		stack1.push(3);
		System.out.println(stack1.getMin());//3
		stack1.push(4);
		System.out.println(stack1.getMin());//3
		stack1.push(1);
		System.out.println(stack1.getMin());//1
		System.out.println("-------------------------------");
		System.out.println(stack1.getMin());//1
		System.out.println(stack1.pop());//1
		System.out.println("*******************************");
		MyStack2 stack2 = new MyStack2();
		stack2.push(3);
		System.out.println(stack2.getMin());//3
		stack2.push(4);
		System.out.println(stack2.getMin());//3
		stack2.push(1);
		System.out.println(stack2.getMin());//1
		System.out.println("-------------------------------");
		System.out.println(stack2.getMin());//1
		System.out.println(stack2.pop());//1
		
	}
}
