package gym.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class signup extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public signup() {
        setTitle("Gym Management System Sign Up");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 10, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 10, 160, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 40, 160, 25);
        panel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(10, 70, 120, 25);
        panel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(130, 70, 130, 25);
        panel.add(confirmPasswordField);
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(75, 110, 80, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new Homepage().setVisible(true); 
            }
        });
        panel.add(backButton);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(150, 110, 80, 25);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                if (password.equals(confirmPassword)) {
                    if (addUserToDatabase(username, password)) {
                        JOptionPane.showMessageDialog(signup.this, "User added successfully!");
                        dispose();
                    new Mainpage().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(signup.this, "Failed to add user.");
                    }
                } else {
                    JOptionPane.showMessageDialog(signup.this, "Passwords do not match.");
                }
            }
        });
        panel.add(signupButton);

        getContentPane().add(panel);
        setVisible(true);
    }

    private boolean addUserToDatabase(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            String DB_URL = "jdbc:mysql://localhost:3306/gymsys";
            String USER = "root";
            String PASS = "savio511";

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Check if username already exists
            String checkIfExistsQuery = "SELECT * FROM gym_users WHERE username=?";
            PreparedStatement checkStmt = conn.prepareStatement(checkIfExistsQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(signup.this, "Username already exists.");
                return false;
            }

            // Add user to the database
            String addUserQuery = "INSERT INTO gym_users (username, password) VALUES (?, ?)";
            stmt = conn.prepareStatement(addUserQuery);
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new signup());
    }
}

