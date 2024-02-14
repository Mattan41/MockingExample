package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    @DisplayName("given a fixed number of pins knocked down when calling roll(), then points added to total score should be equal to the number of pins knocked down")
    void givenAFixedNumberOfPinsKnockedDownWhenCallingRollThenPointsAddedToTotalScoreShouldBeEqualToTheNumberOfPinsKnockedDown() {

        Game game = new Game();
        int pinsKnockedDown = 5;

        game.roll(pinsKnockedDown);

        assertThat(game.getTotalPoints()).isEqualTo(5);
    }

}