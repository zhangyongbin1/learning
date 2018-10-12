package com.learning.MultiThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyCollectionUse {
	public static void main(String[] args){
		//多线程环境下使用vector替代list,hashtable替代hashmap
		//final Vector<String> vector = new Vector<>();
		final List<String> ticket = new ArrayList<>();
		final List<String> tickets = Collections.synchronizedList(ticket);
		for(int i = 1; i <= 1000; i++){
			tickets.add("火车票"+i);
		}
		for(int i = 0; i < 100; i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					while(true){
						if(tickets.isEmpty()){
							break;
						}
						System.out.println(Thread.currentThread().getName()+"---"+tickets.remove(0));
					}
				}
				
			},"线程"+i).start();
		}
	}
	

}
