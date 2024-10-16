package ro.ubb.studentlabapp.Repository.Impl;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentRepository implements ICRUDRepository<Student> {
    private List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
    }

    @Override
    public List<Student> findAll() {
        return this.students;
    }

    @Override
    public boolean save(Student student) {
        students.add(student);
        return true;
    }

    @Override
    public boolean delete(UUID id) {
        Student studentToDelete = findById(id);
        return students.remove(studentToDelete);
    }

    @Override
    public Student findById(UUID id) {
        for(Student student : students) {
            if(student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
}
