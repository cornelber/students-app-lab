package ro.ubb.studentlabapp.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Utility class for LocalDate & LocalDateTime format.
 */
public class DateFormatterUtil {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm";
    /**
     * Format a LocalDate object to a string using the specified pattern.
     *
     * @param date The LocalDate object to be formatted.
     * @return The formatted date string.
     */
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
    /**
     * Format a LocalDate object to a string using the specified pattern.
     *
     * @param dateTime The LocalDate object to be formatted.
     * @return The formatted date string.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
    /**
     * Parse a string representing a date to a LocalDate object using the specified pattern.
     *
     * @param dateString The string representing the date.
     * @return The parsed LocalDate object.
     */
    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
    /**
     * Parse a string representing a date and time to a LocalDateTime object using the specified pattern.
     *
     * @param dateTimeString The string representing the date and time.
     * @return The parsed LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
}

