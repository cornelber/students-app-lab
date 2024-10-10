package ro.ubb.studentlabapp.Repository.Impl;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.IEntityRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class StudentRepository implements IEntityRepository<Student> {
    private List<Student> students;

    public StudentRepository(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean save(Student student) {
        if (student.getId() == null || findById(student.getId()) != null) {
            throw new IllegalArgumentException(ErrorMessageStrings.CAR_ID_FOUND);
        }
        students.add(student);
        return true;
    }


    @Override
    public boolean update(UUID id, Student student) {
        Student existingStudent = findById(id);
        if(existingStudent == null) {
            throw new NoSuchElementException(ErrorMessageStrings.STUDENT_ID_NOT_FOUND);
        }

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());

        return true;

    }

    @Override
    public boolean delete(UUID id) {
        Student studentToDelete = findById(id);
        if (studentToDelete == null) {
            throw new NoSuchElementException(ErrorMessageStrings.STUDENT_ID_NOT_FOUND);
        }

        students.remove(studentToDelete);

        return true;
    }

    @Override
    public List<Student> findAll() {
        if(students.isEmpty()) {
            throw new NoSuchElementException(ErrorMessageStrings.STUDENT_EMPTY_LIST);
        }
        return students;
    }

    @Override
    public Student findById(UUID id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
