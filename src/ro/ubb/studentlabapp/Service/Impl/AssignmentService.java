package ro.ubb.studentlabapp.Service.Impl;

import ro.ubb.studentlabapp.Domain.Assignment;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;
import ro.ubb.studentlabapp.Service.IAssignmentService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class that manages assignments-related operations.
 * Implements {@link IAssignmentService} for basic CRUD operations and additional logic.
 */
public class AssignmentService implements IAssignmentService {
    private final ICRUDRepository<Assignment> assignmentRepository;
    private final ICRUDRepository<Student> studentRepository;
    private final ICRUDRepository<LabProblem> labProblemRepository;

    /**
     * Constructor for AssignmentService.
     *
     * @param assignmentRepository   The repository used for assignment operations.
     * @param studentRepository      The repository used for student-related operations.
     * @param labProblemRepository   The repository used for lab problem-related operations.
     */
    public AssignmentService(ICRUDRepository<Assignment> assignmentRepository, ICRUDRepository<Student> studentRepository, ICRUDRepository<LabProblem> labProblemRepository) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
        this.labProblemRepository = labProblemRepository;
    }

    /**
     * Adds a new assignment to the repository.
     *
     * @param assignmentToAdd The assignment to be added.
     * @return true if the assignment was successfully added, false otherwise.
     */
    @Override
    public boolean add(Assignment assignmentToAdd) {
        return assignmentRepository.save(assignmentToAdd);
    }

    /**
     * Updates an existing assignment in the repository.
     *
     * @param id The UUID of the assignment to be updated.
     * @param assignmentToUpdate The updated assignment details.
     * @return true if the assignment was successfully updated, false otherwise.
     */
    @Override
    public boolean update(UUID id, Assignment assignmentToUpdate) {
        return assignmentRepository.update(id, assignmentToUpdate);
    }

    /**
     * Deletes an assignment from the repository.
     *
     * @param assignmentIdToDelete The UUID of the assignment to be deleted.
     * @return true if the assignment was successfully deleted, false otherwise.
     */
    @Override
    public boolean delete(UUID assignmentIdToDelete) {
        return assignmentRepository.delete(assignmentIdToDelete);
    }

    /**
     * Retrieves all assignments from the repository.
     *
     * @return A list of all assignments.
     */
    @Override
    public List<Assignment> getAll() {
        return assignmentRepository.findAll();
    }

    /**
     * Finds a student by their unique UUID.
     *
     * @param studentIdToFind The UUID of the student to be found.
     * @return The student object if found, or null if not found.
     */
    @Override
    public Student findStudentById(UUID studentIdToFind) {
        return studentRepository.findById(studentIdToFind);
    }

    /**
     * Finds a lab problem by its unique UUID.
     *
     * @param labProblemIdToFind The UUID of the lab problem to be found.
     * @return The lab problem object if found, or null if not found.
     */
    @Override
    public LabProblem findLabProblemById(UUID labProblemIdToFind) {
        return labProblemRepository.findById(labProblemIdToFind);
    }

    /**
     * Retrieves a list of students who do not have any assignments.
     *
     * @return A list of students without assignments.
     */
    @Override
    public List<Student> filterStudentsWithoutAssignments() {
        List<Assignment> allAssignments = assignmentRepository.findAll();
        return studentRepository.findAll().stream()
                .filter(student -> allAssignments.stream().noneMatch(assignment -> assignment.getStudent().getId().equals(student.getId())))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the lab problems with the highest number of assignments.
     *
     * @return A map containing the lab problems and their corresponding number of assignments.
     *         If no assignments are found, an empty map is returned.
     */
    public Map<LabProblem, Long> getMostAssignedLabProblems() {
        // Grouping by LabProblem and counting assignments for each lab problem
        Map<LabProblem, Long> labProblemCountMap = assignmentRepository.findAll().stream()
                .collect(Collectors.groupingBy(Assignment::getLabProblem, Collectors.counting()));

        // Find max count of assignments
        Optional<Long> maxCount = labProblemCountMap.values().stream().max(Long::compare);

        // Return all lab problems which has max count
        return maxCount.map(count -> labProblemCountMap.entrySet().stream()
                        .filter(entry -> entry.getValue().equals(count))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .orElse(Collections.emptyMap());  // If there are no assignments, return empty map
    }
}
