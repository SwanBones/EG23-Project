package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomBlueButton extends CustomButton {
   
	public CustomBlueButton(Icon icon) {
        super(icon);
        setBackground(Main.getBlue());
        setForeground(Main.getLightBlue());
        setFont(new Font("Century Gothic", Font.PLAIN, 14));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Main.getPressedBlue());
                setForeground(Main.getDarkBlue());
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	setBackground(Main.getBlue());
                setForeground(Main.getLightBlue());
            }
        });
    }
	
    public CustomBlueButton(String text) {
        super(text);
        setBackground(Main.getBlue());
        setForeground(Main.getLightBlue());
        setFont(new Font("Century Gothic", Font.PLAIN, 14));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Main.getPressedBlue());
                setForeground(Main.getDarkBlue());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Main.getBlue());
                setForeground(Main.getLightBlue());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintComponent(g);
    }
}