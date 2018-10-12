package com.learning.MultiThread;

public class Task implements Runnable{
	private int id;
	private String TaskName;
	
	
	public Task(int id, String taskName) {
		super();
		this.id = id;
		TaskName = taskName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return TaskName;
	}

	public void setTaskName(String taskName) {
		TaskName = taskName;
	}

	@Override
	public void run() {
			try {
				System.out.println("run task："+this.id);
				Thread.sleep(5*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public String toString(){
		return Integer.toString(this.id);
	}

}
