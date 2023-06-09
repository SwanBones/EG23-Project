package model;

import java.awt.Color;

public class Main {
	public static Color getRed() {
		return red;
	}
	public static void setRed(Color red) {
		Main.red = red;
	}
	public static Color getBlue() {
		return blue;
	}
	public static void setBlue(Color blue) {
		Main.blue = blue;
	}
	private static String player1Name;
	private static String player2Name;
	
	private static Color beige = new Color(207, 200, 178);
	private static Color lightBeige = new Color(237, 225, 205);
	private static Color darkBlue = new Color(143, 131, 107);
	private static Color lightBlue = new Color(212, 200, 182);

	private static Color gray = new Color(80, 80, 80);
	private static Color PressedGray = new Color(60, 60, 60);
		
	public static Color getPressedGray() {
		return PressedGray;
	}
	public static void setPressedGray(Color pressedGray) {
		PressedGray = pressedGray;
	}
	public static void setGray(Color gray) {
		Main.gray = gray;
	}
	private static Color red = new Color(159, 1, 1);
	private static Color pressedRed = new Color(128, 0, 0);
	
	public static Color getPressedRed() {
		return pressedRed;
	}
	public static void setPressedRed(Color pressedRed) {
		Main.pressedRed = pressedRed;
	}
	public static Color getPressedBlue() {
		return pressedBlue;
	}
	public static void setPressedBlue(Color pressedBlue) {
		Main.pressedBlue = pressedBlue;
	}
	private static Color blue = new Color(36, 66, 134);
	private static Color pressedBlue = new Color(30, 55, 111);
	
	//private static Color beige = new Color(218, 179, 124);
	//private static Color lightBeige = new Color(243, 220, 188);
	//private static Color darkBlue = new Color(73, 95, 109);
	//private static Color lightBlue = new Color(222, 241, 255);
	
	public static Color getBeige() {
		return beige;
	}
	public static void setBeige(Color beige) {
		Main.beige = beige;
	}
	public static Color getLightBeige() {
		return lightBeige;
	}
	public static void setLightBeige(Color lightBeige) {
		Main.lightBeige = lightBeige;
	}
	public static Color getDarkBlue() {
		return darkBlue;
	}
	public static void setDarkBlue(Color darkBlue) {
		Main.darkBlue = darkBlue;
	}
	public static Color getLightBlue() {
		return lightBlue;
	}
	public static void setLightBlue(Color lightBlue) {
		Main.lightBlue = lightBlue;
	}
	public static String getPlayer1Name() {
		return player1Name;
	}
	public static void setPlayer1Name(String player1Name) {
		Main.player1Name = player1Name;
	}
	public static String getPlayer2Name() {
		return player2Name;
	}
	public static void setPlayer2Name(String player2Name) {
		Main.player2Name = player2Name;
	}
	public static Color getGray() {
		// TODO Auto-generated method stub
		return gray;
	}
	
}
