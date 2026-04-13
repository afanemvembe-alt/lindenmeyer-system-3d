<<<<<<< HEAD:src/lindenmeyer/ui/InterfaceLsystem.java
package lindenmeyer.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;
//import javafx.geometry.Point3D;

import lindenmeyer.axiom.*;
import lindenmeyer.symbols.*;
import lindenmeyer.rules.*;
import lindenmeyer.lsystem.*;
import lindenmeyer.lsystem.history.*;
import lindenmeyer.turtle.*;

public class InterfaceLsystem extends JFrame implements ActionListener {

    public VueLsystem display;
    public LSystem lsystem;

	//Panels de commande
    public JPanel commands;
    public JTextField modifAxiom;
    public JTextField rule;
    public JTextField nbStep;

	//Sous panels de commande
    public JPanel panelGeneration;
    public JPanel panelZoom;
    public JPanel panelLsystem;

	//Boutons de controle de l'interface
    public JButton defineLsystem;
    public JButton generate;
    public JButton random;
    public JButton clear;
    public JButton zoomP;
    public JButton zoomM;
    public JButton settings;
    public JButton play;
    
    //Infos sur les preset
    public JPanel panelInfo;
	public JTextArea presetInfo;
    
    //Listes de systemes predefinis et leur configuration
    public ArrayList<LSystem> preSet;
    private ArrayList<ConfigLsystem> preSetConfig;
    //public JComboBox<ConfigLsystem> presetSelector;
    
    //Boite de dialogue
    private ParamDialog paramDialog;
    
    //Selecteur de Couleurs
    public JComboBox<String> colorSelector;
	private Color selectedColor = Color.BLACK;

    // paramètres tortue
    private int longueur = 10;
    private int angleRotation = 60;
    
    public JSlider historySlider;  
	public History history;
	public int maxStep=20;  
	
	//Pour play/pause du Lsystem
	private boolean playing = false;
	private Timer playTimer;
	
	//Gestion des vues 2D et 3D
	private JPanel dessin;
	private JScrollPane scroll2D;
	private boolean mode3D = false;
	public JButton switch3D;
	private Vue3D vue3D;
	private JPanel centerPanel;
	private CardLayout centerLayout;
	private JFXPanel fxPanel;
	
    public InterfaceLsystem() {
        super("LSystem");

		// not sure about this
        MenubarLsystem menuBar = new MenubarLsystem(this);
        this.setJMenuBar(menuBar);

        this.commands = new JPanel();
        Color bg = new Color(245,245,245);
		this.commands.setBackground(bg);
        //this.commands.setLayout(new GridLayout(4,1,5,5));
        this.commands.setLayout(new BoxLayout(this.commands, BoxLayout.Y_AXIS));
        this.commands.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Commandes"),BorderFactory.createEmptyBorder(10,10,10,10)));
        this.commands.setPreferredSize(new Dimension(300, 800));
        //this.commands.setPreferredSize(new Dimension(300, 200));

        this.modifAxiom = new JTextField();
        this.rule = new JTextField();
        this.nbStep = new JTextField();
        Font uiFont = new Font("Segoe UI", Font.PLAIN, 14);
		this.modifAxiom.setFont(uiFont);
		this.rule.setFont(uiFont);
		this.nbStep.setFont(uiFont);

		//Panels des JTextField
        JPanel ligneAxiom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligneAxiom.add(new JLabel("Axiome : "));
        ligneAxiom.add(this.modifAxiom);
        JPanel ligneRegle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligneRegle.add(new JLabel("Regle : "));
        ligneRegle.add(this.rule);
        JPanel ligneStep = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligneStep.add(new JLabel("Etapes : "));
        ligneStep.add(this.nbStep);

		//Definition des boutons
        this.defineLsystem = new JButton("Definir LSystem");
        this.generate = new JButton("Generer");
        this.clear = new JButton("Effacer");
        this.zoomP = new JButton("Zoom+");
        this.zoomM = new JButton("Zoom-");
        this.random = new JButton("Random");
        this.settings = new JButton("Parametres");
        this.play = new JButton("Play/Pause");
        this.switch3D = new JButton("Switch3D");
        
        //Definition de couleur de fond des boutons
        this.generate.setBackground(new Color(120,200,120));
		this.random.setBackground(new Color(120,170,240));
		this.clear.setBackground(new Color(240,120,120));
        
        //Definition de la taille des boutons
        Dimension boutonSize = new Dimension(130,30);
		this.defineLsystem.setPreferredSize(boutonSize);
		this.generate.setPreferredSize(boutonSize);
		this.random.setPreferredSize(boutonSize);
		this.clear.setPreferredSize(boutonSize);
		this.zoomP.setPreferredSize(boutonSize);
		this.zoomM.setPreferredSize(boutonSize);
		this.settings.setPreferredSize(boutonSize);
		this.play.setPreferredSize(boutonSize);
		this.switch3D.setPreferredSize(boutonSize);
		
		//Definition de la police des boutons
		this.defineLsystem.setFont(uiFont);
		this.generate.setFont(uiFont);
		this.random.setFont(uiFont);
		this.clear.setFont(uiFont);
		this.zoomP.setFont(uiFont);
		this.zoomM.setFont(uiFont);
		this.settings.setFont(uiFont);
		this.play.setFont(uiFont);
		this.switch3D.setFont(uiFont);

		//Action des boutons
        this.defineLsystem.addActionListener(this);
        this.generate.addActionListener(this);
        this.clear.addActionListener(this);
        this.zoomP.addActionListener(this);
        this.zoomM.addActionListener(this);
        this.random.addActionListener(this);
        this.settings.addActionListener(this);
        this.play.addActionListener(this);
        this.switch3D.addActionListener(this);

		//Taille des JTextField
        Dimension taille = new Dimension(180, 30);
        this.modifAxiom.setPreferredSize(taille);
        this.rule.setPreferredSize(taille);
        this.nbStep.setPreferredSize(taille);

		//Creation de bordures pour chaque sous Panel
        this.panelLsystem = new JPanel(new FlowLayout());
        this.panelGeneration = new JPanel(new FlowLayout());
        this.panelZoom = new JPanel(new FlowLayout());
        this.panelInfo = new JPanel(new BorderLayout());
        //
        this.panelLsystem.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panelGeneration.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panelZoom.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panelInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
		//
        this.panelLsystem.setBorder(BorderFactory.createTitledBorder("Definition du LSystem"));
		this.panelGeneration.setBorder(BorderFactory.createTitledBorder("Generation"));
		this.panelZoom.setBorder(BorderFactory.createTitledBorder("Affichage"));
		this.panelInfo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		this.panelInfo.setBorder(BorderFactory.createTitledBorder("Informations preset"));

		//Infos
		this.presetInfo = new JTextArea(3,20);
		this.presetInfo.setRows(3);
		this.presetInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		presetInfo.setEditable(false);
		presetInfo.setLineWrap(true);
		presetInfo.setWrapStyleWord(true);
		JScrollPane scrollInfo = new JScrollPane(presetInfo);
		this.panelInfo.add(scrollInfo, BorderLayout.CENTER);
		
		//Selecteur de couleurs
		String[] colors = {"Automatique", "Noir", "Rouge", "Vert", "Bleu", "Jaune", "Magenta", "Cyan", "Orange", "Rose", "Gris"};
		colorSelector = new JComboBox<>(colors);
		colorSelector.setSelectedIndex(0); 
		colorSelector.addActionListener(this);
		
		//Selecteur LSystem
		//String[] presetNames = preSetConfig.stream().map(cfg -> cfg.info.split("\n")[0]).toArray(String[]::new);
		//presetSelector = new JComboBox<>(preSetConfig.toArray(new ConfigLsystem[0]));
		//presetSelector.setSelectedIndex(-1);
		//presetSelector.addActionListener(this);

		
		// Slider historique
		this.historySlider = new JSlider(0, this.maxStep, 0);
		this.historySlider.setMajorTickSpacing(1);
		this.historySlider.setPaintTicks(true);
		this.historySlider.setPaintLabels(true);
		this.historySlider.addChangeListener(e -> {
			if (this.history == null || this.historySlider.getValueIsAdjusting()) {
				return;
			}
			int index = this.historySlider.getValue();
			if (index == 0) {
				this.display.clearSegments();
				this.display.repaint();

				Platform.runLater(() -> {
					if (this.vue3D != null) {
						this.vue3D.setSegments(new ArrayList<>());
						this.vue3D.redraw();
					}
				});
				return;
			}
			index = index - 1;
			if (index >= this.history.size()) {
				index = this.history.size() - 1;
			}
			State s = (State) this.history.getState(index);
			SymbolList symbols = s.getState();

			ConfigTortue config = new ConfigTortue(this.longueur, this.angleRotation);
			// Pour la 2D
			Tortue tortue = new Tortue(300, 400, -90, config);
			List<Segment> segments2D = tortue.interpreter(symbols.toString());
			this.display.setSegments(segments2D);
			this.display.repaint();
			// Pour la 3D
			Turtle3D tortue3D = new Turtle3D(config);
			for (Symbol sym : symbols) {
				sym.interpret(tortue3D);
			}
			List<Segment3D> segments3D = tortue3D.getSegments();

			Platform.runLater(() -> {
				if (this.vue3D != null) {
					this.vue3D.setSegments(segments3D);
					this.vue3D.redraw();
				}
			});
		});
		
		
		//Ajout de composants dans le sous Panel correspondant
        this.panelLsystem.add(ligneAxiom);
        this.panelLsystem.add(ligneRegle);
        this.panelLsystem.add(this.switch3D);
        this.panelLsystem.add(this.defineLsystem);

        this.panelGeneration.add(ligneStep);
        this.panelGeneration.add(this.generate);
        this.panelGeneration.add(this.clear);
        this.panelGeneration.add(this.random);

		this.panelZoom.add(new JLabel("Couleur : "));
		this.panelZoom.add(colorSelector);
        this.panelZoom.add(this.zoomP);
        this.panelZoom.add(this.zoomM);
        this.panelZoom.add(this.settings);
        this.panelZoom.add(this.play);
        
        this.commands.add(this.panelLsystem);
		this.commands.add(Box.createVerticalStrut(8));
		this.commands.add(this.panelGeneration);
		this.commands.add(Box.createVerticalStrut(8));
		this.commands.add(this.panelZoom);
		this.commands.add(Box.createVerticalStrut(8));
		this.commands.add(this.panelInfo);
        
		//
		this.preSet= new ArrayList<>();
		this.preSetConfig= new ArrayList<>();
		this.addPreSet();
		this.paramDialog= new ParamDialog(this);
        this.lsystem = new LSystem(new Axiom("F"));
        this.display = new VueLsystem(this.lsystem);
        
        //Gestion visuelle de la partie dessin+slider
        // JavaFX instructions
		this.fxPanel = new JFXPanel();
		this.centerLayout = new CardLayout();
		this.centerPanel = new JPanel(this.centerLayout);
		this.setLayout(new BorderLayout());
		this.display.setPreferredSize(new Dimension(2000, 2000));
		this.scroll2D = new JScrollPane(display);
		// panneau commun pour la zone centrale + slider
		this.dessin = new JPanel(new BorderLayout());
		// slider commun aux deux modes
		JPanel sliderPanel = new JPanel(new BorderLayout());
		sliderPanel.setBorder(BorderFactory.createTitledBorder("Historique des generations"));
		sliderPanel.add(this.historySlider, BorderLayout.CENTER);
		// cartes 2D / 3D
		this.centerPanel.add(this.scroll2D, "2D");
		this.centerPanel.add(this.fxPanel, "3D");
		// panneau global : cartes au centre, slider en bas
		this.dessin.add(this.centerPanel, BorderLayout.CENTER);
		this.dessin.add(sliderPanel, BorderLayout.SOUTH);
		Platform.runLater(() -> {
			this.vue3D = new Vue3D(new ArrayList<>());
			this.fxPanel.setScene(this.vue3D);
		});
		
		//Scroll vertical pour les commandes
		JScrollPane scrollCommands = new JScrollPane(this.commands);
		scrollCommands.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollCommands.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCommands.setBorder(null);
		scrollCommands.setPreferredSize(new Dimension(320, 0));
		this.add(this.dessin, BorderLayout.CENTER);
		this.add(scrollCommands, BorderLayout.EAST);
		//this.add(this.commands, BorderLayout.EAST);
        
        
        this.setSize(1200, 750);
		this.setMinimumSize(new Dimension(1100, 700));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

	public void setLongeur(int l)
	{
		this.longueur = l;
	}

	public void setAngleRotation(int a)
	{
		this.angleRotation = a;
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
		// Koch Flake
		LSystem koch = new LSystem(new Axiom("F--F--F"));
		koch.ajouterRegle('F', "F+F--F+F");
		this.preSet.add(koch);
		this.preSetConfig.add(new ConfigLsystem(60, 10, 300, 400,
			"Koch Flake - Max steps 6\nDescription: Flocon de Koch classique\nAngle: 60\nLongueur: 10"));

		// Tree
		LSystem tree = new LSystem(new Axiom("X"));
		tree.ajouterRegle('X', "F-[[X]+X]+F[+FX]-X");
		tree.ajouterRegle('F', "FF");
		this.preSet.add(tree);
		this.preSetConfig.add(new ConfigLsystem(25, 5, 350, 500,
			"Simple Tree - Max steps 5\nDescription: Arbre fractal\nAngle: 25\nLongueur: 5"));

		// Dragon Curve
		LSystem dragon = new LSystem(new Axiom("FX"));
		dragon.ajouterRegle('X', "X+YF+");
		dragon.ajouterRegle('Y', "-FX-Y");
		this.preSet.add(dragon);
		this.preSetConfig.add(new ConfigLsystem(90, 5, 400, 400,
			"Dragon Curve - Max steps 5\nDescription: Courbe du dragon\nAngle: 90\nLongueur: 5"));

		// Sierpinski Triangle
		LSystem sierpinski = new LSystem(new Axiom("A"));
		sierpinski.ajouterRegle('A', "B-A-B");
		sierpinski.ajouterRegle('B', "A+B+A");
		this.preSet.add(sierpinski);
		this.preSetConfig.add(new ConfigLsystem(60, 5, 300, 300,
			"Sierpinski Triangle - Max steps 5\nDescription: Triangle de Sierpinski\nAngle: 60\nLongueur: 5"));

		// Hilbert Curve
		LSystem hilbert = new LSystem(new Axiom("A"));
		hilbert.ajouterRegle('A', "-BF+AFA+FB-");
		hilbert.ajouterRegle('B', "+AF-BFB-FA+");
		this.preSet.add(hilbert);
		this.preSetConfig.add(new ConfigLsystem(90, 5, 400, 400,
			"Hilbert Curve - Max steps 5\nDescription: Courbe de Hilbert\nAngle: 90\nLongueur: 5"));

		// Peano Curve
		LSystem peano = new LSystem(new Axiom("X"));
		peano.ajouterRegle('X', "XFYFX+F+YFXFY-F-XFYFX");
		peano.ajouterRegle('Y', "YFXFY-F-XFYFX+F+YFXFY");
		this.preSet.add(peano);
		this.preSetConfig.add(new ConfigLsystem(90, 3, 350, 350,
			"Peano Curve - Max steps 3\nDescription: Courbe de Peano\nAngle: 90\nLongueur: 3"));

		// Levy C Curve
		LSystem levy = new LSystem(new Axiom("F"));
		levy.ajouterRegle('F', "+F--F+");
		this.preSet.add(levy);
		this.preSetConfig.add(new ConfigLsystem(45, 8, 400, 400,
			"Levy C Curve - Max steps 8\nDescription: Courbe de Levy C\nAngle: 45\nLongueur: 8"));

		// Quadratic Koch Island
		LSystem kochIsland = new LSystem(new Axiom("F+F+F+F"));
		kochIsland.ajouterRegle('F', "F+F-F-FF+F+F-F");
		this.preSet.add(kochIsland);
		this.preSetConfig.add(new ConfigLsystem(90, 4, 300, 300,
			"Quadratic Koch Island - Max steps 4\nDescription: Flocon quadratique\nAngle: 90\nLongueur: 4"));

		// Plant-like fractal
		LSystem plant = new LSystem(new Axiom("X"));
		plant.ajouterRegle('X', "F-[[X]+X]+F[+FX]-X");
		plant.ajouterRegle('F', "FF");
		this.preSet.add(plant);
		this.preSetConfig.add(new ConfigLsystem(25, 5, 350, 500,
			"Plant-like fractal - Max steps 5\nDescription: Plante fractale\nAngle: 25\nLongueur: 5"));

		// Tree variant 2
		LSystem tree2 = new LSystem(new Axiom("F"));
		tree2.ajouterRegle('F', "F[+F]F[-F]F");
		this.preSet.add(tree2);
		this.preSetConfig.add(new ConfigLsystem(25, 5, 350, 500,
			"Tree variant 2 - Max steps 5\nDescription: Variante d'arbre\nAngle: 25\nLongueur: 5"));
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
						showError(this.rule, "Le symbole doit etre un seul caractère : \"" + r + "\"");
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
        
        else if (e.getSource() == this.switch3D) {
			if (mode3D) {
				this.centerLayout.show(this.centerPanel, "2D");
				mode3D = false;
				this.switch3D.setText("Switch3D");
			} else {
				this.centerLayout.show(this.centerPanel, "3D");
				mode3D = true;
				this.switch3D.setText("Switch2D");
			}
		}

        else if (e.getSource() == this.generate) {
			JDialog loading = new JDialog(this, "Chargement", true);
			loading.setLayout(new BorderLayout());
			loading.add(new JLabel("Generation en cours...", SwingConstants.CENTER), BorderLayout.CENTER);
			loading.setSize(200,100);
			loading.setLocationRelativeTo(this);
			history = new History();
            new Thread(() -> {
				int n = 2;
				try {
					if (!step.isEmpty()) n = Integer.parseInt(step);
				} catch (NumberFormatException ex) {
					n = 2;
				}
				if(n>this.maxStep){
					n=this.maxStep;
				}

				ConfigTortue config = new ConfigTortue(longueur, angleRotation);
				Tortue tortue = new Tortue(300, 400, -90, config);

				LSystem temp = new LSystem(
					new Axiom(this.display.getLSystem().getAxiome().getContent()),
					this.display.getLSystem().getRegles(),
					this.display.getLSystem().getSymbolFactory()
				);
				history.addState(new State(temp.getCurrentGeneration()));


				for (int i = 0; i < n; i++){
					temp.step();
					history.addState(new State(temp.getCurrentGeneration()));
				}
				

				List<Segment> finalSegments = tortue.interpreter(temp.getCurrentGeneration().toString());

				//Gestion 3D------------------------
				Turtle3D tortue3D = new Turtle3D(config);
				SymbolList generation3D = temp.getCurrentGeneration();
				for (Symbol s : generation3D) {
					s.interpret(tortue3D);
				}
				List<Segment3D> finalSegments3D = tortue3D.getSegments();
				Platform.runLater(() -> {
					if (this.vue3D != null) {
						this.vue3D.setSegments(finalSegments3D);
						this.vue3D.redraw();
					}
				});
				//----------------------Fin gestion3D 
				
				
				// Mise à jour de l'UI sur le thread Swing
				SwingUtilities.invokeLater(() -> {
					//this.longueur = config.pas;
					//this.angleRotation = config.angleRotation;
					this.display.setSegments(finalSegments);
					this.display.repaint();
					this.historySlider.setMaximum(this.history.size());
					this.historySlider.setValue(this.history.size());
					loading.dispose();
				});
			}).start();
			loading.setVisible(true);
        }

        else if (e.getSource() == this.settings) {		
            this.paramDialog.setVisible(true);
            this.longueur = this.paramDialog.getLongueur();
            this.angleRotation = this.paramDialog.getAngle();
        } 
        
        else if (e.getSource() == this.colorSelector) {
			switch ((String) this.colorSelector.getSelectedItem()) {
				case "Automatique" -> this.selectedColor = null;
				case "Noir" -> this.selectedColor = Color.BLACK;
				case "Rouge" -> this.selectedColor = Color.RED;
				case "Vert" -> this.selectedColor = Color.GREEN;
				case "Bleu" -> this.selectedColor = Color.BLUE;
				case "Jaune" -> this.selectedColor = Color.YELLOW;
				case "Magenta" -> this.selectedColor = Color.MAGENTA;
				case "Cyan" -> this.selectedColor = Color.CYAN;
				case "Orange" -> this.selectedColor = Color.ORANGE;
				case "Rose" -> this.selectedColor = Color.PINK;
				case "Gris" -> this.selectedColor = Color.GRAY;
			}

			List<Segment> current = this.display.getSegments();
			if (current != null && !current.isEmpty()) {
				this.display.setDrawColor(selectedColor);
				this.display.repaint();
			}
		}
		

        else if (e.getSource() == this.clear) {
            this.display.clearSegments();
            this.display.repaint();
            Platform.runLater(() -> {
				if (this.vue3D != null) {
					this.vue3D.setSegments(new ArrayList<>());
					this.vue3D.redraw();
				}
			});
        }

        else if (e.getSource() == this.zoomP) {
            this.display.zoomIn();
        }

        else if (e.getSource() == this.zoomM) {
			this.display.zoomOut();
        }

        else if (e.getSource() == this.random) {
			JDialog loading = new JDialog(this, "Chargement", true);
			loading.setLayout(new BorderLayout());
			loading.add(new JLabel("Generation en cours...", SwingConstants.CENTER), BorderLayout.CENTER);
			loading.setSize(200,100);
			loading.setLocationRelativeTo(this);
			history = new History();
			new Thread(() -> {
				int pos = (int)(Math.random() * this.preSet.size());
				LSystem chosen = this.preSet.get(pos);

				// Mets à jour les champs (UI) sur EDT
				SwingUtilities.invokeLater(() -> {
					this.modifAxiom.setText(chosen.getAxiome().getContent());
					String s = "";
					for (GenericRule r : chosen.getRegles()) s += r.toString() + ",";
					this.rule.setText(s);
				});

				ConfigLsystem cfg = this.preSetConfig.get(pos);
				this.presetInfo.setText(cfg.info);
				ConfigTortue configT = new ConfigTortue(cfg.pas, cfg.angle);
				Tortue tortue = new Tortue(cfg.startX, cfg.startY, -90, configT);

				LSystem temp = new LSystem(
					new Axiom(chosen.getAxiome().getContent()),
					chosen.getRegles(),
					chosen.getSymbolFactory()
				);
				this.display.setLSystem(temp);

				int n = 3;
				try {
					if (!step.isEmpty()) n = Integer.parseInt(step);
				} catch (NumberFormatException ex) {
					n = 3;
				}
				if(n>this.maxStep){
					n=this.maxStep;
				}
				

				for (int i = 0; i < n; i++){ 
					temp.step();
					history.addState(new State(temp.getCurrentGeneration()));
				}

				List<Segment> finalSegments = tortue.interpreter(temp.getCurrentGeneration().toString());

				// --- Gestion3D pour random------------
				Turtle3D tortue3D = new Turtle3D(configT);
				SymbolList generation3D = temp.getCurrentGeneration();

				for (Symbol s : generation3D) {
					s.interpret(tortue3D);
				}

				List<Segment3D> finalSegments3D = tortue3D.getSegments();

				Platform.runLater(() -> {
					if (this.vue3D != null) {
						this.vue3D.setSegments(finalSegments3D);
						this.vue3D.redraw();
					}
				});
				//-------------3D pour random------------

				SwingUtilities.invokeLater(() -> {
					this.longueur = cfg.pas;
					this.angleRotation = cfg.angle;
					this.display.setSegments(finalSegments);
					this.display.getLSystem().setAxiome(new Axiom(chosen.getAxiome().getContent()));
					this.display.repaint();
					this.historySlider.setMaximum(this.history.size());
					this.historySlider.setValue(this.history.size());
					loading.dispose();
				});
			}).start();
			loading.setVisible(true);
		}
		
		else if(e.getSource() == this.play) {
			if (playing) {
				if (playTimer != null && playTimer.isRunning()) {
					playTimer.stop();
				}
				play.setText("Play");
				playing = false;
				return;
			}

			playing = true;
			play.setText("Pause");

			int n = 3;
			try {
				if (!step.isEmpty()) n = Integer.parseInt(step);
			} catch (NumberFormatException ex) {
				n = 3;
			}
			if (n > this.maxStep) n = this.maxStep;

			LSystem temp = new LSystem(
				new Axiom(this.display.getLSystem().getAxiome().getContent()),
				this.display.getLSystem().getRegles(),
				this.display.getLSystem().getSymbolFactory()
			);
			this.display.setLSystem(temp);
			for (int i = 0; i < n; i++) temp.step();

			ConfigTortue config = new ConfigTortue(longueur, angleRotation);
			Tortue tortue = new Tortue(300, 400, -90, config);
			List<Segment> finalSegments = tortue.interpreter(temp.getCurrentGeneration().toString());
			
			Turtle3D tortue3D = new Turtle3D(config);
			SymbolList generation3D = temp.getCurrentGeneration();
			for (Symbol s : generation3D) {
				s.interpret(tortue3D);
			}
			List<Segment3D> finalSegments3D = tortue3D.getSegments();
			
			this.display.clearSegments();
			final int[] index = {0};

			if (playTimer != null && playTimer.isRunning()) {
				playTimer.stop();
			}
			playTimer = new Timer(20, null);
			playTimer.addActionListener(ev -> {
				if (index[0] >= finalSegments.size()) {
					playTimer.stop();
					play.setText("Play");
					playing = false;
					return;
				}

				List<Segment> current = new ArrayList<>(this.display.getSegments());

				int speed = 10;
				for (int i = 0; i < speed && index[0] < finalSegments.size(); i++) {
					current.add(finalSegments.get(index[0]));
					index[0]++;
				}

				this.display.setSegments(current);
				this.display.repaint();

				final int currentIndex = index[0];
				Platform.runLater(() -> {
					if (this.vue3D != null) {
						List<Segment3D> current3D = new ArrayList<>(finalSegments3D.subList(0, Math.min(currentIndex, finalSegments3D.size())));
						this.vue3D.setSegments(current3D);
						this.vue3D.redraw();
					}
				});
			});

			playTimer.start();			
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

