package ro.ubb.studentlabapp.Domain;

import ro.ubb.studentlabapp.Utils.DateFormatterUtil;

import java.util.UUID;

/**
 * This class represents an assignment of a student for a specific lab problem and their grade.
 */
public class Assignment {
    private final UUID id;
    private Student student;
    private LabProblem labProblem;
    private int grade;

    /**
     * Constructor to create a new Assignment.
     *
     * @param student    The student who is assigned the lab problem
     * @param labProblem The lab problem assigned to the student
     * @param grade      The grade the student received for the assignment (must be between 0 and 100)
     * @throws IllegalArgumentException if the grade is not between 0 and 100
     */
    public Assignment(Student student, LabProblem labProblem, int grade) {
        this.id = UUID.randomUUID();
        this.student = student;
        this.labProblem = labProblem;
        this.grade = grade;
    }

    /**
     * Constructor to create an assignment only with grade (used for updates).
     *
     * @param grade The grade the student received for the assignment (must be between 0 and 100)
     * @throws IllegalArgumentException if the grade is not between 0 and 100
     */
    public Assignment(int grade) {
        this.id = UUID.randomUUID();
        this.grade = grade;
    }

    // Getter for assignment ID
    public UUID getId() {
        return this.id;
    }

    // Getter for student
    public Student getStudent() {
        return student;
    }

    // Getter for lab problem
    public LabProblem getLabProblem() {
        return labProblem;
    }

    // Getter for grade
    public int getGrade() {
        return grade;
    }

    // Setter for grade
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * Returns a formatted string representation of the assignment.
     *
     * @return A string representing the assignment details.
     */
    @Override
    public String toString() {
        return String.format("| %-36s | %-36s | %-30s | %-20s | %-10s | %-15d | %-10d |",
                getId(),
                getStudent().getFirstName() + " " + getStudent().getLastName(),
                getStudent().getEmail(),
                getLabProblem().getSubject(),
                DateFormatterUtil.formatDate(getLabProblem().getDueDate()),
                getGrade(),
                getLabProblem().getMaxScore()
        );
    }
}
