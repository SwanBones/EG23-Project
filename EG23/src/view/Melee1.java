package view;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.Timer;

import model.CustomBlueButton;
import model.CustomButton;
import model.CustomLabel;
import model.CustomRedButton;
import model.CustomScrollPane;
import model.ImagePanel;
import model.Main;
import model.Student;
import model.Zones;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class Melee1 extends JFrame {
    private static final boolean SCALABLE = true;
    private static final String[] LIEUX = {"BDE", "Centre administratif", "Halle sportive", "-", "-"};
    
    //map
    ImagePanel mapImagePanel;
    String defaultMapIcon = "src/pngs/maps/melee/0.png";
    
    String mapIcon0 = "src/pngs/maps/melee/0.png";
    
    String redSoldierIcon = "src/pngs/soldierIcons/red_icon.png";
    String orangeSoldierIcon = "src/pngs/soldierIcons/orange_icon.png";
    String yellowSoldierIcon = "src/pngs/soldierIcons/yellow_icon.png";
    
    
    BufferedImage defaultMapImage = null;
	BufferedImage mapImage0 = null;

    public Melee1() {
    	initialize();
    }
    
    public void initialize() {
    	 mapIcon0 = "src/pngs/maps/melee/0.png";
    	    
    	try {
    	defaultMapImage = ImageIO.read(new File(defaultMapIcon));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	try {
        mapImage0 = ImageIO.read(new File(mapIcon0));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	Zones.reset();
    	
        setTitle("Mêlée");
        getContentPane().setBackground(Main.getBeige());
        getContentPane().setLayout(new BorderLayout());

        // Title
        CustomLabel title = new CustomLabel("Mêlée", SwingConstants.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 24));
        title.setForeground(Main.getDarkBlue());
        getContentPane().add(title, BorderLayout.NORTH);

        //Image
        mapImagePanel= new ImagePanel(defaultMapImage);
        if (SCALABLE) {
            mapImagePanel.setPreferredSize(new Dimension(500, 500));
        }
        getContentPane().add(mapImagePanel, BorderLayout.CENTER);

        
        // Left Panel Wrapper so that it looks pretty
        JPanel leftPanelWrapper = new JPanel();
        leftPanelWrapper.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        leftPanelWrapper.setBackground(Main.getBeige());
        
        
        leftPanelWrapper.setLayout(new BoxLayout(leftPanelWrapper, BoxLayout.X_AXIS));
        getContentPane().add(leftPanelWrapper, BorderLayout.WEST);
        
        JPanel zoneListPanel = new JPanel();
        leftPanelWrapper.add(zoneListPanel);
        zoneListPanel.setLayout(new GridLayout(5, 0, 100, 0));
        zoneListPanel.setPreferredSize(new Dimension(400, 500));
        zoneListPanel.setBackground(Main.getLightBeige());
        
        JPanel zonePanel1 = new JPanel();
        zoneListPanel.add(zonePanel1);
        zonePanel1.setLayout(new BorderLayout(0, 0));
        CustomLabel lblNewLabel_3 = new CustomLabel("BDE");
        zonePanel1.add(lblNewLabel_3, BorderLayout.NORTH);
        JPanel Area1 = new JPanel();
        zonePanel1.add(Area1, BorderLayout.CENTER);
        
        JPanel zonePanel2 = new JPanel();
        zoneListPanel.add(zonePanel2);
        zonePanel2.setLayout(new BorderLayout(0, 0));
        CustomLabel lblNewLabel = new CustomLabel("Quartier administratif");
        zonePanel2.add(lblNewLabel, BorderLayout.NORTH);
        JPanel Area2 = new JPanel();
        zonePanel2.add(Area2, BorderLayout.CENTER);
        
        JPanel zonePanel3 = new JPanel();
        zoneListPanel.add(zonePanel3);
        zonePanel3.setLayout(new BorderLayout(0, 0));
        CustomLabel lblHalleSportive = new CustomLabel("Halle sportive");
        zonePanel3.add(lblHalleSportive, BorderLayout.NORTH);
        JPanel Area3 = new JPanel();
        zonePanel3.add(Area3, BorderLayout.CENTER);
        
        JPanel zonePanel4 = new JPanel();
        zoneListPanel.add(zonePanel4);
        zonePanel4.setLayout(new BorderLayout(0, 0));
        CustomLabel lblBibliothque = new CustomLabel("Bibliothèque");
        zonePanel4.add(lblBibliothque, BorderLayout.NORTH);
        JPanel Area4 = new JPanel();
        zonePanel4.add(Area4, BorderLayout.CENTER);
        
        JPanel zonePanel5 = new JPanel();
        zoneListPanel.add(zonePanel5);
        zonePanel5.setLayout(new BorderLayout(0, 0));
        CustomLabel lblNewLabel_2_1 = new CustomLabel("Halles Industrielles");
        zonePanel5.add(lblNewLabel_2_1, BorderLayout.NORTH);
        JPanel Area5 = new JPanel();
        zonePanel5.add(Area5, BorderLayout.CENTER);
        
        
        // Buttons 
        List<CustomButton> blue_buttons = new ArrayList<>();
        List<CustomButton> red_buttons = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            CustomButton blue_button;
            
            String iconPath = i == 1 ? redSoldierIcon : i <= 5 ? orangeSoldierIcon : yellowSoldierIcon;
            ImageIcon icon = new ImageIcon(iconPath);
            Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            
            blue_button = new CustomBlueButton("");
            blue_button.setPreferredSize(new Dimension(50, 50));
            blue_button.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            blue_button.setIcon(new ImageIcon(image));
            blue_button.setText("Soldat " + i);
            
            blue_button.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent evt) {
            		buttonClicked(evt,blue_button);
            	}
            });

            if (i==1 || i==3 || i%5==0 ) {
            	Area3.add(blue_button);
            } else if (i%4 == 0) {
            	Area2.add(blue_button);
            } else if (i%3 == 0) {
            	Area1.add(blue_button);
            } else if (i%2 == 0) {
            	Area4.add(blue_button);
            } else {
            	Area5.add(blue_button);
            }
            blue_buttons.add(blue_button);
        }
        
        for (int i = 1; i <= 20; i++) {
            CustomButton red_button;
            
            String iconPath = i == 1 ? redSoldierIcon : i <= 5 ? orangeSoldierIcon : yellowSoldierIcon;
            ImageIcon icon = new ImageIcon(iconPath);
            Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            
            red_button = new CustomRedButton("");
            red_button.setPreferredSize(new Dimension(50, 50));
            red_button.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            red_button.setIcon(new ImageIcon(image));
            red_button.setText("Soldat " + i);  
            
            red_button.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent evt) {
            		buttonClicked(evt,red_button);
            	}
            });
            
            if (i%5 == 0) {
            	Area3.add(red_button);
            } else if (i%4 == 0) {
            	Area2.add(red_button);
            } else if (i%3 == 0) {
            	Area1.add(red_button);
            } else if (i%2 == 0) {
            	Area4.add(red_button);
            } else {
            	Area5.add(red_button);
            }
            red_buttons.add(red_button);
        }
        

        	int[] red_buttonIndices1 = {4,9,14,19};
            makeButtonsDisappear(red_buttonIndices1,0,red_buttons);
            int[] red_buttonIndices2 = {2,6,7};
            makeButtonsDisappear(red_buttonIndices2,0,red_buttons);
            int[] blue_buttonIndices1 = {4,9,19};
            makeButtonsDisappear(blue_buttonIndices1,0,blue_buttons);
            int[] blue_buttonIndices2 = {6,10};
            makeButtonsDisappear(blue_buttonIndices2,0,blue_buttons);

   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(0, 1, 0, 0));
        panel.setBackground(Main.getBeige());
        JPanel panel_1 = new JPanel();
        panel.add(panel_1);
        panel_1.setBackground(Main.getBeige());
        
        Timer timer = new Timer(9000, new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		Treve window = new Treve(1);
        		window.frame.setVisible(true);
        		dispose();
        	}
        });

        timer.setRepeats(false);
        timer.start();
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
    }
  
    private void buttonClicked(MouseEvent evt,CustomButton button) {
    	SoldierInfo soldierInfo = new SoldierInfo(button.getText());
    	
		// TODO Auto-generated method stub
		
	}


    private void makeButtonsDisappear(int[] buttonIndices, int index, List<CustomButton> buttons) {
        if (index >= 0 && index < buttonIndices.length) {
            int buttonIndex = buttonIndices[index];
            if (buttonIndex >= 0 && buttonIndex < buttons.size()) {
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttons.get(buttonIndex).setVisible(false);
                        makeButtonsDisappear(buttonIndices, index + 1, buttons); // Call the method recursively for the next button index
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }
    
    
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new Melee1());
        
    }
}
