package ro.ubb.studentlabapp.Utils;

import java.util.List;
import java.util.Map;

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
     * Display a map of discounts in table format.
     *
     * @param header The header of the table.
     * @param discounts The map containing the discount details.
     */
    public static void displayDiscountTable(String header, Map<String, Float> discounts) {
        int separatorLength = calculateHeaderLength(header);
        String topAndBottomBorder = generateTableLine(separatorLength, TABLE_TOP_BOTTOM_BORDER);
        String separator = generateTableLine(separatorLength, TABLE_MIDDLE_LINE);

        // Display the top border of the table
        System.out.println(topAndBottomBorder);

        int size = discounts.size();
        if (size != 1) {
            // Display the header
            System.out.print(header);
            System.out.println(separator);
        }

        // Display each discount entry except the last one
        int index = 0;
        Map.Entry<String, Float> lastEntry = null;
        for (Map.Entry<String, Float> entry : discounts.entrySet()) {
            if(index == size - 1) {
                lastEntry = entry;
                break;
            }

            System.out.printf("| %-25s | %-15.2f |\n", entry.getKey(), entry.getValue());
            index++;
        }

        // Display the last entry if it exists
        if( size != 1 ) {
            System.out.println(separator);
        }

        if (lastEntry != null) {
            System.out.printf("| %-25s | %-15.2f |\n", lastEntry.getKey(), lastEntry.getValue());
        }

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
