package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculatorKata {

    public static String addNumbers(String numbers) {

        int sum = 0;

        if (numbers.isEmpty())
            return "0";

        Pattern pattern = Pattern.compile("^//((?:\\[(.+?)]|(.+?))*)\n");

        Matcher matcher = pattern.matcher(numbers);

        List<String> delimiters = new ArrayList<>();

        String[] numbersArray;

        if (matcher.find()) {

            String delimiterString = matcher.group(1);

            Pattern delimiterPattern = Pattern.compile("\\[(.+?)]");

            Matcher delimiterMatcher = delimiterPattern.matcher(delimiterString);

            while (delimiterMatcher.find()) {
                delimiters.add(Pattern.quote(delimiterMatcher.group(1)));
            }

            if (delimiters.isEmpty()) {
                delimiters.add(Pattern.quote(delimiterString));
            }

            numbers = numbers.substring(matcher.end());

            String delimitersRegex = String.join("|", delimiters);

            numbersArray = numbers.split(delimitersRegex);

        } else {
            String delimiter = "[,\n]"; //default delimiter
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
