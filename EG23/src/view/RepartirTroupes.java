package view;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RepartirTroupes extends JFrame {
    private static final boolean SCALABLE = true;
    private static final String[] LIEUX = {"BDE", "Centre administratif", "Halle sportive", "-", "-"};

    public RepartirTroupes() {
        setTitle("Répartissez vos troupes");
        getContentPane().setBackground(new Color(217, 179, 124));
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Répartissez vos troupes", SwingConstants.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 24));
        title.setForeground(new Color(73, 95, 109));
        add(title, BorderLayout.NORTH);

        // Image
        ImageIcon imageIcon = new ImageIcon("src/pngs/plan_utt.png");
        JLabel imageLabel = new JLabel(imageIcon);
        if (SCALABLE) {
            imageLabel.setPreferredSize(new Dimension(500, 500));
        }
        add(imageLabel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setPreferredSize(new Dimension(200, 500));
        add(scrollPane, BorderLayout.WEST);

        // Buttons
        for (int i = 1; i <= 20; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(100, 100));
            button.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            button.setForeground(new Color(73, 95, 109));
       
            String iconPath = i == 1 ? "src/pngs/red_icon.png" : i <= 5 ? "src/pngs/orange_icon.png" : "src/pngs/yellow_icon.png";
            Image image = imageIcon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            button.setIcon(new ImageIcon(iconPath));
            buttonPanel.add(button);

            JLabel cityLabel = new JLabel("Soldat " + i +": "+LIEUX[new Random().nextInt(LIEUX.length)]);
            cityLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            cityLabel.setForeground(new Color(73, 95, 109));
            buttonPanel.add(cityLabel);
        }

        // Next button
        JButton nextButton = new JButton("Suivant");
        nextButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        nextButton.setForeground(new Color(73, 95, 109));
        add(nextButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RepartirTroupes::new);
    }
}
