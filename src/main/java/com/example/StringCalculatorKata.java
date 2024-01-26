package com.example;

import java.util.ArrayList;
import java.util.List;

public class StringCalculatorKata {

        public static String addNumbers(String numbers) {

            int sum = 0;

            if (numbers.isEmpty())
                return "0";

            String[] numbersArray = numbers.split(",");
            List<Integer> numbersList = new ArrayList<>();

            for (String number : numbersArray) {
                    numbersList.add(Integer.parseInt(number));
                }

            for (String number : numbersArray) {
                sum += Integer.parseInt(number);
            }

            return String.valueOf(sum);

        }
}
