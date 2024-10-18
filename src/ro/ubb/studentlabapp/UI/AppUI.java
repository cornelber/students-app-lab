package ro.ubb.studentlabapp.UI;

import ro.ubb.studentlabapp.Domain.Assignment;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Service.IAssignmentService;
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

    private IAssignmentService assignmentService;

    public AppUI (IStudentService studentService, ILabProblemService labProblemService, IAssignmentService assignmentService) {
        this.studentService = studentService;
        this.labProblemService = labProblemService;
        this.assignmentService = assignmentService;
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
        System.out.println("9. Assign problem to student");
        System.out.println("10. Update an assignment");
        System.out.println("11. Delete an assignment");
        System.out.println("12. View all assignments");
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
            case 6:
                updateLabProblem();
                break;
            case 7:
                removeLabProblem();
                break;
            case 8:
                viewAllLabProblems();
                break;
            case 9:
                addAssignment();
                break;
            case 10:
                updateAssignment();
                break;
            case 11:
                removeAssignment();
                break;
            case 12:
                viewAllAssignments();
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

    private void addAssignment(){
        try {
            System.out.println("Assign problem to student:\n");

            viewAllStudents();
            UUID studentId = UUID.fromString(InputReaderUtil.readString("student id: "));
            Student student = assignmentService.findStudentById(studentId);

            viewAllLabProblems();
            UUID labProblemId = UUID.fromString(InputReaderUtil.readString("labProblem id: "));
            LabProblem labProblem = assignmentService.findLabProblemById(labProblemId);

            int grade = InputReaderUtil.readInt("Enter the grade: ");

            Assignment assignmentToAdd = new Assignment(student, labProblem, grade);


            boolean successfullyAdded = assignmentService.add(assignmentToAdd);
            System.out.println(successfullyAdded ? " successfully added" : "Failed to add laboratory problem");

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

    private void updateLabProblem() {
        try {
            System.out.println("\nUpdating a lab. problem details:");
            viewAllLabProblems();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter lab. problem id you want to update from the list: "));
            LabProblem updatedLabProblem = readLabProblemDetailsFromUser();
            boolean successfullyUpdated = labProblemService.update(id, updatedLabProblem);
            System.out.println(successfullyUpdated ? "Student successfully updated" : "Failed to update student");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAssignment() {
        try {
            System.out.println("\nUpdating an assignment details:");
            viewAllAssignments();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter assignment id you want to update from the list: "));

            int grade = InputReaderUtil.readInt("Enter the grade: ");

            Assignment updatedAssignment = new Assignment(grade);

            boolean successfullyUpdated = assignmentService.update(id, updatedAssignment);
            System.out.println(successfullyUpdated ? "Assignment successfully updated" : "Failed to update Assignment");
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

    private void removeLabProblem() {
        try {
            System.out.println("\nDeleting a lab problem:");
            viewAllLabProblems();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter lab problem id you want to delete from the list: "));
            boolean successfullyDeleted = labProblemService.delete(id);
            System.out.println(successfullyDeleted ? "Lab. problem successfully deleted" : "Failed to delete lab. problem");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeAssignment() {
        try {
            System.out.println("\nDeleting an assignment:");
            viewAllAssignments();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter assignment id you want to delete from the list: "));
            boolean successfullyDeleted = assignmentService.delete(id);
            System.out.println(successfullyDeleted ? "Assignment successfully deleted" : "Failed to delete assignment");

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
        int maxScore = InputReaderUtil.readInt("Enter the max score: ");

        return new LabProblem(subject, dueDate, maxScore);
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

    private void viewAllAssignments() {
        try {
            List<Assignment> assignments = assignmentService.getAll();
            displayAssignmentList(assignments);
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
        String header = String.format("| %-36s | %-20s | %-10s | %-15s |\n",
                "ID", "Subject", "Due Date", "Max Score");

        TableFormatterUtil.displayTableFormat(header, labProblems);
    }

    public void displayAssignmentList(List<Assignment> assignments) {
        String header = String.format("| %-36s | %-36s | %-20s | %-10s |\n",
                "ID", "Student", "Lab Problem", "Grade");

        TableFormatterUtil.displayTableFormat(header, assignments);
    }
}