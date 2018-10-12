package com.learning.MultiThread;

import java.util.concurrent.CountDownLatch;

public class MyCountDownLatchUse {
	
	public static void main(String[] args){
		final CountDownLatch countDownLatch = new CountDownLatch(2);//说明await一次需要两次notify
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
				System.out.println("进入线程"+Thread.currentThread().getName()+",等待其它线程处理完成");
					countDownLatch.await();
				System.out.println("线程"+Thread.currentThread().getName()+",处理完毕");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		},"t1");
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
				System.out.println("进入线程"+Thread.currentThread().getName()+",等待其它线程处理完成");
					Thread.sleep(1000);
				System.out.println("线程"+Thread.currentThread().getName()+",处理完毕，发出第一次通知");
				countDownLatch.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		},"t2");
		
		Thread t3 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
				System.out.println("进入线程"+Thread.currentThread().getName()+",等待其它线程处理完成");
					Thread.sleep(1000);
				System.out.println("线程"+Thread.currentThread().getName()+",处理完毕,发出第二次通知");
				countDownLatch.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		},"t3");
		
		t1.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t3.start();
		
	}

}
