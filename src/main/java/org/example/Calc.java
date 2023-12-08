package org.example;

import java.util.regex.*;

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

        String[] numberArray = numbers.split(delimiter);
        
        for (String x: numberArray) {
            try{
                sum += Integer.parseInt(x);
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("Рядок містить символи відмінні від чисел або деліметра");
            }
        }
        return sum;
    }
}