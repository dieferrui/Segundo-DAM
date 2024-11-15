package es.cheste;

import java.util.Scanner;

public class CommonMethod {

    private final Scanner scanner = new Scanner(System.in);

    public int getValidIndex(int max) {
        int choice;

        do {
            System.out.print("Enter your choice: ");
            choice = getValidInteger();

            if (choice < 1 || choice > max) {
                System.out.println("Please select a number between 1 and " + max + ".");
            }

        } while (choice < 1 || choice > max);

        return choice;
    }

    public int getValidInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer value (1, 2, 3...)");

            }
        }
    }
}
