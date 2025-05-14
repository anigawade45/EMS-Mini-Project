package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField tfeducation, tffname, tfaddress, tfphone, tfaadhar, tfemail, tfsalary, tfdesignation;
    JLabel lblempId;
    JButton updateBtn, backBtn;
    String empId;
    
    UpdateEmployee(String empId) {
        this.empId = empId;

        getContentPane().setBackground(new Color(230, 230, 250)); // Light lavender background
        setLayout(new BorderLayout()); // Use BorderLayout to center the form panel

        setTitle("Update Employee");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the form panel and set its layout and size
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(800, 750)); // Set preferred form size
        formPanel.setBackground(new Color(230, 230, 250)); // Match background color

        int y = 20; // Starting y-coordinate
        int gap = 50;

        // HEADING
        JLabel heading = new JLabel("Update Employee Details");
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 32));
        heading.setBounds(250, y, 400, 40);
        formPanel.add(heading);

        y += 80;

        JLabel labelname = new JLabel("First Name:");
        labelname.setBounds(100, y, 200, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(labelname);

        JLabel lblname = new JLabel();
        lblname.setBounds(300, y, 300, 30);
        lblname.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(lblname);

        y += gap;

        JLabel labelfname = new JLabel("Surname Name:");
        labelfname.setBounds(100, y, 200, 30);
        labelfname.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(labelfname);

        tffname = new JTextField();
        tffname.setBounds(300, y, 300, 30);
        formPanel.add(tffname);

        y += gap;

        JLabel labeldob = new JLabel("Date of Birth:");
        labeldob.setBounds(100, y, 200, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(labeldob);

        JLabel lbldob = new JLabel();
        lbldob.setBounds(300, y, 300, 30);
        lbldob.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(lbldob);

        y += gap;

        JLabel labelsalary = new JLabel("Salary:");
        labelsalary.setBounds(100, y, 200, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(labelsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(300, y, 300, 30);
        formPanel.add(tfsalary);

        y += gap;

        JLabel labeladdress = new JLabel("Address:");
        labeladdress.setBounds(100, y, 200, 30);
        labeladdress.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(labeladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(300, y, 300, 30);
        formPanel.add(tfaddress);

        y += gap;

        JLabel labelphone = new JLabel("Phone:");
        labelphone.setBounds(100, y, 200, 30);
        labelphone.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(labelphone);

        tfphone = new JTextField();
        tfphone.setBounds(300, y, 300, 30);
        formPanel.add(tfphone);

        y += gap;

        JLabel labelemail = new JLabel("Email:");
        labelemail.setBounds(100, y, 200, 30);
        labelemail.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(labelemail);

        tfemail = new JTextField();
        tfemail.setBounds(300, y, 300, 30);
        formPanel.add(tfemail);

        y += gap;

        JLabel labeleducation = new JLabel("Highest Education:");
        labeleducation.setBounds(100, y, 200, 30);
        labeleducation.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(labeleducation);

        tfeducation = new JTextField();
        tfeducation.setBounds(300, y, 300, 30);
        formPanel.add(tfeducation);

        y += gap;

        JLabel labeldesignation = new JLabel("Designation:");
        labeldesignation.setBounds(100, y, 200, 30);
        labeldesignation.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(labeldesignation);

        tfdesignation = new JTextField();
        tfdesignation.setBounds(300, y, 300, 30);
        formPanel.add(tfdesignation);

        y += gap;

        JLabel labelaadhar = new JLabel("Aadhar Number:");
        labelaadhar.setBounds(100, y, 200, 30);
        labelaadhar.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(labelaadhar);

        JLabel lblaadhar = new JLabel();
        lblaadhar.setBounds(300, y, 300, 30);
        lblaadhar.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(lblaadhar);

        y += gap;

        JLabel labelempIdLabel = new JLabel("Employee ID:");
        labelempIdLabel.setBounds(100, y, 200, 30);
        labelempIdLabel.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(labelempIdLabel);

        lblempId = new JLabel();
        lblempId.setBounds(300, y, 300, 30);
        lblempId.setFont(new Font("serif", Font.PLAIN, 20));
        formPanel.add(lblempId);

        y += gap + 20;

        updateBtn = new JButton("Update");
        updateBtn.setBounds(300, y, 120, 40);
        updateBtn.setBackground(Color.BLACK);
        updateBtn.setForeground(Color.WHITE);
        updateBtn.addActionListener(this);
        formPanel.add(updateBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(450, y, 120, 40);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(this);
        formPanel.add(backBtn);

        // Load employee data
        try {
            Conn c = new Conn();
            String query = "select * from employee where empId = '" + empId + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                lblname.setText(rs.getString("name"));
                tffname.setText(rs.getString("fname"));
                lbldob.setText(rs.getString("dob"));
                tfaddress.setText(rs.getString("address"));
                tfsalary.setText(rs.getString("salary"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                tfeducation.setText(rs.getString("education"));
                lblaadhar.setText(rs.getString("aadhar"));
                lblempId.setText(rs.getString("empId"));
                tfdesignation.setText(rs.getString("designation"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ Add formPanel to wrapperPanel to center it
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBackground(new Color(230, 230, 250));
        wrapperPanel.add(formPanel, new GridBagConstraints());

        // ✅ Add wrapperPanel to JFrame
        add(wrapperPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateBtn) {
            String fname = tffname.getText();
            String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = tfeducation.getText();
            String designation = tfdesignation.getText();

            try {
                Conn conn = new Conn();
                String query = "update employee set fname='" + fname + "', salary='" + salary + "', address='" + address +
                        "', phone='" + phone + "', email='" + email + "', education='" + education +
                        "', designation='" + designation + "' where empId='" + empId + "'";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("");
    }
}
