package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SoldierPage {
    private JFrame frame;
    private JLabel soldierIcon;
    private JLabel soldierName;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SoldierPage window = new SoldierPage();
            window.initialize();
        });
    }

    public SoldierPage() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(217, 179, 124));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Get the current window size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        frame.setSize(width, height);
    }

    public void initialize() {
        JPanel leftPanel = createLeftPanel();
        leftPanel.setPreferredSize(new Dimension(500, 800));
        JPanel paddedLeftPanel = createPaddedPanel(leftPanel, 200,new Color(218, 179, 124));
        frame.add(paddedLeftPanel, BorderLayout.WEST);

        JPanel rightPanel = createRightPanel();
        rightPanel.setPreferredSize(new Dimension(500, 800));
        JPanel paddedRightPanel = createPaddedPanel(rightPanel, 200,new Color(218, 179, 124));
        frame.add(paddedRightPanel, BorderLayout.EAST);

        JPanel topPanel = createTopPanel();
        frame.add(topPanel, BorderLayout.NORTH);

        soldierIcon = new JLabel();
        soldierIcon.setPreferredSize(new Dimension(75, 75));
        soldierIcon.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(soldierIcon);

        soldierName = new JLabel();
        soldierName.setFont(new Font("Century Gothic", Font.BOLD, 50));
        soldierName.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(soldierName);

        JLabel lblTitle = new JLabel("Liste des combattants");
        lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 20));
        //frame.add(lblTitle, BorderLayout.SOUTH);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(frame.getContentPane().getBackground());
        titlePanel.add(lblTitle);
        paddedLeftPanel.add(titlePanel, BorderLayout.SOUTH);


        
        frame.setVisible(true);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(242, 220, 187));
        panel.setLayout(new GridBagLayout());

        // Adding icons
        int numRows = 5;
        int numCols = 4;
        int iconSize = 50;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.PAGE_START;

        ImageIcon redIcon = createScaledIcon("src/pngs/red_icon.png", iconSize);
        panel.add(createIconButton(redIcon, "Maitre de Guerre"), gbc);

        
        
        for (int col = 1; col<numCols; col++) {
        	gbc.gridx++;
        	ImageIcon orangeIcon = createScaledIcon("src/pngs/orange_icon.png", iconSize);
        	panel.add(createIconButton(orangeIcon, "Combattant"), gbc);
        }
      
        gbc.gridy++;
        gbc.gridx = 0;

        for (int row = 1; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                ImageIcon icon;
                String name;

                if (row == 1 && col == 0) {
                    icon = createScaledIcon("src/pngs/orange_icon.png", iconSize);
                    name = "Combattant";
                } else {
                    icon = createScaledIcon("src/pngs/yellow_icon.png", iconSize);
                    name = "Soldat";
                }

                panel.add(createIconButton(icon, name), gbc);
                gbc.gridx++;
            }
            gbc.gridy++;
            gbc.gridx = 0;
        }

        return panel;
    }

    private JButton createIconButton(ImageIcon icon, String name) {
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(75, 75));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soldierIcon.setIcon(icon);
                soldierName.setText(name);
            }
        });
        return button;
    }

    private ImageIcon createScaledIcon(String path, int size) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(242, 220, 187));
        panel.setLayout(new GridBagLayout());

        JLabel lblCharacteristics = new JLabel("Caractéristiques");
        lblCharacteristics.setFont(new Font("Century Gothic", Font.BOLD, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(lblCharacteristics, gbc);

        // Add other components as per your requirements

        return panel;
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(218, 179, 124));
        return panel;
    }

    private JPanel createPaddedPanel(JPanel panel, int padding, Color backgroundColor) {
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setBackground(backgroundColor);
        paddedPanel.add(panel, BorderLayout.CENTER);
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        return paddedPanel;
    }

}
