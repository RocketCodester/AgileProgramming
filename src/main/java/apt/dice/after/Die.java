package apt.dice.after;

import java.util.Random;

public class Die {
	public int getFaceValue() {
		return faceValue;
	}
	
	public void roll() {
		faceValue = rand.nextInt(6) + 1;
	}
	
	private int faceValue;
	private final Random rand = new Random();
}
