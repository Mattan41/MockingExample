package com.example;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Integer> totalPoints = new ArrayList<>();

    private List<Frame> frames = new ArrayList<>();
    private Frame currentFrame = new Frame();

    public List<Integer> getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(List<Integer> totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Frame getCurrentFrame() {
        return currentFrame;
    }

    public void roll(int pinsKnockedDown) {
        currentFrame.roll(pinsKnockedDown);
        if (currentFrame.isStrike() || (currentFrame.getSecondRoll() != null)) {
            nextFrame();
        }
    }

    private void nextFrame() {
        frames.add(currentFrame);
        currentFrame = new Frame();
    }

    public int score() {
        int totalScore = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            totalScore += frame.score();
            if (frame.isSpare() && i + 1 < frames.size()) {
                totalScore += frames.get(i + 1).getFirstRoll();
            }
            if (frame.isStrike() && i + 1 < frames.size()) {
                Frame nextFrame = frames.get(i + 1);
                totalScore += nextFrame.getFirstRoll();
                if (nextFrame.isStrike() && i + 2 < frames.size()) {
                    totalScore += frames.get(i + 2).getFirstRoll();
                } else if (nextFrame.getSecondRoll() != null) {
                    totalScore += nextFrame.getSecondRoll();
                }
            }
        }
        totalScore += currentFrame.score();
        return totalScore;
    }
}
