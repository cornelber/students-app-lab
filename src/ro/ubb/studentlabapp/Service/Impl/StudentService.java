package ro.ubb.studentlabapp.Service.Impl;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;
import ro.ubb.studentlabapp.Service.IStudentService;

import java.util.List;

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
}
