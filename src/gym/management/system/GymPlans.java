package gym.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GymPlans extends JFrame {

    public GymPlans() {
        setTitle("Your Gym Plan");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Your Gym Plan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(200, 20, 300, 30);
        panel.add(titleLabel);
        JLabel plan1Label = createPlanLabel("1 Month Plan: ₹1000", 80);
        JLabel plan3Label = createPlanLabel("3 Month Plan: ₹2500", 130);
        JLabel plan6Label = createPlanLabel("6 Month Plan: ₹4500", 180);
        JLabel plan12Label = createPlanLabel("12 Month Plan: ₹8000", 230);

        panel.add(plan1Label);
        panel.add(plan3Label);
        panel.add(plan6Label);
        panel.add(plan12Label);

        JTextArea detailsArea = new JTextArea("* Refund available within 7 days of purchase.\n* Enjoy a trial week before payment.");
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Arial", Font.ITALIC, 14));
        detailsArea.setBounds(100, 300, 400, 80);
        detailsArea.setBackground(Color.LIGHT_GRAY); // Gray background for details
        detailsArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsArea.setAlignmentY(Component.CENTER_ALIGNMENT);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        panel.add(detailsArea);
        
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBounds(10, 10, 80, 30);
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

    private JLabel createPlanLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(150, y, 300, 30);
        label.setHorizontalAlignment(SwingConstants.CENTER); 
        label.setForeground(Color.BLACK); 
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GymPlans::new);
    }
}
