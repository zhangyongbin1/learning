package com.learning.MultiThread;

import java.util.concurrent.atomic.AtomicInteger;

public class MyVolatileAndAtomicIntegerNoAtomic extends Thread{
	//private volatile static int count = 0;//加上stati修改是后count变量才是线程间共享的，所有出现线程安全问题
	private static AtomicInteger count = new AtomicInteger(0);
	public void run(){
		addCount();
	}
	private static synchronized void addCount() {
		// TODO Auto-generated method stub
		for(int i =0; i < 1000; i++){
//			count++;//即使是使用volatile关键修饰也无法保证操作的原子性.需要使用synchronized修饰保证线原子性
			count.incrementAndGet();//即使使用atomicInteger修饰，也无法保证多次count.incrementAndGet()操作的原子性
		}
		System.out.println(count);
	}
	public static void main(String[] args){
		//创建MyVolatileAndAtomicIntegerNoAtomic线程数组
		MyVolatileAndAtomicIntegerNoAtomic[] arr = new MyVolatileAndAtomicIntegerNoAtomic[100];
		for(int i = 0; i < 10; i++){
			arr[i] = new MyVolatileAndAtomicIntegerNoAtomic();//初始化十个线程
		}
		for(int i = 0; i < 10; i++){
			arr[i].start();//启动数组中的十个线程
		}
	}
}
