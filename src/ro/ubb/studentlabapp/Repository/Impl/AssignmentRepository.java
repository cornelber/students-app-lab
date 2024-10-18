package ro.ubb.studentlabapp.Repository.Impl;

import ro.ubb.studentlabapp.Domain.Assignment;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of {@link ICRUDRepository} for managing Assignment entities.
 */
public class AssignmentRepository implements ICRUDRepository<Assignment> {
    private final List<Assignment> assignments;

    /**
     * Constructor for AssignmentRepository.
     * Initializes an empty list of assignments.
     */
    public AssignmentRepository() {
        this.assignments = new ArrayList<>();
    }

    /**
     * Saves a new assignment to the repository.
     *
     * @param assignmentToAdd The assignment to be saved.
     * @return true if the assignment was successfully saved, false otherwise.
     * @throws RuntimeException if an error occurs while saving the assignment.
     */
    @Override
    public boolean save(Assignment assignmentToAdd) {
        try {
            return assignments.add(assignmentToAdd);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the assignment: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing assignment in the repository by its UUID.
     *
     * @param id      The UUID of the assignment to be updated.
     * @param assignmentToUpdate The updated assignment details.
     * @return true if the assignment was successfully updated, false otherwise.
     * @throws RuntimeException if the assignment is not found or an error occurs during the update.
     */
    @Override
    public boolean update(UUID id, Assignment assignmentToUpdate) {
        try {
            Assignment existingAssignmentToUpdate = findById(id);
            if (existingAssignmentToUpdate == null) {
                throw new RuntimeException("Assignment with id " + id + " not found.");
            }

            existingAssignmentToUpdate.setGrade(assignmentToUpdate.getGrade());
            return true;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the assignment: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes an assignment from the repository by its UUID.
     *
     * @param assignmentIdToDelete The UUID of the assignment to be deleted.
     * @return true if the assignment was successfully deleted, false otherwise.
     * @throws RuntimeException if the assignment is not found or an error occurs during the deletion.
     */
    @Override
    public boolean delete(UUID assignmentIdToDelete) {
        try {
            Assignment assignmentToDelete = findById(assignmentIdToDelete);
            if (assignmentToDelete == null) {
                throw new RuntimeException("Assignment with id " + assignmentIdToDelete + " not found.");
            }

            return assignments.remove(assignmentToDelete);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the assignment: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves all assignments from the repository.
     *
     * @return A list of all assignments.
     */
    @Override
    public List<Assignment> findAll() {
        return this.assignments;
    }

    /**
     * Finds an assignment by its UUID.
     *
     * @param assignmentId The UUID of the assignment to be found.
     * @return The assignment object if found, or null if not found.
     * @throws RuntimeException if an error occurs while searching for the assignment.
     */
    @Override
    public Assignment findById(UUID assignmentId) {
        try {
            return assignments.stream()
                    .filter(assignment -> assignment.getId().equals(assignmentId))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while finding the assignment: " + e.getMessage(), e);
        }
    }
}
