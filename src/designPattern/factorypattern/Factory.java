package designPattern.factorypattern;

import designPattern.proxypattern.Moveable;

public class Factory extends AbstractFacotry{

	@Override
	Flyable createFlyable() {
		// TODO Auto-generated method stub
		return new Aircraft();
	}

	@Override
	Moveable createMoveable() {
		// TODO Auto-generated method stub
		return new Car();
	}

}
