package gym.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GymAdminLogin extends JFrame {
    public GymAdminLogin() {
        setTitle("Admin Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton addUserButton = new JButton("Add New User");
        addUserButton.setBounds(100, 30, 200, 25);
        panel.add(addUserButton);

        JButton removeUserButton = new JButton("Remove User");
        removeUserButton.setBounds(100, 70, 200, 25);
        panel.add(removeUserButton);

        JButton updateUserButton = new JButton("Update User Details");
        updateUserButton.setBounds(100, 110, 200, 25);
        panel.add(updateUserButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 150, 100, 25);
        panel.add(logoutButton);

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new adminadd().setVisible(true);
            }
        });

        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new adminremove().setVisible(true);
            }
        });

        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new adminupdate().setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmLogout = JOptionPane.showConfirmDialog(GymAdminLogin.this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmLogout == JOptionPane.YES_OPTION) {
                    dispose(); // Close the admin page
                    new Homepage().setVisible(true); // Go back to the login screen
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GymAdminLogin();
            }
        });
    }
}
