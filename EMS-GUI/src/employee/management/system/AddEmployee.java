package employee.management.system;

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;
import java.awt.event.*;

public class AddEmployee extends JFrame implements ActionListener {
    Random ran = new Random();
    int number = ran.nextInt(999999);
    
    JTextField tfname, tffname, tfsalary, tfaddress, tfphone, tfaadhar, tfdesignation, tfemail;
    JDateChooser dcdob;
    JComboBox<String> ceducation;
    JLabel lbelempId;
    JButton add, back;

    AddEmployee() {
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background
        setLayout(null);

        Font labelFont = new Font("SAN_SARIF", Font.BOLD, 22);
        Font fieldFont = new Font("SAN_SARIF", Font.PLAIN, 20);

        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(550, 30, 500, 50);
        heading.setFont(new Font("SAN_SARIF", Font.BOLD, 35));
        heading.setForeground(new Color(0, 102, 204));
        add(heading);

        int xLabel = 300;
        int xField = 600;
        int y = 120;
        int gap = 50;

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(xLabel, y, 200, 30);
        labelname.setFont(labelFont);
        add(labelname);

        tfname = new JTextField();
        tfname.setBounds(xField, y, 300, 30);
        tfname.setFont(fieldFont);
        add(tfname);
        y += gap;

        JLabel labelfname = new JLabel("Surname");
        labelfname.setBounds(xLabel, y, 200, 30);
        labelfname.setFont(labelFont);
        add(labelfname);

        tffname = new JTextField();
        tffname.setBounds(xField, y, 300, 30);
        tffname.setFont(fieldFont);
        add(tffname);
        y += gap;

        JLabel labeldob = new JLabel("Date of Birth");
        labeldob.setBounds(xLabel, y, 200, 30);
        labeldob.setFont(labelFont);
        add(labeldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(xField, y, 300, 30);
        dcdob.setFont(fieldFont);
        add(dcdob);
        y += gap;

        JLabel labelsalary = new JLabel("Salary");
        labelsalary.setBounds(xLabel, y, 200, 30);
        labelsalary.setFont(labelFont);
        add(labelsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(xField, y, 300, 30);
        tfsalary.setFont(fieldFont);
        add(tfsalary);
        y += gap;

        JLabel labeladdress = new JLabel("Address");
        labeladdress.setBounds(xLabel, y, 200, 30);
        labeladdress.setFont(labelFont);
        add(labeladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(xField, y, 300, 30);
        tfaddress.setFont(fieldFont);
        add(tfaddress);
        y += gap;

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(xLabel, y, 200, 30);
        labelphone.setFont(labelFont);
        add(labelphone);

        tfphone = new JTextField();
        tfphone.setBounds(xField, y, 300, 30);
        tfphone.setFont(fieldFont);
        add(tfphone);
        y += gap;

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(xLabel, y, 200, 30);
        labelemail.setFont(labelFont);
        add(labelemail);

        tfemail = new JTextField();
        tfemail.setBounds(xField, y, 300, 30);
        tfemail.setFont(fieldFont);
        add(tfemail);
        y += gap;

        JLabel labeleducation = new JLabel("Highest Education");
        labeleducation.setBounds(xLabel, y, 250, 30);
        labeleducation.setFont(labelFont);
        add(labeleducation);

        String courses[] = { "BBA", "BCA", "BA", "BSC", "B.COM", "BTech", "MBA", "MCA", "MA", "MTech", "MSC", "PHD" };
        ceducation = new JComboBox<>(courses);
        ceducation.setBounds(xField, y, 300, 30);
        ceducation.setFont(fieldFont);
        add(ceducation);
        y += gap;

        JLabel labeldesignation = new JLabel("Designation");
        labeldesignation.setBounds(xLabel, y, 200, 30);
        labeldesignation.setFont(labelFont);
        add(labeldesignation);

        tfdesignation = new JTextField();
        tfdesignation.setBounds(xField, y, 300, 30);
        tfdesignation.setFont(fieldFont);
        add(tfdesignation);
        y += gap;

        JLabel labelaadhar = new JLabel("Aadhar Number");
        labelaadhar.setBounds(xLabel, y, 200, 30);
        labelaadhar.setFont(labelFont);
        add(labelaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(xField, y, 300, 30);
        tfaadhar.setFont(fieldFont);
        add(tfaadhar);
        y += gap;

        JLabel labelempid = new JLabel("Employee ID");
        labelempid.setBounds(xLabel, y, 200, 30);
        labelempid.setFont(labelFont);
        add(labelempid);

        lbelempId = new JLabel("" + number);
        lbelempId.setBounds(xField, y, 300, 30);
        lbelempId.setFont(fieldFont);
        add(lbelempId);
        y += gap + 20;

        add = new JButton("Add Details");
        add.setBounds(xLabel + 50, y, 180, 40);
        add.addActionListener(this);
        add.setFont(new Font("SAN_SARIF", Font.BOLD, 18));
        add.setBackground(new Color(34, 139, 34));
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Back");
        back.setBounds(xField, y, 180, 40);
        back.addActionListener(this);
        back.setFont(new Font("SAN_SARIF", Font.BOLD, 18));
        back.setBackground(new Color(178, 34, 34));
        back.setForeground(Color.WHITE);
        add(back);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocation(0, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = tfname.getText();
            String fname = tffname.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = (String) ceducation.getSelectedItem();
            String designation = tfdesignation.getText();
            String aadhar = tfaadhar.getText();
            String empId = lbelempId.getText();

            try {
                Conn conn = new Conn();
                String query = "insert into employee values('" + name + "','" + fname + "','" + dob + "','" + salary + "','" + address + "','" + phone + "','" + email + "','" + education + "','" + designation + "','" + aadhar + "','" + empId + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
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
        new AddEmployee();
    }
}
