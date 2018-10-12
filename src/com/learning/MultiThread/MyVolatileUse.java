package com.learning.MultiThread;

public class MyVolatileUse extends Thread {
	/**
	 * 注意就是类中的static变量不能被序列化保存。
	 * static修饰的变量在线程之间是共享的，但是在进程之间不是共享
	 * 
	 * static和volatile的区别：
	 * 1. volatile是告诉编译器,每次取这个变量的值都需要从主存中取,而不是用自己线程工作内存中的缓存.
		2. static 是说这个变量,在主存中所有此类的实例用的是同一份,各个线程创建时需要从主存同一个位置拷贝到自己工作内存中去(而不是拷贝此类不同实例中的这个变量的值),
		也就是说只能保证线程创建时,变量的值是相同来源的,运行时还是使用各自工作内存中的值,依然会有不同步的问题.
	 */
	private volatile static boolean isRunning = true;
	
	public void run(){
		System.out.println("当前线程："+Thread.currentThread().getName()+"进入run方法");
		while(isRunning){
			
		}
		System.out.println("当前线程:"+Thread.currentThread().getName()+"停止");
	}
	
	public void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public static void main(String[] args){
		MyVolatileUse my = new MyVolatileUse();
		my.start();
		System.out.println("myThread:"+my.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyVolatileUse my2 = new MyVolatileUse();
		my2.start();
		System.out.println("my2Thread:"+my2.currentThread().getName());
		my2.setRunning(false);//在主线程中修改了isRunning的值，在子线程my在不一定能"看见"，但是使用volatile修饰后就能看见
		System.out.println("在在当前线程:"+Thread.currentThread().getName()+"中已经把isRunning的值设置成了false");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyVolatileUse my3 = new MyVolatileUse();
		my3.start();
	}
	

}
