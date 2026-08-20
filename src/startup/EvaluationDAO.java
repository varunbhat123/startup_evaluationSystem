package startup;

import java.sql.*;
import java.util.Scanner;

public class EvaluationDAO {


    public static void showRankings() {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/startup_evaluation",
                    "root",
                    "varunbhat@2004");

            String sql =
            		"SELECT s.startup_name,e.total_score,e.feedback " +
            		"FROM startups s " +
            		"JOIN evaluations e " +
            		"ON s.startup_id=e.startup_id " +
            		"ORDER BY e.total_score DESC";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            int rank = 1;

            while(rs.next()) {

            	System.out.println(
            			rank++ + " | " +
            			rs.getString("startup_name") +
            			" | Score: " +
            			rs.getDouble("total_score") +
            			" | " +
            			rs.getString("feedback"));
            }

            con.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}