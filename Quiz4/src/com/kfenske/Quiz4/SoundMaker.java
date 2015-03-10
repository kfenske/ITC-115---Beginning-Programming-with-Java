package com.kfenske.Quiz4;

public class SoundMaker implements SoundInterface {

	@Override
	public void makeSound(String s) {
		System.out.println("The sound your animal makes is " + s);
	}

}
