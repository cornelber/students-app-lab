package ro.ubb.studentlabapp.UI;

import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Service.IStudentService;
import ro.ubb.studentlabapp.Utils.InputReaderUtil;
import ro.ubb.studentlabapp.Utils.TableFormatterUtil;

import java.util.List;
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
                removeStudent();
                break;
            case 4:
                viewAllStudents();
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

    private void removeStudent() {
        try {
            System.out.println("\nDeleting a student:");
            viewAllStudents();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter student id you want to delete from the list: "));
            boolean successfullyDeleted = studentService.delete(id);

            if (successfullyDeleted) {
                System.out.println("Student successfully deleted fom the list!");
            } else {
                System.out.println("Failed to delete student from the list.");
            }
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

    private void viewAllStudents() {
        try {
            List<Student> students = studentService.getAll();
            displayStudentsList(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayStudentsList(List<Student> students) {
        String header = String.format("| %-36s | %-15s | %-15s | %-30s |\n",
                "ID", "First Name", "Second Name", "Email");

        TableFormatterUtil.displayTableFormat(header, students);
    }
}