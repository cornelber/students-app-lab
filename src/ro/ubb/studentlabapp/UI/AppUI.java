package ro.ubb.studentlabapp.UI;

import ro.ubb.studentlabapp.Utils.InputReaderUtil;

public class AppUI {

    public AppUI () {}


    public void run() {
        int choice;

        do {
            choice = displayMainMenu();
            processChoice(choice);
        } while ( choice != 0);
    }

    private int displayMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Add Student");
        System.out.println("2. ");
        System.out.println("3. ");
        System.out.println("4. ");
        System.out.println("0. Close app");
        return InputReaderUtil.readInt("Enter your choice: ");
    }

    private void processChoice(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Closing app...");
                break;
            case 1:
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
}
