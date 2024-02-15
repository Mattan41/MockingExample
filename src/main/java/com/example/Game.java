package com.example;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Integer> totalPoints = new ArrayList<>();

    private List<Frame> frames = new ArrayList<>();
    private Frame currentFrame = new Frame(false);

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
    if (getFrameCount() >= 10 && !currentFrame.isComplete())
        throw new IllegalStateException("Cannot roll after the 10th frame");

    currentFrame.roll(pinsKnockedDown);
    if (getFrameCount() < 10 && currentFrame.isComplete()) {
        nextFrame();
    }
}

    private void nextFrame() {
        frames.add(currentFrame);
        if (frames.size() == 9) {
            currentFrame = new Frame(true); // It's the 10th frame
        } else {
            currentFrame = new Frame(false); // It's not the 10th frame
        }
    }

    public int score() {
        int totalScore = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            totalScore += frame.score();
            if (frame.isSpare() && i < frames.size() - 1) {
                totalScore += spareBonus(i);
            }
            if (frame.isStrike() && i < frames.size() - 1) {
                totalScore += strikeBonus(i);
            }
        }
        totalScore += currentFrame.score();
        if (getFrameCount() == 9 && (currentFrame.isSpare() || currentFrame.isStrike())) {
            totalScore += currentFrame.getThirdRoll();
        }
        return totalScore;
    }

    private int spareBonus(int frameIndex) {
        if (frameIndex + 1 < frames.size()) {
            return frames.get(frameIndex + 1).getFirstRoll();
        }
        return 0;
    }

    private int strikeBonus(int frameIndex) {
        if (frameIndex + 1 < frames.size()) {
            Frame nextFrame = frames.get(frameIndex + 1);
            int bonus = nextFrame.getFirstRoll();
            if (nextFrame.isStrike() && frameIndex + 2 < frames.size()) {
                bonus += frames.get(frameIndex + 2).getFirstRoll();
            } else if (nextFrame.getSecondRoll() != null) {
                bonus += nextFrame.getSecondRoll();
            }
            return bonus;
        }
        return 0;
    }

    public int getFrameCount() {
        return frames.size();
    }
}
