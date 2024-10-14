package ro.ubb.studentlabapp.Domain;

public class Assignment {
    private Student student;
    private LabProblem labProblem;
    private double grade;

    public Assignment(Student student, LabProblem labProblem, double grade) {
        this.student = student;
        this.labProblem = labProblem;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public double getGrade() {
        return grade;
    }

    public LabProblem getLabProblem() {
        return labProblem;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "student=" + student +
                ", labProblem=" + labProblem +
                ", grade=" + grade +
                '}';
    }
}
