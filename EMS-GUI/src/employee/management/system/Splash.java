package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame implements ActionListener {

    Splash() {
        setUndecorated(true); // Full screen without window borders
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize to full screen
        setLayout(null);

        // Get screen dimensionszz
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        // Background Image
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("icons/main.jpg"));
        Image bgImg = bgIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledBg = new ImageIcon(bgImg);
        JLabel background = new JLabel(scaledBg);
        background.setBounds(0, 0, width, height);
        add(background);

        // Heading Label at the Top Center
        JLabel heading = new JLabel("Welcome to WorkforceHub");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 48));
        heading.setForeground(Color.WHITE);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBounds((width - 800) / 2, 40, 800, 60); // Adjust width and top position
        background.add(heading);

        // Button
        JButton clickHere = new JButton("Click Here to Continue");
        clickHere.setBounds((width - 300) / 2, height - 180, 300, 60); // Center bottom
        clickHere.setFont(new Font("Segoe UI", Font.BOLD, 20));
        clickHere.setBackground(new Color(33, 150, 243));
        clickHere.setForeground(Color.WHITE);
        clickHere.setFocusPainted(false);
        clickHere.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clickHere.setEnabled(false); // Disabled initially
        background.add(clickHere);

        setVisible(true);

        // Progress Bar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(false);
        progressBar.setStringPainted(true); // Shows percentage
        progressBar.setValue(0);
        progressBar.setBounds((width - 300) / 2, height - 250, 300, 20); // Above the button
        progressBar.setForeground(new Color(33, 150, 243));
        progressBar.setBackground(Color.WHITE);
        progressBar.setBorderPainted(false);
        background.add(progressBar);

        // Simulate loading with filling progress
        Timer timer = new Timer(30, null);
        timer.addActionListener(new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                progressBar.setValue(progress);
                progress += 1;
                if (progress > 100) {
                    timer.stop();
                    progressBar.setVisible(false);
                    clickHere.setEnabled(true);
                    clickHere.addActionListener(Splash.this); // Enable button
                }
            }
        });
        timer.start();

        // Blinking heading
        new Thread(() -> {
            try {
                while (true) {
                    SwingUtilities.invokeLater(() -> heading.setVisible(false));
                    Thread.sleep(600);
                    SwingUtilities.invokeLater(() -> heading.setVisible(true));
                    Thread.sleep(600);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        new Splash();
    }
}
