package view;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import model.CustomButton;
import model.CustomLabel;

import java.awt.FlowLayout;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinJeu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinJeu window = new FinJeu();
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
	public FinJeu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(218, 179, 124));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(218, 179, 124));
		frame.getContentPane().add(leftPanel, BorderLayout.WEST);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(218, 179, 124));
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		CustomLabel lblNewLabel = new CustomLabel("FIN DU JEU");
		lblNewLabel.setForeground(new Color(73, 95, 110));
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 23));
		topPanel.add(lblNewLabel);
		
		//Center Panel for map
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(new Color(218, 179, 124));
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		ImageIcon map = new ImageIcon("src/pngs/plan_utt.png");
		
		CustomLabel lblNewLabel_1 = new CustomLabel("Le Joueur 2 A Gagné!");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(73, 95, 110));
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 23));
		centerPanel.add(lblNewLabel_1, BorderLayout.CENTER);
		
		CustomButton btnNewButton = new CustomButton("Retourner au menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
                menu.initialize();
                frame.dispose(); // Close the menu frame
			}
		});
		centerPanel.add(btnNewButton, BorderLayout.SOUTH);
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 11));

		
		//Bottom Panel
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(new Color(218, 179, 124));
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(218, 179, 124));
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}
