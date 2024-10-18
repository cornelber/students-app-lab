package ro.ubb.studentlabapp.Service.Impl;

import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;
import ro.ubb.studentlabapp.Service.ILabProblemService;

import java.util.List;
import java.util.UUID;

/**
 * Service class for managing lab problem-related operations.
 * Implements {@link ILabProblemService} for basic CRUD operations on lab problems.
 */
public class LabProblemService implements ILabProblemService {
    private final ICRUDRepository<LabProblem> labProblemRepository;

    /**
     * Constructor for LabProblemService.
     *
     * @param labProblemRepository The repository used for lab problem operations.
     */
    public LabProblemService(ICRUDRepository<LabProblem> labProblemRepository) {
        this.labProblemRepository = labProblemRepository;
    }

    /**
     * Adds a new lab problem to the repository.
     *
     * @param labProblemToAdd The lab problem to be added.
     * @return true if the lab problem was successfully added, false otherwise.
     */
    @Override
    public boolean add(LabProblem labProblemToAdd) {
        return labProblemRepository.save(labProblemToAdd);
    }

    /**
     * Updates an existing lab problem in the repository.
     *
     * @param id         The UUID of the lab problem to be updated.
     * @param labProblemToUpdate The updated lab problem details.
     * @return true if the lab problem was successfully updated, false otherwise.
     */
    @Override
    public boolean update(UUID id, LabProblem labProblemToUpdate) {
        return labProblemRepository.update(id, labProblemToUpdate);
    }

    /**
     * Deletes a lab problem from the repository.
     *
     * @param labProblemIdToDelete The UUID of the lab problem to be deleted.
     * @return true if the lab problem was successfully deleted, false otherwise.
     */
    @Override
    public boolean delete(UUID labProblemIdToDelete) {
        return labProblemRepository.delete(labProblemIdToDelete);
    }

    /**
     * Retrieves all lab problems from the repository.
     *
     * @return A list of all lab problems.
     */
    @Override
    public List<LabProblem> getAll() {
        return labProblemRepository.findAll();
    }
}
