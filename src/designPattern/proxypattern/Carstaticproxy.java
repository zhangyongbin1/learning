package designPattern.proxypattern;

public class Carstaticproxy implements Moveable{
	private Moveable carImpl;
	public Carstaticproxy(Moveable carImpl){
		this.carImpl = carImpl;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("使用静态代理之前做的处理......");
		carImpl.move();
		System.out.println("使用静态代理之后......");
	}

}
