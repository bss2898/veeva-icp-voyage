package com.kilokart.app;

import java.math.BigDecimal;
import java.util.Scanner;

final class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    String readLine(String prompt) {
        System.out.print(prompt);
        System.out.flush();
        return scanner.hasNextLine() ? scanner.nextLine() : "0";
    }

    String readText(String prompt) {
        return readLine(prompt).trim();
    }

    String readPassword(String prompt) {
        return readLine(prompt);
    }

    int readInt(String prompt) {
        while (true) {
            String value = readText(prompt);
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Enter a whole number.");
            }
        }
    }

    BigDecimal readMoney(String prompt) {
        while (true) {
            String value = readText(prompt);
            try {
                return new BigDecimal(value);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid amount, for example 199.99.");
            }
        }
    }

    boolean confirm(String prompt) {
        while (true) {
            String answer = readText(prompt + " (y/n): ");
            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                return true;
            }
            if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
                return false;
            }
            System.out.println("Please enter y or n.");
        }
    }
}
