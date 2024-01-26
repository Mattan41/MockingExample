package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringCalculatorKataTest {


    @Test
    @DisplayName("given two numbers separated by comma, when calling addNumbers, then return sum of numbers")
    void givenTwoNumbersSeparatedByCommaWhenCallingAddNumbersThenReturnSumOfNumbers() {

        String numbers = "1,2";

        String result = StringCalculatorKata.addNumbers(numbers);

        assertThat(result).isEqualTo("3");
    }

}
