package startup;

import java.util.Scanner;

public class AdminMenu {

    public static void adminDashboard() {

        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.println("\n===== ADMIN DASHBOARD =====");
            System.out.println("1. View All Startups");
            System.out.println("2. View Rankings");
            System.out.println("3. Logout");

            int choice = sc.nextInt();

            switch(choice) {

                case 1:
                    StartupDAO.viewAllStartups();
                    break;

                case 2:
                    EvaluationDAO.showRankings();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}