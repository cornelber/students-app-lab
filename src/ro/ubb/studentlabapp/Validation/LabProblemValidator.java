package ro.ubb.studentlabapp.Validation;

import ro.ubb.studentlabapp.Constants.ErrorMessageStrings;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;

import java.util.ArrayList;
import java.util.List;

public class LabProblemValidator {


    public static void validateLabProblemDetails(LabProblem labProblem) {

        List<String> errors = new ArrayList<>();

        if (labProblem.getSubject().isEmpty()) {
            errors.add(ErrorMessageStrings.LABPROBLEM_SUBJECT_VALIDATION);
        }

        if (!MaxScoreValidator.isMaxScoreValid(labProblem.getMaxScore())) {
            errors.add(ErrorMessageStrings.LABPROBLEM_MAXSCORE_VALIDATION);
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }
}
