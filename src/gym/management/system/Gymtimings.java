package gym.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gymtimings extends JFrame {

    public Gymtimings() {
        setTitle("Gym Timings and Holidays");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Gym Timings:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(50, 20, 200, 30);
        panel.add(titleLabel);

        JTextArea timingsArea = new JTextArea("Monday-Friday: 6:00 AM - 10:00 PM\nSaturday-Sunday: 8:00 AM - 8:00 PM");
        timingsArea.setEditable(false);
        timingsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        timingsArea.setBounds(50, 60, 500, 60);
        timingsArea.setBackground(Color.WHITE);
        panel.add(timingsArea);

        JLabel holidaysLabel = new JLabel("Indian Government Holidays:");
        holidaysLabel.setFont(new Font("Arial", Font.BOLD, 20));
        holidaysLabel.setBounds(50, 150, 300, 30);
        panel.add(holidaysLabel);

        JTextArea holidaysArea = new JTextArea("1. Republic Day - January 26\n2. Independence Day - August 15\n3. Gandhi Jayanti - October 2");
        holidaysArea.setEditable(false);
        holidaysArea.setFont(new Font("Arial", Font.PLAIN, 16));
        holidaysArea.setBounds(50, 190, 500, 100);
        holidaysArea.setBackground(Color.WHITE);
        panel.add(holidaysArea);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBounds(10, 300, 80, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Mainpage().setVisible(true);
            }
        });
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Gymtimings::new);
    }
}
