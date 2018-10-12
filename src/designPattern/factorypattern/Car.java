package designPattern.factorypattern;

import designPattern.proxypattern.Moveable;

public class Car implements Moveable{
	@Override
	public void move(){
		System.out.println("我是一辆车，我正在移动......");
	}
}
