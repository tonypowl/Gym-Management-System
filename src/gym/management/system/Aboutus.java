package gym.management.system;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aboutus extends JFrame {
    public Aboutus() {
        setTitle("About Us");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel aboutLabel = new JLabel("<html><center><h1>About Our Gym Management System</h1><p><p>This gym management system is designed to help gym owners manage their facilities efficiently. It provides features such as member management, class scheduling, billing, and more.</p><p>Developed by: Antony Paul, Rejin K, Suraj Sanjay<</p><p>Version: 1.0</p></center></html>");
        aboutLabel.setBounds(20, 20, 360, 200);
        panel.add(aboutLabel);

        getContentPane().add(panel);
        setVisible(true);
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 230, 100, 40);
        panel.add(backButton);
      
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Mainpage().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Aboutus());
    }
}