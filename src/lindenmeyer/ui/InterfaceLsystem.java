package lindenmeyer.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import lindenmeyer.axiom.Axiom;
import lindenmeyer.lsystem.LSystem;
import lindenmeyer.turtle.Segment;
import lindenmeyer.turtle.Tortue;

public class InterfaceLsystem extends JFrame implements ActionListener {

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
    public JButton generate;
    public JButton random;
    public JButton clear;
    public JButton zoomP;
    public JButton zoomM;
    public JButton settings;

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
        this.symbol = new JTextField();
        this.rule = new JTextField();
        this.nbStep = new JTextField();

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
        this.symbol.setPreferredSize(taille);
        this.rule.setPreferredSize(taille);
        this.nbStep.setPreferredSize(taille);

        this.panelLsystem = new JPanel(new FlowLayout());
        this.panelGeneration = new JPanel(new FlowLayout());
        this.panelZoom = new JPanel(new FlowLayout());

        this.panelLsystem.add(ligneAxiom);
        this.panelLsystem.add(ligneSymbole);
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

        this.lsystem = new LSystem(new Axiom("F"));
        this.display = new VueLsystem(this.lsystem);

        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.CENTER);
        this.add(this.commands, BorderLayout.EAST);

        this.setSize(1000, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String text = this.modifAxiom.getText();
        String symbole = this.symbol.getText();
        String regle = this.rule.getText();
        String step = this.nbStep.getText();

        if (e.getSource() == this.defineLsystem) {

            this.display.getLSystem().setAxiome(new Axiom(text));

            if (!symbole.equals("") && !regle.equals("")) {
                this.display.getLSystem().ajouterRegle(symbole.charAt(0), regle);
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

            LSystem temp = new LSystem(
                    new Axiom(this.display.getLSystem().getAxiome().getContent())
            );

            List<Segment> allSegments = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                temp.step();
                List<Segment> segments =
                        tortue.interpreter(temp.getCurrentGeneration().toString());

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
            // à faire
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