package ro.ubb.studentlabapp.Constants;

public class ErrorMessageStrings {
    // Input error messages
    public static final String INPUT_INTEGER = "Invalid input. Please enter an integer";
    public static final String INPUT_BOOLEAN = "Invalid input. Please enter 'true' or 'false'";
    public static final String INPUT_FLOAT = "Invalid input. Please enter a floating-point number";
    public static final String INPUT_DATE = "Invalid date format. Please enter the date in the format (dd.mm.yyyy).";
    public static final String INPUT_DATE_TIME = "Invalid date time format. Please enter the date in the format (dd.mm.yyyy HH:mm).";


    // Car-related error messages
    public static final String STUDENT_ID_FOUND = "A student with the specified ID already exists.";
    public static final String STUDENT_ID_NOT_FOUND = "A student with the specified ID not found.";
    public static final String STUDENT_EMPTY_LIST = "There are no students available.";
    public static final String STUDENT_NAME_VALIDATION = "The student name cannot be empty.";
    public static final String STUDENT_EMAIL_VALIDATION = "The student email cannot be empty.";
    public static final String STUDENT_EMAIL_FORMAT_VALIDATION = "Invalid email format. The email must end with @scs.ubbcluj.ro.";
    public static final String EMPTY_STUDENT_SEARCH_RESULTS = "No students found matching the search criteria.";
}
