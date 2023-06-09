package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.CustomBlueButton;
import model.CustomButton;
import model.CustomGrayButton;
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

public class Redeployer extends JFrame {
    private static final boolean SCALABLE = true;
    private static final String[] LIEUX = {"BDE", "Centre administratif", "Halle sportive", "-", "-"};
    private JFrame frame;
    
    private boolean buttonPanelIsDraggable = false;
    private Map<CustomButton, Point> buttonOrigins = new HashMap<>();
    private Map<CustomButton, JPanel> buttonContainers = new HashMap<>();
    //private Map<JPanel, JPanel> dragZones = new HashMap<>();
    private ArrayList<JPanel> dragZonesJpanel = new ArrayList();//TODO to set
    private JPanel mapImageLabel;
    
    //map
    ImagePanel mapImagePanel;
    String defaultMapIcon = "src/pngs/maps/default.png";
    
    String mapIcon1 = "src/pngs/maps/1-gray.png";
    String mapIcon2 = "src/pngs/maps/2-gray.png";
    String mapIcon3 = "src/pngs/maps/3-gray.png";
    String mapIcon4 = "src/pngs/maps/4-gray.png";
    String mapIcon5 = "src/pngs/maps/5-gray.png";
    
    String mapIconStep1 = "src/pngs/maps/melee/1.png" ;
    String mapIconStep2 = "src/pngs/maps/melee/2.png" ;
    String mapIconStep3 = "src/pngs/maps/melee/3.png" ;
    String mapIconStep4 = "src/pngs/maps/melee/4.png";
        
    
    String redSoldierIcon = "src/pngs/soldierIcons/red_icon.png";
    String orangeSoldierIcon = "src/pngs/soldierIcons/orange_icon.png";
    String yellowSoldierIcon = "src/pngs/soldierIcons/yellow_icon.png";
    
    
    BufferedImage defaultMapImage = null;
	BufferedImage mapImage1 = null;
	BufferedImage mapImage2 = null;
	BufferedImage mapImage3 = null;
	BufferedImage mapImage4 = null;
	BufferedImage mapImage5 = null;
	
	BufferedImage mapImageStep1 = null;
	BufferedImage mapImageStep2 = null;
	BufferedImage mapImageStep3 = null;
	BufferedImage mapImageStep4 = null;
	BufferedImage mapImageStep5 = null;
    
	Color beforeHover1 = null;
    Color whileHover1 = null;
    Color beforeHover2 = null;
    Color whileHover2 = null;
    Color beforeHover3 = null;
    Color whileHover3 = null;
    Color beforeHover4 = null;
    Color whileHover4 = null;
    Color beforeHover5 = null;
    Color whileHover5 = null ;
  
    
    public void refreshZonePanels() {//TEXT BASED
    	///*
    	for (int i = 0; i<5;i++) {
    		//get JPanel and remove all CustomLabels inside JPanel
    		JPanel panelToRefresh = dragZonesJpanel.get(i);
    		panelToRefresh.removeAll();
    		//get zone to add (use pointer?)
    		ArrayList<Student> zoneToAdd = Zones.getZoneArrayList().get(i);//iterate through the arraylist
    		for (int j = 0; j<zoneToAdd.size();j++) {
    			CustomLabel label = new CustomLabel("");
    			label.setText(zoneToAdd.get(j).getName()); //change the label of the CustomLabel
    			panelToRefresh.add(label);//add a CustomLabel to the JPanel
 
    		}
    	}
    	//*/
    }
    private void buttonClicked(MouseEvent evt,CustomButton button) {
    	SoldierInfo soldierInfo = new SoldierInfo(button.getText());
		// TODO Auto-generated method stub
		
	}
    private void buttonDragged(MouseEvent evt) {
        CustomButton button = (CustomButton)evt.getSource();
        Point newLocation = SwingUtilities.convertPoint(button, evt.getPoint(), button.getParent());
        button.setLocation(button.getX() + evt.getX() - button.getWidth() / 2, button.getY() + evt.getY() - button.getHeight() / 2);
    } 

    private void buttonReleased(MouseEvent evt) {
        CustomButton button = (CustomButton)evt.getSource();
        JPanel buttonPanel = buttonContainers.get(button);
        Point buttonOrigin = buttonOrigins.get(button);

        // Get the location of the cursor on the screen
        Point cursorLocation = evt.getLocationOnScreen();

        for (JPanel panel : dragZonesJpanel) {
            Rectangle panelBounds = new Rectangle(panel.getLocationOnScreen(), panel.getSize());

            if (panelBounds.contains(cursorLocation)) {
                buttonPanel.remove(button);
                panel.add(button);
                buttonContainers.put(button, panel);
                // revalidate and repaint both containers
                buttonPanel.revalidate();
                buttonPanel.repaint();
                panel.revalidate();
                panel.repaint();
                return;
            }
        }

        // If not over any drop zone, return the button to its original position
        button.setLocation(buttonOrigin);
        this.revalidate();
        this.repaint();
    }

    public Redeployer(String color, String player,int step) {
    	initialize(color, player,step);
    }
    public void initialize(String color, String player,int step) {
    	frame = this;
    	 setExtendedState(JFrame.MAXIMIZED_BOTH);
    	 mapIcon1 = "src/pngs/maps/1-"+color+".png";
    	 mapIcon2 = "src/pngs/maps/2-"+color+".png";
    	 mapIcon3 = "src/pngs/maps/3-"+color+".png";
    	 mapIcon4 = "src/pngs/maps/4-"+color+".png";
    	 mapIcon5 = "src/pngs/maps/5-"+color+".png";
    	    
    	try {
    	defaultMapImage = ImageIO.read(new File(defaultMapIcon));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	try {
        mapImage1 = ImageIO.read(new File(mapIcon1));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	try {
        mapImage2 = ImageIO.read(new File(mapIcon2));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	try {
        mapImage3 = ImageIO.read(new File(mapIcon3));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	try {
    	mapImage4 = ImageIO.read(new File(mapIcon4));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	try {
        mapImage5 = ImageIO.read(new File(mapIcon5));
		}catch(Exception e) {
		e.printStackTrace();
		}
    	
    	
    	
    	try {
            mapImageStep1 = ImageIO.read(new File(mapIconStep1));
    		}catch(Exception e) {
    		e.printStackTrace();
    	}
    	try {
    		mapImageStep2 = ImageIO.read(new File(mapIconStep2));
    		}catch(Exception e) {
    		e.printStackTrace();
    			}
    	try {
    		mapImageStep3 = ImageIO.read(new File(mapIconStep3));
    		}catch(Exception e) {
    		e.printStackTrace();
    			}
    	try {
    		mapImageStep4 = ImageIO.read(new File(mapIconStep4));
    		}catch(Exception e) {
    		e.printStackTrace();
    			}
    	
    	
    	Zones.reset();
    	
        setTitle("Répartissez vos troupes");
        getContentPane().setBackground(new Color(217, 179, 124));
        getContentPane().setLayout(new BorderLayout());

        // Title
        CustomLabel title = new CustomLabel(player+": Redéployez vos troupes", SwingConstants.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 24));
        if (player.equals(Main.getPlayer1Name())){
        	title.setForeground(Main.getBlue());
        }else {
        	title.setForeground(Main.getRed());
        }
        
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
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        
        
        buttonPanel.setBackground(Main.getBeige());
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        CustomScrollPane scrollPane = new CustomScrollPane(buttonPanel);
        
        CustomLabel lblNewLabel_1 = new CustomLabel("Réservistes");
        lblNewLabel_1.setForeground(Main.getDarkBlue());
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(lblNewLabel_1);
        scrollPane.setBackground(Main.getBeige());
        scrollPane.setBorder(null);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(200, 500));
        //add(scrollPane, BorderLayout.WEST);

        
        leftPanelWrapper.setLayout(new BoxLayout(leftPanelWrapper, BoxLayout.X_AXIS));
        
        //Add Scroll Pane to the Left Panel Wrapper
        leftPanelWrapper.add(scrollPane);
        // Add Left Panel Wrapper to the main layout
        getContentPane().add(leftPanelWrapper, BorderLayout.WEST);
        
        JPanel zoneListPanel = new JPanel();
        leftPanelWrapper.add(zoneListPanel);
        zoneListPanel.setLayout(new GridLayout(5, 0, 100, 0));
        zoneListPanel.setPreferredSize(new Dimension(200, 500));
        zoneListPanel.setBackground(Main.getLightBeige());
        
        JPanel zonePanel1 = new JPanel();
        zoneListPanel.add(zonePanel1);
        zonePanel1.setLayout(new BorderLayout(0, 0));
        
        CustomLabel lblNewLabel_3 = new CustomLabel("BDE");
        zonePanel1.add(lblNewLabel_3, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere1 = new JPanel();
        
        
        zonePanel1.add(draggedButtonsHere1, BorderLayout.CENTER);
        
        JPanel zonePanel2 = new JPanel();
        zoneListPanel.add(zonePanel2);
        zonePanel2.setLayout(new BorderLayout(0, 0));
        
        CustomLabel lblNewLabel = new CustomLabel("Quartier administratif");
        zonePanel2.add(lblNewLabel, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere2 = new JPanel();
        zonePanel2.add(draggedButtonsHere2, BorderLayout.CENTER);
        
        JPanel zonePanel3 = new JPanel();
        zoneListPanel.add(zonePanel3);
        zonePanel3.setLayout(new BorderLayout(0, 0));
        
        CustomLabel lblHalleSportive = new CustomLabel("Halle sportive");
        zonePanel3.add(lblHalleSportive, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere3 = new JPanel();
        zonePanel3.add(draggedButtonsHere3, BorderLayout.CENTER);
        
        JPanel zonePanel4 = new JPanel();
        zoneListPanel.add(zonePanel4);
        zonePanel4.setLayout(new BorderLayout(0, 0));
        
        CustomLabel lblBibliothque = new CustomLabel("Bibliothèque");
        zonePanel4.add(lblBibliothque, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere4 = new JPanel();
        zonePanel4.add(draggedButtonsHere4, BorderLayout.CENTER);
        
        JPanel zonePanel5 = new JPanel();
        zoneListPanel.add(zonePanel5);
        zonePanel5.setLayout(new BorderLayout(0, 0));
        
        CustomLabel lblNewLabel_2_1 = new CustomLabel("Halles Industrielles");
        zonePanel5.add(lblNewLabel_2_1, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere5 = new JPanel();
        zonePanel5.add(draggedButtonsHere5, BorderLayout.CENTER);
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        
        
        // The rest of your code...
     // Define the dragZones
        dragZonesJpanel.add(draggedButtonsHere1);
        draggedButtonsHere1.setLayout(new GridLayout(1, 0, 0, 0));
        dragZonesJpanel.add(draggedButtonsHere2);
        draggedButtonsHere2.setLayout(new GridLayout(1, 0, 0, 0));
        dragZonesJpanel.add(draggedButtonsHere3);
        draggedButtonsHere3.setLayout(new GridLayout(1, 0, 0, 0));
        dragZonesJpanel.add(draggedButtonsHere4);
        draggedButtonsHere4.setLayout(new GridLayout(1, 0, 0, 0));
        dragZonesJpanel.add(draggedButtonsHere5);
        draggedButtonsHere5.setLayout(new GridLayout(1, 0, 0, 0));
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(0, 1, 0, 0));
        panel.setBackground(Main.getBeige());
        JPanel panel_1 = new JPanel();
        panel.add(panel_1);
        panel_1.setBackground(Main.getBeige());
        
        
        //Keeping info on buttons
        /*
        Component[] draggedButtons = new Component[5];
        
        JPanel Buttons1 = buttonContainers.get(draggedButtonsHere1);
        JPanel Buttons2 = buttonContainers.get(draggedButtonsHere2);
        JPanel Buttons3 = buttonContainers.get(draggedButtonsHere3);
        JPanel Buttons4 = buttonContainers.get(draggedButtonsHere4);
        JPanel Buttons5 = buttonContainers.get(draggedButtonsHere5);
        
        draggedButtons[0] = Buttons1;
        draggedButtons[1] = Buttons2;
        draggedButtons[2] = Buttons3;
        draggedButtons[3] = Buttons4;
        draggedButtons[4] = Buttons5;
        */
        
        CustomButton nextButton = new CustomButton("");
        if (player.equals(Main.getPlayer1Name())){
        	nextButton.setText("Joueur 2 ("+Main.getPlayer2Name()+") : Déployez vos troupes");
        }else {
        	nextButton.setText("Continuer");
        }
        
        nextButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (player.equals(Main.getPlayer1Name())) {
        			Redeployer repartirtroupes2 = new Redeployer("red",Main.getPlayer2Name(),step);
        			dispose();
        		}else{
        			switch (step) {
        			case 1:
        				Melee2 window2 = new Melee2();
            			window2.frame.setVisible(true);
						frame.dispose();
        			break;
        			case 2:
        				Melee3 window3 = new Melee3();
            			window3.frame.setVisible(true);
						frame.dispose();
            		break;
        			case 3:
        				Melee4 window4 = new Melee4();
            			window4.frame.setVisible(true);
						frame.dispose();
            		break;
        			}
        			
        		}
        	}
        });
        panel.add(nextButton);
        if(buttonPanelIsDraggable) {
        	dragZonesJpanel.add(buttonPanel);
        }
                
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        try {
        	defaultMapImage = ImageIO.read(new File(defaultMapIcon));
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        
        switch (step) {
	    case 1:
			//playerLabel.setForeground(Main.getBlue());
	    	
	    	
	    	if (player.equals(Main.getPlayer1Name())) {
//RESERVISTES
	        	
	        
	        newSoldierButton("blue",5,buttonPanel);
	        newSoldierButton("blue",6,buttonPanel);
	        newSoldierButton("blue",7,buttonPanel);
	        newSoldierButton("blue",8,buttonPanel);
	        newSoldierButton("blue",9,buttonPanel);
//SOLDIERS ON THE MAP	        
	        newUndraggableSoldierButton("blue",10,draggedButtonsHere1);
	        newUndraggableSoldierButton("blue",11,draggedButtonsHere1);
	        newUndraggableSoldierButton("blue",12,draggedButtonsHere1);
	        
	        newUndraggableSoldierButton("blue",2,draggedButtonsHere2);
	        newUndraggableSoldierButton("blue",13,draggedButtonsHere2);
	        newUndraggableSoldierButton("blue",14,draggedButtonsHere2);
	        newUndraggableSoldierButton("blue",15,draggedButtonsHere2);
	        
	        newSoldierButton("blue",1,draggedButtonsHere3);
	        newSoldierButton("blue",3,draggedButtonsHere3);
	        newSoldierButton("blue",16,draggedButtonsHere3);
	        
	        newUndraggableSoldierButton("blue",4,draggedButtonsHere4);
	        newUndraggableSoldierButton("blue",17,draggedButtonsHere4);
	        
	        newUndraggableSoldierButton("blue",18,draggedButtonsHere5);
	        newUndraggableSoldierButton("blue",19,draggedButtonsHere5);
	        newUndraggableSoldierButton("blue",20,draggedButtonsHere5);
	        
	    	}else {
//RESERVISTES
	    		
	    	newSoldierButton("red",5,buttonPanel);
	    	newSoldierButton("red",6,buttonPanel);
	    	newSoldierButton("red",7,buttonPanel);
	    	newSoldierButton("red",8,buttonPanel);
	    	newSoldierButton("red",9,buttonPanel);
//SOLDIERS ON THE MAP	    	
	    	newUndraggableSoldierButton("red",10,draggedButtonsHere1);
	        newUndraggableSoldierButton("red",11,draggedButtonsHere1);
	        newUndraggableSoldierButton("red",12,draggedButtonsHere1);
	        
	        newUndraggableSoldierButton("red",2,draggedButtonsHere2);
	        newUndraggableSoldierButton("red",13,draggedButtonsHere2);
	        newUndraggableSoldierButton("red",14,draggedButtonsHere2);
	        
	        newUndraggableSoldierButton("red",3,draggedButtonsHere4);
	        newUndraggableSoldierButton("red",15,draggedButtonsHere4);
	        
	        newUndraggableSoldierButton("red",1,draggedButtonsHere5);
	        newUndraggableSoldierButton("red",16,draggedButtonsHere5);
	        newUndraggableSoldierButton("red",17,draggedButtonsHere5);
	        newUndraggableSoldierButton("red",18,draggedButtonsHere5);
	        newUndraggableSoldierButton("red",19,draggedButtonsHere5);
	    		
	    		
	    	}
	    	
	    	
	    	defaultMapImage = mapImageStep1;
	    	 beforeHover1 = Main.getGray();
	         whileHover1 = Main.getPressedGray();
	         beforeHover2 = Main.getGray();
	         whileHover2 = Main.getPressedGray();
	         beforeHover3 = Main.getBlue();
	         whileHover3 = Main.getPressedBlue();
	         beforeHover4 = Main.getGray();
	         whileHover4 = Main.getPressedGray();
	         beforeHover5 = Main.getGray();
	         whileHover5 = Main.getPressedGray();
	        
			
	        break;
	    case 2:
	    	
	    	if (player.equals(Main.getPlayer1Name())) {
//RESERVISTES
	    		newSoldierButton("blue",5,buttonPanel);
		        newSoldierButton("blue",6,buttonPanel);
		        newSoldierButton("blue",7,buttonPanel);
		        newSoldierButton("blue",8,buttonPanel);
		        newSoldierButton("blue",9,buttonPanel);
//SOLDIERS ON THE MAP		        
		        newUndraggableSoldierButton("blue",11,draggedButtonsHere1);
		        newUndraggableSoldierButton("blue",12,draggedButtonsHere1);
		        
		        newUndraggableSoldierButton("blue",13,draggedButtonsHere2);
		        newUndraggableSoldierButton("blue",14,draggedButtonsHere2);
		        newUndraggableSoldierButton("blue",15,draggedButtonsHere2);
		        
		        newSoldierButton("blue",1,draggedButtonsHere3);
		        newSoldierButton("blue",3,draggedButtonsHere3);
		        newSoldierButton("blue",16,draggedButtonsHere3);
		        
		        newUndraggableSoldierButton("blue",4,draggedButtonsHere4);
		        newUndraggableSoldierButton("blue",17,draggedButtonsHere4);
		        
		        
	    	}else {
//RESERVISTES
	    		newSoldierButton("red",5,buttonPanel);
		    	newSoldierButton("red",6,buttonPanel);
		    	newSoldierButton("red",7,buttonPanel);
		    	newSoldierButton("red",8,buttonPanel);
		    	newSoldierButton("red",9,buttonPanel);
//SOLDIERS ON THE MAP
		    	newUndraggableSoldierButton("red",10,draggedButtonsHere1);
		        newUndraggableSoldierButton("red",12,draggedButtonsHere1);
		        
		        newUndraggableSoldierButton("red",14,draggedButtonsHere2);
		        
		        newUndraggableSoldierButton("red",3,draggedButtonsHere4);
		        newUndraggableSoldierButton("red",15,draggedButtonsHere4);
		        
		        newSoldierButton("red",18,draggedButtonsHere5);
		        newSoldierButton("red",19,draggedButtonsHere5);
	    	}
	    	
	    	//playerLabel.setForeground(Main.getRed());
	    	defaultMapImage = mapImageStep2;
	    	beforeHover1 = Main.getGray();
	        whileHover1 = Main.getPressedGray();
	        beforeHover2 = Main.getGray();
	        whileHover2 = Main.getPressedGray();
	        beforeHover3 = Main.getBlue();
	        whileHover3 = Main.getPressedBlue();
	        beforeHover4 = Main.getGray();
	        whileHover4 = Main.getPressedGray();
	        beforeHover5 = Main.getRed();
	        whileHover5 = Main.getPressedRed();
	    
	        break;
	    case 3:
	    	if (player.equals(Main.getPlayer1Name())) {
//RESERVISTES
	    		newSoldierButton("blue",5,buttonPanel);
		        newSoldierButton("blue",6,buttonPanel);
		        newSoldierButton("blue",7,buttonPanel);
		        newSoldierButton("blue",8,buttonPanel);
		        newSoldierButton("blue",9,buttonPanel);
//SOLDIERS ON THE MAP		        
		        
		       
		        newUndraggableSoldierButton("blue",12,draggedButtonsHere1);
		        
		        
		        newSoldierButton("blue",14,draggedButtonsHere2);
		        newSoldierButton("blue",15,draggedButtonsHere2);
		        
		        newSoldierButton("blue",1,draggedButtonsHere3);
		        newSoldierButton("blue",3,draggedButtonsHere3);
		        newSoldierButton("blue",16,draggedButtonsHere3);
		        
		        newUndraggableSoldierButton("blue",4,draggedButtonsHere4);
	    	}else {
//RESERVISTES
	    		newSoldierButton("red",5,buttonPanel);
		    	newSoldierButton("red",6,buttonPanel);
		    	newSoldierButton("red",7,buttonPanel);
		    	newSoldierButton("red",8,buttonPanel);
		    	newSoldierButton("red",9,buttonPanel);
//SOLDIERS ON THE MAP		    	
		    	
		        newUndraggableSoldierButton("red",12,draggedButtonsHere1);
		        
		        newUndraggableSoldierButton("red",3,draggedButtonsHere4);
		        newUndraggableSoldierButton("red",15,draggedButtonsHere4);
		        
		        newSoldierButton("red",18,draggedButtonsHere5);
		        newSoldierButton("red",19,draggedButtonsHere5);
	    	}
	    	
	    	//playerLabel.setForeground(Main.getBlue());
	    	defaultMapImage = mapImageStep2;
	    	beforeHover1 = Main.getGray();
	        whileHover1 = Main.getPressedGray();
	        beforeHover2 = Main.getBlue();
	        whileHover2 = Main.getPressedBlue();
	        beforeHover3 = Main.getBlue();
	        whileHover3 = Main.getPressedBlue();
	        beforeHover4 = Main.getGray();
	        whileHover4 = Main.getPressedGray();
	        beforeHover5 = Main.getRed();
	        whileHover5 = Main.getPressedRed();
	    	
	        break;
	    case 4:
	    	if (player.equals(Main.getPlayer1Name())) {
	    		
	    	}else {
	    		
	    	}
	    	
	    	//playerLabel.setForeground(Main.getBlue());
	    	defaultMapImage = mapImageStep4;
	    	beforeHover1 = Main.getBlue();
	        whileHover1 = Main.getPressedBlue();
	        beforeHover2 = Main.getBlue();
	        whileHover2 = Main.getPressedBlue();
	        beforeHover3 = Main.getBlue();
	        whileHover3 = Main.getPressedBlue();
	        beforeHover4 = Main.getGray();
	        whileHover4 = Main.getPressedGray();
	        beforeHover5 = Main.getRed();
	        whileHover5 = Main.getPressedRed();
	    	
	    	
	        break;
		}
        zonePanel1.setBackground(beforeHover1);
        zonePanel2.setBackground(beforeHover2);
		zonePanel3.setBackground(beforeHover3);
		zonePanel4.setBackground(beforeHover4);
		zonePanel5.setBackground(beforeHover5);
		draggedButtonsHere1.setBackground(beforeHover1);
        draggedButtonsHere2.setBackground(beforeHover2);
        draggedButtonsHere3.setBackground(beforeHover3);
        draggedButtonsHere4.setBackground(beforeHover4);
        draggedButtonsHere5.setBackground(beforeHover5);
        
        mapImagePanel.setImage(defaultMapImage);
      //zone hovering
        zonePanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mapImagePanel.setImage(mapImage1);
                draggedButtonsHere1.setBackground(whileHover1);
                zonePanel1.setBackground(whileHover1);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere1.setBackground(beforeHover1);
                zonePanel1.setBackground(beforeHover1);
            }
        });
        zonePanel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage2);
                draggedButtonsHere2.setBackground(whileHover2);
                zonePanel2.setBackground(whileHover2);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere2.setBackground(beforeHover2);
                zonePanel2.setBackground(beforeHover2);
            }
        });
        zonePanel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage3);
                draggedButtonsHere3.setBackground(whileHover3);
                zonePanel3.setBackground(whileHover3);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere3.setBackground(beforeHover3);
                zonePanel3.setBackground(beforeHover3);
            }
        });
        zonePanel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage4);
                draggedButtonsHere4.setBackground(whileHover4);
                zonePanel4.setBackground(whileHover4);  
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere4.setBackground(beforeHover4);
                zonePanel4.setBackground(beforeHover4);
            }
        });
        zonePanel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage5);
                draggedButtonsHere5.setBackground(whileHover5);
                zonePanel5.setBackground(whileHover5);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere5.setBackground(beforeHover5);
                zonePanel5.setBackground(beforeHover5);
            }
        });
        
        
    }
  public void newSoldierButton(String color,int soldierNumber,JPanel buttonPanel) {
	// Buttons
      
      	CustomButton button;
          if (color.equals("blue")) {
          	 button = new CustomBlueButton("");
          }else {
          	button = new CustomRedButton("");
          }
          button.setPreferredSize(new Dimension(50, 50));
          button.setFont(new Font("Century Gothic", Font.PLAIN, 12));
          String iconPath = soldierNumber == 1 ? redSoldierIcon : soldierNumber <= 5 ? orangeSoldierIcon : yellowSoldierIcon;
          ImageIcon icon = new ImageIcon(iconPath);
          Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
          button.setIcon(new ImageIcon(image));
          button.setText("Soldat " + soldierNumber);
          buttonPanel.add(button);
          
          //dragging
          button.setAlignmentX(Component.CENTER_ALIGNMENT);
          buttonOrigins.put(button, button.getLocation());
          buttonContainers.put(button, buttonPanel);
          
          button.addMouseListener(new MouseAdapter() {
          	@Override
          	public void mouseClicked(MouseEvent evt) {
          		buttonClicked(evt,button);
          	}
          });
          button.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
              public void mouseDragged(java.awt.event.MouseEvent evt) {
                  buttonDragged(evt);
              }
          });
          button.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseReleased(java.awt.event.MouseEvent evt) {
                  buttonReleased(evt);
              }
          });
       
  }
  
  public void newUndraggableSoldierButton(String color,int soldierNumber,JPanel buttonPanel) {
		// Buttons
	      
	      	CustomGrayButton button = new CustomGrayButton("");
	      	
	          button.setPreferredSize(new Dimension(50, 50));
	          button.setFont(new Font("Century Gothic", Font.PLAIN, 12));
	          String iconPath = soldierNumber == 1 ? redSoldierIcon : soldierNumber <= 5 ? orangeSoldierIcon : yellowSoldierIcon;
	          ImageIcon icon = new ImageIcon(iconPath);
	          Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	          button.setIcon(new ImageIcon(image));
	          button.setText("Soldat " + soldierNumber);
	          buttonPanel.add(button);
	          
	          //dragging
	          button.setAlignmentX(Component.CENTER_ALIGNMENT);
	          buttonOrigins.put(button, button.getLocation());
	          buttonContainers.put(button, buttonPanel);
	          
	          button.addMouseListener(new MouseAdapter() {
	          	@Override
	          	public void mouseClicked(MouseEvent evt) {
	          		buttonClicked(evt,button);
	          	}
	          });
	         
	  }
    
    public static void main(String[] args) {
    	Main.setPlayer1Name("j1");
    	Main.setPlayer2Name("j2");
    	SwingUtilities.invokeLater(() -> new Redeployer("blue",Main.getPlayer1Name(), 2));
        
    }
}
