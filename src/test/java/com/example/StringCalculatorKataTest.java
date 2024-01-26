package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorKataTest {

    @Test
    @DisplayName("given two numbers separated by comma, when calling addNumbers, then return sum of numbers")
    void givenTwoNumbersSeparatedByCommaWhenCallingAddNumbersThenReturnSumOfNumbers() {

        String numbers = "1,2";

        String result = StringCalculatorKata.addNumbers(numbers);

        assertThat(result).isEqualTo("3");
    }

    @Test
    @DisplayName("given one number when calling addNumbers then return the number")
    void givenOneNumberWhenCallingAddNumbersThenReturnTheNumber() {

            String number = "1";

            String result = StringCalculatorKata.addNumbers(number);

            assertThat(result).isEqualTo("1");
    }

    @Test
    @DisplayName("given empty string when calling addNumbers then return zero")
    void givenEmptyStringWhenCallingAddNumbersThenReturnZero() {

            String emptyString = "";

            String result = StringCalculatorKata.addNumbers(emptyString);

            assertThat(result).isEqualTo("0");
    }

    @Test
    void givenThreeNumbersSeparatedByCommaWhenCallingAddNumbersThenThrowException() {
        String numbers = "1,2,3";
        assertThrows(IllegalArgumentException.class, () -> {
            String result = StringCalculatorKata.addNumbers(numbers);
        });
    }

}
