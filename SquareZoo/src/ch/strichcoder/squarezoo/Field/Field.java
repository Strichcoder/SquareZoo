package ch.strichcoder.squarezoo.Field;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import ch.strichcoder.squarezoo.main.Screen;

// Everything that may change upon Loading belongs here,
// everything else (e.g. Layout) belongs to Screen.java
public class Field extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static int tilexmax=8;
	private static int tileymax=8;
	private int xOffset;
	private int yOffset;

	private Random rand;
	
	private Fence[][] fencesX; // horizontal fences
	private Fence[][] fencesY;
	private Tile[][] tiles;
	
	JToggleButton sellButton;
	JTextField moneyTextField;
	
	private Fence selectedFence; // Helper fence for the opening and closing
	
	private int money;
	private final int startMoney=100;
	
	public Field()
	{
		xOffset=(Screen.WIDTH-tilexmax*Tile.getTWidth())/2;
		yOffset=(Screen.HEIGHT-tileymax*Tile.getTHeight())/2;
		
		setBounds(xOffset-Fence.thickness/2,yOffset-Fence.thickness/2,Tile.getTWidth()*tilexmax+Fence.thickness,Tile.getTHeight()*tileymax+Fence.thickness);
		setBackground(Color.WHITE);
		setOpaque(true);
		setVisible(true);
		setLayout(null);
		
		fencesX = new Fence[tilexmax][tileymax+1];
		fencesY = new Fence[tilexmax+1][tileymax];
		tiles = new Tile[tilexmax][tileymax];
	    for (int i = 0; i < tilexmax+1; i++) {
	      for (int j = 0; j < tileymax+1; j++)
	      {
	    	if(j<tileymax)
	    	{ // yGates
	    		JButton a = new JButton();
		    	fencesY[i][j] = new Fence(i,j,a);
		    	a.setBounds(i*Tile.gettWidth(),j*Tile.gettHeight()+Fence.thickness,Fence.thickness,Tile.gettHeight()-Fence.thickness);
	        	add(a);
		    	a.addActionListener(this);
	    	}
	    	if(i<tilexmax)
	    	{ // xGates
	    		JButton b = new JButton();
	    		fencesX[i][j] = new Fence(i,j,b);
	    		b.setBounds(i*Tile.gettWidth()+Fence.thickness,j*Tile.gettHeight(),Tile.gettWidth()-Fence.thickness,Fence.thickness);
	    		add(b);
	    		b.addActionListener(this);
	    	}
	    	if(j<tileymax && i<tilexmax)
	    	{ // Tiles
	    		JButton c = new JButton();
	    		tiles[i][j]= new Tile(i,j,c);
	    		c.setBounds(i*Tile.gettWidth()+Fence.thickness,j*Tile.gettHeight()+Fence.thickness,Tile.gettWidth()-Fence.thickness,Tile.gettHeight()-Fence.thickness);
	    		add(c);
	    		c.addActionListener(this);
	    	}
	      }
	    }

		rand=new Random();
	}
	
	public void spawn() // at least 1 trapped fence and fence all around.
	{
		for(int i=0;i<tiles.length;i++){
			for(int j=0;j<tiles[0].length;j++){
				if(!fencesX[i][j].isOpen() && !fencesY[i][j].isOpen() && !fencesX[i][j+1].isOpen() && !fencesY[i+1][j].isOpen())
				{ // all are closed
					int count=0; // amount of trapped fences
					if(fencesX[i][j].isTrapped())
						count++;
					if(fencesY[i][j].isTrapped()) 
						count++;
					if(fencesX[i][j+1].isTrapped())
						count++;
					if(fencesY[i+1][j].isTrapped()) 
						count++;
					if(count>0)
					{
						tiles[i][j].setColorID(Math.abs(rand.nextInt())%Tile.getButtonColors().length);
						if(tiles[i][j].getColorID()!=0)
						{ // if something actually got caught
							if(fencesX[i][j].isTrapped())
								fencesX[i][j].setTrapped(false);
							if(fencesY[i][j].isTrapped())
								fencesY[i][j].setTrapped(false);
							if(fencesX[i][j+1].isTrapped())
								fencesX[i][j+1].setTrapped(false);
							if(fencesY[i+1][j].isTrapped())
								fencesY[i+1][j].setTrapped(false);
						}
					}
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	  {
	    for (int i = 0; i < tiles.length; i++) {
	      for (int j = 0; j < tiles[0].length; j++) {
	        if (e.getSource() == tiles[i][j].button)
	        {
	          if(sellButton.isSelected()) // Sell Tile
	          {
	        	  Tile tile=tiles[i][j];
	        	  addMoney(tile.getColorValue()[tile.getColorID()]);
	        	  tile.setColorID(0);
	          }
	        }
	      }
	    }
	    for(int i=0;i<fencesX.length;i++){
	      for(int j=0;j<fencesX[0].length;j++){
	    	if(e.getSource()==fencesX[i][j].button)
	    	{
	    		fenceAction(fencesX[i][j]);
	    	}
	      }
	    }
	    for(int i=0;i<fencesY.length;i++){
		  for(int j=0;j<fencesY[0].length;j++){
		    if(e.getSource()==fencesY[i][j].button)
		    {
		    	fenceAction(fencesY[i][j]);
		    }
		  }
		}
	  }
	
	private void fenceAction(Fence fence)
	{
		if(sellButton.isSelected())
		{
			if(!fence.isOpen())
			{
				fence.setOpen(true);
				addMoney(Fence.value);
			}
		}else{ // sellButton is not selected
			if(!fence.isOpen()){ // there is a fence
				if(selectedFence==null)
				{
					if(!fence.isTrapped())
						selectedFence=fence;
				}else if(fence==selectedFence && money>=Fence.trapCost){
					addMoney(-Fence.trapCost);
					fence.setTrapped(true);
					selectedFence=null;
				}
			}else{ // fence is open
				if(selectedFence!=null) // if there is a selected Fence
				{
					if(		(fence.getX()<tilexmax-1 && selectedFence==fencesX[fence.getX()+1][fence.getY()]) ||
							(fence.getX()>0 && selectedFence==fencesX[fence.getX()-1][fence.getY()]) ||
							(fence.getY()<tileymax-1 && selectedFence==fencesY[fence.getX()][fence.getY()+1]) ||
							(fence.getY()>0 && selectedFence==fencesY[fence.getX()][fence.getY()-1]))
					{ // move fence
						fence.setOpen(false);
						selectedFence.setOpen(true);
					}
					selectedFence=null;
				}else if(money>=Fence.cost){
				addMoney(-Fence.cost);
				fence.setOpen(false);
				}
			}
		}
	}
	
	public static int getTilexmax() {
		return tilexmax;
	}

	public static void setTilexmax(int tilexmax) {
		Field.tilexmax = tilexmax;
	}

	public static int getTileymax() {
		return tileymax;
	}

	public static void setTileymax(int tileymax) {
		Field.tileymax = tileymax;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public void setSellButton(JToggleButton sellButton) {
		this.sellButton = sellButton;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
		moneyTextField.setText("Money: "+money);
	}
	
	public void addMoney(int income)
	{
		setMoney(getMoney()+income);
	}
	
	public void setMoneyTextField(JTextField moneyTextField) {
		this.moneyTextField = moneyTextField;
		setMoney(startMoney);
	}
}
