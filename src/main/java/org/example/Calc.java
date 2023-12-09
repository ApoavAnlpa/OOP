package org.example;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {
    public int add(String numbers) {
        int sum = 0;
        String delimiter = ",|\\\\n";

        if (numbers.isBlank()) return sum;

        if (numbers.startsWith("//")) {
            int endIndex = numbers.indexOf("\\n");

            if (endIndex != -1) {
                String customDelimiterSection = numbers.substring(2, endIndex);
                numbers = numbers.substring(endIndex + 2);

                Pattern customPattern = Pattern.compile("\\[([^]]+)]");
                Matcher matcher = customPattern.matcher(customDelimiterSection);

                StringBuilder custom = new StringBuilder();
                while (matcher.find()) {
                    if (!custom.isEmpty()) {
                        custom.append("|");
                    }
                    custom.append(Pattern.quote(matcher.group(1)));
                }

                if (!custom.isEmpty()) {
                    delimiter += "|" + custom;
                } else {
                    delimiter += "|" + Pattern.quote(customDelimiterSection);
                }
            }
        }

        ArrayList<String> negNumbers = new ArrayList<>();
        String[] numberArray = numbers.split(delimiter);

        for (String x : numberArray) {
            try {
                if (Integer.parseInt(x) < 0) {
                    negNumbers.add(x);
                } else if (Integer.parseInt(x) < 1001) {
                    sum += Integer.parseInt(x);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Рядок містить символи відмінні від чисел або деліметра");
            }
        }

        if (negNumbers.isEmpty()) {
            return sum;
        } else {
            throw new IllegalArgumentException("Додані наступні від'ємні числа: " + String.join(", ", negNumbers));
        }
    }
}