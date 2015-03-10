package com.kfenske.Quiz4;

public class Program {

	public static void main(String[] args) {
		SoundMaker sm = new SoundMaker();
		Cat c = new Cat();
		Dog d = new Dog();
		
		sm.makeSound(c.meow);
		sm.makeSound(d.bark);
	}

}
