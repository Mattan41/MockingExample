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

    public void roll(int pinsKnockedDown) {
        currentFrame.roll(pinsKnockedDown);
        if (currentFrame.isSpare() || currentFrame.score() != 0) {
            frames.add(currentFrame);
            currentFrame = new Frame();
        }
    }

    public int score() {
        int totalScore = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            totalScore += frame.score();
            if (frame.isSpare() && i + 1 < frames.size()) {
                totalScore += frames.get(i + 1).getFirstRoll();
            }
        }
        return totalScore;
    }

}
