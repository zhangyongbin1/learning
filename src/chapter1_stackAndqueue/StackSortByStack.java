package chapter1_stackAndqueue;

import java.util.Stack;
/**
 * 使用一个辅助栈和一个辅助变量，将一个栈进行排序操作。从栈顶到栈底为从大到小的顺序
 * @author zhangy
 *
 */
public class StackSortByStack {

	public static void sortStackByStack(Stack<Integer> stack) {
		Stack<Integer> helpStack = new Stack<Integer>();//新建一个辅助栈结构
		while (!stack.isEmpty()) {
			int cur = stack.pop();//弹出原始栈结构的栈顶元素
			//将弹出的栈顶元素不断与辅助栈中的元素就行比较
			while (!helpStack.isEmpty() && cur > helpStack.peek()) {//这个地方要注意了，不能将这两个条件用if单独分开，分开的话会出现helpStack为空的情况
				stack.push(helpStack.pop());//如果原始栈顶元素cur的值大，那么就将辅助栈中的元素压回到原始栈中
			}
			helpStack.push(cur);//如果原始栈顶元素cur小，那么就直接压入到辅助栈中
		}
		while (!helpStack.isEmpty()) {//辅助栈中的元素是从栈顶到栈底是从小到大
			stack.push(helpStack.pop());//所以将辅助栈中的元素压回到原始栈中，实现逆序，从而是原始栈中的元素从栈顶到栈底是从大到小排序的
		}
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(5);
		stack.push(1);
		stack.push(3);
		stack.push(4);
		stack.push(2);
		sortStackByStack(stack);
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

}
