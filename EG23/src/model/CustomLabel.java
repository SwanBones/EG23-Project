package model;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    public CustomLabel(String text) {
        super(text);
        setForeground(Main.getDarkBlue());
        setFont(new Font("Century Gothic", Font.PLAIN, 14));
    }
    

	public CustomLabel(String string, int center) {
		super(string,center);
        setForeground(Main.getDarkBlue());
        setFont(new Font("Century Gothic", Font.PLAIN, 14));
	}}