package view;

import java.awt.EventQueue;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Main;
import model.CustomButton;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Menu {

	private JFrame frame;
	private JPanel menuPanel;
	private JPanel authorsPanel;
	private Rules rulesWindow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {

		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setBackground(Main.getBeige());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());

		menuPanel = new JPanel();
		menuPanel.setBackground(Main.getBeige());
		GridBagLayout gbl_menuPanel = new GridBagLayout();
		gbl_menuPanel.columnWidths = new int[] { 354, 0 };
		gbl_menuPanel.rowHeights = new int[] { 37, 37, 37, 37, 0 };
		gbl_menuPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_menuPanel.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		menuPanel.setLayout(gbl_menuPanel);

		JLabel lblNewLabel = new JLabel("La Bataille des Programmes");
		lblNewLabel.setBackground(Main.getLightBlue());
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.CENTER;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		gbc_lblNewLabel.anchor = GridBagConstraints.CENTER;
		menuPanel.add(lblNewLabel, gbc_lblNewLabel);

		CustomButton btnNewButton_1 = new CustomButton("Règles");
		btnNewButton_1.setBackground(Main.getDarkBlue());
		btnNewButton_1.setForeground(Main.getLightBlue());
		btnNewButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rulesWindow = new Rules(); // Create an instance of the Rules class
				//rulesWindow.initialize(); // Initialize the rules window
				rulesWindow.getFrame().setLocationRelativeTo(frame); // Set the location relative to the menu frame
				rulesWindow.getFrame().setVisible(true); // Make the rules window visible
			}
		});

		CustomButton btnNewButton = new CustomButton("Jouer");
		btnNewButton.setBackground(Main.getDarkBlue());
		btnNewButton.setForeground(Main.getLightBlue());
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.CENTER;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		gbc_btnNewButton.anchor = GridBagConstraints.CENTER;
		menuPanel.add(btnNewButton, gbc_btnNewButton);

		// Action listener for the "Jouer" button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerInfo playerInfo = new PlayerInfo();
				playerInfo.getFrame().setLocationRelativeTo(frame); // Set the location relative to the menu frame
				
				frame.dispose(); // Close the menu frame
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.CENTER;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		gbc_btnNewButton_1.anchor = GridBagConstraints.CENTER;
		menuPanel.add(btnNewButton_1, gbc_btnNewButton_1);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		frame.getContentPane().add(menuPanel, BorderLayout.CENTER);

		CustomButton btnNewButton_2 = new CustomButton("Auteurs");
		btnNewButton_2.setForeground(Main.getLightBlue());
		btnNewButton_2.setBackground(Main.getDarkBlue());
		btnNewButton_2.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAuthorsPage();
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.CENTER;
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 3;
		gbc_btnNewButton_2.anchor = GridBagConstraints.CENTER;
		menuPanel.add(btnNewButton_2, gbc_btnNewButton_2);

		JPanel panel_north = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_north.getLayout();
		flowLayout_2.setVgap(20);
		panel_north.setBorder(null);
		panel_north.setBackground(Main.getBeige());
		frame.getContentPane().add(panel_north, BorderLayout.NORTH);

		JPanel panel_south = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_south.getLayout();
		flowLayout.setVgap(20);
		panel_south.setBorder(null);
		panel_south.setForeground(new Color(0, 0, 0));
		panel_south.setBackground(Main.getBeige());
		frame.getContentPane().add(panel_south, BorderLayout.SOUTH);

		JPanel panel_west = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_west.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setVgap(0);
		panel_west.setBackground(Main.getBeige());
		panel_west.setBorder(null);
		frame.getContentPane().add(panel_west, BorderLayout.WEST);

		JPanel panel_east = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_east.getLayout();
		flowLayout_3.setHgap(20);
		panel_east.setBackground(Main.getBeige());
		panel_east.setBorder(null);
		frame.getContentPane().add(panel_east, BorderLayout.EAST);
		
		// Set the preferred size for the menuPanel to maintain its initial size
		menuPanel.setPreferredSize(new Dimension(450, 148));
	}

	private void showAuthorsPage() {
		authorsPanel = new JPanel();
		authorsPanel.setBackground(Main.getBeige());
		authorsPanel.setLayout(new GridLayout(4, 1, 0, 10));

		JLabel lblNewLabel = new JLabel("AUTEURS:");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		authorsPanel.add(lblNewLabel);

		JLabel nameLabel1 = new JLabel("Xuan BOONE");
		nameLabel1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		nameLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		authorsPanel.add(nameLabel1);

		JLabel nameLabel2 = new JLabel("Emmanuel TRAN");
		nameLabel2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		nameLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		authorsPanel.add(nameLabel2);

		CustomButton backButton = new CustomButton("Retour");
		backButton.setBackground(Main.getDarkBlue());
		backButton.setForeground(Main.getLightBlue());
		backButton.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(authorsPanel);
				frame.getContentPane().add(menuPanel);
				frame.revalidate();
				frame.repaint();
			}
		});
		authorsPanel.add(backButton);

		frame.getContentPane().remove(menuPanel);
		frame.getContentPane().add(authorsPanel);
		frame.revalidate();
		frame.repaint();
	}
}
