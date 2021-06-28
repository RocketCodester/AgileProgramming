package apt.dice.after;

public class DiceGame {
	public DiceGame() {
		dice[0] = new Die();
		dice[1] = new Die();
	}
	
	public int throwDice() {
		getDie(0).roll();
		getDie(1).roll();
		return getDiceValue();
	}
	
	private int getDiceValue() {
		return getDie(0).getFaceValue() + getDie(1).getFaceValue();
	}
	
	private Die getDie(int index) {
		return dice[index];
	}

	private final Die[] dice = new Die[2];
}
