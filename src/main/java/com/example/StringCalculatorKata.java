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

            Pattern pattern = Pattern.compile("^//(?:\\[(.+?)]|(.+?))\n");


            Matcher matcher = pattern.matcher(numbers);

            String delimiter;

            String[] numbersArray;

            if (matcher.find()) {

                if (matcher.group(1) != null)
                    delimiter = matcher.group(1);

                else
                    delimiter = matcher.group(2);

                numbers = numbers.substring(matcher.end());
                numbersArray = numbers.split(Pattern.quote(delimiter));
            } else {
                delimiter = "[,\n]"; //default delimiter
                numbersArray = numbers.split(delimiter);
            }

            List<Integer> numbersList = new ArrayList<>();

            for (String number : numbersArray) {
                    numbersList.add(Integer.parseInt(number));
                }

            List<Integer> negativeNumbers = new ArrayList<>();

            for (Integer number : numbersList) {

                if (number < 0)
                    negativeNumbers.add(number);
                else if (number <= 1000)
                    sum += number;

            }


            if (!negativeNumbers.isEmpty())
                throw new IllegalArgumentException("Negatives not allowed: " + negativeNumbers);


            return String.valueOf(sum);

        }
}
