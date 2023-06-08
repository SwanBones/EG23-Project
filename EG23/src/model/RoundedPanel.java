package model;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
    private static final int ARC_WIDTH = 40; // Adjust the arc width as needed
    private static final int ARC_HEIGHT = 40; // Adjust the arc height as needed

    public RoundedPanel() {
        setLayout(new BorderLayout());
    }

    public RoundedPanel(BorderLayout layout) {
        setLayout(layout);
    }

    public RoundedPanel(FlowLayout flowLayout) {
        this();
        setLayout(flowLayout);
    }
    public RoundedPanel(GridBagLayout gridBagLayout) {
        setOpaque(false); // Make the panel transparent
        setLayout(gridBagLayout); // Set the specified GridBagLayout
    }

    
	@Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
        g2d.dispose();
    }
}