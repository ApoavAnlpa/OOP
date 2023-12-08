package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Calc Calc = new Calc();

        System.out.println("Рядковий Калькулятор");
        System.out.print("Введіть: ");
        String numbers = scanner.nextLine();
        System.out.println("Результат: " + Calc.add(numbers));
    }
}