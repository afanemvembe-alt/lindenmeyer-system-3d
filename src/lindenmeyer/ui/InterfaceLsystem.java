package lindenmeyer.ui;

import lindenmeyer.axiom.*;
import lindenmeyer.symbols.*;
import lindenmeyer.lsystem.*;
import lindenmeyer.rules.*;
import lindenmeyer.turtle.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class InterfaceLsystem extends JFrame implements ActionListener{
	
	public VueLsystem display;
	public LSystem lsystem;
	public JPanel commands;
	public JTextField modifAxiom;
	public JTextField symbol;
	public JTextField rule;
	public JTextField nbStep;
	public JPanel panelGeneration;
	public JPanel panelZoom;
	public JPanel panelLsystem;
	public JButton defineLsystem;
	public JButton ajoutSymbol;
	public JButton ajoutRegle;
	public JButton nbIterations;
	public JButton generate;
	public JButton random;
	public JButton clear;
	public JButton zoomP;
	public JButton zoomM;
	
	public InterfaceLsystem(){
		super("LSystem");
		
		this.commands= new JPanel();
		this.commands.setLayout(new GridLayout(3,1,5,5));
		this.commands.setBackground(Color.WHITE);
		this.commands.setBorder(BorderFactory.createTitledBorder("Commandes"));
		this.commands.setPreferredSize(new Dimension(300, 200));
		
		this.modifAxiom= new JTextField();
		this.symbol= new JTextField();
		this.rule= new JTextField();
		this.nbStep= new JTextField();
		JLabel labelAxiom = new JLabel("Axiome : ");
		JLabel labelSymbol = new JLabel("Symbole : ");
		JLabel labelRule = new JLabel("Regle : ");
		JLabel labelStep = new JLabel("Iterations : ");
		
		JPanel ligneAxiom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ligneAxiom.add(new JLabel("Axiome : "));
		ligneAxiom.add(this.modifAxiom);
		JPanel ligneSymbole = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ligneSymbole.add(new JLabel("Symbole : "));
		ligneSymbole.add(this.symbol);
		JPanel ligneRegle = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ligneRegle.add(new JLabel("Regle : "));
		ligneRegle.add(this.rule);
		JPanel ligneStep = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ligneStep.add(new JLabel("Etapes : "));
		ligneStep.add(this.nbStep);
		
		this.defineLsystem= new JButton("Definir LSystem");
		this.generate= new JButton("Generer");
		this.clear= new JButton("Effacer");
		this.zoomP= new JButton("Zoom+");
		this.zoomM= new JButton("Zoom-");
		this.random= new JButton("Random");
		
		this.defineLsystem.addActionListener(this);
		this.generate.addActionListener(this);
		this.clear.addActionListener(this);
		this.zoomP.addActionListener(this);
		this.zoomM.addActionListener(this);
		this.random.addActionListener(this);
		Dimension taille = new Dimension(150, 25);
		this.modifAxiom.setPreferredSize(taille);
		this.symbol.setPreferredSize(taille);
		this.rule.setPreferredSize(taille);
		this.nbStep.setPreferredSize(taille);
		this.defineLsystem.setPreferredSize(taille);
		this.generate.setPreferredSize(taille);
		this.clear.setPreferredSize(taille);
		this.random.setPreferredSize(taille);
		this.zoomP.setPreferredSize(taille);
		this.zoomM.setPreferredSize(taille);
		
		this.panelLsystem= new JPanel();
		this.panelGeneration= new JPanel();
		this.panelZoom= new JPanel();
		
		this.panelLsystem.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.panelGeneration.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.panelZoom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//this.panelLsystem.add(labelAxiom);
		//this.panelLsystem.add(this.modifAxiom);
		//this.panelLsystem.add(labelSymbol);
		//this.panelLsystem.add(this.symbol);
		//this.panelLsystem.add(labelRule);
		//this.panelLsystem.add(this.rule);
		//this.panelLsystem.add(this.defineLsystem);
		this.panelLsystem.add(ligneAxiom);
		this.panelLsystem.add(ligneSymbole);
		this.panelLsystem.add(ligneRegle);
		this.panelLsystem.add(this.defineLsystem);
		this.panelGeneration.add(ligneStep);
		this.panelGeneration.add(this.nbStep);
		this.panelGeneration.add(this.generate);
		this.panelGeneration.add(this.clear);
		this.panelGeneration.add(this.random);
		this.panelZoom.add(this.zoomP);
		this.panelZoom.add(this.zoomM);
		this.commands.add(this.panelLsystem);
		this.commands.add(this.panelGeneration);
		this.commands.add(this.panelZoom);
		
		this.lsystem= new LSystem(new Axiom("F"));
		this.display= new VueLsystem(this.lsystem);
		this.setLayout(new BorderLayout());
		this.add(display, BorderLayout.CENTER);
		
		this.add(this.commands, BorderLayout.EAST);
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		String text= this.modifAxiom.getText();
		String symbole = this.symbol.getText();
		String regle= this.rule.getText();
		String step= this.nbStep.getText();
		if(e.getSource()==this.defineLsystem){
			this.display.getLSystem().setAxiome((new Axiom(text)));
			if(!(symbole.equals(""))){
				this.display.getLSystem().ajouterRegle(symbole.charAt(0), regle);
			}
		} 
		else if(e.getSource()==this.generate){
			int n= 2;
			if (!step.isEmpty()) {
				try {
					n = Integer.parseInt(step);
				} catch(NumberFormatException ex) {
					n = 2;
				}
			}
			Tortue tortue = new Tortue(300, 400, 0);
			//String sequence = this.display.getLSystem().generer(n);
			List<Segment> allSegments = new ArrayList<>();
			LSystem temp = new LSystem(new Axiom(this.display.getLSystem().getAxiome().getContent()));
			for(int i = 0; i < n; i++){
				temp.step(); 
				List<Segment> segments = tortue.interpreter(temp.getCurrentGeneration().toString());
				allSegments.addAll(segments); 
				this.display.setSegments(new ArrayList<>(allSegments));
				this.display.repaint();
			}
		} 
		else if(e.getSource()==this.clear){
			this.display.clearSegments();
			this.display.repaint();
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
		
