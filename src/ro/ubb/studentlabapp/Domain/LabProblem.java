package ro.ubb.studentlabapp.Domain;

import ro.ubb.studentlabapp.Utils.DateFormatterUtil;

import java.time.LocalDate;
import java.util.UUID;

/**
 * This class represents a lab problem with a unique ID, subject, due date, and maximum score.
 */
public class LabProblem {
    private UUID problemId;
    private String subject;
    private LocalDate dueDate;

    /**
     * Default constructor to create an empty LabProblem.
     * The UUID will be generated automatically.
     */
    public LabProblem() {
        this.problemId = UUID.randomUUID();
    }

    /**
     * Constructor to create a new LabProblem with specific details.
     *
     * @param subject   The subject of the lab problem
     * @param dueDate   The due date for the lab problem
     */
    public LabProblem(String subject, LocalDate dueDate) {
        this.problemId = UUID.randomUUID();
        this.subject = subject;
        this.dueDate = dueDate;
    }

    // Getter for problem ID
    public UUID getProblemId() {
        return problemId;
    }

    // Getter for lab problem subject
    public String getSubject() {
        return subject;
    }

    // Getter for due date
    public LocalDate getDueDate() {
        return dueDate;
    }

    // Setter for subject
    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Setter for due date
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns a formatted string representation of the lab problem.
     *
     * @return A string representing the lab problem details.
     */
    @Override
    public String toString() {
        return String.format("| %-36s | %-20s | %-10s |",
                getProblemId(),
                getSubject(),
                DateFormatterUtil.formatDate(getDueDate())
        );
    }
}
