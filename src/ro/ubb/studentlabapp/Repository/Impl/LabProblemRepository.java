package ro.ubb.studentlabapp.Repository.Impl;

import ro.ubb.studentlabapp.Constants.ErrorMessageStrings;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Repository.IEntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LabProblemRepository implements IEntityRepository<LabProblem> {
    private List<LabProblem> labProblems;

    public LabProblemRepository() {
        this.labProblems =  new ArrayList<>();
    }

    @Override
    public boolean save(LabProblem labProblem) {
        if (labProblem.getProblemId() == null || findById(labProblem.getProblemId()) != null) {
            throw new IllegalArgumentException(ErrorMessageStrings.LABPROBLEM_ID_FOUND);
        }
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
        return null;
    }

    @Override
    public LabProblem findById(UUID id) {
        return labProblems.stream()
                .filter(student -> student.getProblemId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
