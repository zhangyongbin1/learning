package com.learning.MultiThread;

public class MyThreadLocal {
	private static ThreadLocal<String> tl = new ThreadLocal<>();//线程局部变量，只有当前线程可以看到这个变量值的修改
	public void setValue(String str){
		tl.set(str);
	}
	public void getValue(){
		System.out.println("当前线程："+Thread.currentThread().getName()+"获取的value="+tl.get());
	}
	public static void main(String[] args){
		final MyThreadLocal mtl = new MyThreadLocal();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				mtl.setValue("张三");
				mtl.getValue();
			}
			
		},"t1");
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mtl.getValue();//获取的值为null,因为ThreadLocal修饰的变量只有当前自己线程可以看见
			}
			
		},"t2");
		t1.start();
		t2.start();
	}

}
