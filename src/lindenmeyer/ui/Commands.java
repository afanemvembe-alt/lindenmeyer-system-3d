package lindenmeyer.ui;

import lindenmeyer.axiom.*;
import lindenmeyer.symbols.*;
import lindenmeyer.rules.*;
import lindenmeyer.turtle.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Commands extends JPanel implements ActionListener{
	public JTextField modifAxiom;
	public JPanel panelGeneration;
	public JPanel panelZoom;
	public JPanel panelAxiom;
	public JButton changeAxiom;
	public JButton generate;
	public JButton random;
	public JButton clear;
	public JButton zoomP;
	public JButton zoomM;
	
	public Commands(){
		super();
		this.setLayout(new GridLayout(3,1,5,5));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder("Commandes"));
		this.setPreferredSize(new Dimension(300, 200));
		
		this.modifAxiom= new JTextField();
		this.changeAxiom= new JButton("Changer l'axiome");
		this.generate= new JButton("Generer");
		this.clear= new JButton("Effacer");
		this.zoomP= new JButton("Zoom+");
		this.zoomM= new JButton("Zoom-");
		this.random= new JButton("Random");
		
		this.changeAxiom.addActionListener(this);
		this.generate.addActionListener(this);
		this.clear.addActionListener(this);
		this.zoomP.addActionListener(this);
		this.zoomM.addActionListener(this);
		this.random.addActionListener(this);
		Dimension taille = new Dimension(150, 25);
		this.modifAxiom.setPreferredSize(taille);
		this.changeAxiom.setPreferredSize(taille);
		this.generate.setPreferredSize(taille);
		this.clear.setPreferredSize(taille);
		this.random.setPreferredSize(taille);
		this.zoomP.setPreferredSize(taille);
		this.zoomM.setPreferredSize(taille);
		
		this.panelAxiom= new JPanel();
		this.panelGeneration= new JPanel();
		this.panelZoom= new JPanel();
		
		this.panelAxiom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.panelGeneration.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.panelZoom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.panelAxiom.add(this.modifAxiom);
		this.panelAxiom.add(this.changeAxiom);
		this.panelGeneration.add(this.generate);
		this.panelGeneration.add(this.clear);
		this.panelGeneration.add(this.random);
		this.panelZoom.add(this.zoomP);
		this.panelZoom.add(this.zoomM);
		this.add(this.panelAxiom);
		this.add(this.panelGeneration);
		this.add(this.panelZoom);
	}
	
	public void actionPerformed(ActionEvent e){
		String text= this.modifAxiom.getText();
		if(e.getSource()==this.changeAxiom){
			//
		} 
		else if(e.getSource()==this.generate){
			//
		} 
		else if(e.getSource()==this.clear){
			//
		}
		else if(e.getSource()==this.zoomP){
			//
		}
		else if(e.getSource()==this.zoomM){
			//
		}
		else if(e.getSource()==this.random){
			//
		}
	}
}
		
