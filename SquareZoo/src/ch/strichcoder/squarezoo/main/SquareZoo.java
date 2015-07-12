package ch.strichcoder.squarezoo.main;
import java.awt.*; 

import javax.swing.*; 

public class SquareZoo extends JFrame{

	public static final String TITLE = "Square Zoo";
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	private static final long serialVersionUID = 1L;
	private Screen screen;
	
	public SquareZoo(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    setTitle(TITLE);
	    setSize(WIDTH,HEIGHT);
		setLayout(new GridLayout(1,1,0,0));
	    setLocationRelativeTo(null);

		screen= new Screen(); // mainPanel
		add(screen);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SquareZoo sqareZoo = new SquareZoo();
	}
	
}