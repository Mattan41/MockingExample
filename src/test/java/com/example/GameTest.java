package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GameTest {
    @Test
    @DisplayName("calling score() should return the total score of the game")
    void callingScoreShouldReturnTheTotalScoreOfTheGame() {

        Game game = new Game();
        int pinsKnockedDownFirstRollFirstFrame = 5;
        int pinsKnockedDownSecondRollFirstFrame = 3;
        int pinsKnockedDownFirstRollSecondFrame = 2;

        game.roll(pinsKnockedDownFirstRollFirstFrame);
        game.roll(pinsKnockedDownSecondRollFirstFrame);
        game.roll(pinsKnockedDownFirstRollSecondFrame);

        assertThat(game.score()).isEqualTo(10);
    }

    @Test
    @DisplayName("given a fixed number of pins knocked down when calling roll(), then points added to total score should be equal to the number of pins knocked down")
    void givenAFixedNumberOfPinsKnockedDownWhenCallingRollThenPointsAddedToTotalScoreShouldBeEqualToTheNumberOfPinsKnockedDown() {

        Game game = new Game();
        int pinsKnockedDown = 5;

        game.roll(pinsKnockedDown);

        assertThat(game.score()).isEqualTo(5);
    }

    @Test
    @DisplayName("given a spare then points added to total score should be equal to 10 plus the number of pins knocked down in the next roll")
    void givenASpareThenPointsAddedToTotalScoreShouldBeEqualTo10PlusTheNumberOfPinsKnockedDownInTheNextRoll() {

        Game game = new Game();
        int pinsKnockedDownFirstRoll = 5;
        int pinsKnockedDownSecondRoll = 5;
        int pinsKnockedDownFirstRollNextFrame = 3;

        game.roll(pinsKnockedDownFirstRoll);
        game.roll(pinsKnockedDownSecondRoll);
        game.roll(pinsKnockedDownFirstRollNextFrame);

        assertThat(game.score()).isEqualTo(13);
    }

    @Test
    @DisplayName("Strike should move the game to the next frame")
    void strikeShouldMoveTheGameToTheNextFrame() {
        Game game = new Game();

        Frame firstFrame = game.getCurrentFrame();
        game.roll(10); // Strike
        Frame secondFrame = game.getCurrentFrame();

        assertNotEquals(firstFrame, secondFrame);

    }

    @Test
    @DisplayName("rolling less than strike on first roll should keep the game in the same frame")
    void rollingLessThanStrikeOnFirstRollShouldKeepTheGameInTheSameFrame() {
        Game game = new Game();

        Frame firstFrame = game.getCurrentFrame();
        game.roll(9); // not Strike
        Frame secondFrame = game.getCurrentFrame();

        assertThat(firstFrame).isEqualTo(secondFrame);
    }

}