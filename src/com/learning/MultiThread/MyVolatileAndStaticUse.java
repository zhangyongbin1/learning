package com.learning.MultiThread;

public class MyVolatileAndStaticUse {
	
	private volatile boolean isRunning = true;
	
	public void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	public boolean getRunning(){
		return this.isRunning;
	}
	
	public static void main(String[] args){
		final MyVolatileAndStaticUse mvas = new MyVolatileAndStaticUse();
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println(mvas.getRunning());
				mvas.setRunning(false);
			}
			
		},"t1");
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				//mvas.getRunning();
				System.out.println(mvas.getRunning());
			}
			
		},"t2");
		
		t1.start();
		/*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		t2.start();
	}

}
