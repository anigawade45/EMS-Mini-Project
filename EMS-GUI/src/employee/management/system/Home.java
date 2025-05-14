package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    JButton add, view, update, remove;

    public Home() {
        setLayout(null);

        // Get screen dimensions
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, screenWidth, screenHeight);
        image.setLayout(null);
        add(image);

        // Heading label
        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(screenWidth - 700, 50, 600, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 36));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        // Buttons with styling and hover
        add = new JButton("Add Employee");
        add.setBounds(screenWidth - 700, 150, 200, 50);
        styleButton(add);
        image.add(add);

        view = new JButton("View Employees");
        view.setBounds(screenWidth - 470, 150, 200, 50);
        styleButton(view);
        image.add(view);

        update = new JButton("Update Employee");
        update.setBounds(screenWidth - 700, 230, 200, 50);
        styleButton(update);
        image.add(update);

        remove = new JButton("Remove Employee");
        remove.setBounds(screenWidth - 470, 230, 200, 50);
        styleButton(remove);
        image.add(remove);

        // Frame settings
        setTitle("Home - Employee Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        setUndecorated(false); // Keep window title bar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        System.out.println("Home window opened");
    }

    private void styleButton(JButton button) {
        Color normalColor = new Color(0, 102, 204);
        Color hoverColor = new Color(30, 144, 255);

        button.setBackground(normalColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);

        // Hover effect using MouseAdapter
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == view || ae.getSource() == update) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == remove) {
            setVisible(false);
            new RemoveEmployee();
        }
    }

    public static void main(String args[]) {
        new Home();
    }
}
