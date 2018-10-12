package com.learning.MultiThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyDefineThreadPool {

	public static void main(String[] args) {
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
		ThreadPoolExecutor exc = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, queue, new MyRejectStrategy());
		Task task1 = new Task(1, "任务1");
		Task task2 = new Task(2, "任务2");
		Task task3 = new Task(3, "任务3");
		Task task4 = new Task(4, "任务4");
		Task task5 = new Task(5, "任务5");
		Task task6 = new Task(6, "任务6");
		exc.execute(task1);
		exc.execute(task2);
		exc.execute(task3);
		exc.execute(task4);
		exc.execute(task5);
		exc.execute(task6);

		exc.shutdown();
	}

}
