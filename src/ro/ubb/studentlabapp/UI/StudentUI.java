package ro.ubb.studentlabapp.UI;

import ro.ubb.studentlabapp.Constants.ErrorMessageStrings;
import ro.ubb.studentlabapp.Domain.Student;
import ro.ubb.studentlabapp.Service.IStudentService;
import ro.ubb.studentlabapp.Utils.InputReaderUtil;
import ro.ubb.studentlabapp.Utils.TableFormatterUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class StudentUI {
    private IStudentService studentService;

    public StudentUI(IStudentService studentService) {
        this.studentService = studentService;
    }


    public void start() {
        int choice;

        do {
            choice = displayStudentMenu();
            processStudentMenuChoice(choice);
        } while (choice != 0);
    }


    private int displayStudentMenu() {
        System.out.println("\nStudent Menu:");
        System.out.println("1. Add a new student");
        System.out.println("2. Update a student");
        System.out.println("3. Delete a student");
        System.out.println("4. View all students");
        System.out.println("0. Back to main menu");
        return InputReaderUtil.readInt("Enter your choice: ");
    }


    private void processStudentMenuChoice(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Returning to main menu...");
                break;
            case 1:
                addStudent();
                break;
            case 2:
                updateStudent();
                break;
            case 3:
                deleteStudent();
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

            if (successfullyAdded) {
                System.out.println("Student successfully added to the database!");
            } else {
                System.out.println("Failed to add student to the database.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot add student due to the following errors:\n" + e.getMessage());
//            e.printStackTrace();
        }
    }


    private Student readStudentDetailsFromUser() {
        System.out.println("\nAding new student:");

        // Reading car details from user input
        String name = InputReaderUtil.readString("Enter the student name: ");
        String email = InputReaderUtil.readString("Enter the student email: ");

        return new Student(UUID.randomUUID(), name, email);
    }

    /**
     * Updates the details of an existing car.
     */
    private void updateStudent() {
        try {
            System.out.println("\nUpdating a student:");


            if (studentService.getAll().isEmpty()) {
                System.out.println("Cannot update student due to the following errors:\n" + ErrorMessageStrings.STUDENT_EMPTY_LIST);
                return;
            }

            viewAllStudents();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter the ID of the student you want to update: "));
            Student updatedStudentDetails = readUpdatedStudentDetailsFromUser();
            boolean successfullyUpdated = studentService.update(id, updatedStudentDetails);

            if (successfullyUpdated) {
                System.out.println("Student successfully updated!");
            } else {
                System.out.println("Failed to update student.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot update student due to the following errors:\n" + e.getMessage());
        }
    }


    private Student readUpdatedStudentDetailsFromUser() {
        String name = InputReaderUtil.readString("Enter the updated student name: ");
        String email = InputReaderUtil.readString("Enter the updated student email: ");

        return new Student(UUID.randomUUID(), name, email);
    }


    private void deleteStudent() {
        try {
            System.out.println("\nDeleting a student:");
            viewAllStudents();
            UUID id = UUID.fromString(InputReaderUtil.readString("Enter the ID of the student you want to delete from the list: "));
            boolean successfullyDeleted = studentService.delete(id);

            if (successfullyDeleted) {
                System.out.println("Student successfully deleted fom the database!");
            } else {
                System.out.println("Failed to delete student from the database.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot delete student due to the following errors:\n" + e.getMessage());
        }
    }


    private void viewAllStudents() {
        try {
            List<Student> students = studentService.getAll();
            displayStudentsList(students);
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayStudentsList(List<Student> students) {
        String header = String.format("| %-36s | %-20s | %-30s |", "ID", "Student Name", "Email");
        String separator = new String(new char[header.length()]).replace('\0', '-'); // Linie de separare

        // Print the header
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);

        // Iterate through the students list and format each student
        students.forEach(student -> {
                    String row = String.format("| %-36s | %-20s | %-30s |",
                            student.getId(),
                            student.getName(),
                            student.getEmail());
                    System.out.println(row);
        });

        // Print the bottom separator
        System.out.println(separator);



//        String header = String.format("| %-36s | %-20s | %-10s\n",
//                "ID", "Student  Name", "Email");

//        TableFormatterUtil.displayTableFormat(header, students);
    }

}
