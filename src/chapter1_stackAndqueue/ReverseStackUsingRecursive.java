package chapter1_stackAndqueue;

import java.util.Stack;

/**
 * 如何仅用递归函数和栈操作逆序一个栈，只能使用递归函数
 * 思路：主要是利用递归函数的性质：递归函数是逐层返回值，每一层的返回值都保存在一个栈中，只有这层的值返回了，栈才会销毁
 * 两个栈可以实现逆序，所以两个递归函数也是可以实现逆序的，递归函数每次return都是返回上次调用递归函数的地方
 * @author zhangy
 *
 */
public class ReverseStackUsingRecursive {
	
	public static int getAndRemoveLastElement(Stack<Integer> stack){
		int result = stack.pop();
		if(stack.isEmpty()){
			return result; //说明此时的元素是栈底元素。将其直接返回,由于递归函数的性质，此时程序返回到递归的第二层resutl的值
		}
		int last = getAndRemoveLastElement(stack);//如果栈不是空的那么使用递归函数的特点
		stack.push(result);//将栈中非栈底元素重新压回栈中
		return last;//在这个递归函数中只返回同一个值，也就是说每一层return都是返回第一次的栈底元素，方便把其余原始再push回去
	}
	
	public static void reverse(Stack<Integer> stack){
		if(stack.isEmpty()){
			return;
		}
		int i  = getAndRemoveLastElement(stack);//获取栈底元素并且删除栈底元素，这样栈中的元素就是每次递归少一个元素
		reverse(stack);//为了使用递归的性质，循环地取出栈中的元素，并且可以逐层递返回每次从栈中取出的元素
		stack.push(i);//从最后一层开始，逐层地将getAndRemoveLastElement获取的栈底数据压回到栈中，从而实现了对栈的逆序
	}
	
	public static void main(String[] args){
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		reverse2(stack);
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
	}
	
	public static int getAndRemoveLastElements2(Stack<Integer> stack){
		int result = stack.pop();
		if(stack.isEmpty()){
			return result;
		}
		int last = getAndRemoveLastElements2(stack);
		stack.push(result);
		return last;//这一步很重要，保证了getAndRemoveLastElements2方法返回的是每一次调用该方法是的栈底元素
	}
	
	public static void reverse2(Stack<Integer> stack){
		if(stack.isEmpty()){
			return ;
		}
		int i = getAndRemoveLastElements2(stack);
		reverse2(stack);
		stack.push(i);
	}

}
