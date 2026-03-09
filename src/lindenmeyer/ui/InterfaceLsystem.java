package lindenmeyer.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import lindenmeyer.axiom.*;
import lindenmeyer.symbols.*;
import lindenmeyer.rules.*;
import lindenmeyer.lsystem.*;
import lindenmeyer.turtle.*;

public class InterfaceLsystem extends JFrame implements ActionListener {

    public VueLsystem display;
    public LSystem lsystem;

    public JPanel commands;
    public JTextField modifAxiom;
    public JTextField rule;
    public JTextField nbStep;

    public JPanel panelGeneration;
    public JPanel panelZoom;
    public JPanel panelLsystem;

    public JButton defineLsystem;
    public JButton generate;
    public JButton random;
    public JButton clear;
    public JButton zoomP;
    public JButton zoomM;
    public JButton settings;
    public ArrayList<LSystem> preSet;

    // paramètres tortue
    private int longueur = 10;
    private int angleRotation = 60;

    public InterfaceLsystem() {
        super("LSystem");

        this.commands = new JPanel();
        this.commands.setLayout(new GridLayout(3,1,5,5));
        this.commands.setBackground(Color.WHITE);
        this.commands.setBorder(BorderFactory.createTitledBorder("Commandes"));
        this.commands.setPreferredSize(new Dimension(300, 200));

        this.modifAxiom = new JTextField();
        this.rule = new JTextField();
        this.nbStep = new JTextField();

        JPanel ligneAxiom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligneAxiom.add(new JLabel("Axiome : "));
        ligneAxiom.add(this.modifAxiom);

        JPanel ligneRegle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligneRegle.add(new JLabel("Regle : "));
        ligneRegle.add(this.rule);

        JPanel ligneStep = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligneStep.add(new JLabel("Etapes : "));
        ligneStep.add(this.nbStep);

        this.defineLsystem = new JButton("Definir LSystem");
        this.generate = new JButton("Generer");
        this.clear = new JButton("Effacer");
        this.zoomP = new JButton("Zoom+");
        this.zoomM = new JButton("Zoom-");
        this.random = new JButton("Random");
        this.settings = new JButton("Parametres");

        this.defineLsystem.addActionListener(this);
        this.generate.addActionListener(this);
        this.clear.addActionListener(this);
        this.zoomP.addActionListener(this);
        this.zoomM.addActionListener(this);
        this.random.addActionListener(this);
        this.settings.addActionListener(this);

        Dimension taille = new Dimension(150, 25);
        this.modifAxiom.setPreferredSize(taille);
        this.rule.setPreferredSize(taille);
        this.nbStep.setPreferredSize(taille);

        this.panelLsystem = new JPanel(new FlowLayout());
        this.panelGeneration = new JPanel(new FlowLayout());
        this.panelZoom = new JPanel(new FlowLayout());

        this.panelLsystem.add(ligneAxiom);
        this.panelLsystem.add(ligneRegle);
        this.panelLsystem.add(this.defineLsystem);

        this.panelGeneration.add(ligneStep);
        this.panelGeneration.add(this.generate);
        this.panelGeneration.add(this.clear);
        this.panelGeneration.add(this.random);

        this.panelZoom.add(this.zoomP);
        this.panelZoom.add(this.zoomM);
        this.panelZoom.add(this.settings);

        this.commands.add(this.panelLsystem);
        this.commands.add(this.panelGeneration);
        this.commands.add(this.panelZoom);

		this.preSet= new ArrayList<>();
		this.addPreSet();
        this.lsystem = new LSystem(new Axiom("F"));
        this.display = new VueLsystem(this.lsystem);

        this.setLayout(new BorderLayout());
        // taille large pour scroll
        display.setPreferredSize(new Dimension(2000, 2000));
        JScrollPane scroll = new JScrollPane(display);
        this.add(scroll, BorderLayout.CENTER);
        this.add(this.commands, BorderLayout.EAST);

        this.setSize(1000, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void showError(JTextField field, String message) {
		field.setBorder(BorderFactory.createLineBorder(Color.RED));
		JOptionPane.showMessageDialog(
				this,
				message,
				"Erreur de saisie",
				JOptionPane.ERROR_MESSAGE
		);
	}
	
	public void resetField(JTextField field) {
		field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public void addPreSet(){
		LSystem koch = new LSystem(new Axiom("F--F--F"));
		koch.ajouterRegle('F', "F+F--F+F");
		this.preSet.add(koch);

		LSystem tree = new LSystem(new Axiom("X"));
		tree.ajouterRegle('X', "F-[[X]+X]+F[+FX]-X");
		tree.ajouterRegle('F', "FF");
		this.preSet.add(tree);

		LSystem dragon = new LSystem(new Axiom("FX"));
		dragon.ajouterRegle('X', "X+YF+");
		dragon.ajouterRegle('Y', "-FX-Y");
		this.preSet.add(dragon);
	}

    public void actionPerformed(ActionEvent e) {

        String text = this.modifAxiom.getText();
        String regle = this.rule.getText();
        String step = this.nbStep.getText();

        if (e.getSource() == this.defineLsystem) {
			if (!text.isEmpty()){ 
				if (text.matches(".*\\d.*")){ 
					showError(this.modifAxiom, "L'axiome ne peut pas contenir de chiffres");
					return;
				}
				this.display.getLSystem().setAxiome(new Axiom(text));
				resetField(modifAxiom);
			} else {
				resetField(modifAxiom);
			}

			if (regle.isEmpty()) {
				showError(this.rule, "Vous devez entrer au moins une regle");
				return;
			} else if(!regle.isEmpty()) {
				String[] rules = regle.split(",");
				for (String r : rules){
					String[] parts = r.split(">");
					if (parts.length != 2) {
						showError(this.rule, "Regle invalide : \"" + r + "\". Format attendu : symbole>regle");
						return;
					}
					if (parts[0].length() != 1) {
						showError(this.rule, "Le symbole doit être un seul caractère : \"" + r + "\"");
						return;
					}
				}
			}
			resetField(rule);

            RuleSetFactory rsf = new RuleSetFactory(new SymbolFactory());
			RuleSet rules = rsf.parseString(rule.getText());
			for (GenericRule r : rules) {
				this.display.getLSystem().ajouterRegle(r);
			}
        }

        else if (e.getSource() == this.generate) {
            int n = 2;
            try {
                if (!step.isEmpty()) {
                    n = Integer.parseInt(step);
                }
            } catch (NumberFormatException ex) {
                n = 2;
            }

            Tortue tortue = new Tortue(300, 400, 0, longueur, angleRotation);
            LSystem temp = new LSystem(new Axiom(this.display.getLSystem().getAxiome().getContent()), this.display.getLSystem().getRegles(), this.display.getLSystem().getSymbolFactory());
            List<Segment> allSegments = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                temp.step();
                List<Segment> segments = tortue.interpreter(temp.getCurrentGeneration().toString());
                allSegments.addAll(segments);
            }

            this.display.setSegments(allSegments);
            this.display.repaint();
        }

        else if (e.getSource() == this.settings) {

            ParamDialog dialog = new ParamDialog(this);
            dialog.setVisible(true);

            this.longueur = dialog.getLongueur();
            this.angleRotation = dialog.getAngle();
        }

        else if (e.getSource() == this.clear) {
            this.display.clearSegments();
            this.display.repaint();
        }

        else if (e.getSource() == this.zoomP) {
            // à faire
        }

        else if (e.getSource() == this.zoomM) {
            // à faire
        }

        else if (e.getSource() == this.random) {
			this.display.clearSegments();
            int pos = (int)(Math.random()*this.preSet.size());
			LSystem chosen = this.preSet.get(pos);

			Tortue tortue = new Tortue(300, 400, 0, longueur, angleRotation);
			List<Segment> allSegments = new ArrayList<>();
			LSystem temp = new LSystem(new Axiom(chosen.getAxiome().getContent()), chosen.getRegles(), chosen.getSymbolFactory());

			int n = 3;
            try {
                if (!step.isEmpty()){
                    n = Integer.parseInt(step);
                }
            } catch (NumberFormatException ex){
                n = 3;
            }
			for(int i=0; i<n; i++){
				temp.step();
				allSegments.addAll(tortue.interpreter(temp.getCurrentGeneration().toString()));
			}

			this.display.setSegments(allSegments);
			this.display.getLSystem().setAxiome(new Axiom(chosen.getAxiome().getContent()));
			this.display.repaint();
		}
    }
    
	public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new InterfaceLsystem();
        }
    });
}
}