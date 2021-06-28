package unit;

import apt.GameOfLife;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameOfLifeTest {
    @Test
    public void gameShouldRememberState() {
        GameOfLife game = new GameOfLife(5, 5);
        game.setAlive(1, 1);
        assertTrue(game.isAlive(1, 1));
        assertFalse(game.isAlive(3, 3));
    }

    @Test
    public void gameReturnsCorrectNumberOfAliveNeighbors() {
        GameOfLife game = new GameOfLife(5, 5);
        assertTrue(game.doesCellHaveFewerThanTwoAliveNeighbors(0, 0));

        game.setAlive(0, 1);
        assertTrue(game.doesCellHaveFewerThanTwoAliveNeighbors(0, 0));

        game.setAlive(1, 1);
        assertFalse(game.doesCellHaveFewerThanTwoAliveNeighbors(0, 0));
    }

    @Test
    public void cellsShouldDieFromUnderpopulation() {
        GameOfLife game = new GameOfLife(5, 5);
        game.setAlive(1, 1);
        game.setAlive(0, 0);
        game.setAlive(3, 3);
        game.computeNextGeneration();
        assertFalse(game.isAlive(1, 1));
        assertFalse(game.isAlive(3, 3));
    }

    @Test
    public void cellsShouldStayAlive() {
        GameOfLife game = new GameOfLife(5, 5);
        game.setAlive(1, 1);
        game.setAlive(0, 0);
        game.setAlive(0, 1);
        game.setAlive(1, 0);
        game.setAlive(3, 3);
        game.setAlive(4, 3);
        game.setAlive(3, 4);
        game.computeNextGeneration();
        assertTrue(game.isAlive(1, 1));
        assertTrue(game.isAlive(3, 3));
    }

//    @Test
//    public void createNewGrid() {
//        GameOfLife game = new GameOfLife(5, 5);
//
//        game.setAlive(0, 1);
//        game.setAlive(1, 1);
//
////        GameOfLife newGame = game.createNewGrid();
//
//        assertFalse(newGame.doesCellHaveFewerThanTwoAliveNeighbors(0, 0));
//
//        assertTrue(newGame.doesCellHaveFewerThanTwoAliveNeighbors(0, 1));
//        assertTrue(newGame.doesCellHaveFewerThanTwoAliveNeighbors(1, 1));
//    }

    @Test
    public void isPointInGameOfLife() {
        GameOfLife game = new GameOfLife(5, 5);

        assertTrue(game.exists(0, 0));
        assertTrue(game.exists(5, 5));
        assertTrue(game.exists(2, 3));

        assertFalse(game.exists(-1, 3));
        assertFalse(game.exists(3, -1));
        assertFalse(game.exists(-1, -1));
        assertFalse(game.exists(6, 3));
        assertFalse(game.exists(3, 6));
        assertFalse(game.exists(6, 6));
    }
}
