package com.example;

import java.util.ArrayList;
import java.util.List;

public class StringCalculatorKata {

        public static String addNumbers(String numbers) {

            if (numbers.isEmpty())
                return "0";

            String[] numbersArray = numbers.split(",");
            List<Integer> numbersList = new ArrayList<>();

                for (String number : numbersArray) {
                    numbersList.add(Integer.parseInt(number));
                }

            if (numbersList.size() == 1) {
                return numbersList.get(0).toString();
            }

            int sum = numbersList.get(0) + numbersList.get(1);
            return String.valueOf(sum);


        }
}
