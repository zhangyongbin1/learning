package com.learning.MultiThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MyScheduledJob {
	public static void main(String[] args){
		Temp command = new Temp();
		ScheduledExecutorService scheduler=Executors.newScheduledThreadPool(1);
		/**
		 * 初始化的时候需要花费5秒钟，然后每隔一秒钟执行一次commd命令，command需要是runnable类型
		 */
		ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(command, 5, 1, TimeUnit.SECONDS);
	}
}

class Temp extends Thread{
	public void run(){
		System.out.println("run");
	}
}
