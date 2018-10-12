package designPattern.factorypattern;

import org.junit.Test;

public class SimpleFactory {
	
	public Object create(Class<?> clazz){
		if(clazz.getName().equals(Car.class.getName())){
			return createCar();
		}else if(clazz.getName().equals(Aircraft.class.getName())){
			return crateAircraft();
		}
		return null;
	}
	
	public Car createCar(){
		return new Car();
	}
	public Aircraft crateAircraft(){
		return new Aircraft();
	}
	class Car{
		public void move(){
			System.out.println("我是一辆车，我正在移动......");
		}
	}
	class Aircraft{
		public void fly(){
			System.out.println("我是一个架飞机,我正在飞行......");
		}
	}

	@Test
	public void test(){
		Car car = (Car) create(Car.class);
		car.move();
		Aircraft aircraft = (Aircraft) create(Aircraft.class);
		aircraft.fly();
	}
}
