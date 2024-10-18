package ro.ubb.studentlabapp.Service;

import ro.ubb.studentlabapp.Domain.Assignment;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface for assignment-related operations.
 *
 * <p>Extends {@link ICRUDService}, adding specific behavior for managing assignments, students, and lab problems.</p>
 */
public interface IAssignmentService extends ICRUDService<Assignment>{
    /**
     * Finds a student by their unique ID.
     *
     * @param id The UUID of the student to be found.
     * @return The student object if found, or null if not found.
     */
    Student findStudentById(UUID id);

    /**
     * Finds a lab problem by its unique ID.
     *
     * @param id The UUID of the lab problem to be found.
     * @return The lab problem object if found, or null if not found.
     */
    LabProblem findLabProblemById(UUID id);

    /**
     * Retrieves a list of students who currently do not have any assignments.
     *
     * @return A list of students without assignments.
     */
    List<Student> filterStudentsWithoutAssignments();

    /**
     * Retrieves the lab problems with the highest number of assignments.
     *
     * @return A map containing the lab problems and their corresponding assignment counts.
     */
    Map<LabProblem, Long> getMostAssignedLabProblems();
}
