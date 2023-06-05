package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class RepartirTroupes extends JFrame {
    private static final boolean SCALABLE = true;
    private static final String[] LIEUX = {"BDE", "Centre administratif", "Halle sportive", "-", "-"};

    

    private Map<JButton, Point> buttonOrigins = new HashMap<>();
    private Map<JButton, JPanel> buttonContainers = new HashMap<>();
    private Map<JPanel, JPanel> dragZones = new HashMap<>();
    
    private void buttonDragged(MouseEvent evt) {
        JButton button = (JButton)evt.getSource();
        button.setLocation(button.getX() + evt.getX() - button.getWidth() / 2, button.getY() + evt.getY() - button.getHeight() / 2);
    }

    private void buttonReleased(MouseEvent evt) {
        JButton button = (JButton)evt.getSource();
        JPanel buttonPanel = buttonContainers.get(button);
        Point buttonOrigin = buttonOrigins.get(button);

        for (Map.Entry<JPanel, JPanel> entry : dragZones.entrySet()) {
            if (entry.getKey().getBounds().contains(button.getLocation())) {
                buttonPanel.remove(button);
                entry.getKey().add(button);
                button.setLocation(buttonOrigin);
                buttonContainers.put(button, entry.getKey());
                this.revalidate();
                this.repaint();
                return;
            }
        }
     // If not over any drop zone, return the button to its original position
        button.setLocation(buttonOrigin);
        this.revalidate();
        this.repaint();
    }
    public RepartirTroupes() {
    	
        setTitle("Répartissez vos troupes");
        getContentPane().setBackground(new Color(217, 179, 124));
        getContentPane().setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Répartissez vos troupes", SwingConstants.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 24));
        title.setForeground(new Color(73, 95, 109));
        getContentPane().add(title, BorderLayout.NORTH);

        //Image
        ImageIcon imageIcon = new ImageIcon("src/pngs/plan1.png");
        JLabel imageLabel = new JLabel(imageIcon);
        if (SCALABLE) {
            imageLabel.setPreferredSize(new Dimension(500, 500));
        }
        getContentPane().add(imageLabel, BorderLayout.CENTER);
        
        // Left Panel Wrapper so that it looks pretty
        JPanel leftPanelWrapper = new JPanel();
        leftPanelWrapper.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        leftPanelWrapper.setBackground(new Color(217, 179, 124));
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(200, 500));
        //add(scrollPane, BorderLayout.WEST);

        // Buttons
        for (int i = 1; i <= 20; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(50, 50));
            button.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            button.setForeground(new Color(73, 95, 109));

            String iconPath = i == 1 ? "src/pngs/red_icon.png" : i <= 5 ? "src/pngs/orange_icon.png" : "src/pngs/yellow_icon.png";
            ImageIcon icon = new ImageIcon(iconPath);
            Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(image));
            button.setText("Soldat " + i);
            buttonPanel.add(button);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonOrigins.put(button, button.getLocation());
            buttonContainers.put(button, buttonPanel);

            button.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                public void mouseDragged(java.awt.event.MouseEvent evt) {
                    buttonDragged(evt);
                }
            });
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    buttonReleased(evt);
                }
            });

            // The rest of your code...
        }
        leftPanelWrapper.setLayout(new BoxLayout(leftPanelWrapper, BoxLayout.X_AXIS));
        
        //Add Scroll Pane to the Left Panel Wrapper
        leftPanelWrapper.add(scrollPane);
        // Add Left Panel Wrapper to the main layout
        getContentPane().add(leftPanelWrapper, BorderLayout.WEST);
        
        JPanel zoneListPanel = new JPanel();
        leftPanelWrapper.add(zoneListPanel);
        zoneListPanel.setLayout(new GridLayout(5, 0, 100, 0));
        zoneListPanel.setPreferredSize(new Dimension(200, 500));
        
        JPanel zonePanel1 = new JPanel();
        zoneListPanel.add(zonePanel1);
        zonePanel1.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel_3 = new JLabel("BDE");
        zonePanel1.add(lblNewLabel_3, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere1 = new JPanel();
        zonePanel1.add(draggedButtonsHere1, BorderLayout.CENTER);
        
        JPanel zonePanel2 = new JPanel();
        zoneListPanel.add(zonePanel2);
        zonePanel2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel = new JLabel("Quartier administratif");
        zonePanel2.add(lblNewLabel, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere2 = new JPanel();
        zonePanel2.add(draggedButtonsHere2, BorderLayout.CENTER);
        
        JPanel zonePanel3 = new JPanel();
        zoneListPanel.add(zonePanel3);
        zonePanel3.setLayout(new BorderLayout(0, 0));
        
        JLabel lblHalleSportive = new JLabel("Halle sportive");
        zonePanel3.add(lblHalleSportive, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere3 = new JPanel();
        zonePanel3.add(draggedButtonsHere3, BorderLayout.CENTER);
        
        JPanel zonePanel4 = new JPanel();
        zoneListPanel.add(zonePanel4);
        zonePanel4.setLayout(new BorderLayout(0, 0));
        
        JLabel lblBibliothque = new JLabel("Bibliothèque");
        zonePanel4.add(lblBibliothque, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere4 = new JPanel();
        zonePanel4.add(draggedButtonsHere4, BorderLayout.CENTER);
        
        JPanel zonePanel5 = new JPanel();
        zoneListPanel.add(zonePanel5);
        zonePanel5.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel_2_1 = new JLabel("Halles Industrielles");
        zonePanel5.add(lblNewLabel_2_1, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere5 = new JPanel();
        zonePanel5.add(draggedButtonsHere5, BorderLayout.CENTER);
        
        // Next button
        JButton nextButton = new JButton("Suivant");
        nextButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        nextButton.setForeground(new Color(73, 95, 109));
        getContentPane().add(nextButton, BorderLayout.SOUTH);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.EAST);
        panel.setLayout(new GridLayout(1, 0, 0, 0));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
     
        // The rest of your code...
     // Define the dragZones
        dragZones.put(draggedButtonsHere1, zonePanel1);
        dragZones.put(draggedButtonsHere2, zonePanel2);
        dragZones.put(draggedButtonsHere3, zonePanel3);
        dragZones.put(draggedButtonsHere4, zonePanel4);
        dragZones.put(draggedButtonsHere5, zonePanel5);
        
    }
  
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RepartirTroupes::new);
    }
}
