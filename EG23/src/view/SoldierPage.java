package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.CustomButton;
import model.Main;

public class SoldierPage {
    private JFrame frame;
    private JLabel soldierIcon;
    private JLabel soldierName;
    
    String redSoldierIcon = "src/pngs/soldierIcons/red_icon.png";
    String orangeSoldierIcon = "src/pngs/soldierIcons/orange_icon.png";
    String yellowSoldierIcon = "src/pngs/soldierIcons/yellow_icon.png";
    int width = 500;
    int height = 500;
    
    JLabel joueurLabel;
    
    String usingPlayer;
    
    public static void main(java.lang.String[] args) {
    	Main.setPlayer1Name("1");
    	Main.setPlayer2Name("2");
        SwingUtilities.invokeLater(() -> {
            SoldierPage window = new SoldierPage(Main.getPlayer1Name());
           
        });
    }

    public SoldierPage(String player) {
    	usingPlayer = player;
        frame = new JFrame();
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(217, 179, 124));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Get the current window size so it fits the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        frame.setSize(width, height);
        initialize();
    }
    /**
     * @wbp.parser.entryPoint
     */
    public void initialize() {
    	
    	// font color
    	Color fontColor = new Color(73, 95, 110);
    	
        JPanel leftPanel = createLeftPanel();
        leftPanel.setPreferredSize(new Dimension(500, 800));
        JPanel paddedLeftPanel = createPaddedPanel(leftPanel, 200,new Color(218, 179, 124));
        frame.getContentPane().add(paddedLeftPanel, BorderLayout.WEST);

        JPanel rightPanel = createRightPanel();
        rightPanel.setPreferredSize(new Dimension(500, 800));
        JPanel paddedRightPanel = createPaddedPanel(rightPanel, 200,new Color(218, 179, 124));
        frame.getContentPane().add(paddedRightPanel, BorderLayout.EAST);

        JPanel topPanel = createTopPanel();
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        
        JPanel bottomPanel = createBottomPanel();
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        soldierIcon = new JLabel();
        soldierIcon.setPreferredSize(new Dimension(75, 75));
        soldierIcon.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(soldierIcon);

        soldierName = new JLabel();
        soldierName.setFont(new Font("Century Gothic", Font.BOLD, 50));
        soldierName.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(soldierName);

        JLabel lblTitle = new JLabel("Liste des combattants");
        lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 30));
        lblTitle.setForeground(fontColor);
        //frame.add(lblTitle, BorderLayout.SOUTH);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(frame.getContentPane().getBackground());
        titlePanel.add(lblTitle);
        paddedLeftPanel.add(titlePanel, BorderLayout.SOUTH);
        frame.setSize(width, height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

        //Naming the icons
        ImageIcon redIcon = createScaledIcon(redSoldierIcon, iconSize);
        panel.add(createIconButton(redIcon, "Maître de Guerre"), gbc);

        for (int col = 1; col<numCols; col++) {
        	gbc.gridx++;
        	ImageIcon orangeIcon = createScaledIcon(orangeSoldierIcon, iconSize);
        	panel.add(createIconButton(orangeIcon, "Soldat élite "+ col), gbc);
        }
      
        gbc.gridy++;
        gbc.gridx = 0;

        int nb_soldier = 1;
        for (int row = 1; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
            	ImageIcon icon;
                String name;

                if (row == 1 && col == 0) {
                    icon = createScaledIcon(orangeSoldierIcon, iconSize);
                    name = "Soldat élite 4";
                } else {
                    icon = createScaledIcon(yellowSoldierIcon, iconSize);
                    name = "Soldat "+ nb_soldier;
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

    private CustomButton createIconButton(ImageIcon icon, String name) {
    	CustomButton button = new CustomButton(icon);
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
        // font color
        Color fontColor = new Color(73, 95, 110);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(242, 220, 187));
        panel.setLayout(new BorderLayout());

        //////////// Top Panel for characteristics sliders
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(panel.getBackground());

        JLabel lblCharacteristique = new JLabel("Caractéristiques");
        lblCharacteristique.setFont(new Font("Century Gothic", Font.BOLD, 30));
        lblCharacteristique.setForeground(fontColor);
        lblCharacteristique.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(lblCharacteristique, BorderLayout.NORTH);

        JPanel slidersPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        slidersPanel.setBackground(panel.getBackground());
        topPanel.add(slidersPanel, BorderLayout.CENTER);

        // Array of characteristic names
        String[] characteristicNames = {"Force", "Dextérité", "Résistance", "Constitution", "Initiative"};

        for (String characteristic : characteristicNames) {
            JPanel characteristicPanel = new JPanel(new BorderLayout());
            characteristicPanel.setBackground(slidersPanel.getBackground());
            slidersPanel.add(characteristicPanel);

            JLabel lblCharacteristicName = new JLabel(characteristic);
            lblCharacteristicName.setFont(new Font("Century Gothic", Font.BOLD, 20));
            lblCharacteristicName.setForeground(fontColor);
            characteristicPanel.add(lblCharacteristicName, BorderLayout.NORTH);

            JPanel sliderPanel = new JPanel(new BorderLayout());
            sliderPanel.setBackground(slidersPanel.getBackground());
            characteristicPanel.add(sliderPanel, BorderLayout.CENTER);

            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 30, 15);
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
     // Middle Panel for Intelligence and radio buttons
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBackground(panel.getBackground());

        JPanel intelligencePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        intelligencePanel.setBackground(panel.getBackground());

        JLabel lblIntelligence = new JLabel("Intelligence");
        lblIntelligence.setFont(new Font("Century Gothic", Font.BOLD, 20));
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

        CustomButton actifButton = new CustomButton("Actif");
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

        CustomButton appliquerButton = new CustomButton("Appliquer");
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
        
        joueurLabel = new JLabel(Main.getPlayer1Name()+": Paramétrez vos troupes");
        joueurLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        joueurLabel.setForeground(Color.WHITE);
        joueurLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(joueurLabel);
        
        return panel;
    }
    
    private JPanel createBottomPanel() {
    	JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	bottomPanel.setBackground(new Color(218, 179, 124));
    	CustomButton suivantButton = new CustomButton("Suivant");
        bottomPanel.add(suivantButton);
        suivantButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (usingPlayer.equals(Main.getPlayer1Name())) {
        			joueurLabel.setText(Main.getPlayer2Name()+": Paramétrez vos troupes");
        			usingPlayer = Main.getPlayer2Name();
        		}else {
        		RepartirTroupes RepartirTroupes2 = new RepartirTroupes("blue",Main.getPlayer1Name());
                frame.dispose(); // Close the menu frame
        		}
        	}
        });
        	
        return bottomPanel;
    }

    // To leave some space on the sides of the panels
    private JPanel createPaddedPanel(JPanel panel, int padding, Color backgroundColor) {
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setBackground(backgroundColor);
        paddedPanel.add(panel, BorderLayout.CENTER);
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        return paddedPanel;
    }

}