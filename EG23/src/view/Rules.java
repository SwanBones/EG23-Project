package view;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import model.Main;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rules {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rules window = new Rules();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Rules() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setVisible(true);
		
		frame.getContentPane().setBackground(Main.getBeige());
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Main.getLightBeige());
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTextArea Rules = new JTextArea();
		Rules.setLineWrap(true);
		Rules.setWrapStyleWord(true);
		Rules.setFont(new Font("Century Gothic", Font.BOLD, 12));
		Rules.setForeground(Main.getDarkBlue());
		Rules.setBackground(Main.getLightBeige());
		Rules.setText("Représente un des 7 programmes de l'UTT affronte ton adversaire sur 5 zones d’influence du campus! Vous devrez gérer vos troupes, répartir vos points de caractéristiques, choisir votre stratégie et faire preuve de tactique pour remporter la victoire. Le jeu se déroule en plusieurs étapes, avec des phases de combat et des phases de trêve, où vous pourrez faire des mouvements de troupes et appeler des réservistes! N’attendez plus, rejoignez le War Game à l’UTT et faites triompher votre programme !");
		panel.add(Rules, BorderLayout.CENTER);
		Rules.setEditable(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Main.getBeige());
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Règles du Jeu");
		panel_1.add(lblNewLabel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(218, 179, 124));
		frame.getContentPane().add(panel_1_1, BorderLayout.SOUTH);
		
		JButton btnJaiCompris = new JButton("J'ai compris!");
		btnJaiCompris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel_1_1.add(btnJaiCompris);
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
