package com.example;

public class StringCalculatorKata {

        public static String addNumbers(String numbers) {

            String[] numbersArray = numbers.split(",");
            String a = numbersArray[0];
            String b = numbersArray[1];
            int sum = Integer.parseInt(a) + Integer.parseInt(b);

            return String.valueOf(sum);
        }
}
