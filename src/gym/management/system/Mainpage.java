package gym.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mainpage extends JFrame {
    
    
    public Mainpage() {
        
        setTitle("Gym Management System - Homepage");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
                
        JLabel welcomeLabel = new JLabel("Welcome to Christ Gym!!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(250, 50, 300, 30);
        panel.add(welcomeLabel);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(600, 500, 100, 25);
        panel.add(logoutButton);

        
        JButton currentGymPlanButton = new JButton("Current Gym Plan");
        currentGymPlanButton.setBounds(250, 150, 300, 50);
        panel.add(currentGymPlanButton);
        currentGymPlanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GymPlans().setVisible(true);
            }
        });
        
        JButton gymTimingsButton = new JButton("Gym Timings");
        gymTimingsButton.setBounds(250, 220, 300, 50);
        panel.add(gymTimingsButton);
        gymTimingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Gymtimings().setVisible(true);
            }
        });
        
        JButton paymentDetailsButton = new JButton("Payment Details");
        paymentDetailsButton.setBounds(250, 290, 300, 50);
        panel.add(paymentDetailsButton);
        
        JButton personalDetailsButton = new JButton("Personal Details");
        personalDetailsButton.setBounds(250, 360, 300, 50);
        personalDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Persondets().setVisible(true);
            }
        });
        panel.add(personalDetailsButton);
        
        JButton aboutUsButton = new JButton("About Us");
        aboutUsButton.setBounds(250, 430, 300, 50);
        panel.add(aboutUsButton);
        aboutUsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Aboutus().setVisible(true);
            }
        });
        
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmLogout = JOptionPane.showConfirmDialog(Mainpage.this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmLogout == JOptionPane.YES_OPTION) {
                    dispose(); // Close the admin page
                    new Homepage().setVisible(true); // Go back to the login screen
                }
            }
        });
        
        add(panel);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Mainpage().setVisible(true);
        });
    }
}