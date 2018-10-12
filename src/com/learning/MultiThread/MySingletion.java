package com.learning.MultiThread;

public class MySingletion {
	
	private MySingletion(){}//不能在其他类中进行实例化，但是如果是在本类中还是可以实例化的
	
	private static class HolderClass{
		private final static MySingletion singletion = new MySingletion();
	}
	public static MySingletion getInstance(){
		return HolderClass.singletion;
	}
	
	public static void main(String[] args){//在同一个类中的方法还是可以进行new操作进行初始化的
		MySingletion m1 = MySingletion.getInstance();
		MySingletion m2 = MySingletion.getInstance();
		if((m1 == m2)&&(m1.hashCode() == m2.hashCode())){
			System.out.println("单实例");
		}else{
			System.out.println("非单实例");
		}
	}

}
