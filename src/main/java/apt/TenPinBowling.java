package apt;

public class TenPinBowling {
    private int totalScore;
    private int spareCount = 0;
    private boolean doesPreviousFrameContainStrike;
    private boolean doLastTwoFramesContainStrikes;

    public void addFrame(int ball1, int ball2) {
        if (ball1 + ball2 > 10) {
            throw new IllegalArgumentException("Can not knock over more than 10 pins in a frame.");
        }
        if (ball1 < 0 || ball2 < 0) {
            throw new IllegalArgumentException("Can not knock over a negative number of pins in a frame.");
        }
        if (doLastTwoFramesContainStrikes) {
            totalScore += 2 * ball1;
        }

        if (doesPreviousFrameContainStrike && isStrike(ball1, ball2)) {
            totalScore += ball1;
            doesPreviousFrameContainStrike = true;
            doLastTwoFramesContainStrikes = true;
        }
        if (doesPreviousFrameContainStrike && !isStrike(ball1, ball2)) {
            totalScore += ball1 + ball2;
            doesPreviousFrameContainStrike = false;
        }
        if (isStrike(ball1, ball2)) {
            doesPreviousFrameContainStrike = true;
        }
        totalScore += ball1 + ball2;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public boolean isStrike(int ball1, int ball2) {
        return ball1 == 10;
    }

    public boolean isSpare(int ball1, int ball2) {
        if (ball1 == 10) {
            return false;
        }
        return ball1 + ball2 == 10;
    }

}
