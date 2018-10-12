package designPattern.proxypattern;

public class Car implements Moveable{

	@Override
	public void move() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("汽车正在行驶中......");
	}

}
