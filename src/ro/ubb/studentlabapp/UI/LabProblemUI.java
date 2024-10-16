package ro.ubb.studentlabapp.UI;

import ro.ubb.studentlabapp.Service.ILabProblemService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class LabProblemUI {
    private ILabProblemService labProblemService;

    public LabProblemUI(ILabProblemService labProblemService) {
        this.labProblemService = labProblemService;
    }

    public void start() {
        int choice;

        do {
            choice = displayLabProblemMenu();
            processLabProblemMenuChoice(choice);
        } while (choice != 0);
    }


    private int displayLabProblemMenu() {
        System.out.println("\nLabProblem Menu:");
        System.out.println("1. Add a new labProblem");
        System.out.println("2. Update a labProblem");
        System.out.println("3. Delete a labProblem");
        System.out.println("4. View all labProblems");
        System.out.println("0. Back to main menu");
        return InputReaderUtil.readInt("Enter your choice: ");
    }


    private void processLabProblemMenuChoice(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Returning to main menu...");
                break;
            case 1:
                addLabProblem();
                break;
            case 2:
                updateLabProblem();
                break;
            case 3:
                deleteLabProblem();
                break;
            case 4:
                viewAllLabProblems();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }


    private void addLabProblem() {
        try {
            LabProblem labProblem = readLabProblemDetailsFromUser();

            boolean successfullyAdded = labProblemService.add(labProblem);

            if (successfullyAdded) {
                System.out.println("LabProblem successfully added to the database!");
            } else {
                System.out.println("Failed to add labProblem to the database.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot add labProblem due to the following errors:\n" + e.getMessage());
//            e.printStackTrace();
        }
    }


    private LabProblem readLabProblemDetailsFromUser() {
        System.out.println("\nAdding new labProblem:");

        String subject = InputReaderUtil.readString("Enter the labProblem subject: ");
        LocalDate dueDate = InputReaderUtil.readDate("Enter due date (dd.mm.yyyy): ");
        int maxScore = InputReaderUtil.readInt("Enter the maximum score: ");
        return new LabProblem(UUID.randomUUID(), subject, dueDate, maxScore);
    }

    /**
     * Updates the details of an existing car.
     */
    private void updateLabProblem() {
        try {
            System.out.println("\nUpdating a labProblem:");


            if (labProblemService.getAll().isEmpty()) {
                System.out.println("Cannot update labProblem due to the following errors:\n" + ErrorMessageStrings.LABPROBLEM_EMPTY_LIST);
                return;
            }

            viewAllLabProblems();

            UUID id = UUID.fromString(InputReaderUtil.readString("Enter the ID of the labProblem you want to update: "));
            LabProblem updatedLabProblemDetails = readUpdatedLabProblemDetailsFromUser();
            boolean successfullyUpdated = labProblemService.update(id, updatedLabProblemDetails);

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


    private LabProblem readUpdatedLabProblemDetailsFromUser() {
        String subject = InputReaderUtil.readString("Enter the updated subject: ");
        int maxScore = InputReaderUtil.readInt("Enter the maximum score: ");
        LocalDate dueDate = InputReaderUtil.readDate("Enter due date (dd.mm.yyyy): ");
        return new LabProblem(UUID.randomUUID(), subject, dueDate, maxScore);
    }


    private void deleteLabProblem() {
        try {
            System.out.println("\nDeleting a labProblem:");

            viewAllLabProblems();
            UUID id = UUID.fromString(InputReaderUtil.readString("Enter the ID of the labProblem you want to delete from the list: "));
            boolean successfullyDeleted = labProblemService.delete(id);

            if (successfullyDeleted) {
                System.out.println("LabProblem successfully deleted fom the database!");
            } else {
                System.out.println("Failed to delete labProblem from the database.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot delete labProblem due to the following errors:\n" + e.getMessage());
        }
    }


    private void viewAllLabProblems() {
        try {
            List<LabProblem> labProblems = labProblemService.getAll();
            displayLabProblemList(labProblems);
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayLabProblemList(List<LabProblem> labProblems) {
        String header = String.format("| %-36s | %-20s | %-30s | %-10s", "ID", "Subject", "DueDate", "MaxScore");
        String separator = new String(new char[header.length()]).replace('\0', '-'); // Linie de separare

        // Print the header
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);

        // Iterate through the students list and format each student
        labProblems.forEach(labProblem -> {
            String row = String.format("| %-36s | %-20s | %-30s | %-10s",
                    labProblem.getProblemId(),
                    labProblem.getSubject(),
                    labProblem.getDueDate(),
                    labProblem.getMaxScore());
            System.out.println(row);
        });

        // Print the bottom separator
        System.out.println(separator);



//        String header = String.format("| %-36s | %-20s | %-10s\n",
//                "ID", "Student  Name", "Email");

//        TableFormatterUtil.displayTableFormat(header, students);
    }

}
