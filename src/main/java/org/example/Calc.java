package org.example;

public class Calc {
    public int add(String numbers) {
        int sum = 0;

        if (numbers.isBlank()) return sum;

        String[] numberArray = numbers.split(",");
        if (numberArray.length > 2) throw new IllegalArgumentException("Не більше 2 чисел");
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