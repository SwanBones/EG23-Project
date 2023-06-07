package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private BufferedImage image;
    private double aspectRatio;

    public ImagePanel(BufferedImage img) {
        setImage(img);
    }

    public void setImage(BufferedImage img) {
        this.image = img;
        aspectRatio = (double)img.getWidth(null)/(double)img.getHeight(null);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Calculate the appropriate size and position for the image to keep its aspect ratio.
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();

        double panelAspectRatio = (double)panelWidth/(double)panelHeight;
        
        int imgWidth;
        int imgHeight;
        
        if (panelAspectRatio > aspectRatio) {
            imgHeight = panelHeight;
            imgWidth = (int)(imgHeight * aspectRatio);
        } else {
            imgWidth = panelWidth;
            imgHeight = (int)(imgWidth / aspectRatio);
        }
        
        g.drawImage(image.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), 0, 0, null);
    }
}