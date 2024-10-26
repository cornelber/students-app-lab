package ro.ubb.studentlabapp.Repository.Impl;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of {@link ICRUDRepository} for managing Student entities.
 */
public class StudentRepository implements ICRUDRepository<Student> {
    private final List<Student> students;

    /**
     * Constructor for StudentRepository.
     * Initializes an empty list of students.
     */
    public StudentRepository() {
        this.students = new ArrayList<>();
    }

    /**
     * Saves a new student to the repository.
     *
     * @param studentToAdd The student to be saved.
     * @return true if the student was successfully saved, false otherwise.
     * @throws RuntimeException if an error occurs while saving the student.
     */
    @Override
    public boolean save(Student studentToAdd) {
        try {
            return students.add(studentToAdd);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the student: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing student in the repository by their UUID.
     *
     * @param id      The UUID of the student to be updated.
     * @param studentToUpdate The updated student details.
     * @return true if the student was successfully updated, false otherwise.
     * @throws RuntimeException if the student is not found or an error occurs during the update.
     */
    @Override
    public boolean update(UUID id, Student studentToUpdate) {
        try {
            Student oldStudent = findById(id);
            if (oldStudent == null) {
                throw new RuntimeException("Student with id " + id + " not found.");
            }

            oldStudent.setFirstName(studentToUpdate.getFirstName());
            oldStudent.setLastName(studentToUpdate.getLastName());
            oldStudent.setEmail(studentToUpdate.getEmail());

            return true;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the student: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a student from the repository by their UUID.
     *
     * @param studentIdToDelete The UUID of the student to be deleted.
     * @return true if the student was successfully deleted, false otherwise.
     * @throws RuntimeException if the student is not found or an error occurs during the deletion.
     */
    @Override
    public boolean delete(UUID studentIdToDelete) {
        try {
            Student studentToDelete = findById(studentIdToDelete);
            if (studentToDelete == null) {
                throw new RuntimeException("Student with id " + studentIdToDelete + " not found.");
            }

            return students.remove(studentToDelete);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the student: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves all students from the repository.
     *
     * @return A list of all students.
     */
    @Override
    public List<Student> findAll() {
        return this.students;
    }

    /**
     * Finds a student by their UUID.
     *
     * @param studentId The UUID of the student to be found.
     * @return The student object if found, or null if not found.
     * @throws RuntimeException if an error occurs while searching for the student.
     */
    @Override
    public Student findById(UUID studentId) {
        try {
            return students.stream()
                    .filter(student -> student.getId().equals(studentId))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while finding the student: " + e.getMessage(), e);
        }
    }
}
