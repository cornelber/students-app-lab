package ro.ubb.studentlabapp.Utils;

import java.util.List;

/**
 * Utility class for generating formatted tables.
 */
public class TableFormatterUtil {
    private static final char TABLE_TOP_BOTTOM_BORDER = '=';
    private static final char TABLE_MIDDLE_LINE = '-';
    /**
     * Generates a formatted table with the specified header and list of data.
     *
     * @param header The header of the table.
     * @param data   The list of data to be displayed in the table.
     */
    public static <Entity> void displayTableFormat(String header, List<Entity> data) {
        int separatorLength = calculateHeaderLength(header);
        String topAndBottomBorder = generateTableLine(separatorLength, TABLE_TOP_BOTTOM_BORDER);
        String separator = generateTableLine(separatorLength, TABLE_MIDDLE_LINE );

        // Display the top border of the table
        System.out.println(topAndBottomBorder);

        // Display the header
        System.out.print(header);
        System.out.println(separator);

        // Display each row of data
        data.forEach(System.out::println);

        // Display the bottom border of the table
        System.out.println(topAndBottomBorder);
    }

    /**
     * Calculates the length of the header for generating table lines.
     *
     * @param header The header of the table.
     * @return The calculated length of the header.
     */
    private static int calculateHeaderLength(String header) {
        return header.length() - 1;
    }

    /**
     * Generates a string of characters of length n.
     *
     * @param n The length of the generated string.
     * @param c The character to repeat.
     * @return The generated string.
     */
    private static String generateTableLine(int n, char c) {
        return String.valueOf(c).repeat(Math.max(0, n));
    }
}
