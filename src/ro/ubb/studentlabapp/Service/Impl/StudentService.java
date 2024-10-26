package ro.ubb.studentlabapp.Service.Impl;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;
import ro.ubb.studentlabapp.Service.IStudentService;

import java.util.List;
import java.util.UUID;

/**
 * Service class for managing student-related operations.
 * Implements {@link IStudentService} for basic CRUD operations on students.
 */
public class StudentService implements IStudentService {
    private final ICRUDRepository<Student> studentRepository;

    /**
     * Constructor for StudentService.
     *
     * @param studentRepository The repository used for student operations.
     */
    public StudentService(ICRUDRepository<Student> studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Adds a new student to the repository.
     *
     * @param studentToAdd The student to be added.
     * @return true if the student was successfully added, false otherwise.
     */
    @Override
    public boolean add(Student studentToAdd) {
        return studentRepository.save(studentToAdd);
    }

    /**
     * Updates an existing student in the repository.
     *
     * @param id      The UUID of the student to be updated.
     * @param studentToUpdate The updated student details.
     * @return true if the student was successfully updated, false otherwise.
     */
    @Override
    public boolean update(UUID id, Student studentToUpdate) {
        return studentRepository.update(id, studentToUpdate);
    }

    /**
     * Deletes a student from the repository.
     *
     * @param studentIdToDelete The UUID of the student to be deleted.
     * @return true if the student was successfully deleted, false otherwise.
     */
    @Override
    public boolean delete(UUID studentIdToDelete) {
        return studentRepository.delete(studentIdToDelete);
    }

    /**
     * Retrieves all students from the repository.
     *
     * @return A list of all students.
     */
    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
