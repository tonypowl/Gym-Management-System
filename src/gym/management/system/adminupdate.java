package gym.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class adminupdate extends JFrame {
    private JComboBox<String> userComboBox;
    private JTextField nameField;
    private JTextField ageField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JComboBox<String> countryCodeComboBox;
    private JTextField contactField;

    public adminupdate() {
        setTitle("Admin - Update User Details");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Select User:");
        userLabel.setBounds(20, 20, 100, 25);
        panel.add(userLabel);

        userComboBox = new JComboBox<>();
        userComboBox.setBounds(130, 20, 200, 25);
        populateUserComboBox();
        panel.add(userComboBox);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 100, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(130, 60, 200, 25);
        panel.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(20, 100, 100, 25);
        panel.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(130, 100, 200, 25);
        panel.add(ageField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(20, 140, 100, 25);
        panel.add(genderLabel);

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(130, 140, 80, 25);
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(220, 140, 80, 25);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        panel.add(maleRadioButton);
        panel.add(femaleRadioButton);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(20, 180, 100, 25);
        panel.add(contactLabel);

        countryCodeComboBox = new JComboBox<>();
        countryCodeComboBox.setBounds(130, 180, 80, 25);
        countryCodeComboBox.addItem("+91");
        countryCodeComboBox.addItem("+1");
        countryCodeComboBox.addItem("+44");
        // Add more country codes as needed
        panel.add(countryCodeComboBox);

        contactField = new JTextField();
        contactField.setBounds(220, 180, 110, 25);
        panel.add(contactField);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(100, 220, 100, 30);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                updateUserDetails();
            }
        });
        panel.add(updateButton);
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(200, 220, 100, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new GymAdminLogin().setVisible(true); 
            }
        });
        
        panel.add(backButton);

        getContentPane().add(panel);
        setVisible(true);
    }

    private void populateUserComboBox() {
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

            String sql = "SELECT username FROM gym_users";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                userComboBox.addItem(rs.getString("username"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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

    private void updateUserDetails() {
        String selectedUser = (String) userComboBox.getSelectedItem();
        String newName = nameField.getText();
        String newAge = ageField.getText();
        String newGender = maleRadioButton.isSelected() ? "Male" : "Female";
        String newContact = (String) countryCodeComboBox.getSelectedItem() + contactField.getText();

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            String DB_URL = "jdbc:mysql://localhost:3306/gymsys";
            String USER = "root";
            String PASS = "savio511";

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "UPDATE personal_details SET name=?, age=?, gender=?, contact=? WHERE username=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newName);
            stmt.setString(2, newAge);
            stmt.setString(3, newGender);
            stmt.setString(4, newContact);
            stmt.setString(5, selectedUser);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                dispose();
                JOptionPane.showMessageDialog(adminupdate.this, "User details updated successfully");
                dispose();
                new GymAdminLogin().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(adminupdate.this, "Failed to update user details");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
        SwingUtilities.invokeLater(() -> new adminupdate());
    }
}
         
