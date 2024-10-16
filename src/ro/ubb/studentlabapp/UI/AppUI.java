package ro.ubb.studentlabapp.UI;

import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Service.ILabProblemService;
import ro.ubb.studentlabapp.Service.IStudentService;
import ro.ubb.studentlabapp.Utils.InputReaderUtil;
import ro.ubb.studentlabapp.Utils.TableFormatterUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AppUI {
    private IStudentService studentService;
    private ILabProblemService labProblemService;

    public AppUI (IStudentService studentService, ILabProblemService labProblemService) {
        this.studentService = studentService;
        this.labProblemService = labProblemService;
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
        System.out.println("==================================");
        System.out.println("1. Add a new student");
        System.out.println("2. Update a student");
        System.out.println("3. Delete a student");
        System.out.println("4. View all students");
        System.out.println("==================================");
        System.out.println("5. Add a new laboratory problem");
        System.out.println("6. Update a laboratory problem");
        System.out.println("7. Delete a laboratory problem");
        System.out.println("8. View all laboratory problems");
        System.out.println("==================================");
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
                updateStudent();
                break;
            case 3:
                removeStudent();
                break;
            case 4:
                viewAllStudents();
                break;
            case 5:
                addLabProblem();
                break;
            case 8:
                viewAllLabProblems();
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

    private void addLabProblem() {
        try {
            LabProblem labProblem = readLabProblemDetailsFromUser();
            boolean successfullyAdded = labProblemService.add(labProblem);
            System.out.println(successfullyAdded ? "Laboratory Problem successfully added" : "Failed to add laboratory problem");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStudent() {
        try {
            System.out.println("\nUpdating a student details:");
            viewAllStudents();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter student id you want to update from the list: "));
            Student updatedStudent = readStudentDetailsFromUser();
            boolean successfullyUpdated = studentService.update(id, updatedStudent);
            System.out.println(successfullyUpdated ? "Student successfully updated" : "Failed to update student");

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
            System.out.println(successfullyDeleted ? "Student successfully deleted" : "Failed to delete student");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Student readStudentDetailsFromUser() {
        String firstName = InputReaderUtil.readString("Enter the student first name: ");
        String lastName = InputReaderUtil.readString("Enter the student last name: ");
        String email = InputReaderUtil.readString("Enter the student email: ");

        return new Student(firstName, lastName, email);
    }

    private LabProblem readLabProblemDetailsFromUser() {
        String subject = InputReaderUtil.readString("Enter the lab problem subject: ");
        LocalDate dueDate = InputReaderUtil.readDate("Enter the due date (dd.MM.yyyy): ");

        return new LabProblem(subject, dueDate);
    }

    private void viewAllStudents() {
        try {
            List<Student> students = studentService.getAll();
            displayStudentsList(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewAllLabProblems() {
        try {
            List<LabProblem> labProblems = labProblemService.getAll();
            displayLabProblemsList(labProblems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayStudentsList(List<Student> students) {
        String header = String.format("| %-36s | %-15s | %-15s | %-30s |\n",
                "ID", "First Name", "Second Name", "Email");

        TableFormatterUtil.displayTableFormat(header, students);
    }

    public void displayLabProblemsList(List<LabProblem> labProblems) {
        String header = String.format("| %-36s | %-20s | %-10s |\n",
                "ID", "Subject", "Due Date");

        TableFormatterUtil.displayTableFormat(header, labProblems);
    }
}