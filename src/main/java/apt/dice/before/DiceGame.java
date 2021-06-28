package apt.dice.before;

import java.util.Random;

public class DiceGame {
	public DiceGame() {
		dice[0] = new Die();
		dice[1] = new Die();
	}
	
	public int getScore() {
		int result;
		result = rand.nextInt(6) + 1;
		dice[0].setFaceValue(result);
		result = rand.nextInt(6) + 1;
		dice[1].setFaceValue(result);
		int score = dice[0].getFaceValue() + dice[1].getFaceValue();
		return score;
	}
	
	private Die[] dice = new Die[2];
	private Random rand = new Random();
}
