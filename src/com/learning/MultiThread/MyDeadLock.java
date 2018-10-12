package com.learning.MultiThread;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyDeadLock implements Runnable{//这个类对象相当于一个线程，用以创建多个线程进行执行
	private String tag;
//	private static Object locka = new Object();
//	private static Object lockb = new Object();
	private static Lock locka = new ReentrantLock();//为什么对象锁得用static修饰？因为m1和m2是两个实例对象，所以使用static修饰对象锁是共用同一个锁
	private static Lock lockb = new ReentrantLock();
	public void setTag(String tag){
		this.tag = tag;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(tag.equals("a")){
			synchronized(locka){
				System.out.println("当前线程： "+Thread.currentThread().getName()+"进入到locka执行");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(lockb){
					System.out.println("当前线程： "+Thread.currentThread().getName()+"进入到lockb执行");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if(tag.equals("b")){
			synchronized(lockb){
				System.out.println("当前线程： "+Thread.currentThread().getName()+"进入到lockb执行");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(locka){
					System.out.println("当前线程： "+Thread.currentThread().getName()+"进入到locka执行");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void main(String[] args){
		MyDeadLock m1 = new MyDeadLock();
		m1.setTag("a");
		MyDeadLock m2 = new MyDeadLock();
		m2.setTag("b");
		Thread t1 = new Thread(m1,"t1");
		Thread t2 = new Thread(m2,"t2");
		t1.start();
		HashMap map = new HashMap();
		try {
			Thread.sleep(100);//这里睡眠的时间肯定得比子线程中睡眠的时间短
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
	}
}
