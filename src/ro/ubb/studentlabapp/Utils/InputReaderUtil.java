package ro.ubb.studentlabapp.Utils;

import ro.ubb.studentlabapp.Constants.ErrorMessageStrings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReaderUtil {
    private static final Scanner scanner = new Scanner(System.in);

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


//    public static LocalDate readDate(String prompt) {
//        try {
//            System.out.print(prompt);
//            String input = scanner.nextLine();
//
//            return DateFormatterUtil.parseDate(input);
//        } catch (DateTimeParseException e) {
//            System.out.println(ErrorMessageStrings.INPUT_DATE);
//            return readDate(prompt);
//        }
//    }
//
//
//    public static LocalDateTime readDateTime(String prompt) {
//        try {
//            System.out.print(prompt);
//            String input = scanner.nextLine();
//
//            return DateFormatterUtil.parseDateTime(input);
//        } catch (DateTimeParseException e) {
//            System.out.println(ErrorMessageStrings.INPUT_DATE_TIME);
//            return readDateTime(prompt);
//        }
//    }


    public static String readString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim(); // Read the user input and remove the white spaces
        return input.trim();
    }

}
