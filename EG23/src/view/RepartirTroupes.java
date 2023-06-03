package view;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.AbstractListModel;

public class RepartirTroupes {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RepartirTroupes window = new RepartirTroupes();
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
	public RepartirTroupes() {
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
		leftPanel.setBackground(new Color(73, 95, 110));
		frame.getContentPane().add(leftPanel, BorderLayout.WEST);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(218, 179, 124));
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Répartir vos troupes");
		lblNewLabel.setForeground(new Color(73, 95, 110));
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 23));
		topPanel.add(lblNewLabel);
		
		//Center Panel for map
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(new Color(218, 179, 124));
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		ImageIcon map = new ImageIcon("src/pngs/plan_utt.png");
		JLabel imageLabel = new JLabel(map);
		imageLabel.setBackground(new Color(218, 179, 124));
		centerPanel.add(imageLabel, BorderLayout.CENTER);

		
		//Bottom Panel
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(new Color(218, 179, 124));
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Suivant");
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 11));
		bottomPanel.add(btnNewButton);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}
