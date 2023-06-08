package model;

import java.awt.Color;

public class Main {
	private static String player1Name;
	private static String player2Name;
	
	private static Color beige = new Color(203, 188, 154);
	private static Color lightBeige = new Color(237, 225, 205);
	private static Color darkBlue = new Color(164, 150, 123);
	private static Color lightBlue = new Color(217, 209, 137);
		
	
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
	
}
