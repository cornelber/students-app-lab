package ro.ubb.studentlabapp.UI;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Service.IStudentService;
import ro.ubb.studentlabapp.Utils.InputReaderUtil;

import java.util.UUID;

public class AppUI {
    private IStudentService studentService;

    public AppUI (IStudentService studentService) {
        this.studentService = studentService;
    }


    public void run() {
        int choice;

        do {
            choice = displayMainMenu();
            processChoice(choice);
        } while ( choice != 0);
    }

    private int displayMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Add a new student");
        System.out.println("2. Update a student");
        System.out.println("3. Delete a student");
        System.out.println("4. View all students");
        System.out.println("0. Close app");
        return InputReaderUtil.readInt("Enter your choice: ");
    }

    private void processChoice(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Closing app...");
                break;
            case 1:
                addStudent();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void addStudent() {
        try {
            Student student = readStudentDetailsFromUser();
            boolean successfullyAdded = studentService.add(student);

            System.out.println(successfullyAdded ? "Student successfully added" : "Failed to add student");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Student readStudentDetailsFromUser() {
        // Reading car details from user input
        String firstName = InputReaderUtil.readString("Enter the student first name: ");
        String lastName = InputReaderUtil.readString("Enter the student last name: ");
        String email = InputReaderUtil.readString("Enter the student email: ");

        return new Student(firstName, lastName, email);
    }
}