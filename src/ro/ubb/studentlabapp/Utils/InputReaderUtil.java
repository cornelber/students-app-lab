package ro.ubb.studentlabapp.Utils;

import ro.ubb.studentlabapp.Constants.ErrorMessageStrings;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class for reading user input from the console.
 */
public class InputReaderUtil {
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Reads an integer input from the console.
     *
     * @param prompt The message prompt displayed to the user.
     * @return The integer input provided by the user.
     */
    public static int readInt(String prompt) {
        try {
            System.out.print(prompt);
            int input = scanner.nextInt();
            scanner.nextLine(); //  Consume the remaining newline
            return input;
        } catch (InputMismatchException ime) {
            System.out.println(ErrorMessageStrings.INPUT_INTEGER);
            scanner.next(); // Consume incorrect input
            return readInt(prompt);
        }
    }

    /**
     * Reads a floating-point number input from the console.
     *
     * @param prompt The message prompt displayed to the user.
     * @return The floating-point number input provided by the user.
     */
    public static float readFloat(String prompt) {
        try {
            System.out.print(prompt);
            float input =  scanner.nextFloat();
            scanner.nextLine(); // Consume the remaining newline
            return input;
        } catch (InputMismatchException ime) {
            System.out.println(ErrorMessageStrings.INPUT_FLOAT);
            scanner.next(); // Consume incorrect input
            return readFloat(prompt);
        }
    }

    /**
     * Reads a double precision floating-point number input from the console.
     *
     * @param prompt The message prompt displayed to the user.
     * @return The double precision floating-point number input provided by the user.
     */
    public static double readDouble(String prompt) {
        try {
            System.out.print(prompt);
            double input = scanner.nextDouble();
            scanner.nextLine(); // Consume the remaining newline
            return input;
        } catch (InputMismatchException ime) {
            System.out.println("Please enter a valid double.");
            scanner.next(); // Consume incorrect input
            return readDouble(prompt);
        }
    }

    /**
     * Reads a boolean input from the console.
     *
     * @param prompt The message prompt displayed to the user.
     * @return The boolean input provided by the user.
     */
    public static boolean readBoolean(String prompt) {
        try {
            System.out.print(prompt);
            boolean input =  scanner.nextBoolean();
            scanner.nextLine(); // Consume the remaining newline
            return input;
        } catch (InputMismatchException ime) {
            System.out.println(ErrorMessageStrings.INPUT_BOOLEAN);
            scanner.next(); // Consume incorrect input
            return readBoolean(prompt);
        }
    }

    /**
     * Reads a date from the user input in the format dd.mm.yyyy.
     *
     * @param prompt The message prompt to be displayed to the user.
     * @return The parsed LocalDate object.
     */
    public static LocalDate readDate(String prompt) {
        try {
            System.out.print(prompt);
            String input = scanner.nextLine();

            // Parse the input string into a LocalDate object
            return DateFormatterUtil.parseDate(input);
        } catch (DateTimeParseException e) {
            System.out.println(ErrorMessageStrings.INPUT_DATE);
            return readDate(prompt);
        }
    }

    /**
     * Reads a string input from the console.
     *
     * @param prompt The message prompt displayed to the user.
     * @return The string input provided by the user.
     */
    public static String readString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim(); // Read the user input and remove the white spaces
        return input.trim();
    }
}
