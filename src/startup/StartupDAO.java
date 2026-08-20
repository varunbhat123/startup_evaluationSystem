package startup;

import java.sql.*;
import java.util.Scanner;

public class StartupDAO {

    static final String URL =
            "jdbc:mysql://localhost:3306/startup_evaluation";

    static final String USER = "root";

    static final String PASSWORD = "varunbhat@2004";

    // Submit Startup
    public static void submitStartup(int userId) {

        Scanner sc = new Scanner(System.in);

        sc.nextLine();

        System.out.print("Startup Name: ");
        String startupName = sc.nextLine();

        System.out.print("Domain: ");
        String domain = sc.nextLine();

        System.out.print("Description: ");
        String description = sc.nextLine();
        
        int innovation =
                AIEvaluator.innovationScore(description);

        int market =
                AIEvaluator.marketScore(description);

        int technical =
                AIEvaluator.technicalScore(description);

        int financial =
                AIEvaluator.financialScore(description);

        double total =
                (innovation + market + technical + financial) / 4.0;

        try {

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD);

            String sql =
            "INSERT INTO startups(user_id,startup_name,domain,description,status) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, userId);
            ps.setString(2, startupName);
            ps.setString(3, domain);
            ps.setString(4, description);
            ps.setString(5, "Pending");

            ps.executeUpdate();
            
            ResultSet keys = ps.getGeneratedKeys();

            int startupId = 0;

            if(keys.next()) {

                startupId = keys.getInt(1);
            }
            
            String evalSql =

            		"INSERT INTO evaluations(startup_id,innovation_score,market_score,technical_score,financial_score,total_score,feedback) VALUES(?,?,?,?,?,?,?)";

            		PreparedStatement eps =

            		        con.prepareStatement(evalSql);

            		eps.setInt(1, startupId);

            		eps.setInt(2, innovation);

            		eps.setInt(3, market);

            		eps.setInt(4, technical);

            		eps.setInt(5, financial);

            		eps.setDouble(6, total);

            		if(total >= 80)

            		    eps.setString(7, "High Potential Startup");

            		else if(total >= 65)

            		    eps.setString(7, "Promising Startup");

            		else

            		    eps.setString(7, "Needs Improvement");

            		eps.executeUpdate();

            System.out.println("\nStartup Submitted Successfully!");
            
            System.out.println("\nStartup Submitted Successfully!");

            System.out.println("\n===== AI EVALUATION =====");

            System.out.println("Innovation : " + innovation);

            System.out.println("Market     : " + market);

            System.out.println("Technical  : " + technical);

            System.out.println("Financial  : " + financial);

            System.out.println("Total Score: " + total);

            con.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    // View Startups of Logged-In User
    public static void viewMyStartups(int userId) {

        try {

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD);

            String sql =
            "SELECT * FROM startups WHERE user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== MY STARTUPS =====");

            while(rs.next()) {

                System.out.println(
                        "ID: " + rs.getInt("startup_id")
                        + " | Name: " + rs.getString("startup_name")
                        + " | Domain: " + rs.getString("domain")
                        + " | Status: " + rs.getString("status"));
            }

            con.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    // View All Startups (Admin)
    public static void viewAllStartups() {

        try {

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD);

            String sql = "SELECT * FROM startups";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n===== ALL STARTUPS =====");

            while(rs.next()) {

                System.out.println(
                        "ID: " + rs.getInt("startup_id")
                        + " | User ID: " + rs.getInt("user_id")
                        + " | Name: " + rs.getString("startup_name")
                        + " | Domain: " + rs.getString("domain")
                        + " | Status: " + rs.getString("status"));
            }

            con.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}