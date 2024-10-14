package ro.ubb.studentlabapp;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Repository.IEntityRepository;
import ro.ubb.studentlabapp.Repository.Impl.StudentRepository;
import ro.ubb.studentlabapp.Service.IStudentService;
import ro.ubb.studentlabapp.Service.Impl.StudentService;
import ro.ubb.studentlabapp.UI.AppUI;
import ro.ubb.studentlabapp.UI.StudentUI;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<Student> students = null;
        
        IEntityRepository<Student> studentRepository = new StudentRepository(students);
//        IEntityRepository<ClientCard> clientCardRepository = new ClientCardRepository();
//        IEntityRepository<Transaction> transactionRepository = new TransactionRepository();

        IStudentService studentService = new StudentService(studentRepository);
//        IClientCardService clientCardService = new ClientCardServiceImpl(clientCardRepository);
//        ITransactionService transactionService = new TransactionServiceImpl(transactionRepository, carRepository, clientCardRepository);

        StudentUI studentUI = new StudentUI(studentService);
//        ClientCardUI clientCardUI = new ClientCardUI(clientCardService);
//        TransactionUI transactionUI = new TransactionUI(transactionService);

        AppUI appUI = new AppUI(studentUI);

        appUI.start();
    }
}