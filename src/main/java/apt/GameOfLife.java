package apt;

public class GameOfLife {
    private final int width;
    private final int height;
    private boolean[][] grid;

    public GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;
        grid = newGrid(width, height);
    }

    private boolean[][] newGrid(int width, int height) {
        boolean[][] newGrid = new boolean[width][];
        for (int x = 0; x < width; x++) {
            newGrid[x] = new boolean[height];
        }
        return newGrid;
    }

    public void setAlive(int x, int y) {
        grid[x][y] = true;
    }

    public void setDead(int x, int y) {
        grid[x][y] = false;
    }

    public boolean isAlive(int x, int y) {
        return grid[x][y];
    }

    public boolean exists(int x, int y) {
        if (x < 0 || x > width) {
            return false;
        }
        return y >= 0 && y <= height;
    }

    public boolean doesCellHaveFewerThanTwoAliveNeighbors(int x, int y) {
        return calculateNumberOfAliveNeighbors(x, y) < 2;
    }

    public int calculateNumberOfAliveNeighbors(int x, int y) {
        int numberOfAliveNeighbors = 0;

        if (exists(x - 1, y - 1) && isAlive(x - 1, y - 1)) {
            numberOfAliveNeighbors++;
        }
        if (exists(x, y - 1) && isAlive(x, y - 1)) {
            numberOfAliveNeighbors++;
        }
        if (exists(x + 1, y - 1) && isAlive(x + 1, y + 1)) {
            numberOfAliveNeighbors++;
        }

        if (exists(x - 1, y) && isAlive(x - 1, y)) {
            numberOfAliveNeighbors++;
        }
        if (exists(x + 1, y) && isAlive(x + 1, y)) {
            numberOfAliveNeighbors++;
        }

        if (exists(x - 1, y + 1) && isAlive(x - 1, y + 1)) {
            numberOfAliveNeighbors++;
        }
        if (exists(x, y + 1) && isAlive(x, y + 1)) {
            numberOfAliveNeighbors++;
        }
        if (exists(x + 1, y + 1) && isAlive(x + 1, y + 1)) {
            numberOfAliveNeighbors++;
        }

        return numberOfAliveNeighbors;
    }

    public void computeNextGeneration() {
        boolean[][] nextGrid = newGrid(width, height);
        for (int x = 0; x < width; x++) {
//            for (y = 0; y < height; y++) {
//
//            }
        }
        grid = nextGrid;
    }

//    private int getNumberOfLiveNeighbors(int x, int y) {
//        int numberAlive = 0;
//        for (int testX = x - 1; testX <= x + 1; testX++) {
//            for (int testY = y -1; testY<)
//        }
//    }

//    public GameOfLife createNewGrid() {
//        newGrid(width, height);
//    }
}
