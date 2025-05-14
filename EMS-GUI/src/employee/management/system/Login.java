package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField tfusername;
    JPasswordField tfpassword;
    JButton login;

    Login() {
        setTitle("Login - WorkforceHub");
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background
        setLayout(null);

        // Title Label
        JLabel heading = new JLabel("WorkforceHub Login");
        heading.setBounds(580, 100, 400, 50);
        heading.setFont(new Font("Serif", Font.BOLD, 32));
        heading.setForeground(new Color(25, 25, 112)); // Midnight blue
        add(heading);

        // Username Label
        JLabel lblusername = new JLabel("Username:");
        lblusername.setBounds(520, 200, 120, 30);
        lblusername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(650, 200, 200, 30);
        tfusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(tfusername);

        // Password Label
        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setBounds(520, 250, 120, 30);
        lblpassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(650, 250, 200, 30);
        tfpassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(tfpassword);

        // Login Button
        login = new JButton("LOGIN");
        login.setBounds(650, 320, 200, 40);
        login.setFont(new Font("Tahoma", Font.BOLD, 16));
        login.setBackground(new Color(0, 102, 204));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.addActionListener(this);
        add(login);

        // Image on the left
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 120, 400, 400);
        add(image);

        // Frame settings
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        String username = tfusername.getText().trim();
        String password = new String(tfpassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM login WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                setVisible(false);
                new Home();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error. Logging in with default user for testing.");

            if (username.equals("admin") && password.equals("admin123")) {
                JOptionPane.showMessageDialog(this, "Default login successful.");
                setVisible(false);
                new Home();
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}