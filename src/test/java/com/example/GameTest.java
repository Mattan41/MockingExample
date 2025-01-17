package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("given a Strike then points added to total score should be equal to 10 plus the number of pins knocked down in the next two rolls")
    void givenAStrikeThenPointsAddedToTotalScoreShouldBeEqualTo10PlusTheNumberOfPinsKnockedDownInTheNextTwoRolls() {

        Game game = new Game();
        int pinsKnockedDownFirstRoll = 10;
        int pinsKnockedDownFirstRollNextFrame = 3;
        int pinsKnockedDownSecondRollNextFrame = 4;

        game.roll(pinsKnockedDownFirstRoll);
        game.roll(pinsKnockedDownFirstRollNextFrame);
        game.roll(pinsKnockedDownSecondRollNextFrame);

        assertThat(game.score()).isEqualTo(24);
    }

    @Test
    @DisplayName("Rolling 0 on first roll then 10 on the second should count as a spare")
    void rolling0OnFirstRollThen10OnTheSecondShouldCountAsASpare() {
        Game game = new Game();
        int pinsKnockedDownFirstRoll = 0;
        int pinsKnockedDownSecondRoll = 10;
        int pinsKnockedDownFirstRollNextFrame = 3;
        int pinsKnockedDownSecondRollNextFrame = 4;

        game.roll(pinsKnockedDownFirstRoll);
        game.roll(pinsKnockedDownSecondRoll);
        game.roll(pinsKnockedDownFirstRollNextFrame);
        game.roll(pinsKnockedDownSecondRollNextFrame);

        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    @DisplayName("the game should not allow more than 10 frames")
    void theGameShouldNotAllowMoreThan10Frames() {
        Game game = new Game();
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }
        assertThrows(IllegalStateException.class, () -> game.roll(0));
        assertEquals("Cannot roll after the 10th frame", assertThrows(IllegalStateException.class, () -> game.roll(0)).getMessage());
    }

    @Test
    @DisplayName("in the 10th frame, after a spare, a third roll should be allowed but not a fourth")
    void inThe10ThFrameAfterASpareAThirdRollShouldBeAllowedButNotAFourth() {
        Game game = new Game();
        for (int i = 0; i < 18; i++) {
            game.roll(0);
        }
        game.roll(5);
        game.roll(5);
        game.roll(5);

        assertThrows(IllegalStateException.class, () -> game.roll(5));
    }

    @Test
    @DisplayName("in the 10th frame, after a strike, a third roll should be allowed but not a fourth")
    void inThe10ThFrameAfterAStrikeAThirdRollShouldBeAllowedButNotAFourth() {
        Game game = new Game();
        for (int i = 0; i < 18; i++) {
            game.roll(0);
        }
        game.roll(10);
        game.roll(5);
        game.roll(5);

        assertThrows(IllegalStateException.class, () -> game.roll(5));
    }

    @Test
    @DisplayName("in the 10th frame, if a spare is rolled, the score should include the bonus roll")
    void inThe10ThFrameIfASpareIsRolledTheScoreShouldIncludeTheBonusRoll() {
        Game game = new Game();
        for (int i = 0; i < 18; i++) {
            game.roll(0);
        }
        game.roll(5);
        game.roll(5);
        game.roll(5);

        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    @DisplayName("in the 10th frame, if a strike is rolled, the score should include the bonus roll")
    void inThe10ThFrameIfAStrikeIsRolledTheScoreShouldIncludeTheBonusRoll() {
        Game game = new Game();
        for (int i = 0; i < 18; i++) {
            game.roll(0);
        }
        game.roll(10);
        game.roll(10);
        game.roll(10);

        assertThat(game.score()).isEqualTo(30);
    }

    @Test
    @DisplayName("given a perfect game then the score should be 300")
    void givenAPerfectGameThenTheScoreShouldBe300() {
        Game game = new Game();
        for (int i = 0; i < 12; i++) {
            game.roll(10);
        }

        assertThat(game.score()).isEqualTo(300);
    }

    @Test
    @DisplayName("given first roll is 0 and second roll is 10 should count as a spare")
    void givenFirstRollIs0AndSecondIs10ShouldCountAsASpare() {
        Game game = new Game();
        game.roll(0);
        game.roll(10);
        game.roll(5);
        game.roll(3);

        assertThat(game.score()).isEqualTo(23);
    }


}