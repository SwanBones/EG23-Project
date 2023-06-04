package view;

import java.awt.EventQueue;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;

public class Menu {

	private JFrame frame;
	private JPanel menuPanel;
	private JPanel authorsPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(218, 179, 124));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuPanel = new JPanel();
        menuPanel.setBackground(new Color(218, 179, 124));
        menuPanel.setLayout(new GridLayout(4, 1, 0, 10));
		
		JLabel lblNewLabel = new JLabel("La Bataille des Programmes");
		lblNewLabel.setBackground(new Color(222, 241, 255));
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Jouer");
		btnNewButton.setBackground(new Color(73, 95, 109));
		btnNewButton.setForeground(new Color(222, 241, 255));
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		menuPanel.add(btnNewButton);
		
		// Action listener for the "Jouer" button
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoldierPage soldierPage = new SoldierPage();
                soldierPage.initialize();
                frame.dispose(); // Close the menu frame
            }
        });
		
		JButton btnNewButton_1 = new JButton("Règles");
		btnNewButton_1.setBackground(new Color(73, 95, 109));
		btnNewButton_1.setForeground(new Color(222, 241, 255));
		btnNewButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Auteurs");
		btnNewButton_2.setForeground(new Color(222, 241, 255));
		btnNewButton_2.setBackground(new Color(73, 95, 109));
		btnNewButton_2.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAuthorsPage();
            }
        });
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		menuPanel.add(btnNewButton_2);
		
		frame.getContentPane().add(menuPanel, BorderLayout.CENTER);
		
		JPanel panel_north = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_north.getLayout();
		flowLayout_2.setVgap(20);
		panel_north.setBorder(null);
		panel_north.setBackground(new Color(218, 179, 124));
		frame.getContentPane().add(panel_north, BorderLayout.NORTH);
		
		JPanel panel_south = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_south.getLayout();
		flowLayout.setVgap(20);
		panel_south.setBorder(null);
		panel_south.setForeground(new Color(0, 0, 0));
		panel_south.setBackground(new Color(218, 179, 124));
		frame.getContentPane().add(panel_south, BorderLayout.SOUTH);
		
		JPanel panel_west = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_west.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setVgap(0);
		panel_west.setBackground(new Color(218, 179, 124));
		panel_west.setBorder(null);
		frame.getContentPane().add(panel_west, BorderLayout.WEST);
		
		JPanel panel_east = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_east.getLayout();
		flowLayout_3.setHgap(20);
		panel_east.setBackground(new Color(218, 179, 124));
		panel_east.setBorder(null);
		frame.getContentPane().add(panel_east, BorderLayout.EAST);
	}

	private void showAuthorsPage() {
		authorsPanel = new JPanel();
        authorsPanel.setBackground(new Color(218, 179, 124));
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

        JButton backButton = new JButton("Retour");
        backButton.setBackground(new Color(73, 95, 109));
        backButton.setForeground(new Color(222, 241, 255));
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