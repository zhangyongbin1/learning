package designPattern.proxypattern;

import java.lang.reflect.Proxy;

public class test {
	public static void main(String[] args){
		Moveable car = new Car();
//		Carstaticproxy proxy = new Carstaticproxy(car);
		VariableProxy handler = new VariableProxy(car);
		Class<?> cls = car.getClass();
//		proxy.move();
		Moveable m = (Moveable)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), handler);
		m.move();
	}

}
