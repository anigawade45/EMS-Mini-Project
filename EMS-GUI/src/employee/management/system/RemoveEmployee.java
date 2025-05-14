package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice cEmpId;
    JButton delete, back;

    RemoveEmployee() {
        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Set frame size to the screen size
        setSize(screenWidth, screenHeight);
        
        // Set background color and layout
        getContentPane().setBackground(new Color(211, 184,197)); // Soft gray background
        setLayout(null);
        setTitle("Remove Employee");

        // Employee Id Label
        JLabel labelempId = new JLabel("Employee Id");
        labelempId.setBounds(50, 100, 150, 30);
        labelempId.setFont(new Font("Arial", Font.BOLD, 20));
        add(labelempId);

        // Employee Id Choice
        cEmpId = new Choice();
        cEmpId.setBounds(200, 100, 200, 30);
        cEmpId.setFont(new Font("Arial", Font.PLAIN, 16));
        add(cEmpId);

        // Populate Employee ID dropdown from the database
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM employee"; // Fetch all employee IDs
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                cEmpId.add(rs.getString("empId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load employee IDs: " + e.getMessage());
        }

        // Name Label
        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("Arial", Font.BOLD, 20));
        add(labelname);

        JLabel lblname = new JLabel();
        lblname.setBounds(200, 150, 300, 30);
        lblname.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lblname);

        // Phone Label
        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50, 200, 150, 30);
        labelphone.setFont(new Font("Arial", Font.BOLD, 20));
        add(labelphone);

        JLabel lblphone = new JLabel();
        lblphone.setBounds(200, 200, 300, 30);
        lblphone.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lblphone);

        // Email Label
        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 250, 150, 30);
        labelemail.setFont(new Font("Arial", Font.BOLD, 20));
        add(labelemail);

        JLabel lblemail = new JLabel();
        lblemail.setBounds(200, 250, 300, 30);
        lblemail.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lblemail);

        // Updating Labels when Employee ID is selected
        cEmpId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "SELECT * FROM employee WHERE empId = '" + cEmpId.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        lblname.setText(rs.getString("name"));
                        lblphone.setText(rs.getString("phone"));
                        lblemail.setText(rs.getString("email"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to load employee details: " + e.getMessage());
                }
            }
        });

        // Delete Button
        delete = new JButton("Delete");
        delete.setBounds(80, 320, 150, 40);
        delete.setBackground(Color.BLACK); // Vibrant Orange color
        delete.setForeground(Color.WHITE);
        delete.setFont(new Font("Arial", Font.BOLD, 18));
        delete.addActionListener(this);
        add(delete);

        // Back Button
        back = new JButton("Back");
        back.setBounds(250, 320, 150, 40);
        back.setBackground(Color.BLACK); // Bright Blue
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 18));
        back.addActionListener(this);
        add(back);

        // Image for the background
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH); // Increased image size
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450, 50, 700, 500); // Adjusted position and size
        add(image);

        // Set frame to be centered on the screen
        setLocation((screenWidth - getWidth()) / 2, (screenHeight - getHeight()) / 2);
        
        setVisible(true);
    }

    // ActionPerformed for Delete and Back buttons
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                Conn c = new Conn();
                String query = "DELETE FROM employee WHERE empId = '" + cEmpId.getSelectedItem() + "'";
                int rowsAffected = c.s.executeUpdate(query);
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Employee Information Deleted Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Employee ID not found!");
                }
                setVisible(false);
                new Home();  // Redirect to Home screen
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error deleting employee: " + e.getMessage());
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
