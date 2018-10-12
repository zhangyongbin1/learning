package com.learning.MultiThread;

public class MyDirtyRead {
	private String name="huangyinjuan";
	private String password="123456";

	public void setValue(String name, String password) {
		this.name = name;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.password = password;
		System.out.println("setValue执行后得到的最终结果： name=" + name + ", password=" + password);
	}

	public void getValue() {
		System.out.println("getValue得到的结果： name=" + this.name + ", password=" + this.password);
	}

	public static void main(String[] args) {
		final MyDirtyRead my = new MyDirtyRead();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				my.setValue("zhangyongbin", "1234567890");
			}

		}, "t1");
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		my.getValue();
	}
}
