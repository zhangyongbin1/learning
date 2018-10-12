package designPattern.factorypattern;

public class Aircraft implements Flyable {
	@Override
	public void fly(){
		System.out.println("我是一个架飞机,我正在飞行......");
	}
}
