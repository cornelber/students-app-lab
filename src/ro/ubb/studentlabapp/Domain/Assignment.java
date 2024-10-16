package ro.ubb.studentlabapp.Domain;

/**
 * This class represents an assignment of a student for a specific lab problem and their grade.
 */
public class Assignment {
    private Student student;
    private LabProblem labProblem;
    private double grade;

    /**
     * Constructor to create a new Assignment.
     *
     * @param student    The student who is assigned the lab problem
     * @param labProblem The lab problem assigned to the student
     * @param grade      The grade the student received for the assignment
     */
    public Assignment(Student student, LabProblem labProblem, double grade) {
        this.student = student;
        this.labProblem = labProblem;
        this.grade = grade;
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
    public double getGrade() {
        return grade;
    }

    // Setter for grade
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Returns a formatted string representation of the assignment.
     *
     * @return A string representing the assignment details.
     */
    @Override
    public String toString() {
        return String.format("| %-36s | %-36s | %-20s | %-10.2f |",
                getStudent().getId(),
                getStudent().getFirstName() + " " + getStudent().getLastName(),
                getLabProblem().getSubject(),
                getGrade()
        );
    }
}
