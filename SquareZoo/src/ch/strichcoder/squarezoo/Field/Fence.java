package ch.strichcoder.squarezoo.Field;

import java.awt.Color;
import javax.swing.JButton;

public class Fence extends JButton{

	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	public JButton button;
	public static int thickness=5;
	boolean open;
	public static int cost=20; // to purchase
	public static int value=10; // to sell
	public static int trapCost=10; // to upgrade to trap
	private boolean trapped;
	public static Color trappedColor = Color.blue;
	public static Color fenceColor = Color.black;
	
	public Fence(int x, int y, JButton button)
	{
		
		this.x=x;
		this.y=y;
		this.button=button;
		setOpen(true);
	}

	public void setOpen(boolean open)
	{
		this.open=open;
		if(open)
			button.setBackground(Tile.getButtonColors()[0]);
		else
			button.setBackground(fenceColor);
	}
	
	public boolean isOpen()
	{
		return open;
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

	public boolean isTrapped() {
		return trapped;
	}

	public void setTrapped(boolean trapped) {
		this.trapped = trapped;
		if(trapped)
			button.setBackground(trappedColor);
		else
			button.setBackground(fenceColor);
	}
}
