package ro.ubb.studentlabapp;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.ICRUDRepository;
import ro.ubb.studentlabapp.Repository.Impl.StudentRepository;
import ro.ubb.studentlabapp.Service.IStudentService;
import ro.ubb.studentlabapp.Service.Impl.StudentService;
import ro.ubb.studentlabapp.UI.AppUI;

public class Main {
    public static void main(String[] args) {
        ICRUDRepository<Student> studentRepository = new StudentRepository();
        IStudentService studentService = new StudentService(studentRepository);

        AppUI appUI = new AppUI(studentService);
        appUI.run();
    }
}