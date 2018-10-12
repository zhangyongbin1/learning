package designPattern.factorypattern;

public class Test {
	public static void main(String[] args){
		AircraftFactory factory = new AircraftFactory();
		Aircraft aircraft = (Aircraft) factory.create();
//		aircraft.fly();
		//测是抽象工厂模式
		Factory factory1 =  new Factory();
		Aircraft aircraft2 = (Aircraft) factory1.createFlyable();
		Car car = (Car) factory1.createMoveable();
		aircraft2.fly();
		car.move();
	}

}
