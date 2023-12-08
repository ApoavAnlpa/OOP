package org.example;

public class Calc {
    public int add(String numbers) {
        int sum = 0;

        if (numbers.isBlank()) return sum;

        String[] numberArray = numbers.split(",");
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