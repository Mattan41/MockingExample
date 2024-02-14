package com.example;

public class Frame {
    private Integer firstRoll;
    private Integer secondRoll;
    private boolean isSpare;
    private boolean isStrike;

    public void roll(int pinsKnockedDown) {
        if (firstRoll == null) {
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
        if (firstRoll == null) {
            return 0;
        } else if (secondRoll == null) {
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

    public Integer getFirstRoll() {
        return firstRoll;
    }

    public Integer getSecondRoll() {
        return secondRoll;
    }

}
