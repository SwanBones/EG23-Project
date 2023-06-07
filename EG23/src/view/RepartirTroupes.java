package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.CustomButton;
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
    
    Color lightGray = new Color(238, 238, 238);
    
    public void refreshZonePanels() {//TEXT BASED
    	///*
    	for (int i = 0; i<5;i++) {
    		//get JPanel and remove all JLabels inside JPanel
    		JPanel panelToRefresh = dragZonesJpanel.get(i);
    		panelToRefresh.removeAll();
    		//get zone to add (use pointer?)
    		ArrayList<Student> zoneToAdd = Zones.getZoneArrayList().get(i);//iterate through the arraylist
    		for (int j = 0; j<zoneToAdd.size();j++) {
    			JLabel label = new JLabel();
    			label.setText(zoneToAdd.get(j).getName()); //change the label of the JLabel
    			panelToRefresh.add(label);//add a JLabel to the JPanel
 
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
        JLabel title = new JLabel(player+": Répartissez vos troupes", SwingConstants.CENTER);
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
        leftPanelWrapper.setBackground(new Color(217, 179, 124));
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(200, 500));
        //add(scrollPane, BorderLayout.WEST);

        // Buttons
        for (int i = 1; i <= 20; i++) {
            CustomButton button = new CustomButton("");
            button.setPreferredSize(new Dimension(50, 50));
            button.setFont(new Font("Century Gothic", Font.PLAIN, 12));

            String iconPath = i == 1 ? redSoldierIcon : i <= 5 ? orangeSoldierIcon : yellowSoldierIcon;
            ImageIcon icon = new ImageIcon(iconPath);
            Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(image));
            button.setText("Soldat " + i);
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
            

            // The rest of your code...
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
        
        JPanel zonePanel1 = new JPanel();
        zoneListPanel.add(zonePanel1);
        zonePanel1.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel_3 = new JLabel("BDE");
        zonePanel1.add(lblNewLabel_3, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere1 = new JPanel();
        
        
        zonePanel1.add(draggedButtonsHere1, BorderLayout.CENTER);
        
        JPanel zonePanel2 = new JPanel();
        zoneListPanel.add(zonePanel2);
        zonePanel2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel = new JLabel("Quartier administratif");
        zonePanel2.add(lblNewLabel, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere2 = new JPanel();
        zonePanel2.add(draggedButtonsHere2, BorderLayout.CENTER);
        
        JPanel zonePanel3 = new JPanel();
        zoneListPanel.add(zonePanel3);
        zonePanel3.setLayout(new BorderLayout(0, 0));
        
        JLabel lblHalleSportive = new JLabel("Halle sportive");
        zonePanel3.add(lblHalleSportive, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere3 = new JPanel();
        zonePanel3.add(draggedButtonsHere3, BorderLayout.CENTER);
        
        JPanel zonePanel4 = new JPanel();
        zoneListPanel.add(zonePanel4);
        zonePanel4.setLayout(new BorderLayout(0, 0));
        
        JLabel lblBibliothque = new JLabel("Bibliothèque");
        zonePanel4.add(lblBibliothque, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere4 = new JPanel();
        zonePanel4.add(draggedButtonsHere4, BorderLayout.CENTER);
        
        JPanel zonePanel5 = new JPanel();
        zoneListPanel.add(zonePanel5);
        zonePanel5.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel_2_1 = new JLabel("Halles Industrielles");
        zonePanel5.add(lblNewLabel_2_1, BorderLayout.NORTH);
        
        JPanel draggedButtonsHere5 = new JPanel();
        zonePanel5.add(draggedButtonsHere5, BorderLayout.CENTER);
        
        // Next button
        CustomButton nextButton = new CustomButton("Suivant");
        nextButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	if(player == Main.getPlayer1Name()) {

        		RepartirTroupes RepartirTroupes2 = new RepartirTroupes("red",Main.getPlayer2Name());
                dispose(); // Close the menu frame
        	}
        	else if (player == Main.getPlayer2Name()) {
        			dispose();
        		}
        	}
        });
        nextButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        getContentPane().add(nextButton, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        //zone hovering
        zonePanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mapImagePanel.setImage(mapImage1);
                draggedButtonsHere1.setBackground(Color.gray);
                zonePanel1.setBackground(Color.gray);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere1.setBackground(lightGray);
                zonePanel1.setBackground(lightGray);
            }
        });
        zonePanel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage2);
                draggedButtonsHere2.setBackground(Color.gray);
                zonePanel2.setBackground(Color.gray);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere2.setBackground(lightGray);
                zonePanel2.setBackground(lightGray);
            }
        });
        zonePanel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage3);
                draggedButtonsHere3.setBackground(Color.gray);
                zonePanel3.setBackground(Color.gray);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere3.setBackground(lightGray);
                zonePanel3.setBackground(lightGray);
            }
        });
        zonePanel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage4);
                draggedButtonsHere4.setBackground(Color.gray);
                zonePanel4.setBackground(Color.gray);  
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere4.setBackground(lightGray);
                zonePanel4.setBackground(lightGray);
            }
        });
        zonePanel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mapImagePanel.setImage(mapImage5);
                draggedButtonsHere5.setBackground(Color.gray);
                zonePanel5.setBackground(Color.gray);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	mapImagePanel.setImage(defaultMapImage);
                draggedButtonsHere5.setBackground(lightGray);
                zonePanel5.setBackground(lightGray);
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
