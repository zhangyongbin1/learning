package com.learning.MultiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyFixedThreadPool {
	public static void main(String[] args){
		ExecutorService executor = Executors.newFixedThreadPool(7);
		Future<String> outputs = executor.submit(new Callable<String>(){
			//如果是带有返回值的借口回调最好使用callable
			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				return "i am a task";
			}
			
		});
		String str = null;
		try {
			str = outputs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			executor.shutdown();
		}
		System.out.println(str);
	}
	
}
