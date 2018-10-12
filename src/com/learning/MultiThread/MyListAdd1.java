package com.learning.MultiThread;

import java.util.ArrayList;
import java.util.List;

public class MyListAdd1 {

	private volatile static List<String> list = new ArrayList<String>();

	public void add() {
		list.add("zhangyongbin");
	}
	public int size(){
		return list.size();
	}

	public static void main(String[] args) {

		final MyListAdd1 list1 = new MyListAdd1();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10; i++) {
					list1.add();
					System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素......");
					try {
						Thread.sleep(2000);
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
				// TODO Auto-generated method stub
				while(true){//需要让这个一直轮询着，不能让它停止
					if (list1.size() == 5) {
						System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知，list.size()=5时停止");
						throw new RuntimeException();
					}
				}
			}

		}, "t2");
		t1.start();
		t2.start();
	}

}
