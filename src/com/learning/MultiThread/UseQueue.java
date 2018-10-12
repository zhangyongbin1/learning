package com.learning.MultiThread;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class UseQueue {
	public static void main(String[] args) throws Exception {
		// 高性能无界无阻塞队列concurrentLinkedQueue
		final ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<String>();
		//put方法是阻塞的，而add方法是跟一般的队列一样会报queue full的错误
//		final ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(10);
		final LinkedBlockingQueue<String> array = new LinkedBlockingQueue(10);
		/*for(int i = 0; i < 100; i++){
			clq.add(i+"");
		}
		System.out.println(clq.size());
		System.out.println(clq.peek());
		System.out.println(clq.poll());
		System.out.println(clq.size());*/
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						array.put(Thread.currentThread().getName()+", "+1);
						System.out.println(Thread.currentThread().getName()+",添加元素 "+1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		},"t1");
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						String take = array.take();
						System.out.println(Thread.currentThread().getName()+",移除元素 "+take);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		},"t2");
		t1.start();
		t2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Iterator<String> iterator = array.iterator();
//		while(iterator.hasNext()){
//			System.out.println(iterator.next());
//		}
		
	}
	
	@Test
	public void testArrayBlockingQueue() throws Exception{//有界阻塞队列
		ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
		arrayBlockingQueue.put("a");
		arrayBlockingQueue.put("b");
		arrayBlockingQueue.put("c");
		arrayBlockingQueue.put("d");
		arrayBlockingQueue.put("e");
		//arrayBlockingQueue.put("f");
		//offer()是将元素插入到队列末尾，如果插入成功返回true，如果插入失败(队列已满，则返回false)
		/**
		 * put方法用来向队尾存入元素，如果队列满，则等待；

		　　take方法用来从队首取元素，如果队列为空，则等待；
		
		　　offer方法用来向队尾存入元素，如果队列满，则等待一定的时间，当时间期限达到时，如果还没有插入成功，则返回false；否则返回true；
		
		　　poll方法用来从队首取元素，如果队列空，则等待一定的时间，当时间期限达到时，如果取到，则返回null；否则返回取得的元素；
		 */
		System.out.println(arrayBlockingQueue.offer("a",3,TimeUnit.SECONDS));
	}
	@Test
	public void testLinkedBlockingQueue(){
		//无解阻塞队列，如果指定了初始化参数就会变成有界阻塞队列
		final LinkedBlockingQueue array = new LinkedBlockingQueue(10);
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						array.put(Thread.currentThread().getName()+",添加元素"+1);
						System.out.println(Thread.currentThread().getName()+",添加元素"+1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		},"t1");
		t1.start();
	}

}
