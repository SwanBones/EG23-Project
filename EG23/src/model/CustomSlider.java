package model;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class CustomSlider extends JSlider {
    private static final Color TRACK_COLOR = Main.getLightBeige();
    private static final Color THUMB_COLOR = Main.getDarkBlue();
    private static final Color TICK_COLOR = Color.YELLOW;
    private static final Font FONT = new Font("Century Gothic", Font.PLAIN, 12);

    public CustomSlider(int min, int max, int value) {
        super(min, max, value);
        setUI(new CustomSliderUI(this));
        setForeground(THUMB_COLOR);
        setBackground(TRACK_COLOR);
        setFont(FONT);
        disableFocusUI();
    }
    @Override
    public void updateUI() {
        setUI(new CustomSliderUI(null));
    }

    public CustomSlider(int orientation, int min, int max, int value) {
        super(orientation, min, max, value);
        setUI(new CustomSliderUI(this));
        setForeground(THUMB_COLOR);
        setBackground(TRACK_COLOR);
        setFont(FONT);
    }
    
    private void disableFocusUI() {
        UIManager.put("Slider.focus", UIManager.get("Slider.background"));
    }
    
    public class CustomSliderUI extends BasicSliderUI {
        public CustomSliderUI(JSlider slider) {
            super(slider);
        }

        protected Color getThumbColor() {
            return THUMB_COLOR;
        }

        protected Color getTrackColor() {
            return TRACK_COLOR;
        }

        protected Color getTickColor() {
            return TICK_COLOR;
        }
        @Override
		public void paintFocus(Graphics g) {
            // Do nothing to remove the dotted lines
        }
    }
}
