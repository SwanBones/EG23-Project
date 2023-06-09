package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.CustomButton;
import model.CustomLabel;
import model.CustomTextField;
import model.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Dimension;

public class PlayerInfo {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerInfo window = new PlayerInfo();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlayerInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		CustomLabel lblNewLabel = new CustomLabel("Entrez vos noms");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 40));
		
		panel.add(lblNewLabel);
		panel.setBackground(Main.getLightBeige());
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		panel_1.setBackground(Main.getBeige());
		
		gbl_panel_1.columnWidths = new int[]{414, 0};
		gbl_panel_1.rowHeights = new int[]{53, 53, 53, 53, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		CustomLabel j1 = new CustomLabel("Joueur 1");
		GridBagConstraints gbc_j1 = new GridBagConstraints();
		gbc_j1.fill = GridBagConstraints.CENTER;
		gbc_j1.insets = new Insets(0, 0, 5, 0);
		gbc_j1.gridx = 0;
		gbc_j1.gridy = 0;
		panel_1.add(j1, gbc_j1);
		
		textField = new CustomTextField();
		textField.setPreferredSize(new Dimension(400, 80));
		textField.setMaximumSize(new Dimension(400, 50));
		JPanel panelWrapper = new JPanel();  // Create a wrapper panel
		panelWrapper.setLayout(new BoxLayout(panelWrapper, BoxLayout.X_AXIS));
		panelWrapper.add(Box.createHorizontalGlue());  // Added glue before adding the text field
		panelWrapper.add(textField);
		panelWrapper.add(Box.createHorizontalGlue());  // Added glue after adding the text field
		panelWrapper.setBackground(Main.getBeige());  // Set background color
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panel_1.add(panelWrapper, gbc_textField);  // Add the wrapper panel instead of the text field
		
		CustomLabel j2 = new CustomLabel("Joueur 2");
		GridBagConstraints gbc_j2 = new GridBagConstraints();
		gbc_j2.fill = GridBagConstraints.CENTER;
		gbc_j2.insets = new Insets(0, 0, 5, 0);
		gbc_j2.gridx = 0;
		gbc_j2.gridy = 2;
		panel_1.add(j2, gbc_j2);
		
		textField_1 = new CustomTextField();
		textField_1.setPreferredSize(new Dimension(400, 80));
		textField_1.setMaximumSize(new Dimension(400, 50));
		JPanel panelWrapper1 = new JPanel();  // Create another wrapper panel
		panelWrapper1.setLayout(new BoxLayout(panelWrapper1, BoxLayout.X_AXIS));
		panelWrapper1.add(Box.createHorizontalGlue());  // Added glue before adding the text field
		panelWrapper1.add(textField_1);
		panelWrapper1.add(Box.createHorizontalGlue());  // Added glue after adding the text field
		panelWrapper1.setBackground(Main.getBeige());  // Set background color
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 3;
		panel_1.add(panelWrapper1, gbc_textField_1);  // Add the wrapper panel instead of the text field
		CustomButton btnNewButton = new CustomButton("Valider");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 4;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		
		
		
		CustomLabel errorLabel = new CustomLabel("New label");
		errorLabel.setText("");
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_errorLabel.gridx = 0;
		gbc_errorLabel.gridy = 5;
		panel_1.add(errorLabel, gbc_errorLabel);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setBackground(Main.getBeige());
		
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		panel_5.setBackground(Main.getLightBeige());
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, BorderLayout.WEST);
		panel_3.setBackground(Main.getBeige());
		
		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, BorderLayout.EAST);
		panel_4.setBackground(Main.getBeige());
		
		panel_2.setLayout(new BorderLayout(0, 0));
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p1Name = textField.getText();
				String p2Name = textField_1.getText();
				
				if(p1Name.trim().isEmpty()||p2Name.trim().isEmpty()) {
					errorLabel.setText("Veuillez remplir vos noms!");;
					
				}else if (p1Name.equals(p2Name)) {
					System.out.println(p1Name);
						errorLabel.setText("Les noms ne peuvent pas être identiques!");;
				}else {
					Main.setPlayer1Name(textField.getText());
					Main.setPlayer2Name(textField_1.getText());
					SoldierPage soldierPage = new SoldierPage(Main.getPlayer1Name());
				    frame.dispose(); // Close the menu frame
				}
				
				
				//try {
		       
		        //} catch (Exception ex) {
		        //    ex.printStackTrace();
		        //}
			}
		});		
		
	}
	public JFrame getFrame() {
		return frame;
	}
}
