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
            currentFrame = new Frame(true);
        } else {
            currentFrame = new Frame(false);
        }
    }

//    public int score() {
//        int totalScore = 0;
//        for (int i = 0; i < frames.size(); i++) {
//            Frame frame = frames.get(i);
//            totalScore += frame.score();
//            if (frame.isSpare() && i < frames.size() - 1 && frames.get(i + 1).getFirstRoll() != null) {
//                totalScore += spareBonus(i);
//            }
//            if (frame.isStrike() && i < frames.size() - 1 && frames.get(i + 1).getFirstRoll() != null) {
//                totalScore += strikeBonus(i);
//            }
//        }
//
//        totalScore += currentFrame.score();
//
//        if (frames.size() == 10) {
//            Frame tenthFrame = frames.get(9);
//            if ((tenthFrame.isSpare() || tenthFrame.isStrike()) && tenthFrame.getThirdRoll() != null) {
//                totalScore += tenthFrame.getThirdRoll();
//            }
//        }
//
//        return totalScore;
//    }

//    public int score() {
//        int totalScore = 0;
//        for (int i = 0; i < frames.size(); i++) {
//            Frame frame = frames.get(i);
//            totalScore += frame.score();
//            if (frame.isSpare() && i < frames.size() - 1 && frames.get(i + 1).getFirstRoll() != null) {
//                totalScore += spareBonus(i);
//            }
//            if (frame.isStrike() && i < frames.size() - 1 && frames.get(i + 1).getFirstRoll() != null) {
//                totalScore += strikeBonus(i);
//            }
//        }
//
//        totalScore += currentFrame.score();
//
//        if (frames.size() == 10) {
//            Frame tenthFrame = frames.get(9);
//            // Add the third roll of the last frame always, not only if it is a spare or a strike
//            if (tenthFrame.getThirdRoll() != null) {
//                totalScore += tenthFrame.getThirdRoll();
//            }
//        }
//
//        return totalScore;
//    }

//    public int score() {
//        int totalScore = 0;
//        for (int i = 0; i < frames.size(); i++) {
//            Frame frame = frames.get(i);
//            if (i < 9) {
//                totalScore += frame.score();
//                if (frame.isSpare() && i < frames.size() - 1) {
//                    totalScore += spareBonus(i);
//                }
//                if (frame.isStrike() && i < frames.size() - 1) {
//                    totalScore += strikeBonus(i);
//                }
//            }
//        }
//
//        // Handle the 10th frame separately
//        if (frames.size() == 10) {
//            Frame tenthFrame = frames.get(9);
//            if (tenthFrame.isStrike()) {
//                totalScore += 10 + tenthFrame.getSecondRoll() + tenthFrame.getThirdRoll();
//            } else if (tenthFrame.isSpare()) {
//                totalScore += 10 + tenthFrame.getThirdRoll();
//            } else {
//                totalScore += tenthFrame.score();
//            }
//        } else if (currentFrame.isComplete()) {
//            // Add the score of the current frame only if it is complete
//            totalScore += currentFrame.score();
//        }
//
//        return totalScore;
//    }

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
            } else if (i == 9) {
                // Handle the 10th frame separately
                if (frame.isStrike()) {
                    totalScore += 10 + frame.getSecondRoll() + frame.getThirdRoll();
                }
                else if (frame.getSecondRoll() + frame.getFirstRoll() == 10)
                    totalScore += frame.getFirstRoll() + frame.getSecondRoll() + 2 * frame.getThirdRoll();
                 else {
                    totalScore += frame.score();
                }
            }
        }
        // Add the score of the current frame if it is not the 10th frame
        if (frames.size() < 10) {
            totalScore += currentFrame.score();
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
