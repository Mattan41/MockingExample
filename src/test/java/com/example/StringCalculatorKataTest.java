package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorKataTest {


    @Test
    @DisplayName("given two numbers when calling addNumbers, then return sum of numbers")
    void givenTwoNumbersWhenCallingAddNumbersThenReturnSumOfNumbers() {
        //Given - Arrange
        String a = "1";
        String b = "2";

        //When - Act
        String result = StringCalculatorKata.addNumbers(a, b);

        //Then - Assert
        assertThat(result).isEqualTo("3");
    }

}
