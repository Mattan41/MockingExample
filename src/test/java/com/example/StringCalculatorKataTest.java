package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
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

//    @Test
//    @DisplayName("given three numbers separated by comma when calling addNumbers then throw exception")
//    void givenThreeNumbersSeparatedByCommaWhenCallingAddNumbersThenThrowException() {
//        String numbers = "1,2,3";
//        assertThrows(IllegalArgumentException.class, () -> { StringCalculatorKata.addNumbers(numbers);
//        });
//    }

    @Test
    @DisplayName("given large amount of numbers when calling addNumbers then return sum of numbers")
    void givenLargeAmountOfNumbersWhenCallingAddNumbersThenReturnSumOfNumbers() {
        String numbers = "1,2,3,4,5,6,7,8,9,10";
        String result = StringCalculatorKata.addNumbers(numbers);
        assertThat(result).isEqualTo("55");
    }

    @Test
    @DisplayName("numbers can also be separated by new line")
    void numbersCanAlsoBeSeparatedByNewLine() {
        String numbers = "1\n2,3";
        String result = StringCalculatorKata.addNumbers(numbers);
        assertThat(result).isEqualTo("6");
    }

    @Test
    @DisplayName("It should be possible to change delimiter")
    void itShouldBePossibleToChangeDelimiter() {
        String numbers = "//;\n1;2";
        String result = StringCalculatorKata.addNumbers(numbers);
        assertThat(result).isEqualTo("3");
    }

    @Test
    @DisplayName("Calling addNumbers with negative numbers will throw an exception")
    void callingAddNumbersWithNegativeNumbersWillThrowAnException() {
        String numbers = "-1,2";
        assertThrows(IllegalArgumentException.class, () -> { StringCalculatorKata.addNumbers(numbers);
        });

    }

    @Test
    @DisplayName("Calling addNumbers with negative numbers throws an exception with the negative numbers in the exception message")
    void callingAddNumbersWithNegativeNumbersThrowsAnExceptionWithTheNegativeNumbersInTheExceptionMessage() {
        String numbers = "-1,2,-3";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { StringCalculatorKata.addNumbers(numbers);
        });
        assertThat(exception.getMessage()).isEqualTo("Negatives not allowed: [-1, -3]");
    }
}
