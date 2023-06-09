package model;

import javax.swing.*;
import java.awt.*;

public class CustomRadioButton extends JRadioButton {
    private static final Color BACKGROUND_COLOR = Main.getLightBeige();
    private static final Color FONT_COLOR = Main.getDarkBlue();
    private static final Color RADIO_CIRCLE_COLOR = Main.getBeige();
    private static final Color RADIO_DOT_COLOR = Main.getDarkBlue();
    
    public CustomRadioButton(String text) {
        super(text);
        init();
    }
    
    private void init() {
        setBackground(BACKGROUND_COLOR);
        setForeground(FONT_COLOR);
        setFont(new Font("Century Gothic", Font.PLAIN, 12));
        setUI(new CustomRadioButtonUI());
    }
    
    private class CustomRadioButtonUI extends javax.swing.plaf.basic.BasicRadioButtonUI {
        
       
    
        @Override
        protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
            // Override the default icon painting
            ButtonModel model = ((AbstractButton) c).getModel();
            if (model.isSelected()) {
                int dotDiameter = 8;
                int dotX = iconRect.x + (iconRect.width - dotDiameter) / 2;
                int dotY = iconRect.y + (iconRect.height - dotDiameter) / 2;
                g.setColor(RADIO_DOT_COLOR);
                g.fillOval(dotX, dotY, dotDiameter, dotDiameter);
            }
        }
    
        @Override
        protected void paintFocus(Graphics g, Rectangle x, Dimension y) {
            // Do not paint focus indicator
        }
    
        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton button = (AbstractButton) c;
            ButtonModel model = button.getModel();
    
            if (button.isOpaque()) {
                g.setColor(BACKGROUND_COLOR);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
    
            if (model.isSelected()) {
                g.setColor(RADIO_CIRCLE_COLOR);
                int circleDiameter = Math.min(c.getWidth(), c.getHeight()) - 4;
                int circleX = (c.getWidth() - circleDiameter) / 2;
                int circleY = (c.getHeight() - circleDiameter) / 2;
                g.fillOval(circleX, circleY, circleDiameter, circleDiameter);
            }
    
            super.paint(g, c);
        }
    }
}