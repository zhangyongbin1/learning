package designPattern.factorypattern;

public class AircraftFactory extends FactoryMethod{

	@Override
	Flyable create() {
		// TODO Auto-generated method stub
		return new Aircraft();
	}

}
