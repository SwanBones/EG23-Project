package view;

import javax.swing.*;

import model.Student;
import model.Zones;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.event.MouseAdapter;


public class RepartirTroupes extends JFrame {
    private static final boolean SCALABLE = true;
    private static final String[] LIEUX = {"BDE", "Centre administratif", "Halle sportive", "-", "-"};

    

    private Map<JButton, Point> buttonOrigins = new HashMap<>();
    private Map<JButton, JPanel> buttonContainers = new HashMap<>();
    //private Map<JPanel, JPanel> dragZones = new HashMap<>();
    private ArrayList<JPanel> dragZonesJpanel = new ArrayList();//TODO to set
    private ImageIcon mapIcon;
    private JLabel mapImageLabel;
    
    
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
    
    private void buttonDragged(MouseEvent evt) {
        JButton button = (JButton)evt.getSource();
        Point newLocation = SwingUtilities.convertPoint(button, evt.getPoint(), button.getParent());
        button.setLocation(button.getX() + evt.getX() - button.getWidth() / 2, button.getY() + evt.getY() - button.getHeight() / 2);
    }

    private void buttonReleased(MouseEvent evt) {
        JButton button = (JButton)evt.getSource();
        JPanel buttonPanel = buttonContainers.get(button);
        Point buttonOrigin = buttonOrigins.get(button);
        
        Point buttonScreen = button.getLocationOnScreen();  // Get absolute location on screen

        for (JPanel panel : dragZonesJpanel) {
            Rectangle panelScreen = new Rectangle(panel.getLocationOnScreen(), panel.getSize());  // Get absolute panel area on screen

            if (panelScreen.contains(buttonScreen)) {
                Point buttonInPanel = SwingUtilities.convertPoint(button.getParent(), button.getLocation(), panel);  // Convert point to panel's coordinate system
                panel.add(button);
                button.setLocation(buttonInPanel);  // Set location in panel
                buttonPanel.remove(button);
                buttonContainers.put(button, panel);
                this.revalidate();
                this.repaint();
                return;
            }
        }

        // If not over any drop zone, return the button to its original position
        button.setLocation(buttonOrigin);
        this.revalidate();
        this.repaint();
    }
    public RepartirTroupes() {
    	
    	Zones.reset();
    	
        setTitle("Répartissez vos troupes");
        getContentPane().setBackground(new Color(217, 179, 124));
        getContentPane().setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Répartissez vos troupes", SwingConstants.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 24));
        title.setForeground(new Color(73, 95, 109));
        getContentPane().add(title, BorderLayout.NORTH);

        //Image
        mapIcon = new ImageIcon("src/pngs/plan1.png");
        mapImageLabel = new JLabel(mapIcon);
        if (SCALABLE) {
        	mapImageLabel.setPreferredSize(new Dimension(500, 500));
        }
        getContentPane().add(mapImageLabel, BorderLayout.CENTER);
        
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
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(50, 50));
            button.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            button.setForeground(new Color(73, 95, 109));

            String iconPath = i == 1 ? "src/pngs/red_icon.png" : i <= 5 ? "src/pngs/orange_icon.png" : "src/pngs/yellow_icon.png";
            ImageIcon icon = new ImageIcon(iconPath);
            Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(image));
            button.setText("Soldat " + i);
            buttonPanel.add(button);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonOrigins.put(button, button.getLocation());
            buttonContainers.put(button, buttonPanel);

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
        JButton nextButton = new JButton("Suivant");
        nextButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        nextButton.setForeground(new Color(73, 95, 109));
        getContentPane().add(nextButton, BorderLayout.SOUTH);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.EAST);
        panel.setLayout(new GridLayout(1, 0, 0, 0));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        //zone hovering
        draggedButtonsHere1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mapIcon = new ImageIcon("src/pngs/1.png");
                mapImageLabel.setIcon(mapIcon);
                draggedButtonsHere1.setBackground(Color.gray);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mapIcon = new ImageIcon("src/pngs/plan1.png");
                mapImageLabel.setIcon(mapIcon);
                draggedButtonsHere1.setBackground(new Color(238, 238, 238));
            }
        });
        draggedButtonsHere2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mapIcon = new ImageIcon("src/pngs/2.png");
                mapImageLabel.setIcon(mapIcon);
                draggedButtonsHere2.setBackground(Color.gray);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mapIcon = new ImageIcon("src/pngs/plan1.png");
                mapImageLabel.setIcon(mapIcon);
                draggedButtonsHere2.setBackground(new Color(238, 238, 238));
            }
        });
        draggedButtonsHere3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mapIcon = new ImageIcon("src/pngs/3.png");
                mapImageLabel.setIcon(mapIcon);
                draggedButtonsHere3.setBackground(Color.gray);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mapIcon = new ImageIcon("src/pngs/plan.png");
                mapImageLabel.setIcon(mapIcon);
                draggedButtonsHere3.setBackground(new Color(238, 238, 238));
            }
        });
        draggedButtonsHere4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mapIcon = new ImageIcon("src/pngs/4.png");
                mapImageLabel.setIcon(mapIcon);
                draggedButtonsHere4.setBackground(Color.gray);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mapIcon = new ImageIcon("src/pngs/plan1.png");
                mapImageLabel.setIcon(mapIcon);
                draggedButtonsHere4.setBackground(new Color(238, 238, 238));
            }
        });
        draggedButtonsHere5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mapIcon = new ImageIcon("src/pngs/5.png");
                mapImageLabel.setIcon(mapIcon);
                draggedButtonsHere5.setBackground(Color.gray);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mapIcon = new ImageIcon("src/pngs/plan1.png");
                mapImageLabel.setIcon(mapIcon);
                draggedButtonsHere5.setBackground(new Color(238, 238, 238));
            }
        });
        // The rest of your code...
     // Define the dragZones
        dragZonesJpanel.add(draggedButtonsHere1);
        dragZonesJpanel.add(draggedButtonsHere2);
        dragZonesJpanel.add(draggedButtonsHere3);
        dragZonesJpanel.add(draggedButtonsHere4);
        dragZonesJpanel.add(draggedButtonsHere5);
        
        GridBagLayout gbl_draggedButtonsHere1 = new GridBagLayout();
        gbl_draggedButtonsHere1.columnWidths = new int[]{0};
        gbl_draggedButtonsHere1.rowHeights = new int[]{0};
        gbl_draggedButtonsHere1.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_draggedButtonsHere1.rowWeights = new double[]{Double.MIN_VALUE};
        draggedButtonsHere1.setLayout(gbl_draggedButtonsHere1);
       
        GridBagLayout gbl_draggedButtonsHere2 = new GridBagLayout();
        gbl_draggedButtonsHere2.columnWidths = new int[]{0};
        gbl_draggedButtonsHere2.rowHeights = new int[]{0};
        gbl_draggedButtonsHere2.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_draggedButtonsHere2.rowWeights = new double[]{Double.MIN_VALUE};
        draggedButtonsHere2.setLayout(gbl_draggedButtonsHere2);
        
        GridBagLayout gbl_draggedButtonsHere3 = new GridBagLayout();
        gbl_draggedButtonsHere3.columnWidths = new int[]{0};
        gbl_draggedButtonsHere3.rowHeights = new int[]{0};
        gbl_draggedButtonsHere3.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_draggedButtonsHere3.rowWeights = new double[]{Double.MIN_VALUE};
        draggedButtonsHere3.setLayout(gbl_draggedButtonsHere3);
        
        GridBagLayout gbl_draggedButtonsHere4 = new GridBagLayout();
        gbl_draggedButtonsHere4.columnWidths = new int[]{0};
        gbl_draggedButtonsHere4.rowHeights = new int[]{0};
        gbl_draggedButtonsHere4.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_draggedButtonsHere4.rowWeights = new double[]{Double.MIN_VALUE};
        draggedButtonsHere4.setLayout(gbl_draggedButtonsHere4);
        
        GridBagLayout gbl_draggedButtonsHere5 = new GridBagLayout();
        gbl_draggedButtonsHere5.columnWidths = new int[]{0};
        gbl_draggedButtonsHere5.rowHeights = new int[]{0};
        gbl_draggedButtonsHere5.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_draggedButtonsHere5.rowWeights = new double[]{Double.MIN_VALUE};
        draggedButtonsHere5.setLayout(gbl_draggedButtonsHere5);
        
    }
  
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RepartirTroupes::new);
        
    }
}
