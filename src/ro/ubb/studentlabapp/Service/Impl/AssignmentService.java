package ro.ubb.studentlabapp.Service.Impl;

import ro.ubb.studentlabapp.Domain.Assignment;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;
import ro.ubb.studentlabapp.Service.IAssignmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return assignmentRepository.update(id, assignment);
    }

    @Override
    public boolean delete(UUID id) {
        return assignmentRepository.delete(id);
    }

    @Override
    public List<Assignment> getAll() {
        return assignmentRepository.findAll();
    }

    @Override
    public Student findStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    @Override
    public LabProblem findLabProblemById(UUID id) {
        return labProblemRepository.findById(id);
    }

    @Override
    public List<Student> filterStudentsWithoutAssignments() {
        List<Assignment> allAssignments = assignmentRepository.findAll();
        return studentRepository.findAll().stream()
                .filter(student -> allAssignments.stream().noneMatch(assignment -> assignment.getStudent().getId().equals(student.getId())))
                .collect(Collectors.toList());
    }

    public Optional<LabProblem> getMostAssignedLabProblem() {
        return assignmentRepository.findAll().stream()
                .collect(Collectors.groupingBy(Assignment::getLabProblem, Collectors.counting()))  // Grupăm după labProblem și numărăm
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())  // Găsim labProblem-ul cu cea mai mare valoare (număr de asignări)
                .map(Map.Entry::getKey);  // Extragem labProblem-ul corespunzător
    }
}
