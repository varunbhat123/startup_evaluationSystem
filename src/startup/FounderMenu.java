package startup;

import java.util.Scanner;

public class FounderMenu {

    public static void founderDashboard(int userId) {

        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.println("\n===== FOUNDER DASHBOARD =====");
            System.out.println("1. Submit Startup");
            System.out.println("2. View My Startups");
            System.out.println("3. Logout");

            int choice = sc.nextInt();

            switch(choice) {

                case 1:
                    StartupDAO.submitStartup(userId);
                    break;

                case 2:
                    StartupDAO.viewMyStartups(userId);
                    break;

                case 3:
                    return;
            }
        }
    }
}