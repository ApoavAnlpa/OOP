package org.example;

import java.util.regex.*;
import java.util.ArrayList;

public class Calc {
    public int add(String numbers) {
        int sum = 0;
        String delimiter = ",|\\\\n";

        if (numbers.isBlank()) return sum;

        if (numbers.startsWith("//")) {
            int endIndex = numbers.indexOf("\\n");
            if (endIndex != -1 && endIndex + 2 < numbers.length()) {
                String customDelimiterSection = numbers.substring(2, endIndex);

                Pattern customPattern = Pattern.compile("\\[([^]]+)]");
                Matcher matcher = customPattern.matcher(customDelimiterSection);

                StringBuilder custom = new StringBuilder();
                if (matcher.find()) {
                    custom.append(Pattern.quote(matcher.group(1)));
                    delimiter += "|" + custom;
                } else {
                    delimiter += "|" + Pattern.quote(customDelimiterSection);
                }

                numbers = numbers.substring(endIndex + 2);
            } else {
                throw new IllegalArgumentException("Invalid custom delimiter section");
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
                throw new IllegalArgumentException("String contains characters other than numbers or delimiter");
            }
        }

        if (negNumbers.isEmpty()) {
            return sum;
        } else {
            throw new IllegalArgumentException("Negative numbers were added: " + String.join(", ", negNumbers));
        }
    }
}