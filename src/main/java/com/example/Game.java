package com.example;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Frame> frames = new ArrayList<>();
    private Frame currentFrame = new Frame(false);

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
            currentFrame = new Frame(true);
        } else {
            currentFrame = new Frame(false);
        }
    }

    public int score() {
        int totalScore = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            if (i < 9) {
                totalScore += frame.score();
                if (frame.isSpare() && i < frames.size() - 1) {
                    totalScore += spareBonus(i);
                }
                if (frame.isStrike() && i < frames.size() - 1) {
                    totalScore += strikeBonus(i);
                }
            } else if (i == 9)
                totalScore = getTotalScoreInLastFrame(frame, totalScore);

        }

        if (frames.size() < 10) {
            totalScore += currentFrame.score();
        }
        return totalScore;
    }

    private static int getTotalScoreInLastFrame(Frame frame, int totalScore) {
        if (frame.isStrike()) {
            totalScore += 10 + frame.getSecondRoll() + frame.getThirdRoll();
        } else if (frame.getSecondRoll() + frame.getFirstRoll() == 10)
            totalScore += frame.getFirstRoll() + frame.getSecondRoll() + 2 * frame.getThirdRoll();
        else {
            totalScore += frame.score();
        }
        return totalScore;
    }


    private int spareBonus(int frameIndex) {
        Frame frame = frames.get(frameIndex);
        if (frame.isTenthFrame()) {
            return frame.getSecondRoll();
        } else if (frameIndex + 1 < frames.size()) {
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
