package unit;

import apt.dice.after.DiceGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceGameAfterTest {
	@Test
	public void rollDice() {
		DiceGame game = new DiceGame();
		int score = game.throwDice();
		assertTrue(2 <= score && score <= 12);
	}
}
