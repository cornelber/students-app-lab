package ro.ubb.studentlabapp.Service.Impl;

import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.IEntityRepository;
import ro.ubb.studentlabapp.Service.ILabProblemService;
import ro.ubb.studentlabapp.Validation.LabProblemValidator;
import ro.ubb.studentlabapp.Validation.StudentValidator;

import java.util.List;
import java.util.UUID;

public class LabProblemService implements ILabProblemService {
    private IEntityRepository<LabProblem> labProblemRepository;

    public LabProblemService(IEntityRepository<LabProblem> labProblemRepository) {
        this.labProblemRepository = labProblemRepository;
    }

    @Override
    public boolean add(LabProblem labProblem) {
        LabProblemValidator.validateLabProblemDetails(labProblem);
        return labProblemRepository.save(labProblem);
    }

    @Override
    public boolean update(UUID id, LabProblem labProblem) {
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public List<LabProblem> getAll() {
        return null;
    }
}
