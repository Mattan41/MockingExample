package com.example;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private List<Integer> totalPoints = new ArrayList<>();


    public List<Integer> getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(List<Integer> totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void roll(Integer pinsKnockedDown) {
        totalPoints.add(pinsKnockedDown);
    }

    public int score() {
        return totalPoints.stream().mapToInt(Integer::intValue).sum();
    }
}
