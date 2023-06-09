package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import model.CustomLabel;
import model.Main;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JPanel;
import model.CustomButton;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Treve {

	JFrame frame;
	private int step;
	private String player;
	private String controlledSpace;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int step;
					Main.setPlayer1Name("p1");
					Main.setPlayer2Name("p2");
					//Treve window = new Treve(step,Melee2());
					//window.frame.setVisible(true);
					//window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Treve(int step,JFrame previousMelee) {
		initialize(step,previousMelee);
		}
		
		

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int step,JFrame previousMelee) {
		switch (step) {
	    case 1:
	        player = Main.getPlayer1Name();
	        controlledSpace = "La Halle Sportive";
	        break;
	    case 2:
	        player = Main.getPlayer2Name();
	        controlledSpace = "Les Halles Industrielles";
	        break;
	    case 3:
	        player = Main.getPlayer1Name();
	        controlledSpace = "Le Quartier Administratif";
	        break;
	    case 4:
	        player = Main.getPlayer1Name();
	        controlledSpace = "Le BDE";
	        break;
		}
		
		
		frame = new JFrame();

		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 509, 339);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new GridLayout(0, 1, 0, 0));
		topPanel.setBackground(Main.getBeige());
		
		CustomLabel title = new CustomLabel("Trêve - "+controlledSpace);
		
		title.setFont(new Font("Century Gothic", Font.BOLD, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(title);
		
		CustomLabel playerLabel = new CustomLabel("Joueur 1 : "+player);
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
		
		
		topPanel.add(playerLabel);
		
		JPanel CenterPanel = new JPanel();
		frame.getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(1, 2, 0, 0));
		CenterPanel.setBackground(Main.getLightBeige());
		
		JPanel leftPanel = new JPanel();
		CenterPanel.add(leftPanel);
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel gridBag = new JPanel();
		leftPanel.add(gridBag);
		GridBagLayout gbl_gridBag = new GridBagLayout();
		gbl_gridBag.columnWidths = new int[] {0, 0};
		gbl_gridBag.rowHeights = new int[] {20, 40, 0, 40, 40, 40};
		gbl_gridBag.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_gridBag.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0};
		gridBag.setLayout(gbl_gridBag);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		gridBag.add(panel, gbc_panel);
		
		JPanel one = new JPanel();
		GridBagConstraints gbc_one = new GridBagConstraints();
		gbc_one.insets = new Insets(0, 0, 5, 0);
		gbc_one.fill = GridBagConstraints.BOTH;
		gbc_one.gridx = 0;
		gbc_one.gridy = 1;
		gridBag.add(one, gbc_one);
		
		CustomLabel zone1 = new CustomLabel("Halle Sportive");
		one.add(zone1);
		zone1.setBackground(Main.getBlue());
		one.setBackground(Main.getBlue());
		
		
		JPanel two = new JPanel();
		GridBagConstraints gbc_two = new GridBagConstraints();
		gbc_two.insets = new Insets(0, 0, 5, 0);
		gbc_two.fill = GridBagConstraints.BOTH;
		gbc_two.gridx = 0;
		gbc_two.gridy = 2;
		gridBag.add(two, gbc_two);
		CustomLabel zone2 = new CustomLabel("BDE");
		two.add(zone2);
		/*
		switch (step) {
	    case 1:
	    	
	        break;
	    case 2:
	    	
	        break;
	    case 3:
	        
	        break;
	    case 4:
	       
	        break;
		}
		*/
		
		JPanel three = new JPanel();
		FlowLayout fl_three = (FlowLayout) three.getLayout();
		GridBagConstraints gbc_three = new GridBagConstraints();
		gbc_three.insets = new Insets(0, 0, 5, 0);
		gbc_three.fill = GridBagConstraints.BOTH;
		gbc_three.gridx = 0;
		gbc_three.gridy = 3;
		gridBag.add(three, gbc_three);
		
		CustomLabel zone4 = new CustomLabel("Halles Industrielles");
		three.add(zone4);
		
		JPanel four = new JPanel();
		GridBagConstraints gbc_four = new GridBagConstraints();
		gbc_four.insets = new Insets(0, 0, 5, 0);
		gbc_four.fill = GridBagConstraints.BOTH;
		gbc_four.gridx = 0;
		gbc_four.gridy = 4;
		gridBag.add(four, gbc_four);
		
		CustomLabel zone3 = new CustomLabel("Quartier Administratif");
		four.add(zone3);
		
		JPanel five = new JPanel();
		GridBagConstraints gbc_five = new GridBagConstraints();
		gbc_five.insets = new Insets(0, 0, 5, 0);
		gbc_five.fill = GridBagConstraints.BOTH;
		gbc_five.gridx = 0;
		gbc_five.gridy = 5;
		gridBag.add(five, gbc_five);
		
		CustomLabel zone5 = new CustomLabel("Bibliothèque");
		five.add(zone5);
		
		JPanel rightPanel = new JPanel();
		CenterPanel.add(rightPanel);
		
		JPanel gridBag_1 = new JPanel();
		rightPanel.add(gridBag_1);
		GridBagLayout gbl_gridBag_1 = new GridBagLayout();
		gbl_gridBag_1.columnWidths = new int[]{0, 0};
		gbl_gridBag_1.rowHeights = new int[] {50};
		gbl_gridBag_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_gridBag_1.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0};
		gridBag_1.setLayout(gbl_gridBag_1);
		
		JPanel panel_1_1 = new JPanel();
		GridBagConstraints gbc_panel_1_1 = new GridBagConstraints();
		gbc_panel_1_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1_1.gridx = 0;
		gbc_panel_1_1.gridy = 0;
		gridBag_1.add(panel_1_1, gbc_panel_1_1);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 1;
		gridBag_1.add(panel_5, gbc_panel_5);
		
		CustomButton btnNewButton = new CustomButton("Redéployer les troupes");
		
		
		panel_5.add(btnNewButton);
		
		JPanel panel_3_1 = new JPanel();
		GridBagConstraints gbc_panel_3_1 = new GridBagConstraints();
		gbc_panel_3_1.fill = GridBagConstraints.BOTH;
		gbc_panel_3_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3_1.gridx = 0;
		gbc_panel_3_1.gridy = 2;
		gridBag_1.add(panel_3_1, gbc_panel_3_1);
		
		CustomButton btnNewButton_1 = new CustomButton("Continuer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (step) {
			    case 1:
			    	Melee2 window2 = new Melee2();
			    	window2.setVisible(true);
			    	previousMelee.dispose();
			        break;
			    case 2:
			    	Melee3 window3 = new Melee3();
			    	window3.setVisible(true);
			    	previousMelee.dispose();
			        break;
			    case 3:
			    	Melee4 window4 = new Melee4();
			    	window4.setVisible(true);
			    	previousMelee.dispose();
			        break;
			    //case 4:
			    //	FinJeu window = new FinJeu();
			    //	window.getFrame().setVisible(true);
			     //   break;
			    }
			}
		});
		
		panel_3_1.add(btnNewButton_1);
		
		JPanel panel_2_1 = new JPanel();
		GridBagConstraints gbc_panel_2_1 = new GridBagConstraints();
		gbc_panel_2_1.fill = GridBagConstraints.BOTH;
		gbc_panel_2_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2_1.gridx = 0;
		gbc_panel_2_1.gridy = 3;
		gridBag_1.add(panel_2_1, gbc_panel_2_1);
		
		JPanel panel_4_1 = new JPanel();
		GridBagConstraints gbc_panel_4_1 = new GridBagConstraints();
		gbc_panel_4_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4_1.fill = GridBagConstraints.BOTH;
		gbc_panel_4_1.gridx = 0;
		gbc_panel_4_1.gridy = 4;
		gridBag_1.add(panel_4_1, gbc_panel_4_1);
		
		switch (step) {
	    case 1:
			playerLabel.setForeground(Main.getBlue());
			
			zone2.setText("Quartier Administratif");
			zone3.setText("Halles Industrielles");
	    	zone4.setText("Bibliothèque");
	    	zone5.setText("BDE");
			
			two.setBackground(Main.getGray());
			three.setBackground(Main.getGray());
			four.setBackground(Main.getGray());
			five.setBackground(Main.getGray());
	        break;
	    case 2:
	    	playerLabel.setForeground(Main.getRed());
	    	
	    	zone2.setText("Halles Industrielles");//TAKEN
	    	zone3.setText("Quartier Administratif");
	    	zone4.setText("Bibliothèque");
	    	zone5.setText("BDE");
	    	
	    	two.setBackground(Main.getRed());
			three.setBackground(Main.getGray());
			four.setBackground(Main.getGray());
			five.setBackground(Main.getGray());
	        break;
	    case 3:
	    	playerLabel.setForeground(Main.getBlue());
	    	
	    	zone2.setText("Halles Industrielles");//TAKEN
	    	zone3.setText("Quartier Administratif");//TAKEN
	    	zone4.setText("Bibliothèque");
	    	zone5.setText("BDE");
	    	
	    	two.setBackground(Main.getRed());
	    	three.setBackground(Main.getBlue());
			four.setBackground(Main.getGray());
			five.setBackground(Main.getGray());
	        break;
	    case 4:
	    	playerLabel.setForeground(Main.getBlue());
	    	
	    	zone2.setText("Halles Industrielles");//TAKEN
	    	zone3.setText("Quartier Administratif");//TAKEN
	    	zone4.setText("BDE");//TAKEN
	    	zone5.setText("Bibliothèque");
	    	
	    	two.setBackground(Main.getRed());
	    	three.setBackground(Main.getBlue());
			four.setBackground(Main.getBlue());
			five.setBackground(Main.getGray());
	        break;
		}
	        
	}

}
