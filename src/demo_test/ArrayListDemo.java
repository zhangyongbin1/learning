package demo_test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ArrayListDemo {
	
	@Test
	public void test(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("a");
		list.add("a");
		list.add("cf");
		list.add("c");
		list.add("a");
//		Iterator<String> it = list.iterator();
//		while(it.hasNext()){
//			if(it.next().startsWith("a")){
//				list.remove("a");
//			}
//			System.out.println(it.next());
//		}
		//倒序进行删除，使用角标进行删除
//		for(int i = list.size() -1; i >= 0; i--){
//			if(list.get(i).equals("a")){
//				list.remove(i);
//			}
//			System.out.println(list.get(i));
//		}
//		System.out.println(list.size());
//		增强for不行
//		for(String str : list){
//			if(str.startsWith("a")){
//				list.remove(str);
//			}
//			System.out.println(str);
//		}
//		for(int i = 0; i < list.size(); i++){
//			if(list.get(i).equals("a")){
//				list.remove(i);
//			}
//			System.out.println(list.get(i));
//		}
		/*int[] arr = {1,2,1,3,2,3,4};
		int res = 0;
		for(int cur : arr){
			res ^= cur;
		}
		System.out.println(res);*/
//		String i = "10";
//		String in = new String("10");
//		System.out.println(i.equals(in));
//		ExecutorService pool = Executors.newFixedThreadPool(10);
	}
}
