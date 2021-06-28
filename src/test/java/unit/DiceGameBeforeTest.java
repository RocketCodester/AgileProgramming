package unit;

import apt.dice.before.DiceGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceGameBeforeTest {
	@Test
	public void throwDice() {
		DiceGame game = new DiceGame();
		int score = game.getScore();
		assertTrue(2 <= score && score <= 12);
	}
}
