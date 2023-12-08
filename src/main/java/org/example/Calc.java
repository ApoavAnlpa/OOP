package org.example;

import java.util.regex.*;
import java.util.ArrayList;

public class Calc {
    public int add(String numbers) {
        int sum = 0;
        String delimiter = ",|\\\\n";

        if (numbers.isBlank()) return sum;

        if (numbers.startsWith("//")) {
            int EndIndex = numbers.indexOf("\\n");
            delimiter += "|" + Pattern.quote(numbers.substring(2, EndIndex));
            numbers = numbers.substring(EndIndex + 2);
        }

        ArrayList<String> negNumbers = new ArrayList<>();
        String[] numberArray = numbers.split(delimiter);

        for (String x: numberArray) {
            try{
                if (Integer.parseInt(x) < 0) {
                    negNumbers.add(x);
                } else if (Integer.parseInt(x) < 1001) {
                    sum += Integer.parseInt(x);
                }
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("Рядок містить символи відмінні від чисел або деліметра");
            }
        }
        if (negNumbers.isEmpty()){
            return sum;
        }
        else {
            throw new IllegalArgumentException("Додані наступні від'ємні числа: " + String.join(", ", negNumbers));
        }
    }
}