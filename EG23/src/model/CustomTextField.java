package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomTextField extends JTextField {
   
	private static final int BORDER_RADIUS = 10;
	
	 public CustomTextField() {
	        setOpaque(false);
	        setBackground(Main.getLightBeige());
	        setForeground(Main.getDarkBlue());
	        setFont(new Font("Century Gothic", Font.BOLD, 14));

	        addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                setBorder(BorderFactory.createLineBorder(Main.getDarkBlue(), 1));
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                setBorder(null);
	            }
	        });
	    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, BORDER_RADIUS, BORDER_RADIUS);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint the default border
    }
}