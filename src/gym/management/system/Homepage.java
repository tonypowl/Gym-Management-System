package gym.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Homepage extends JFrame implements ActionListener {
     JButton signUpButton;
     JButton loginButton;

     
    public Homepage() {
        setTitle("Gym Management System");
        setSize(800, 600); // Initial size
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        ImageIcon backgroundImage = new ImageIcon("/Users/antonyshibupaul/Downloads/gym.jpg"); 
        Image scaledImage = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(null); 
        
        
        JLabel welcomeLabel = new JLabel("WELCOME TO CHRIST UNIVERSITY GYM!");
        welcomeLabel.setFont(new Font("Dialog BOLD", Font.BOLD, 35));
        welcomeLabel.setForeground(Color.RED);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(0, 50, getWidth(), 30);
        backgroundLabel.add(welcomeLabel);
        
        
        loginButton = new JButton("Existing Member:");
        loginButton.addActionListener(this);
        loginButton.setBounds(getWidth() / 2 - 100, 150, 200, 50); 
        loginButton.setBackground(Color.BLUE);
        backgroundLabel.add(loginButton);
        
        signUpButton = new JButton("New Member:");
        signUpButton.addActionListener(this);
        signUpButton.setBounds(getWidth() / 2 - 100, 250, 200, 50); 
        signUpButton.setBackground(Color.GREEN);
        backgroundLabel.add(signUpButton);
        
        
        
        setContentPane(backgroundLabel);
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            this.dispose();
            new signup().setVisible(true);
            
        } else if (e.getSource() == loginButton) {
            this.dispose();
            new login().setVisible(true);
        }
    }
    
    public static void main(String[] args) {
            new Homepage().setVisible(true);
    }
}

