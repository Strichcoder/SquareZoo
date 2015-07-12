package ch.strichcoder.squarezoo.Field;

import java.awt.Color;

import javax.swing.JButton;

public class Tile extends JButton{

	private static final long serialVersionUID = 1L;
	private static int tWidth=32;
	private static int tHeight=32; 
	private int width=tWidth;
	private int height=tHeight;
	
	private int x;
	private int y;
	private int xPos;
	private int yPos;
	private boolean inhabited; 
	private static Color[] buttonColors = { Color.WHITE, Color.RED, Color.BLUE, Color.GREEN }; 
	private static int[] colorValue = {     0,           20,        30,         40 };
	public JButton button;
	public int colorID; //  0=inhabited, 1,2,3
	
	public Tile(int x, int y, JButton button)
	{
		this.x=x;
		this.y=y;
		this.xPos=x*width;
		this.yPos=y*height;
		this.button = button;
		button.setBackground(buttonColors[0]);
		colorID=0;
		inhabited=false;
	}
	
	// GETTERS AND SETTERS
	
	public void setColorID(int colorID)
	{
		this.colorID=colorID;
		button.setBackground(buttonColors[colorID]);
	}
	
	public static int getTWidth() {
		return tWidth;
	}

	public static void setWidth(int width) {
		Tile.tWidth = width;
	}

	public static int getTHeight() {
		return tHeight;
	}

	public static void setHeight(int height) {
		Tile.tHeight = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isInhabited() {
		return inhabited;
	}

	public void setInhabited(boolean inhabited) {
		this.inhabited = inhabited;
	}

	public static int gettWidth() {
		return tWidth;
	}

	public static void settWidth(int tWidth) {
		Tile.tWidth = tWidth;
	}

	public static int gettHeight() {
		return tHeight;
	}

	public static void settHeight(int tHeight) {
		Tile.tHeight = tHeight;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getColorValue() {
		return colorValue;
	}

	public int getColorID() {
		return colorID;
	}

	public static Color[] getButtonColors() {
		return buttonColors;
	}
	
}
