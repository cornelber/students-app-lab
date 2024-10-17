package ro.ubb.studentlabapp.Repository.Impl;

import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;
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
        LabProblem labProblemToUpdate = findById(id);

        labProblemToUpdate.setSubject(labProblem.getSubject());
        labProblemToUpdate.setDueDate(labProblem.getDueDate());
        labProblemToUpdate.setMaxScore(labProblem.getMaxScore());

        return true;
    }

    @Override
    public boolean delete(UUID id) {
        LabProblem labProblemToDelete = findById(id);
        return labProblems.remove(labProblemToDelete);
    }

    @Override
    public List<LabProblem> findAll() {
        return this.labProblems;
    }

    @Override
    public LabProblem findById(UUID id) {
        for(LabProblem labProblem : labProblems) {
            if(labProblem.getId().equals(id)) {
                return labProblem;
            }
        }
        return null;
    }
}
