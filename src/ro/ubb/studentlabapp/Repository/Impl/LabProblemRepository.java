package ro.ubb.studentlabapp.Repository.Impl;

import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LabProblemRepository implements ICRUDRepository<LabProblem> {
    private List<LabProblem> labProblems;

    public LabProblemRepository() {
        this.labProblems = new ArrayList<>();
    }

    @Override
    public boolean save(LabProblem labProblem) {
        labProblems.add(labProblem);
        return true;
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
    public List<LabProblem> findAll() {
        return List.of();
    }

    @Override
    public LabProblem findById(UUID id) {
        return null;
    }
}
