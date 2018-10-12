package com.learning.MultiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyListAdd2 {
	private volatile static List<String> list = new ArrayList<String>();

	public void add() {
		list.add("huangyingjuan");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		final Object lock = new Object();
//		final CountDownLatch countDownLatch = new CountDownLatch(1);
		final MyListAdd2 list2 = new MyListAdd2();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					try {
						for (int i = 0; i < 10; i++) {
							list2.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素...");
							Thread.sleep(500);
							if (list.size() == 5) {
								System.out.println("通知已经发出...");
								lock.notify();//不是立马释放锁，而是会执行完剩余代码再释放锁。必须是配合synchronized使用
//								countDownLatch.countDown();
							}
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					try {
						if (list.size() != 5) {
							lock.wait();//必须是配置synchronized关键字使用
//							countDownLatch.await();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("当前线程："+Thread.currentThread().getName()+"接收到消息");
					throw new RuntimeException();
				}
			}
		}, "t2");
		
		t2.start();//必须是保证t2线程先走，要不然t2就会一直停止在wait()状态
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.start();
	}

}
