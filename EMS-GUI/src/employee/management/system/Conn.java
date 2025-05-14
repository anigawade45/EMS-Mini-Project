package employee.management.system;

import java.sql.*;
import javax.swing.*;

public class Conn {
    Connection c;    
    Statement s;

    public Conn() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");  // Correct class name for JDBC driver

            // Establish connection to the MySQL database
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagementsystem", "root", "Aniket05@#@");

            // Create statement to interact with the database
            s = c.createStatement();

        } catch (SQLException e) {
            // Catch and log SQLException for database-related errors
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
        } catch (Exception e) {
            // Catch other exceptions (like ClassNotFoundException)
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        }
    }
}
