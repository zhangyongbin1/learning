package com.learning.MultiThread;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {
	//需要定义一个集合用于存放数据
	private static volatile LinkedList<Object> list = new LinkedList<Object>();
	//需要一个计数器
	private AtomicInteger count = new AtomicInteger(0);
	//需要一个对象锁用于同步
	final Object lock = new Object();
	//统计队列的上限和下限值
	private int maxSize;
	private int minSize;
	//提供一个初始化构造方法
	public MyQueue(int size){
		this.maxSize = size;
	}
	public void put(Object object){
		synchronized(lock){
			try {
				if(count.get() == this.maxSize){
					lock.wait();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(object);
			count.incrementAndGet();
			lock.notify();
			System.out.println("新加入的元素："+object);
		}
	}
	public Object take(){
		Object ret = null;
		synchronized(lock){
			try {
				if(count.get() == this.minSize){
					lock.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ret = list.removeFirst();
			count.decrementAndGet();
			lock.notify();
		}
		return ret;
	}
	public int getSize(){
		return count.get();
	}
	public static void main(String[] args){
		final MyQueue my = new MyQueue(5);
		my.put("1");
		my.put("2");
		my.put("3");
		my.put("4");
		my.put("5");
		System.out.println("当前队列的size:"+my.getSize());
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				my.put("6");
				my.put("7");
			}
			
		},"t1");
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				Object obj1 = my.take();
				System.out.println("移除的元素为："+obj1);
				Object obj2 = my.take();
				System.out.println("移除的元素为："+obj2);
			}
			
		},"t2");
		t1.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
	}
}
