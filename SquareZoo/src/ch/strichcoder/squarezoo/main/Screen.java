package ch.strichcoder.squarezoo.main;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import ch.strichcoder.squarezoo.Field.Field;
import ch.strichcoder.squarezoo.Field.Tile;

public class Screen extends JPanel implements ActionListener{

	//private BufferedImage image;

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	private Field field;
	private JButton spawnButton;
	private JToggleButton sellButton;
	private JTextField moneyTextField;
	
	private int xOffset;
	private int yOffset;

	public Screen(){
		setOpaque(true);
		setBackground(Color.white);
		setLayout(null);
		
		field= new Field();
		xOffset=field.getxOffset();
		yOffset=field.getyOffset();
		add(field);
		
		spawnButton = new JButton();
		spawnButton.setBounds(Tile.gettWidth(),yOffset,Tile.gettWidth()*3,Tile.gettHeight()*3);
		spawnButton.setText("Spawn");
		spawnButton.addActionListener(this);
		add(spawnButton);
		
		sellButton = new JToggleButton();
		sellButton.setBounds(Tile.gettWidth(),yOffset+4*Tile.gettHeight(),Tile.gettWidth()*3,Tile.gettHeight()*3);
		sellButton.setText("Sell");
		sellButton.addActionListener(this);
		//sellButton.setEnabled(false);
		add(sellButton);
		field.setSellButton(sellButton);
		
		moneyTextField = new JTextField();
		moneyTextField.setBounds(WIDTH-Tile.gettWidth()*4,yOffset,Tile.gettWidth()*3,Tile.gettHeight()*3);
		add(moneyTextField);
		field.setMoneyTextField(moneyTextField);
	}
	
	public void spawn()
	{
		field.spawn();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==spawnButton)
			spawn();	
	}

	public JToggleButton getSellButton() {
		return sellButton;
	}

	
}
