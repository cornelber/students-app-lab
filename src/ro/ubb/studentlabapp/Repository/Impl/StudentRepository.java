package ro.ubb.studentlabapp.Repository.Impl;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements ICRUDRepository<Student> {
    private List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
    }

    @Override
    public boolean save(Student student) {
        students.add(student);
        return true;
    }
}
