package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculatorKata {

        public static String addNumbers(String numbers) {

            int sum = 0;

            if (numbers.isEmpty())
                return "0";


           Pattern pattern = Pattern.compile("^//(.)\n");
            Matcher matcher = pattern.matcher(numbers);

            String delimiter;

            if (matcher.find()) {
                delimiter = matcher.group(1);
                numbers = numbers.substring(matcher.end());
            } else {
                delimiter = "[,\n]";
            }

            String[] numbersArray = numbers.split(delimiter);
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
