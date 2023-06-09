package view;

import javax.imageio.ImageIO;
import javax.swing.*;

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

public class RepartirTroupes extends JFrame {
    private static final boolean SCALABLE = true;
    private static final String[] LIEUX = {"BDE", "Centre administratif", "Halle sportive", "-", "-"};

    
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
    
    String redSoldierIcon = "src/pngs/soldierIcons/red_icon.png";
    String orangeSoldierIcon = "src/pngs/soldierIcons/orange_icon.png";
    String yellowSoldierIcon = "src/pngs/soldierIcons/yellow_icon.png";
    
    
    BufferedImage defaultMapImage = null;
	BufferedImage mapImage1 = null;
	BufferedImage mapImage2 = null;
	BufferedImage mapImage3 = null;
	BufferedImage mapImage4 = null;
	BufferedImage mapImage5 = null;
    
  
    
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

    public RepartirTroupes(String color, String player) {
    	initialize(color, player);
    }
    public void initialize(String color, String player) {
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
    	Zones.reset();
    	
        setTitle("Répartissez vos troupes");
        getContentPane().setBackground(new Color(217, 179, 124));
        getContentPane().setLayout(new BorderLayout());

        // Title
        CustomLabel title = new CustomLabel(player+": Répartissez vos troupes", SwingConstants.CENTER);
        if (player.equals(Main.getPlayer1Name())){
        	title.setForeground(Main.getBlue());
        }else {
        	title.setForeground(Main.getRed());
        }
        title.setFont(new Font("Century Gothic", Font.BOLD, 24));
        
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
        scrollPane.setBackground(Main.getBeige());
        scrollPane.setBorder(null);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(200, 500));
        //add(scrollPane, BorderLayout.WEST);

        // Buttons
        for (int i = 1; i <= 15; i++) {
        	CustomButton button;
            if (player.equals(Main.getPlayer1Name())) {
            	 button = new CustomBlueButton("");
            }else {
            	button = new CustomRedButton("");
            }
            button.setPreferredSize(new Dimension(50, 50));
            button.setFont(new Font("Century Gothic", Font.PLAIN, 12));

            String iconPath = i == 1 ? redSoldierIcon : i <= 5 ? orangeSoldierIcon : yellowSoldierIcon;
            ImageIcon icon = new ImageIcon(iconPath);
            Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(image));
            if (i==1) {
            	button.setText("Maitre de Guerre");
            } else if (i<6) {
            	button.setText("Soldat élite " + (i-1));
            } else {
            	button.setText("Soldat " + (i-5));
            }
            
            
            buttonPanel.add(button);
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
        Color beforeHover = Main.getLightBeige();
        Color whileHover = Main.getLightBlue();
        draggedButtonsHere1.setBackground(beforeHover);
        draggedButtonsHere2.setBackground(beforeHover);
        draggedButtonsHere3.setBackground(beforeHover);
        draggedButtonsHere4.setBackground(beforeHover);
        draggedButtonsHere5.setBackground(beforeHover);
        zonePanel1.setBackground(beforeHover);
        zonePanel2.setBackground(beforeHover);
        zonePanel3.setBackground(beforeHover);
        zonePanel4.setBackground(beforeHover);
        zonePanel5.setBackground(beforeHover);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        //zone hovering
        zonePanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mapImagePanel.setImage(mapImage1);
                draggedButtonsHere1.setBackground(whileHover);
                zonePanel1.setBackground(whileHover);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere1.setBackground(beforeHover);
                zonePanel1.setBackground(beforeHover);
            }
        });
        zonePanel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage2);
                draggedButtonsHere2.setBackground(whileHover);
                zonePanel2.setBackground(whileHover);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere2.setBackground(beforeHover);
                zonePanel2.setBackground(beforeHover);
            }
        });
        zonePanel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage3);
                draggedButtonsHere3.setBackground(whileHover);
                zonePanel3.setBackground(whileHover);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere3.setBackground(beforeHover);
                zonePanel3.setBackground(beforeHover);
            }
        });
        zonePanel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage4);
                draggedButtonsHere4.setBackground(whileHover);
                zonePanel4.setBackground(whileHover);  
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere4.setBackground(beforeHover);
                zonePanel4.setBackground(beforeHover);
            }
        });
        zonePanel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage5);
                draggedButtonsHere5.setBackground(whileHover);
                zonePanel5.setBackground(whileHover);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere5.setBackground(beforeHover);
                zonePanel5.setBackground(beforeHover);
            }
        });

        
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
        
        CustomButton nextButton = new CustomButton("Suivant");
        nextButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (player.equals(Main.getPlayer1Name())) {
        			RepartirTroupes repartirtroupes2 = new RepartirTroupes("red",Main.getPlayer2Name());
        			dispose();
        		}else if (player.equals(Main.getPlayer2Name())) {
        			Melee1 melee = new Melee1();
        			dispose();
        		}else {
        			dispose();
        		}
        	}
        });
        panel.add(nextButton);
        if(buttonPanelIsDraggable) {
        	dragZonesJpanel.add(buttonPanel);
        }
        

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
    }
  
    
    public static void main(String[] args) {
    	Main.setPlayer1Name("j1");
    	Main.setPlayer2Name("j2");
    	SwingUtilities.invokeLater(() -> new RepartirTroupes("blue",Main.getPlayer1Name()));
        
    }
}
