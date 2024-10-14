package ro.ubb.studentlabapp.Validation;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Constants.ErrorMessageStrings;

import java.util.ArrayList;
import java.util.List;

public class StudentValidator {

    public static void validateStudentDetails(Student student) {

        List<String> errors = new ArrayList<>();

        if (student.getName().isEmpty()) {
            errors.add(ErrorMessageStrings.STUDENT_NAME_VALIDATION);
        }

        if (student.getEmail().isEmpty()) {
            errors.add(ErrorMessageStrings.STUDENT_EMAIL_VALIDATION);
        }

        if (!EmailValidator.isValidEmail(student.getEmail())) {
            errors.add(ErrorMessageStrings.STUDENT_EMAIL_FORMAT_VALIDATION);
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }
}
