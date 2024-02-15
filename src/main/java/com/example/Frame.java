package com.example;

public class Frame {
    private Integer firstRoll;
    private Integer secondRoll;
    private Integer thirdRoll;
    private boolean isSpare;
    private boolean isStrike;
    private boolean isTenthFrame;

    public Frame(boolean isTenthFrame) {
        this.isTenthFrame = isTenthFrame;
    }

    public void roll(int pinsKnockedDown) {
        if (firstRoll == null) {
            firstRoll = pinsKnockedDown;
            if (firstRoll == 10) {
                isStrike = true;
            }
        } else if (secondRoll == null) {
            secondRoll = pinsKnockedDown;
            if (firstRoll + secondRoll == 10) {
                isSpare = true;
            }
        } else if (isTenthFrame && (isSpare || isStrike)) {
            thirdRoll = pinsKnockedDown;
        } else {
            throw new IllegalStateException("Cannot roll more than twice in a frame, unless it's the 10th frame with a strike or a spare");
        }
    }

    public int score() {
        int score = 0;
        if (firstRoll != null) score += firstRoll;
        if (secondRoll != null) score += secondRoll;
        if (thirdRoll != null) score += thirdRoll;
        return score;
    }

    public boolean isComplete() {
        if (isTenthFrame && (isSpare || isStrike)) {
            return firstRoll != null && secondRoll != null && thirdRoll != null;
        }
        return (firstRoll != null && secondRoll != null) || isStrike;
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

    public Integer getThirdRoll() {
        return thirdRoll;
    }

    public boolean isTenthFrame() {
        return isTenthFrame;
    }
}
