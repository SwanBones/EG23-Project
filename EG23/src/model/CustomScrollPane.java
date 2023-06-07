package model;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomScrollPane extends JScrollPane {


	public CustomScrollPane(Component view) {
        super(view);
        setBorder(null); // Remove border
        getViewport().setBorder(null); // Remove border from viewport
        setComponentZOrder(getVerticalScrollBar(), 0);
        setComponentZOrder(getViewport(), 1);
        getVerticalScrollBar().setOpaque(false);
        getViewport().setOpaque(false);
        SwingUtilities.invokeLater(() -> {
            getVerticalScrollBar().setUI(new CustomScrollBarUI());
            getHorizontalScrollBar().setUI(new CustomScrollBarUI());
            getVerticalScrollBar().setBackground(Main.getLightBeige());
            getHorizontalScrollBar().setBackground(Main.getLightBeige());
        });
    }

    @Override
    public void setVerticalScrollBarPolicy(int policy) {
        super.setVerticalScrollBarPolicy(policy);
        SwingUtilities.invokeLater(() -> getVerticalScrollBar().setBackground(Main.getDarkBlue()));
    }

    @Override
    public void setHorizontalScrollBarPolicy(int policy) {
        super.setHorizontalScrollBarPolicy(policy);
        SwingUtilities.invokeLater(() -> getHorizontalScrollBar().setBackground(Main.getDarkBlue()));
    
   }
    

    private class CustomScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            thumbColor = Main.getDarkBlue();
            thumbHighlightColor = Main.getLightBlue();
            trackColor = Main.getLightBeige();
            trackHighlightColor = Main.getLightBeige();
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.setColor(trackColor);
            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            g.setColor(thumbColor);
            g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        }
        protected TrackListener createTrackListener() {
            return new TrackListener() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    thumbColor = Main.getLightBlue();
                    scrollbar.repaint();
                    super.mouseEntered(e);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    thumbColor = Main.getDarkBlue();
                    scrollbar.repaint();
                    super.mouseExited(e);
                }
            };
        }
    }
    

}

