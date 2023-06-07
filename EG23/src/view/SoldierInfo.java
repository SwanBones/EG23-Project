package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SoldierInfo {
	//private ArrayList<soldierInfo> soldierInfoWindowList = new ArrayList();
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SoldierInfo window = new SoldierInfo();
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
	public SoldierInfo() {
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//soldierInfoWindowList.add(this);

		//frame.setUndecorated(true);

		frame.setVisible(true);
	}
	
	
	public void close() {
		System.exit(0);
	}

}
