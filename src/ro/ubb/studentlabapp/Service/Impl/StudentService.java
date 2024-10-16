package ro.ubb.studentlabapp.Service.Impl;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;
import ro.ubb.studentlabapp.Service.IStudentService;

import java.util.List;
import java.util.UUID;

public class StudentService implements IStudentService {
    private ICRUDRepository<Student> studentRepository;

    public StudentService(ICRUDRepository<Student> studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public boolean add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public boolean update(UUID id, Student student) {
        return studentRepository.update(id, student);
    }

    @Override
    public boolean delete(UUID id) {
        return studentRepository.delete(id);
    }
}
