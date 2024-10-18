package ro.ubb.studentlabapp.Service.Impl;

import ro.ubb.studentlabapp.Domain.Assignment;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;
import ro.ubb.studentlabapp.Service.IAssignmentService;

import java.util.List;
import java.util.UUID;

public class AssignmentService implements IAssignmentService {
    private ICRUDRepository<Assignment> assignmentRepository;
    private ICRUDRepository<Student> studentRepository;
    private ICRUDRepository<LabProblem> labProblemRepository;

    public AssignmentService(ICRUDRepository<Assignment> assignmentRepository, ICRUDRepository<Student> studentRepository, ICRUDRepository<LabProblem> labProblemRepository) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
        this.labProblemRepository = labProblemRepository;
    }

    @Override
    public boolean add(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public boolean update(UUID id, Assignment assignment) {
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public List<Assignment> getAll() {
        return null;
    }

    @Override
    public Student findStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    @Override
    public LabProblem findLabProblemById(UUID id) {
        return labProblemRepository.findById(id);
    }
}
