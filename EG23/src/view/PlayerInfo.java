package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

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
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Entrez vos noms");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel j1 = new JLabel("Joueur 1");
		panel_1.add(j1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel j2 = new JLabel("Joueur 2");
		panel_1.add(j2);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label = new JLabel("New label");
		panel_5.add(label);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, BorderLayout.EAST);
		JButton btnNewButton = new JButton("Valider");
		
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(btnNewButton, BorderLayout.EAST);
		
		JLabel errorLabel = new JLabel("New label");
		panel_2.add(errorLabel, BorderLayout.CENTER);
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
}