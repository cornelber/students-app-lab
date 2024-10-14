package ro.ubb.studentlabapp.Service.Impl;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.IEntityRepository;
import ro.ubb.studentlabapp.Service.IStudentService;
import ro.ubb.studentlabapp.Validation.StudentValidator;
import ro.ubb.studentlabapp.Constants.ErrorMessageStrings;

import java.util.List;
import java.util.UUID;

public class StudentService implements IStudentService {

    private IEntityRepository<Student> studentRepository;

    public StudentService(IEntityRepository<Student> studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean add(Student student) {
        StudentValidator.validateStudentDetails(student);
        return studentRepository.save(student);
    }

    @Override
    public boolean update(UUID id, Student student) {
        StudentValidator.validateStudentDetails(student);
        return studentRepository.update(id, student);
    }

    @Override
    public boolean delete(UUID id) {
        return studentRepository.delete(id);
    }
    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
