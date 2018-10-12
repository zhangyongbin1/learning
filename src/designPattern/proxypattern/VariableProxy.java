package designPattern.proxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class VariableProxy implements InvocationHandler{

	private Object target;
	public VariableProxy(Object target){
		this.target = target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("使用动态代理之前做的事情......");
		method.invoke(target, args);
		System.out.println("使用静态代理之后......");
		return null;
	}

}
