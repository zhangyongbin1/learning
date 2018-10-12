package com.learning.MultiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HashReturnValueMultiThread {

	public static void main(String[] args)throws Exception{
		List<Future<String>> list = new ArrayList<Future<String>>();
		ExecutorService pool = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 500000000; i++){
			Callable callable = new Mycallable(i+" ");
			Future f = pool.submit(callable);
			list.add(f);
		}
		pool.shutdown();
		for(Future f : list){
			System.out.println(f.get().toString());
		}
	}	
}
class Mycallable implements Callable<Object>{
	private String str;
	Mycallable(String str){
		this.str = str;
	}
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return this.str;
	}
	
}
