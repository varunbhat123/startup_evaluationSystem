package startup;

import java.sql.*;
import java.util.Scanner;

public class UserDAO {

    static final String URL =
            "jdbc:mysql://localhost:3306/startup_evaluation";

    static final String USER = "root";

    static final String PASSWORD = "varunbhat@2004";

    static Scanner sc = new Scanner(System.in);

    // REGISTER USER
    public static void registerUser() {

        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        try {

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD);

            String sql =
                    "INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, "FOUNDER");

            ps.executeUpdate();

            System.out.println("Registration Successful!");

            con.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    // LOGIN
    public static void login() {

        sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        try {

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD);

            String sql =
                    "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                int userId = rs.getInt("user_id");

                String role = rs.getString("role");

                System.out.println("\nLogin Successful!");

                if(role.equalsIgnoreCase("ADMIN")) {

                    adminDashboard();

                } else {

                    founderDashboard(userId);
                }

            } else {

                System.out.println("Invalid Credentials!");
            }

            con.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    // FOUNDER MENU
    public static void founderDashboard(int userId) {

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

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // ADMIN MENU
    public static void adminDashboard() {

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