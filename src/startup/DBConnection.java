package startup;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static void main(String[] args) {

        try {

            Connection con =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/startup_evaluation",
                    "root",
                    "varunbhat@2004");

            System.out.println("Database Connected Successfully!");

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}