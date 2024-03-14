package gym.management.system;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public login() {
        setTitle("Gym Management System Login");
        setSize(300, 150);
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

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if("admin".equals(username)&& "admin123".equals(password)){
                    new GymAdminLogin().setVisible(true);
                }
                else if (validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(login.this, "Login successful!");
                    dispose();
                    new Mainpage().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(login.this, "Invalid username or password.");
                }
            }
        });
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(75, 80, 80, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new Homepage().setVisible(true); 
            }
        });
        panel.add(backButton);
        
        panel.add(loginButton);

        getContentPane().add(panel);
        setVisible(true);
    }

    private boolean validateLogin(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            String DB_URL = "jdbc:mysql://localhost:3306/gymsys";
            String USER = "root";
            String PASS = "savio511";

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM gym_users WHERE username='" + username + "' AND password='" + password + "'";
            rs = stmt.executeQuery(sql);

            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new login());
    }
}

