package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import model.CustomButton;
import model.Main;

public class SoldierPage extends JFrame{

	private JFrame frame;
	private JLabel soldierIcon;
    private JLabel soldierName;
    
    String redSoldierIcon = "src/pngs/soldierIcons/red_icon.png";
    String orangeSoldierIcon = "src/pngs/soldierIcons/orange_icon.png";
    String yellowSoldierIcon = "src/pngs/soldierIcons/yellow_icon.png";
    private int width;
    private int height;
    
    JLabel joueurLabel;
    
    String usingPlayer;

    public static void main(String[] args) {
    	Main.setPlayer1Name("1");
    	Main.setPlayer2Name("2");
        SwingUtilities.invokeLater(() -> {
            SoldierPage window = new SoldierPage(Main.getPlayer1Name());
           
        });
    }

    public SoldierPage(String player) {
    	usingPlayer = player;
        frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Main.getBeige());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) (screenSize.width);
        height = (int) (screenSize.height);
        frame.setSize(width, height);
        frame.setVisible(true);
        initialize();
        
    }

	
	private void initialize() {
    	

		//Bottom panel for the "Suivant button"
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Main.getBeige());
        CustomButton suivantButton = new CustomButton("Suivant");
        suivantButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (usingPlayer.equals(Main.getPlayer1Name())) {
        			joueurLabel.setText(Main.getPlayer2Name()+": Paramétrez vos troupes");
        			usingPlayer = Main.getPlayer2Name();
        		} else {
        			RepartirTroupes RepartirTroupes2 = new RepartirTroupes("blue",Main.getPlayer1Name());
        			frame.dispose(); // Close the menu frame
        		}
        	}
        });
        bottomPanel.add(suivantButton);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		///////////////////////////////////////////
		
		//Left Panel for the buttons
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(500, 800));
        leftPanel.setBackground(Main.getLightBeige());
        
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
        leftPanel.add(createIconButton(redIcon, "Maître de Guerre"), gbc);

        for (int col = 1; col<numCols; col++) {
        	gbc.gridx++;
        	ImageIcon orangeIcon = createScaledIcon(orangeSoldierIcon, iconSize);
        	leftPanel.add(createIconButton(orangeIcon, "Soldat élite "+ col), gbc);
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

                leftPanel.add(createIconButton(icon, name), gbc);
                gbc.gridx++;
            }
            gbc.gridy++;
            gbc.gridx = 0;
        }
        JPanel paddedLeftPanel = createPaddedPanel(leftPanel, 20,Main.getBeige());
        frame.getContentPane().add(paddedLeftPanel, BorderLayout.WEST);
        ////////////////////////////
        
        //Top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Main.getBeige());
        
        joueurLabel = new JLabel(Main.getPlayer1Name()+": Paramétrez vos troupes");
        joueurLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        joueurLabel.setForeground(Color.WHITE);
        joueurLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(joueurLabel);
        
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        /////////////
 
        ////////////////////////////
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(500, 800));
        rightPanel.setBackground(Main.getLightBeige());
        
        //Caracteristics in right panel
        JPanel caracPanel = new JPanel(new BorderLayout());
        caracPanel.setBackground(rightPanel.getBackground());

        JLabel lblCharacteristique = new JLabel("Caractéristiques");
        lblCharacteristique.setFont(new Font("Century Gothic", Font.BOLD, 30));
        lblCharacteristique.setForeground(Main.getDarkBlue());
        lblCharacteristique.setHorizontalAlignment(JLabel.CENTER);
        caracPanel.add(lblCharacteristique, BorderLayout.NORTH);

        JPanel slidersPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        slidersPanel.setBackground(slidersPanel.getBackground());
        caracPanel.add(slidersPanel, BorderLayout.CENTER);

        // Array of characteristic names
        String[] characteristicNames = {"Force", "Dextérité", "Résistance", "Constitution", "Initiative"};

        for (String characteristic : characteristicNames) {
        	JPanel characteristicPanel = new JPanel(new BorderLayout());
        	characteristicPanel.setBackground(slidersPanel.getBackground());
        	slidersPanel.add(characteristicPanel);

        	JLabel lblCharacteristicName = new JLabel(characteristic);
        	lblCharacteristicName.setFont(new Font("Century Gothic", Font.BOLD, 20));
        	lblCharacteristicName.setForeground(Main.getDarkBlue());
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

        rightPanel.add(caracPanel, BorderLayout.NORTH);

        //Intelligence and radio buttons
        // Panel for intelligence and radios
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBackground(rightPanel.getBackground());

        JPanel intelligencePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        intelligencePanel.setBackground(rightPanel.getBackground());

        JLabel lblIntelligence = new JLabel("Intelligence");
        lblIntelligence.setFont(new Font("Century Gothic", Font.BOLD, 20));
        lblIntelligence.setForeground(Main.getDarkBlue());
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
        actifPanel.setBackground(rightPanel.getBackground());

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

        rightPanel.add(middlePanel, BorderLayout.CENTER);

        //Bottom part of Right panel
        JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setBackground(rightPanel.getBackground());
   
        //Appliquer Panel
        JPanel appliquerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        appliquerPanel.setBackground(rightPanel.getBackground());

        CustomButton appliquerButton = new CustomButton("Appliquer");
        appliquerPanel.add(appliquerButton);

        //Points restants Panel
        JPanel pointsRestantsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pointsRestantsPanel.setBackground(rightPanel.getBackground()); 
   
        JLabel pointsRestantsLabel = new JLabel("5 points restants");
        pointsRestantsLabel.setForeground(Main.getDarkBlue()); 
        pointsRestantsPanel.add(pointsRestantsLabel);
   
        lowerPanel.add(appliquerPanel, BorderLayout.EAST);
        lowerPanel.add(pointsRestantsPanel, BorderLayout.WEST);
        rightPanel.add(lowerPanel, BorderLayout.SOUTH);
        
        JPanel paddedRightPanel = createPaddedPanel(rightPanel, 20,Main.getBeige());
        frame.getContentPane().add(paddedRightPanel, BorderLayout.EAST);

        
        
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
        lblTitle.setForeground(Main.getDarkBlue());
        //frame.add(lblTitle, BorderLayout.SOUTH);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(frame.getContentPane().getBackground());
        titlePanel.add(lblTitle);
        paddedLeftPanel.add(titlePanel, BorderLayout.SOUTH);
        
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	// To leave some space on the sides of the panels
    private JPanel createPaddedPanel(JPanel panel, int padding, Color backgroundColor) {
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setBackground(backgroundColor);
        paddedPanel.add(panel, BorderLayout.CENTER);
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        return paddedPanel;
    }
    
	
}
