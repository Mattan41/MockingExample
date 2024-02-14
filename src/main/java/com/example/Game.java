package com.example;

public class Game {


    private int totalPoints;

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void roll(int pinsKnockedDown) {
        totalPoints += pinsKnockedDown;
    }
}
