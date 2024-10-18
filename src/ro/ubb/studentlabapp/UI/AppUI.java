package ro.ubb.studentlabapp.UI;

import ro.ubb.studentlabapp.Domain.Assignment;
import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Service.IAssignmentService;
import ro.ubb.studentlabapp.Service.ILabProblemService;
import ro.ubb.studentlabapp.Service.IStudentService;
import ro.ubb.studentlabapp.Utils.DateFormatterUtil;
import ro.ubb.studentlabapp.Utils.InputReaderUtil;
import ro.ubb.studentlabapp.Utils.TableFormatterUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
        addMockDataInMemory();
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
        System.out.println("13. Filter unassigned student.");
        System.out.println("14. Report most assigned lab problem.");
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
            case 13:
                displayStudentsWithoutAssignments();
                break;
            case 14:
                displayMostAssignedLabProblems();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void addMockDataInMemory() {
        // Adding mock students
        Student student1 = new Student("John", "Doe", "john.doe@gmail.com");
        Student student2 = new Student("Ion", "Mihai", "mihai.ion@gmail.com");
        Student student3 = new Student("Ioana", "Ana", "ana@gmail.com");
        Student student4 = new Student("Madalina", "Creanga", "mcreanda@gmail.com");

        studentService.add(student1);
        studentService.add(student2);
        studentService.add(student3);
        studentService.add(student4);

        // Adding mock lab problems
        LabProblem labProblem1 = new LabProblem("Matematica", DateFormatterUtil.parseDate("18.12.2024"), 100);
        LabProblem labProblem2 = new LabProblem("Chimie", DateFormatterUtil.parseDate("10.12.2024"), 100);

        labProblemService.add(labProblem1);
        labProblemService.add(labProblem2);

        // Adding mock assignments
        Assignment assignment1 = new Assignment(student1, labProblem1, 90);
        Assignment assignment2 = new Assignment(student2, labProblem2, 85);
        Assignment assignment3 = new Assignment(student3, labProblem1, 75);

        assignmentService.add(assignment1);
        assignmentService.add(assignment2);
        assignmentService.add(assignment3);
    }

    private void displayStudentsWithoutAssignments(){
        try {
            System.out.println("\nFetching list of students without assignments...");
            List<Student> unassignedStudents = assignmentService.filterStudentsWithoutAssignments();

            Optional.of(unassignedStudents)
                    .filter(list -> !list.isEmpty())
                    .ifPresentOrElse(
                            list -> {
                                System.out.println("\nList of students without assignments:");
                                displayStudentsList(list);
                            },
                            () -> System.out.println("\nAll students have been assigned lab problems.") // fallback method
                    );
        } catch (RuntimeException e) {
            System.out.println("An error occurred while fetching the list of unassigned students. Please try again: " + e.getMessage());
            //e.printStackTrace();
        }

    }

    private void displayMostAssignedLabProblems() {
        try {
            System.out.println("\nFetching the most assigned lab problem...");

            Optional.ofNullable(assignmentService.getMostAssignedLabProblems())
                    .filter(map -> !map.isEmpty())
                    .ifPresentOrElse(
                            mostAssignedLabProblems -> {
                                System.out.println("The most assigned lab problems are:");
                                mostAssignedLabProblems.forEach((labProblem, count) ->
                                        System.out.println(labProblem.getSubject() + " [" + count + " assignments]")
                                );
                            },
                            () -> System.out.println("No lab problems have been assigned yet.") // fallback method
                    );
        } catch (RuntimeException e) {
            System.out.println("An error occurred while fetching the most assigned lab problems. Please try again: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    private void addStudent() {
        try {
            Student studentToAdd = readStudentDetailsFromUser();
            boolean successfullyAdded = studentService.add(studentToAdd);
            System.out.println(successfullyAdded ? "Student successfully added" : "Failed to add student");

        } catch (RuntimeException e) {
            System.out.println("An error occurred while adding the student. Please check the details and try again: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    private void addLabProblem() {
        try {
            LabProblem labProblemToAdd = readLabProblemDetailsFromUser();
            boolean successfullyAdded = labProblemService.add(labProblemToAdd);
            System.out.println(successfullyAdded ? "Laboratory problem successfully added" : "Failed to add laboratory problem");

        } catch (RuntimeException e) {
            System.out.println("An error occurred while adding the laboratory problem. Please check the details and try again: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    private void addAssignment(){
        try {
            Assignment assignmentToAdd = readAssignmentDetailsFromUser();
            boolean successfullyAdded = assignmentService.add(assignmentToAdd);
            System.out.println(successfullyAdded ? "Assignment successfully added" : "Failed to add assignment");

        } catch (RuntimeException e) {
            System.out.println("An error occurred while adding the assignment. Please check the details and try again: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    private void updateStudent() {
        try {
            System.out.println("\nList of available students:");
            viewAllStudents();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter the ID of the student you want to update:\nStudent ID: "));

            Student studentToUpdate = readStudentDetailsFromUser();
            boolean successfullyUpdated = studentService.update(id, studentToUpdate);
            System.out.println(successfullyUpdated ? "Student successfully updated" : "Failed to update student");

        } catch (RuntimeException e) {
            System.out.println("An error occurred while updating the student. Please check the details and try again: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    private void updateLabProblem() {
        try {
            System.out.println("\nList of available lab problems:");
            viewAllLabProblems();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter the ID of the lab problem you want to update:\nLab Problem ID: "));

            LabProblem labProblemToUpdate = readLabProblemDetailsFromUser();
            boolean successfullyUpdated = labProblemService.update(id, labProblemToUpdate);
            System.out.println(successfullyUpdated ? "Lab problem was successfully updated." : "Failed to update the lab problem.");

        } catch (RuntimeException e) {
            System.out.println("An error occurred while updating the lab problem. Please check the details and try again: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    private void updateAssignment() {
        try {
            System.out.println("\nList of available assignments:");
            viewAllAssignments();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter the ID of the assignment you want to update:\nAssignment ID: "));
            int grade = InputReaderUtil.readInt("Enter the new grade for the assignment: ");

            Assignment assignmentToUpdate = new Assignment(grade);
            boolean successfullyUpdated = assignmentService.update(id, assignmentToUpdate);
            System.out.println(successfullyUpdated ? "Assignment was successfully updated." : "Failed to update the assignment.");

        } catch (RuntimeException e) {
            System.out.println("An error occurred while updating the assignment. Please check the details and try again: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    private void removeStudent() {
        try {
            System.out.println("\nList of available students:");
            viewAllStudents();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter the ID of the student you want to delete:\nStudent ID: "));
            boolean successfullyDeleted = studentService.delete(id);
            System.out.println(successfullyDeleted ? "Student was successfully deleted." : "Failed to delete the student.");

        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the student. Please check the details and try again: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    private void removeLabProblem() {
        try {
            System.out.println("\nList of available lab problems:");
            viewAllLabProblems();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter the ID of the lab problem you want to delete:\nLab Problem ID: "));
            boolean successfullyDeleted = labProblemService.delete(id);
            System.out.println(successfullyDeleted ? "Lab problem was successfully deleted." : "Failed to delete the lab problem.");

        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the lab problem. Please check the details and try again: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    private void removeAssignment() {
        try {
            System.out.println("List of available assignments:");
            viewAllAssignments();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter the ID of the assignment you want to delete:\nAssignment ID: "));
            boolean successfullyDeleted = assignmentService.delete(id);
            System.out.println(successfullyDeleted ? "Assignment was successfully deleted." : "Failed to delete the assignment.");

        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the assignment. Please check the details and try again: " + e.getMessage());
            // e.printStackTrace();
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

    private Assignment readAssignmentDetailsFromUser() {
        System.out.println("List of available students:");
        viewAllStudents();
        UUID studentId = UUID.fromString(InputReaderUtil.readString("Enter the ID of the student to whom you want to assign the lab problem\\nStudent ID: "));
        Student student = assignmentService.findStudentById(studentId);

        System.out.println("List of available lab problems:");
        viewAllLabProblems();
        UUID labProblemId = UUID.fromString(InputReaderUtil.readString("Enter the ID of the lab problem to assign\\nLab Problem ID: "));
        LabProblem labProblem = assignmentService.findLabProblemById(labProblemId);

        int grade = InputReaderUtil.readInt("Enter the grade for the assignment: ");

        return new Assignment(student, labProblem, grade);
    }

    /**
     * A generic method that retrieves and displays a list of entities using provided service and display methods.
     *
     * @param <T>           the type of entities being processed
     * @param serviceMethod  a {@code Supplier} that retrieves the list of entities from the appropriate service
     * @param displayMethod  a {@code Consumer} that processes and displays the list of entities
     * @param entityName     the name of the entity type, used for logging and error messages
     *
     * <p>This method performs the following steps:
     * <ol>
     *     <li>Calls the {@code serviceMethod} to retrieve the list of entities</li>
     *     <li>Calls the {@code displayMethod} to display or process the list</li>
     *     <li>Handles exceptions and logs any errors encountered during retrieval or display</li>
     * </ol>
     *
     * In case of an exception, the method logs an error message with the specified {@code entityName}.
     *
     * @throws Exception if any exception occurs during the retrieval or display of entities
     */
    private <T> void viewAllEntities(Supplier<List<T>> serviceMethod, Consumer<List<T>> displayMethod, String entityName) {
        // Supplier<List<T>> serviceMethod - represents a method that takes no arguments and returns a list of T
        // Consumer<List<T>> displayMethod - represents a method that processes a list of T but doesn't return anything
        try {
            List<T> entities = serviceMethod.get();
            displayMethod.accept(entities);
        } catch (RuntimeException e) {
            System.out.println("An error occurred while fetching the list of " + entityName + ". Please try again: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    private void viewAllStudents() {
        viewAllEntities(studentService::getAll, this::displayStudentsList, "students");
    }

    private void viewAllLabProblems() {
        viewAllEntities(labProblemService::getAll, this::displayLabProblemsList, "lab problems");
    }

    private void viewAllAssignments() {
        viewAllEntities(assignmentService::getAll, this::displayAssignmentList, "assignments");
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