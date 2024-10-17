package ro.ubb.studentlabapp.Service.Impl;

import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;
import ro.ubb.studentlabapp.Service.ILabProblemService;

import java.util.List;
import java.util.UUID;

public class LabProblemService implements ILabProblemService {
    private ICRUDRepository<LabProblem> labProblemRepository;

    public LabProblemService(ICRUDRepository<LabProblem> labProblemRepository) {
        this.labProblemRepository = labProblemRepository;
    }

    @Override
    public boolean add(LabProblem labProblem) {
        return labProblemRepository.save(labProblem);
    }

    @Override
    public boolean update(UUID id, LabProblem labProblem) {
        return labProblemRepository.update(id, labProblem);
    }

    @Override
    public boolean delete(UUID id) {
        return labProblemRepository.delete(id);
    }

    @Override
    public List<LabProblem> getAll() {
        return labProblemRepository.findAll();
    }
}
