package unit;

import apt.TenPinBowling;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TenPinBowlingTest {
    @Test
    public void gutterGameShouldScore0() {
        TenPinBowling game = new TenPinBowling();
        for (int iFrame = 0; iFrame < 10; iFrame++) {
            game.addFrame(0, 0);
        }
        assertEquals(0, game.getTotalScore());
    }

    @Test
    public void gameOfSinglesShouldScore20() {
        TenPinBowling game = new TenPinBowling();
        for (int frame = 0; frame < 10; frame++) {
            game.addFrame(1, 1);
        }
        assertEquals(20, game.getTotalScore());
    }

    @Test
    public void gameOfOneStrikeShouldScore24() {
        TenPinBowling game = new TenPinBowling();
        game.addFrame(10, 0);
        game.addFrame(2, 3);
        game.addFrame(4, 0);
        for (int frame = 0; frame < 7; frame++) {
            game.addFrame(0, 0);
        }
        assertEquals(24, game.getTotalScore());
    }

    @Test
    public void gameOfStrikesShouldScore300() {
        TenPinBowling game = new TenPinBowling();
        for (int frame = 0; frame < 10; frame++) {
            game.addFrame(10, 0);
        }
        assertEquals(300, game.getTotalScore());
    }

    @Test
    public void addFrame_shouldThrowIllegalArgumentExceptionWhenNumberOfPinsKnockedOverPerFrameIsMoreThanTen() {
        TenPinBowling game = new TenPinBowling();
        assertThrows(IllegalArgumentException.class, () -> game.addFrame(10, 1));
        assertThrows(IllegalArgumentException.class, () -> game.addFrame(1, 10));
        assertThrows(IllegalArgumentException.class, () -> game.addFrame(11, 11));
    }

    @Test
    public void addFrame_shouldThrowIllegalArgumentExceptionWhenNumberOfPinsKnockedOverPerFrameIsLessThanZero() {
        TenPinBowling game = new TenPinBowling();
        assertThrows(IllegalArgumentException.class, () -> game.addFrame(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> game.addFrame(0, -1));
        assertThrows(IllegalArgumentException.class, () -> game.addFrame(-1, -1));
    }

    @Test
    public void isStrike_shouldReturnTrueOnlyWhenFirstBallKnocksDownTenPins() {
        TenPinBowling game = new TenPinBowling();
        assertTrue(game.isStrike(10, 0));
        assertFalse(game.isStrike(0, 10));
        assertFalse(game.isStrike(0, 9));
    }

    @Test
    public void isSpare_shouldReturnTrueOnlyWhenTwoBallsInTheFrameKnockDownAllPins() {
        TenPinBowling game = new TenPinBowling();
        assertFalse(game.isSpare(10, 0));
        assertFalse(game.isSpare(0, 9));
        assertTrue(game.isSpare(0, 10));
        assertTrue(game.isSpare(6, 4));
    }

//    @Test
}
