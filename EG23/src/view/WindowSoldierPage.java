package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowSoldierPage {
    private JFrame frame;
    private JLabel soldierIcon;
    private JLabel soldierName;

    public static void main(java.lang.String[] args) {
        SwingUtilities.invokeLater(() -> {
        	WindowSoldierPage window = new WindowSoldierPage();
            window.initialize();
        });
    }

    public WindowSoldierPage() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(217, 179, 124));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Get the current window size so it fits the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        frame.setSize(width, height);
    }

    public void initialize() {

        // font color
        Color fontColor = new Color(73, 95, 110);

        JPanel leftPanel = createLeftPanel();
        leftPanel.setPreferredSize(new Dimension(Math.round(frame.getWidth() / 2.5f), frame.getHeight()));
        JPanel paddedLeftPanel = createPaddedPanel(leftPanel, frame.getHeight() / 10, new Color(218, 179, 124));
        frame.getContentPane().add(paddedLeftPanel, BorderLayout.WEST);

        JPanel rightPanel = createRightPanel();
        rightPanel.setPreferredSize(new Dimension(Math.round(frame.getWidth() / 1.9f), frame.getHeight()));
        JPanel paddedRightPanel = createPaddedPanel(rightPanel, frame.getHeight() / 10, new Color(218, 179, 124));
        frame.getContentPane().add(paddedRightPanel, BorderLayout.EAST);

        JPanel topPanel = createTopPanel();
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        soldierIcon = new JLabel();
        soldierIcon.setPreferredSize(new Dimension(frame.getHeight() / 10, frame.getHeight() / 10));
        soldierIcon.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(soldierIcon);

        soldierName = new JLabel();
        soldierName.setFont(new Font("Century Gothic", Font.BOLD, frame.getHeight() / 15));
        soldierName.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(soldierName);

        JLabel lblTitle = new JLabel("Liste des combattants");
        lblTitle.setFont(new Font("Century Gothic", Font.BOLD, frame.getHeight() / 22));
        lblTitle.setForeground(fontColor);

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
        int iconSize = frame.getHeight() / 15;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(frame.getHeight() / 80, frame.getHeight() / 80, frame.getHeight() / 80, frame.getHeight() / 80);
        gbc.anchor = GridBagConstraints.PAGE_START;

        //Naming the icons
        ImageIcon redIcon = createScaledIcon("src/pngs/red_icon.png", iconSize);
        panel.add(createIconButton(redIcon, "Maître de Guerre"), gbc);

        for (int col = 1; col < numCols; col++) {
            gbc.gridx++;
            ImageIcon orangeIcon = createScaledIcon("src/pngs/orange_icon.png", iconSize);
            panel.add(createIconButton(orangeIcon, "Soldat élite " + col), gbc);
        }

        gbc.gridy++;
        gbc.gridx = 0;

        int nb_soldier = 1;
        for (int row = 1; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                ImageIcon icon;
                String name;

                if (row == 1 && col == 0) {
                    icon = createScaledIcon("src/pngs/orange_icon.png", iconSize);
                    name = "Soldat élite 4";
                } else {
                    icon = createScaledIcon("src/pngs/yellow_icon.png", iconSize);
                    name = "Soldat " + nb_soldier;
                    nb_soldier++;
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
        button.setPreferredSize(new Dimension(frame.getHeight() / 10, frame.getHeight() / 10));
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
        // font color
        Color fontColor = new Color(73, 95, 110);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(242, 220, 187));
        panel.setLayout(new BorderLayout());

        //////////// Top Panel for characteristics sliders
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(panel.getBackground());

        JLabel lblCharacteristique = new JLabel("Caractéristiques");
        lblCharacteristique.setFont(new Font("Century Gothic", Font.BOLD, frame.getHeight() / 22));
        lblCharacteristique.setForeground(fontColor);
        lblCharacteristique.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(lblCharacteristique, BorderLayout.NORTH);

        JPanel slidersPanel = new JPanel(new GridLayout(0, 1, 0, frame.getHeight() / 80));
        slidersPanel.setBackground(panel.getBackground());
        topPanel.add(slidersPanel, BorderLayout.CENTER);

        // Array of characteristic names
        String[] characteristicNames = {"Force", "Dextérité", "Résistance", "Constitution", "Initiative"};

        for (String characteristic : characteristicNames) {
            JPanel characteristicPanel = new JPanel(new BorderLayout());
            characteristicPanel.setBackground(slidersPanel.getBackground());
            slidersPanel.add(characteristicPanel);

            JLabel lblCharacteristicName = new JLabel(characteristic);
            lblCharacteristicName.setFont(new Font("Century Gothic", Font.BOLD, frame.getHeight() / 30));
            lblCharacteristicName.setForeground(fontColor);
            characteristicPanel.add(lblCharacteristicName, BorderLayout.NORTH);

            JPanel sliderPanel = new JPanel(new BorderLayout());
            sliderPanel.setBackground(slidersPanel.getBackground());
            characteristicPanel.add(sliderPanel, BorderLayout.CENTER);

            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 30, 15);
            //slider.setPreferredSize(new Dimension(300,slider.getPreferredSize().height));
            slider.setMajorTickSpacing(30);
            slider.setMinorTickSpacing(5);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            sliderPanel.add(slider, BorderLayout.CENTER);

            JTextField textField = new JTextField(3);
            textField.setEditable(false);
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setText(String.valueOf(slider.getValue()));
            sliderPanel.add(textField, BorderLayout.EAST);

            slider.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    JSlider source = (JSlider) evt.getSource();
                    textField.setText(String.valueOf(source.getValue()));
                }
            });
        }

        panel.add(topPanel, BorderLayout.NORTH);

        ////////// Middle Panel for Intelligence and radio buttons
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBackground(panel.getBackground());

        JPanel intelligencePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, frame.getHeight() / 80, frame.getHeight() / 80));
        intelligencePanel.setBackground(panel.getBackground());

        JLabel lblIntelligence = new JLabel("Intelligence");
        lblIntelligence.setFont(new Font("Century Gothic", Font.BOLD, frame.getHeight() / 35));
        lblIntelligence.setForeground(fontColor);
        intelligencePanel.add(lblIntelligence);

        JRadioButton defensiveRadioButton = new JRadioButton("Défensif");
        JRadioButton offensiveRadioButton = new JRadioButton("Offensif");
        JRadioButton randomRadioButton = new JRadioButton("Aléatoire");

        ButtonGroup intelligenceButtonGroup = new ButtonGroup();
        intelligenceButtonGroup.add(defensiveRadioButton);
        intelligenceButtonGroup.add(offensiveRadioButton);
        intelligenceButtonGroup.add(randomRadioButton);

        intelligencePanel.add(defensiveRadioButton);
        intelligencePanel.add(offensiveRadioButton);
        intelligencePanel.add(randomRadioButton);

        middlePanel.add(intelligencePanel, BorderLayout.NORTH);

        JPanel actifPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actifPanel.setBackground(panel.getBackground());

        JButton actifButton = new JButton("Actif");
        actifButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String buttonText = actifButton.getText();
                if (buttonText.equals("Actif")) {
                    actifButton.setText("Réserviste");
                } else {
                    actifButton.setText("Actif");
                }
            }
        });

        actifPanel.add(actifButton);

        middlePanel.add(actifPanel, BorderLayout.CENTER);

        panel.add(middlePanel, BorderLayout.CENTER);

        /////////////BOTTOM PANEL
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(panel.getBackground());

        //Appliquer Panel
        JPanel appliquerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        appliquerPanel.setBackground(panel.getBackground());

        JButton appliquerButton = new JButton("Appliquer");
        appliquerPanel.add(appliquerButton);

        //Points restants Panel
        JPanel pointsRestantsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pointsRestantsPanel.setBackground(panel.getBackground());

        JLabel pointsRestantsLabel = new JLabel("5 points restants");
        pointsRestantsLabel.setForeground(fontColor);
        pointsRestantsPanel.add(pointsRestantsLabel);

        bottomPanel.add(appliquerPanel, BorderLayout.EAST);
        bottomPanel.add(pointsRestantsPanel, BorderLayout.WEST);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(218, 179, 124));
        return panel;
    }

    // To leave some space on the sides of the panels
    private JPanel createPaddedPanel(JPanel panel, int padding, Color backgroundColor) {
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setBackground(backgroundColor);
        paddedPanel.add(panel, BorderLayout.CENTER);
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(frame.getHeight() / padding, frame.getHeight() / padding, frame.getHeight() / padding, frame.getHeight() / padding));
        return paddedPanel;
    }
}

