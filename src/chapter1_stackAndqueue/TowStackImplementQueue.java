package chapter1_stackAndqueue;

import java.util.Stack;

/**
 * 编写一个类。用两个栈实现队列，支持队列的基本操作(add、poll、peek)
 * @author zhangy
 *
 */
public class TowStackImplementQueue {
	public static class MyQueue{
		//初始化两个栈结构，一个拥有实现队列的add操作，一个拥有实现队列的poll操作
		private Stack<Integer> stackPush;
		private Stack<Integer> stackPop;
		public MyQueue(){
			this.stackPush = new Stack<Integer>();
			this.stackPop = new Stack<Integer>();
		}
		public void add(int newNum){//直接加入到push栈结构中
			stackPush.push(newNum);
		}
		
		public int poll(){
			if(stackPush.isEmpty() && stackPop.isEmpty()){//说明队列中是空的
				throw new RuntimeException("Your Queue");
			}else if(stackPop.isEmpty()){//必须保证stackPop为空才能往里面添加数据
				while(!stackPush.isEmpty()){
					stackPop.push(stackPush.pop());//将栈stackPush中的数据一次性全部放入stackPop栈中
				}
			}
			return stackPop.pop();
		}
		
		public int peek(){
			if(stackPush.isEmpty() && stackPop.isEmpty()){
				throw new RuntimeException("Your Queue");
			}else if(stackPop.isEmpty()){
				while(!stackPush.isEmpty()){//每一次都是把push栈中当前的数一次全部压入空的pop栈中
					stackPop.push(stackPush.pop());//使用push栈结构的pop方法就会使数据从push栈结构中弹出
				}
			}//如果pop栈不为空，说明上一次从push栈中得到的数据还没有pop完，直接放回pop栈中的操作即可
			return stackPop.peek();
		}
	}
	
	public static void main(String[] args){
		MyQueue mq = new MyQueue();
		mq.add(1);
		System.out.println(mq.peek());//1
		System.out.println(mq.poll());//1
		mq.add(2);
		mq.add(3);
		System.out.println(mq.peek());//2
		System.out.println(mq.poll());//2
		mq.add(4);
		System.out.println(mq.peek());//3
		System.out.println(mq.poll());//3
		
		System.out.println(mq.peek());//4
		System.out.println(mq.poll());//4
	}
}
