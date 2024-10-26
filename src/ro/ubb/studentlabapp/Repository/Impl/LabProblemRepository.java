package ro.ubb.studentlabapp.Repository.Impl;

import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of {@link ICRUDRepository} for managing LabProblem entities.
 */
public class LabProblemRepository implements ICRUDRepository<LabProblem> {
    private final List<LabProblem> labProblems;

    /**
     * Constructor for LabProblemRepository.
     * Initializes an empty list of lab problems.
     */
    public LabProblemRepository() {
        this.labProblems = new ArrayList<>();
    }

    /**
     * Saves a new lab problem to the repository.
     *
     * @param labProblemToAdd The lab problem to be saved.
     * @return true if the lab problem was successfully saved, false otherwise.
     * @throws RuntimeException if an error occurs while saving the lab problem.
     */
    @Override
    public boolean save(LabProblem labProblemToAdd) {
        try {
            return labProblems.add(labProblemToAdd);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the lab problem: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing lab problem in the repository by its UUID.
     *
     * @param id      The UUID of the lab problem to be updated.
     * @param labProblemToUpdate The updated lab problem details.
     * @return true if the lab problem was successfully updated, false otherwise.
     * @throws RuntimeException if the lab problem is not found or an error occurs during the update.
     */
    @Override
    public boolean update(UUID id, LabProblem labProblemToUpdate) {
        try {
            LabProblem labProblemToUpdateExisting = findById(id);
            if (labProblemToUpdateExisting == null) {
                throw new RuntimeException("Lab problem with id " + id + " not found.");
            }

            labProblemToUpdateExisting.setSubject(labProblemToUpdate.getSubject());
            labProblemToUpdateExisting.setDueDate(labProblemToUpdate.getDueDate());
            labProblemToUpdateExisting.setMaxScore(labProblemToUpdate.getMaxScore());

            return true;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the lab problem: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a lab problem from the repository by its UUID.
     *
     * @param labProblemIdToDelete The UUID of the lab problem to be deleted.
     * @return true if the lab problem was successfully deleted, false otherwise.
     * @throws RuntimeException if the lab problem is not found or an error occurs during the deletion.
     */
    @Override
    public boolean delete(UUID labProblemIdToDelete) {
        try {
            LabProblem labProblemToDelete = findById(labProblemIdToDelete);
            if (labProblemToDelete == null) {
                throw new RuntimeException("Lab problem with id " + labProblemIdToDelete + " not found.");
            }

            return labProblems.remove(labProblemToDelete);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the lab problem: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves all lab problems from the repository.
     *
     * @return A list of all lab problems.
     */
    @Override
    public List<LabProblem> findAll() {
        return this.labProblems;
    }

    /**
     * Finds a lab problem by its UUID.
     *
     * @param labProblemId The UUID of the lab problem to be found.
     * @return The lab problem object if found, or null if not found.
     * @throws RuntimeException if an error occurs while searching for the lab problem.
     */
    @Override
    public LabProblem findById(UUID labProblemId) {
        try {
            return labProblems.stream()
                    .filter(labProblem -> labProblem.getId().equals(labProblemId))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while finding the lab problem: " + e.getMessage(), e);
        }
    }
}
