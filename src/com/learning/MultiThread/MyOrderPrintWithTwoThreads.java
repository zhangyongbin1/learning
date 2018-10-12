package com.learning.MultiThread;

import org.junit.Test;

public class MyOrderPrintWithTwoThreads {

	private volatile int count;
	private Object lock = new Object();

	public void setCount(int data) {
		this.count = data;
	}

	public void printOddNumber() {
		synchronized (lock) {
				try {
					while (count >= 0) {
						if (this.count % 2 == 0) {
							lock.wait();
							if(count < 0){
								System.out.println("------------------");
								break;
							}
						}
						System.out.println("当前线程:"+Thread.currentThread()+"输出： "+this.count);
						count--;
						lock.notify();
					} 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	public void printEvenNumber() {
		synchronized (lock) {
				try {
					while (count >= 0) {
						if (this.count % 2 != 0) {
							lock.wait();
						}
						System.out.println("当前线程:"+Thread.currentThread()+"输出： "+this.count);
						count--;
						lock.notify();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	@Test
	public void test() {
		final MyOrderPrintWithTwoThreads mop = new MyOrderPrintWithTwoThreads();
		mop.setCount(100);
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mop.printEvenNumber();
			}
			
		},"t1");
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mop.printOddNumber();
			}
			
		},"t2");
		t1.start();
		t2.start();
		//主线程不能停止
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
