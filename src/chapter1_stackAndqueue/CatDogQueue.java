package chapter1_stackAndqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列问题,主要是在将队列中所有的猫狗类都按先后顺序poll出时，需要将猫狗对象进入队列的顺序做个标记。
 * @author zhangy
 *
 */
public class CatDogQueue {
	private Queue<PetEnterQueue> dogQueue;
	private Queue<PetEnterQueue> catQueue;
	int count;
	public CatDogQueue(){
		//定义两个队列用于存放猫和狗对象
		this.dogQueue = new LinkedList<PetEnterQueue>();
		this.catQueue = new LinkedList<PetEnterQueue>();
		this.count = 0;//使用count值来标记猫狗进入其相应队列的顺序，count值大的就是后进去的，count值小的就是先进去的
	}
	public void add(Pet pet){
		if(pet.getType() == "cat"){
			catQueue.add(new PetEnterQueue(pet,this.count++));
		}else if(pet.getType() == "dog"){
			dogQueue.add(new PetEnterQueue(pet,this.count++));
		}else{
			throw new RuntimeException("error,not dog or cat!");
		}
	}
	public Pet pollAll(){//判断打上标签的count的值的大小，count值小的先poll出来
		if(!this.dogQueue.isEmpty() && !this.catQueue.isEmpty()){
			if(dogQueue.peek().getCount() > catQueue.peek().getCount()){
				return catQueue.poll().getPet();
			}else{
				return dogQueue.poll().getPet();
			}
		}else if(!this.catQueue.isEmpty()){//可以不用再判断dogqueue为空的情况来，最开始if使用的是&&运算符，所以走到这里说明dogqueue就是空的
			return catQueue.poll().getPet();
		}else{
			throw new RuntimeException("error,queue is empty!");
		}
	}
	
	public Dog pollDog(){//从dogqueue中poll出来
		if(!this.dogQueue.isEmpty()){
			return (Dog)dogQueue.poll().getPet();
		}else{
			throw new RuntimeException("Dog queue is empty");
		}
	}
	public Cat pollCat(){//从catqueue中poll出来
		if(!this.catQueue.isEmpty()){
			return (Cat)catQueue.poll().getPet();
		}else{
			throw new RuntimeException("Cat queue is empty");
		}
	}
	public boolean isEmpty(){
		if(this.dogQueue.isEmpty() || this.catQueue.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	public boolean isDogEmpty(){
		if(this.dogQueue.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	public boolean isCatEmpty(){
		if(this.catQueue.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	public static class Pet{
		private String type;
		public Pet(String type){
			this.type = type;
		}
		public String getType() {
			return type;
		}
	}
	public static class Cat extends Pet{
		public Cat(){
			super("cat");
		}
	}
	public static class Dog extends Pet{
		public Dog(){
			super("dog");
		}
	}
	public class PetEnterQueue{//为了可以给猫狗对象进队列时能够打上时间戳而封装的一个类
		private Pet pet;
		private long count;//加上识别的标记，也可以使用时间戳
		public PetEnterQueue(Pet pet, long count){
			this.count = count;
			this.pet = pet;
		}
		
		public Pet getPet() {
			return pet;
		}
		public long getCount() {
			return count;
		}
		public String getEnterPetType(){
			return this.pet.getType();
		}
	}
	public static void main(String[] args) {
		CatDogQueue test = new CatDogQueue();

		Pet dog1 = new Dog();
		Pet cat1 = new Cat();
		Pet dog2 = new Dog();
		Pet cat2 = new Cat();
		Pet dog3 = new Dog();
		Pet cat3 = new Cat();

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);
		/*while (!test.isDogEmpty()) {
			System.out.println(test.pollDog().getType());
		}*/
		while (!test.isEmpty()) {
			System.out.println(test.pollAll().getType());
		}
	}	

}
