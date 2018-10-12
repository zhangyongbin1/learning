package designPattern.factorypattern;

import designPattern.proxypattern.Moveable;

public abstract class AbstractFacotry {
	abstract Flyable createFlyable();
	abstract Moveable createMoveable();
}
