package com.learning.MultiThread;

public class MyMultiThread {
	private static String num;
	public static synchronized void printNum(String tag){
		try {
			if(tag.equals("a")){
				num="a100";
				System.out.println("tag a set num over!");
				Thread.sleep(2000);
			}else{
				num = "b200";
				System.out.println("tag b set num over!");
			}
			System.out.println("tag:"+tag+", num:"+num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		//新建两个实例对象，那么synchronized默认分别拥有对应的对象为my和my2所以两个线程使用的是两把不同的对象锁
		final MyMultiThread my = new MyMultiThread();
		final MyMultiThread my2 = new MyMultiThread();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				my.printNum("a");
			}
			
		},"t1");
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				my2.printNum("b");
			}
			
		},"t2");
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
	}

}
