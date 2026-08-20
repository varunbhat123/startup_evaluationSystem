package startup;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String URL =
            "jdbc:mysql://localhost:3306/startup_evaluation";
    static final String USER = "root";
    static final String PASSWORD = "varunbhat@2004";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while(true) {

            System.out.println("\n===== Startup Evaluation System =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch(choice) {

                case 1:
                    registerUser();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }

    static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // REGISTER
    static void registerUser() {

        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        try {

            Connection con = getConnection();

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
    static void login() {

        sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        try {

            Connection con = getConnection();

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

                System.out.println("Login Successful!");

                if(role.equals("ADMIN")) {
                    adminDashboard();
                }
                else {
                    founderDashboard(userId);
                }

            } else {

                System.out.println("Invalid Credentials");
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // FOUNDER MENU
    static void founderDashboard(int userId) {

        while(true) {

            System.out.println("\n===== FOUNDER DASHBOARD =====");
            System.out.println("1. Submit Startup");
            System.out.println("2. View My Startups");
            System.out.println("3. Logout");

            int choice = sc.nextInt();

            switch(choice) {

                case 1:
                    submitStartup(userId);
                    break;

                case 2:
                    viewMyStartups(userId);
                    break;

                case 3:
                    return;
            }
        }
    }

    // SUBMIT STARTUP
    static void submitStartup(int userId) {

        sc.nextLine();

        System.out.print("Startup Name: ");
        String startupName = sc.nextLine();

        System.out.print("Domain: ");
        String domain = sc.nextLine();

        System.out.print("Description: ");
        String description = sc.nextLine();

        try {

            Connection con = getConnection();

            String sql =
            "INSERT INTO startups(user_id,startup_name,domain,description,status) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setString(2, startupName);
            ps.setString(3, domain);
            ps.setString(4, description);
            ps.setString(5, "Pending");

            ps.executeUpdate();

            System.out.println("Startup Submitted Successfully!");

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW MY STARTUPS
    static void viewMyStartups(int userId) {

        try {

            Connection con = getConnection();

            String sql =
            "SELECT * FROM startups WHERE user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nMy Startups");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("startup_id")
                        + " | "
                        + rs.getString("startup_name")
                        + " | "
                        + rs.getString("status"));
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // ADMIN MENU
    static void adminDashboard() {

        while(true) {

            System.out.println("\n===== ADMIN DASHBOARD =====");
            System.out.println("1. View All Startups");
            System.out.println("2. Evaluate Startup");
            System.out.println("3. View Rankings");
            System.out.println("4. Logout");

            int choice = sc.nextInt();

            switch(choice) {

                case 1:
                    viewAllStartups();
                    break;

                case 2:
                    evaluateStartup();
                    break;

                case 3:
                    showRankings();
                    break;

                case 4:
                    return;
            }
        }
    }

    static void viewAllStartups() {

        try {

            Connection con = getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
            st.executeQuery("SELECT * FROM startups");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("startup_id")
                        + " | "
                        + rs.getString("startup_name")
                        + " | "
                        + rs.getString("status"));
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    static void evaluateStartup() {

        System.out.print("Startup ID: ");
        int startupId = sc.nextInt();

        System.out.print("Innovation Score: ");
        int innovation = sc.nextInt();

        System.out.print("Market Score: ");
        int market = sc.nextInt();

        System.out.print("Technical Score: ");
        int technical = sc.nextInt();

        System.out.print("Financial Score: ");
        int financial = sc.nextInt();

        sc.nextLine();

        System.out.print("Feedback: ");
        String feedback = sc.nextLine();

        double total =
                innovation +
                market +
                technical +
                financial;

        try {

            Connection con = getConnection();

            String sql =
            "INSERT INTO evaluations(startup_id,innovation_score,market_score,technical_score,financial_score,total_score,feedback) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, startupId);
            ps.setInt(2, innovation);
            ps.setInt(3, market);
            ps.setInt(4, technical);
            ps.setInt(5, financial);
            ps.setDouble(6, total);
            ps.setString(7, feedback);

            ps.executeUpdate();

            PreparedStatement update =
            con.prepareStatement(
            "UPDATE startups SET status='Evaluated' WHERE startup_id=?");

            update.setInt(1, startupId);
            update.executeUpdate();

            System.out.println("Evaluation Saved!");

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    static void showRankings() {

        try {

            Connection con = getConnection();

            String sql =
            "SELECT s.startup_name,e.total_score FROM startups s JOIN evaluations e ON s.startup_id=e.startup_id ORDER BY e.total_score DESC";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            int rank = 1;

            System.out.println("\n===== RANKINGS =====");

            while(rs.next()) {

                System.out.println(
                        rank++
                        + " | "
                        + rs.getString("startup_name")
                        + " | Score: "
                        + rs.getDouble("total_score"));
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}