package com.learning.MultiThread;

import org.junit.Test;

public class OrderPrintThread {
	public synchronized void print(String str){
		notify();//不会立刻释放锁
		System.out.println(str);
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class A implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i = 1; i <= 100; i += 2){
				print("A"+i);
			}
		}
		
	}
	class B implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i = 2; i <= 100; i += 2){
				print("B"+i);
			}
		}
		
	}
	
	@Test
	public void test(){
//		OrderPrintThread opt = new OrderPrintThread();
		new Thread(new A()).start();
		new Thread(new B()).start();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
