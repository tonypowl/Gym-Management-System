
package gym.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class adminremove extends JFrame {
    private JComboBox<String> usernameDropdown;
    private JButton removeButton;

    // Database connection parameters
    private final String url = "jdbc:mysql://localhost:3306/gymsys";
    private final String user = "root";
    private final String password = "savio511";

    public adminremove() {
        setTitle("Remove User");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 80, 25);
        add(usernameLabel);

        usernameDropdown = new JComboBox<>();
        usernameDropdown.setBounds(100, 20, 160, 25);
        populateUsernames(); // Populate the dropdown with usernames from the database
        add(usernameDropdown);
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 60, 100, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new GymAdminLogin().setVisible(true); 
            }
        });

        removeButton = new JButton("Remove");
        removeButton.setBounds(150, 60, 100, 25);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeUser();
                dispose();
                new GymAdminLogin().setVisible(true);
            }
        });
        add(removeButton);
        add(backButton);
    }

    private void populateUsernames() {
        ArrayList<String> usernames = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT username FROM gym_users")) {

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                usernames.add(username);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }

        for (String username : usernames) {
            usernameDropdown.addItem(username);
        }
    }

    private void removeUser() {
        String selectedUsername = (String) usernameDropdown.getSelectedItem();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String deleteQuery = "DELETE FROM gym_users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, selectedUsername);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "User removed successfully.");
                usernameDropdown.removeItem(selectedUsername);
            } else {
                JOptionPane.showMessageDialog(this, "User not found or unable to remove.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                adminremove removeUserGUI = new adminremove();
                removeUserGUI.setVisible(true);
            }
        });
    }
}
