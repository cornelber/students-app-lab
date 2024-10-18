package ro.ubb.studentlabapp.Repository.Impl;

import ro.ubb.studentlabapp.Domain.Assignment;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AssignmentRepository implements ICRUDRepository<Assignment> {

    private List<Assignment> assignments;

    public AssignmentRepository() {
        this.assignments = new ArrayList<>();
    }

    @Override
    public boolean save(Assignment assignment) {
        return assignments.add(assignment);
    }

    @Override
    public boolean update(UUID id, Assignment assignment) {
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        Assignment assignmentToDelete = findById(id);
        return assignments.remove(assignmentToDelete);
    }

    @Override
    public List<Assignment> findAll() {
        return this.assignments;
    }

    @Override
    public Assignment findById(UUID id) {
        for(Assignment assignment : assignments) {
            if(assignment.getId().equals(id)) {
                return assignment;
            }
        }
        return null;
    }
}
