package com.example;

public class Frame {
    private int firstRoll;
    private Integer secondRoll;
    private boolean isSpare;
    private boolean isStrike;

    public void roll(int pinsKnockedDown) {
        if (firstRoll == 0) {
            firstRoll = pinsKnockedDown;
            if (firstRoll == 10) isStrike = true;

        } else {
            secondRoll = pinsKnockedDown;
            if (firstRoll + secondRoll == 10) {
                isSpare = true;
            }
        }
    }

    public int score() {
        if (secondRoll == null) {
            return firstRoll;
        } else {
            return firstRoll + secondRoll;
        }
    }

    public boolean isSpare() {
        return isSpare;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public int getFirstRoll() {
        return firstRoll;
    }

    public Integer getSecondRoll() {
        return secondRoll;
    }

}
