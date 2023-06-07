package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.GridLayout;
import java.awt.MouseInfo;

import javax.swing.JPanel;

import model.Main;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class SoldierInfo {
	//private ArrayList<soldierInfo> soldierInfoWindowList = new ArrayList();
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	private String ia;
	private int soldierNumber;
	private ArrayList<String> aiList = new ArrayList();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SoldierInfo window = new SoldierInfo("Soldat 6");
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
	public SoldierInfo(String soldierName) {
		
		initialize(soldierName);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize(String soldierName) {
		soldierNumber = soldierNumber(soldierName);
		aiList.add("Défensif");
		aiList.add("Offensif");
		aiList.add("Aléatoire");
		
		//for (int i = 0; i<soldierInfoWindowList.size();i++) {//close all existing frames
		//	soldierInfoWindowList.get(i).close();
		//}
		frame = new JFrame();
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				frame.dispose();
			}
			
		});
		
		
		frame.setBounds(100, 100, 200, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		 frame.setLocation(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y - frame.getHeight()+1);
		 
		 int frameY = frame.getLocation().y;
         int cursorY = MouseInfo.getPointerInfo().getLocation().y;

         // Check if the frame is partially or completely off the screen
         if (frameY < 0) {
             int newY = cursorY;
             if (newY + frame.getHeight() > frame.getToolkit().getScreenSize().height) {
                 // If the frame would go below the screen, adjust its position
                 newY = frame.getToolkit().getScreenSize().height - frame.getHeight();
             }
             frame.setLocation(MouseInfo.getPointerInfo().getLocation().x, newY);
         }

		    // Monitor mouse movement and adjust frame position if necessary
		    
		 
		JPanel panel = new JPanel();
		panel.setBackground(Main.getBeige());
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel(soldierName);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Main.getLightBeige());
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Rôle : Soldat Classique");
		if (soldierNumber == 1) {
			lblNewLabel_1.setText("Rôle : Maitre de guerre");
		}
		if (soldierNumber<=5 && soldierNumber>1) {
			lblNewLabel_1.setText("Rôle : Soldat d'élite");
		}
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Force : 2");
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Dextérité : 2");
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Résistance : 2");
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Constitution: 2");
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Initiative : 2");
		panel_1.add(lblNewLabel_1_4_1);
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("IA: "+ aiList.get(new Random().nextInt(3)));
		panel_1.add(lblNewLabel_1_4_1_1);
		//soldierInfoWindowList.add(this);

		frame.setUndecorated(true);

		frame.setVisible(true);
	}
	private int soldierNumber(String soldierName) {
	    String[] parts = soldierName.split(" ");
	    if (parts.length == 2) {
	        try {
	            int number = Integer.parseInt(parts[1]);
	            return number;
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid soldier name format");
	        }
	    } else {
	        System.out.println("Invalid soldier name format");
	    }
		return 0;
	}
	
	
	public void close() {
	}

}
