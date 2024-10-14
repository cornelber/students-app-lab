package ro.ubb.studentlabapp.Domain;

import java.time.LocalDate;
import java.util.UUID;

public class LabProblem {
    private UUID problemId;
    private String subject;
    // vars for reports
    private LocalDate dueDate;
    private int maxScore;

    public LabProblem(UUID problemId, String subject, LocalDate dueDate, int maxScore) {
        this.problemId = problemId;
        this.subject = subject;
        this.dueDate = dueDate;
        this.maxScore = maxScore;
    }

    public UUID getProblemId() {
        return problemId;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return "LabProblem{" +
                "problemId=" + problemId +
                ", subject='" + subject + '\'' +
                ", dueDate=" + dueDate +
                ", maxScore=" + maxScore +
                '}';
    }
}
