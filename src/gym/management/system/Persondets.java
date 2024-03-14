package gym.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class Persondets extends JFrame {
    private JTextField nameField, ageField, contactField;
    private JComboBox<String> countryCodeDropdown, planComboBox;
    private JRadioButton maleRadioButton, femaleRadioButton;

    // Database connection parameters
    private final String url = "jdbc:mysql://localhost:3306/gymsys";
    private final String user = "root";
    private final String password = "savio511";

    public Persondets() {
        setTitle("Personal Details");
        setSize(375, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);


        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 200, 25);
        add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(20, 50, 80, 25);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(100, 50, 100, 25);
        add(ageField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(20, 80, 80, 25);
        add(genderLabel);

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(100, 80, 80, 25);
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(180, 80, 100, 25);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        add(maleRadioButton);
        add(femaleRadioButton);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(20, 110, 80, 25);
        add(contactLabel);
        
        JLabel Plan = new JLabel("Select Plan:");
        Plan.setBounds(20, 150, 250, 25);
        add(Plan);

        String[] plans = {"1 Month", "3 Month", "6 Month", "12 Months"};
        planComboBox = new JComboBox<>(plans);
        planComboBox.setBounds(100, 138, 150, 50);
        add(planComboBox);

        countryCodeDropdown = new JComboBox<>();
        countryCodeDropdown.setBounds(100, 100, 80, 50);
        populateCountryCodes(); // Populate the dropdown with country codes
        add(countryCodeDropdown);

        contactField = new JTextField();
        contactField.setBounds(180, 110, 130, 25);
        add(contactField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(75, 200, 100, 25);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveDetails();
            }
        });
        add(saveButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(175, 200, 100, 25);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Mainpage().setVisible(true);  
            }
        });
        add(backButton);
    }

    private void populateCountryCodes() {
        // Add your country codes to the dropdown list
        countryCodeDropdown.addItem("+91");
        countryCodeDropdown.addItem("+44");
        countryCodeDropdown.addItem("+1");
    }

    private boolean saveDetails() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = maleRadioButton.isSelected() ? "Male" : "Female";
        String countryCode = (String) countryCodeDropdown.getSelectedItem();
        String contact = countryCode +" "+ contactField.getText();
        String gym_plan = (String) planComboBox.getSelectedItem();
        

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Retrieve user id based on username
            String username; // Replace with actual username
            username = nameField.getText();
            
            String checkIfExistsQuery = "SELECT * FROM personal_details WHERE name=?";
            PreparedStatement checkStmt = connection.prepareStatement(checkIfExistsQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(Persondets.this, "Username already exists.");
                return false;
            }
            
            String getUserIdQuery = "SELECT user_id FROM gym_users WHERE username = ?";
            PreparedStatement getUserIdStatement = connection.prepareStatement(getUserIdQuery);
            getUserIdStatement.setString(1, username);
            ResultSet resultSet = getUserIdStatement.executeQuery();
            int userId = -1;
            if (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }

            if (userId != -1) {
                // Insert personal details into the table
                String insertQuery = "INSERT INTO personal_details (user_id, name, age, gender, contact,gym_plan) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, age);
                preparedStatement.setString(4, gender);
                preparedStatement.setString(5, contact);
                preparedStatement.setString(6, gym_plan);
                
                
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Details saved successfully.");
                dispose();
                new Mainpage().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "User not found.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Persondets frame = new Persondets();
                frame.setVisible(true);
            }
        });
    }
}
