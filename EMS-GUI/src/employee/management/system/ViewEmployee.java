package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice cemployeeId;
    JButton search, print, update, back;
    JLabel searchlbl;

    ViewEmployee() {
        // Set background color
        getContentPane().setBackground(new Color(230, 230, 250)); // Lavender
        setLayout(null);

        // Full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create header controls
        searchlbl = new JLabel("Search by Employee ID");
        searchlbl.setFont(new Font("Arial", Font.BOLD, 18));
        searchlbl.setForeground(new Color(75, 0, 130)); // Deep purple
        add(searchlbl);

        cemployeeId = new Choice();
        cemployeeId.setFont(new Font("Arial", Font.PLAIN, 18));
        add(cemployeeId);

        search = new JButton("Search");
        search.setBackground(new Color(70, 130, 180));
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Arial", Font.BOLD, 16));
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(new Color(70, 130, 180));
        print.setForeground(Color.WHITE);
        print.setFont(new Font("Arial", Font.BOLD, 16));
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBackground(new Color(70, 130, 180));
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Arial", Font.BOLD, 16));
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBackground(new Color(70, 130, 180));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        // Fill employee IDs in dropdown
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while (rs.next()) {
                cemployeeId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Table setup
        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        table.setGridColor(new Color(220, 220, 220));
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        add(jsp);

        // Center components after frame is visible
        setVisible(true);
        centerTopControls();
        jsp.setBounds(20, 100, getWidth() - 40, getHeight() - 140);
    }

    private void centerTopControls() {
        int totalWidth = 1060; // total estimated width of all top components
        int startX = (getWidth() - totalWidth) / 2;
        int y = 20;
        int height = 30;

        searchlbl.setBounds(startX, y, 200, height);
        cemployeeId.setBounds(startX + 210, y, 150, height);
        search.setBounds(startX + 370, y, 100, height);
        print.setBounds(startX + 480, y, 100, height);
        update.setBounds(startX + 590, y, 100, height);
        back.setBounds(startX + 700, y, 100, height);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from employee where empId = '" + cemployeeId.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String args[]) {
        new ViewEmployee();
    }
}
