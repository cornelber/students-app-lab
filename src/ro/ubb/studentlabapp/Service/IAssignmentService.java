package ro.ubb.studentlabapp.Service;

import ro.ubb.studentlabapp.Domain.Assignment;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;

import java.util.UUID;

public interface IAssignmentService extends ICRUDService<Assignment>{
    Student findStudentById(UUID id);
    LabProblem findLabProblemById(UUID id);
}
