package ro.ubb.studentlabapp;

import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.IEntityRepository;
import ro.ubb.studentlabapp.Repository.Impl.LabProblemRepository;
import ro.ubb.studentlabapp.Repository.Impl.StudentRepository;
import ro.ubb.studentlabapp.Service.ILabProblemService;
import ro.ubb.studentlabapp.Service.IStudentService;
import ro.ubb.studentlabapp.Service.Impl.LabProblemService;
import ro.ubb.studentlabapp.Service.Impl.StudentService;
import ro.ubb.studentlabapp.UI.AppUI;
import ro.ubb.studentlabapp.UI.LabProblemUI;
import ro.ubb.studentlabapp.UI.StudentUI;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<Student> students = null;

        
        IEntityRepository<Student> studentRepository = new StudentRepository(students);
        IEntityRepository<LabProblem> clientCardRepository = new LabProblemRepository();
//        IEntityRepository<Transaction> transactionRepository = new TransactionRepository();

        IStudentService studentService = new StudentService(studentRepository);
        ILabProblemService labProblemService = new LabProblemService(clientCardRepository);
//        ITransactionService transactionService = new TransactionServiceImpl(transactionRepository, carRepository, clientCardRepository);

        StudentUI studentUI = new StudentUI(studentService);
        LabProblemUI labProblemUI = new LabProblemUI(labProblemService);
//        TransactionUI transactionUI = new TransactionUI(transactionService);

        AppUI appUI = new AppUI(studentUI, labProblemUI);

        appUI.start();
    }
}