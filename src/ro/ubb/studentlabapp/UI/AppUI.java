package ro.ubb.studentlabapp.UI;

import ro.ubb.studentlabapp.Domain.LabProblem;
import ro.ubb.studentlabapp.Utils.InputReaderUtil;

import java.util.List;

public class AppUI {
    private StudentUI studentUI;

    private LabProblemUI labProblemUI;


    public AppUI(StudentUI studentUI, LabProblemUI labProblemUI) {
        this.studentUI = studentUI;
        this.labProblemUI = labProblemUI;
    }


    public void start() {
        int choice;

        do {
            choice = displayMainMenu();
            processChoice(choice);

        } while (choice != 0);
    }


    private int displayMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Student Menu");
        System.out.println("2. LabProblem Menu");
//        System.out.println("3. Transactions Menu");
//        System.out.println("4. Search by keyword");
        System.out.println("0. Close app");
        return InputReaderUtil.readInt("Enter your choice: ");
    }


    private void processChoice(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Closing app...");
                break;
            case 1:
                studentUI.start();
                break;
            case 2:
                labProblemUI.start();
                break;
//            case 3:
//                transactionUI.start();
//                break;
//            case 4:
//                displaySearchByKeywordResults();
//                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

//    private void displaySearchByKeywordResults() {
//        String keyword = InputReaderUtil.readString("\nEnter search keyword: ");
//
//        List<ClientCard> searchedClientCards = clientCardUI.searchByKeyword(keyword);
//        List<Car> searchedCars = carUI.searchByKeyword(keyword);
//
//        if(searchedClientCards != null) {
//            System.out.println("Client cards matching the search criteria:");
//            clientCardUI.displayClientCardsList(searchedClientCards);
//        }
//
//        if(searchedCars != null) {
//            System.out.println("Cars matching the search criteria:");
//            carUI.displayCarsList(searchedCars);
//        }
//    }
}
