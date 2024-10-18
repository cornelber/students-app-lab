package ro.ubb.studentlabapp;

import ro.ubb.studentlabapp.Domain.Assignment;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;
import ro.ubb.studentlabapp.Repository.Impl.AssignmentRepository;
import ro.ubb.studentlabapp.Repository.Impl.LabProblemRepository;
import ro.ubb.studentlabapp.Repository.Impl.StudentRepository;
import ro.ubb.studentlabapp.Service.IAssignmentService;
import ro.ubb.studentlabapp.Service.ILabProblemService;
import ro.ubb.studentlabapp.Service.IStudentService;
import ro.ubb.studentlabapp.Service.Impl.AssignmentService;
import ro.ubb.studentlabapp.Service.Impl.LabProblemService;
import ro.ubb.studentlabapp.Service.Impl.StudentService;
import ro.ubb.studentlabapp.UI.AppUI;

public class Main {
    public static void main(String[] args) {
        ICRUDRepository<Student> studentRepository = new StudentRepository();
        ICRUDRepository<LabProblem> labProblemRepository = new LabProblemRepository();
        ICRUDRepository<Assignment> assignmentRepository = new AssignmentRepository();

        IStudentService studentService = new StudentService(studentRepository);
        ILabProblemService labProblemService = new LabProblemService(labProblemRepository);
        IAssignmentService assignmentService = new AssignmentService(assignmentRepository, studentRepository, labProblemRepository);

        AppUI appUI = new AppUI(studentService, labProblemService, assignmentService);
        appUI.run();
    }
}