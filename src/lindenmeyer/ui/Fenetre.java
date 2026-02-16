package lindenmeyer.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fenetre extends JFrame{
	
	public Commands command;
	
	public Fenetre(){
		super("LSystem");
		this.command= new Commands();
		JPanel display = new JPanel();
		display.setPreferredSize(new Dimension(700, 500));
		this.setLayout(new BorderLayout());
		this.add(display, BorderLayout.CENTER);
		this.add(this.command, BorderLayout.EAST);
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
		
