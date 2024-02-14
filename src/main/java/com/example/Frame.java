package com.example;

public class Frame {
    private int firstRoll;
    private int secondRoll;
    private boolean isSpare;

    public void roll(int pinsKnockedDown) {
        if (firstRoll == 0) {
            firstRoll = pinsKnockedDown;
        } else {
            secondRoll = pinsKnockedDown;
            if (firstRoll + secondRoll == 10) {
                isSpare = true;
            }
        }
    }

    public int score() {
        return firstRoll + secondRoll;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public int getFirstRoll() {
        return firstRoll;
    }

}
