package lindenmeyer.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import lindenmeyer.axiom.Axiom;
import lindenmeyer.lsystem.LSystem;
import lindenmeyer.lsystem.history.History;
import lindenmeyer.lsystem.history.State;
import lindenmeyer.rules.GenericRule;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.rules.RuleSetFactory;
import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;
import lindenmeyer.turtle.ConfigTortue;
import lindenmeyer.turtle.Segment;
import lindenmeyer.turtle.Segment3D;
import lindenmeyer.turtle.Tortue;
import lindenmeyer.turtle.Turtle3D;
// import java.awt.Button;
import lindenmeyer.ui.components.*;
import javax.swing.*;
import java.nio.file.Path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;

import lindenmeyer.axiom.*;
import lindenmeyer.symbols.*;
import lindenmeyer.rules.*;
import lindenmeyer.lsystem.*;
import lindenmeyer.lsystem.history.*;
import lindenmeyer.turtle.*;

public class InterfaceLsystem extends JFrame implements ActionListener 
{
    public VueLsystem display;
    public LSystem lsystem;
	public ConfigLsystem config;

	//Panels de commande
    public JPanel commands, panelGeneration, panelZoom, panelLsystem;

    public JTextField modifAxiom, rule, nbStep;

    //Boutons de controle de l'interface
    public Button defineLsystem;
    public Button generate;
    public Button random;
    public Button clear;
    public Button zoomP;
    public Button zoomM;
    public Button settings;
    public Button play;

    //Infos sur les preset
    public JPanel panelInfo;
    public JTextArea presetInfo;

    //Listes de systemes predefinis et leur configuration
    public ArrayList<Preset> presets = new ArrayList<>();

    
    //Boite de dialogue
    private ParamDialog paramDialog;

    //Selecteur de Couleurs
    public JComboBox<String> colorSelector;
    private Color selectedColor = Color.BLACK;

    public JSlider historySlider;
    public History history = new History();
    public int maxStep = 20;

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

    //Navigation et manipulation en 3D
    private PerspectiveCamera camera3D;
    private Rotate rotateX3D;
    private Rotate rotateY3D;
    private int lastMouseX;
    private int lastMouseY;
    private boolean panning3D = false;

    public InterfaceLsystem() {
        super("LSystem");
		loadPresets();

        MenubarLsystem menuBar = new MenubarLsystem(this);
        this.setJMenuBar(menuBar);

        this.commands = new JPanel();
        Color bg = new Color(245, 245, 245);
        this.commands.setBackground(bg);
        this.commands.setLayout(new BoxLayout(this.commands, BoxLayout.Y_AXIS));
        this.commands.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Commandes"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            )
        );
        this.commands.setPreferredSize(new Dimension(300, 800));

        //Les TextFields
        this.modifAxiom = new TextField();
		this.rule = new TextField();
		this.nbStep = new TextField();
		Font uiFont = new Font("Segoe UI", Font.PLAIN, 14);
        
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

        // Dimension boutonSize = new Dimension(130, 30);
        // Button.setDefaultDimension(boutonSize);

        Button.setBaseFont(uiFont);

        //Definition des boutons
        this.defineLsystem = new Button("Definir LSystem");
        this.generate = new Button("Generer");
        this.clear = new Button("Effacer");
        this.zoomP = new Button("Zoom+");
        this.zoomM = new Button("Zoom-");
        this.random = new Button("Random");
        this.settings = new Button("Parametres");
        this.play = new Button("Play/Pause");
        this.switch3D = new Button("Switch3D");

        //Definition de couleur de fond des boutons
        this.generate.setBackground(new Color(120, 200, 120));
        this.random.setBackground(new Color(120, 170, 240));
        this.clear.setBackground(new Color(240, 120, 120));

        //Definition de la taille des boutons
        // Dimension boutonSize = new Dimension(130, 20);
        // Button.updateDimension(boutonSize);
        // this.defineLsystem.setPreferredSize(boutonSize);
        // this.generate.setPreferredSize(boutonSize);
        // this.random.setPreferredSize(boutonSize);
        // this.clear.setPreferredSize(boutonSize);
        // this.zoomP.setPreferredSize(boutonSize);
        // this.zoomM.setPreferredSize(boutonSize);
        // this.settings.setPreferredSize(boutonSize);
        // this.play.setPreferredSize(boutonSize);
        // this.switch3D.setPreferredSize(boutonSize);
        // this.colorSelectButton.setPreferredSize(boutonSize);

        //Definition de la police des boutons
        // this.defineLsystem.setFont(uiFont);
        // this.generate.setFont(uiFont);
        // this.random.setFont(uiFont);
        // this.clear.setFont(uiFont);
        // this.zoomP.setFont(uiFont);
        // this.zoomM.setFont(uiFont);
        // this.settings.setFont(uiFont);
        // this.play.setFont(uiFont);
        // this.switch3D.setFont(uiFont);

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

        defineLsystem.setActionCommand("definir");
        generate.setActionCommand("generer");
        clear.setActionCommand("clear");
        zoomP.setActionCommand("zoomIn");
        zoomM.setActionCommand("zoomOut");
        random.setActionCommand("random");
        settings.setActionCommand("settings");
        play.setActionCommand("play");
        switch3D.setActionCommand("switch3d");

        //Creation de bordures pour chaque sous Panel
        this.panelLsystem = new TitledPanel("Definition du LSystem", new FlowLayout());
		this.panelGeneration = new TitledPanel("Generation", new FlowLayout());
		this.panelZoom = new TitledPanel("Affichage", new FlowLayout());
		this.panelInfo = new TitledPanel("Informations preset", new BorderLayout());
		this.panelInfo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		
        //Infos
        this.presetInfo = new JTextArea(3, 20);
        this.presetInfo.setRows(3);
        this.presetInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        presetInfo.setEditable(false);
        presetInfo.setLineWrap(true);
        presetInfo.setWrapStyleWord(true);
        JScrollPane scrollInfo = new JScrollPane(presetInfo);
        this.panelInfo.add(scrollInfo, BorderLayout.CENTER);

        //Selecteur de couleurs
        String[] colors = {
            "Automatique",
            "Noir",
            "Rouge",
            "Vert",
            "Bleu",
            "Jaune",
            "Magenta",
            "Cyan",
            "Orange",
            "Rose",
            "Gris",
            "Custom...",
        };
        colorSelector = new JComboBox<>(colors);
        colorSelector.setSelectedIndex(0);
        colorSelector.addActionListener(this);

        // Slider historique
        this.historySlider = new JSlider(0, this.maxStep, 0);
        this.historySlider.setMajorTickSpacing(1);
        this.historySlider.setPaintTicks(true);
        this.historySlider.setPaintLabels(true);
        this.historySlider.addChangeListener(e -> {
            if (
                this.history == null || this.historySlider.getValueIsAdjusting()
            ) {
                return;
            }
            int index = this.historySlider.getValue();
            if (index == 0) {
                clear2D();
                clear3D();
                return;
            }
            index = index - 1;
            if (index >= this.history.size()) {
                index = this.history.size() - 1;
            }
            State s = (State) this.history.getState(index);
            SymbolList symbols = s.getState();

            ConfigTortue config = new ConfigTortue(
                this.config.getPas(),
                this.config.getAngle()
            );
            // Pour la 2D
            List<Segment> segments2D = build2DSegments(symbols, 300, 400, config);
            update2D(segments2D);
            // Pour la 3D
            List<Segment3D> segments3D = build3DSegments(symbols, config);
            update3D(segments3D);
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
        sliderPanel.setBorder(
            BorderFactory.createTitledBorder("Historique des generations")
        );
        sliderPanel.add(this.historySlider, BorderLayout.CENTER);
        // cartes 2D / 3D
        this.centerPanel.add(this.scroll2D, "2D");
        this.centerPanel.add(this.fxPanel, "3D");
        // panneau global : cartes au centre, slider en bas
        this.dessin.add(this.centerPanel, BorderLayout.CENTER);
        this.dessin.add(sliderPanel, BorderLayout.SOUTH);
        Platform.runLater(() -> {
            this.vue3D = new Vue3D(new ArrayList<>());
            this.camera3D = new PerspectiveCamera(true);
            this.camera3D.setNearClip(0.1);
            this.camera3D.setFarClip(10000.0);
            this.camera3D.setTranslateZ(-800);
            this.vue3D.setCamera(this.camera3D);
            // rotations globales pour mieux voir la profondeur
            this.rotateX3D = new Rotate(-20, Rotate.X_AXIS);
            this.rotateY3D = new Rotate(20, Rotate.Y_AXIS);
            ((Group) this.vue3D.getRoot()).getTransforms().addAll(
                this.rotateX3D,
                this.rotateY3D
            );
            this.fxPanel.setScene(this.vue3D);
        });

        //Listeners sur la souris
        this.fxPanel.addMouseWheelListener(e -> {
            if (!mode3D) return;
            Platform.runLater(() -> {
                if (this.camera3D != null) {
                    double z =
                        this.camera3D.getTranslateZ() +
                        e.getWheelRotation() * 50;
                    if (z > -100) z = -100;
                    if (z < -5000) z = -5000;
                    this.camera3D.setTranslateZ(z);
                }
            });
        });

        this.fxPanel.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    lastMouseX = e.getX();
                    lastMouseY = e.getY();
                    panning3D = SwingUtilities.isRightMouseButton(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    panning3D = false;
                }
            }
        );

        this.fxPanel.addMouseMotionListener(
            new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (!mode3D) return;

                    int dx = e.getX() - lastMouseX;
                    int dy = e.getY() - lastMouseY;

                    lastMouseX = e.getX();
                    lastMouseY = e.getY();

                    Platform.runLater(() -> {
                        if (panning3D) {
                            if (camera3D != null) {
                                camera3D.setTranslateX(
                                    camera3D.getTranslateX() + dx
                                );
                                camera3D.setTranslateY(
                                    camera3D.getTranslateY() + dy
                                );
                            }
                        } else {
                            if (rotateY3D != null && rotateX3D != null) {
                                rotateY3D.setAngle(
                                    rotateY3D.getAngle() + dx * 0.5
                                );
                                rotateX3D.setAngle(
                                    rotateX3D.getAngle() - dy * 0.5
                                );
                            }
                        }
                    });
                }
            }
        );

        //Scroll vertical pour les commandes
        JScrollPane scrollCommands = new JScrollPane(this.commands);
        scrollCommands.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        scrollCommands.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
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
    
    private javafx.scene.paint.Color awtToFxColor(Color c) {
		if (c == null) return null;
		return javafx.scene.paint.Color.rgb(
			c.getRed(),
			c.getGreen(),
			c.getBlue(),
			c.getAlpha() / 255.0
		);
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
    
    private JDialog loadingDialog() {
		JDialog loading = new JDialog(this, "Chargement", true);
		loading.setLayout(new BorderLayout());
		loading.add(
			new JLabel("Generation en cours...", SwingConstants.CENTER),
			BorderLayout.CENTER
		);
		loading.setSize(200, 100);
		loading.setLocationRelativeTo(this);
		return loading;
	}
	
	//Constructeurs de segments
	private List<Segment> build2DSegments(SymbolList symbols, double startX, double startY, ConfigTortue config) {
		Tortue tortue = new Tortue(startX, startY, -90, config);
		return tortue.interpreter(symbols.toString());
	}
	private List<Segment3D> build3DSegments(SymbolList symbols, ConfigTortue config) {
		Turtle3D tortue3D = new Turtle3D(config);
		for (Symbol s : symbols) {
			s.interpret(tortue3D);
		}
		return tortue3D.getSegments();
	}
	
	//Dessin des systèmes
	private void update2D(List<Segment> segments) {
		this.display.setSegments(segments);
		this.display.repaint();
	}
	private void update3D(List<Segment3D> segments) {
		Platform.runLater(() -> {
			if (this.vue3D != null) {
				this.vue3D.setSegments(segments);
				this.vue3D.redraw();
			}
		});
	}
	
	//Clear 2D et 3D
	private void clear2D() {
		this.display.clearSegments();
		this.display.repaint();
	}
	private void clear3D() {
		update3D(new ArrayList<>());
	}
	
	//Nombre d'étapes
	private int getStepCount(String stepText, int defaultValue) {
		int n = defaultValue;
		try {
			if (!stepText.isEmpty()) {
				n = Integer.parseInt(stepText);
			}
		} catch (NumberFormatException ex) {
			n = defaultValue;
		}

		if (n > this.maxStep) {
			n = this.maxStep;
		}

		return n;
	}
	
	//Copie d'un LSystem 
	private LSystem copyLSystem(LSystem source) {
		return new LSystem(
			new Axiom(source.getAxiome().getContent()),
			source.getRegles(),
			source.getSymbolFactory()
		);
	}
	
	public void resetField(JTextField field) {
		field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void setLSystem(LSystem lSystem) { this.lsystem = lSystem; }

	public ConfigLsystem getInterfaceConfig() { return this.config; }

	public void setInterfaceConfig(ConfigLsystem config) { this.config = config; }

	public VueLsystem getVueLsystem() { return this.display; }

	public void draw(String step, LSystem lSystem, ConfigLsystem config, VueLsystem vue, History history)
	{
		JDialog loading = new JDialog(this, "Chargement", true);
		loading.setLayout(new BorderLayout());
		loading.add(new JLabel("Generation en cours...", SwingConstants.CENTER), BorderLayout.CENTER);
		loading.setSize(200,100);
		loading.setLocationRelativeTo(this);

		history.clear();

		ConfigTortue configTortue = new ConfigTortue(config.getPas(), config.getAngle());

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

			Tortue tortue = new Tortue(
				config.getStartX(), config.getStartY(),
				-90, configTortue
			);

			// why create a new lsystem?
			LSystem temp = copyLSystem(lSystem);

			// if we're running on a preset ??
			history.addState(new State(temp.getCurrentGeneration()));
			for (int i = 0; i<n ; i++)
			{
				temp.step();
				history.addState(new State(temp.getCurrentGeneration()));
			}

			List<Segment> finalSegments = tortue.interpreter(temp.getCurrentGeneration().toString());

            List<Segment> finalSegments2D = build2DSegments(
                temp.getCurrentGeneration(),
                config.getStartX(),
                config.getStartY(),
                configTortue
            );

            // --- Gestion3D pour random------------
            List<Segment3D> finalSegments3D = build3DSegments(
                temp.getCurrentGeneration(),
                configTortue
            );
            update3D(finalSegments3D);

			SwingUtilities.invokeLater(() -> {
					// this.display.setSegments(finalSegments);
					this.presetInfo.setText(config.info);
                    this.display.setLSystem(temp);
                    this.display.getLSystem().setAxiome(
                        new Axiom(lSystem.getAxiome().getContent())
                    );
                    update2D(finalSegments);
                    this.historySlider.setMaximum(this.history.size());
                    this.historySlider.setValue(this.history.size());
                    loading.dispose();
				});
		}).start();
		loading.setVisible(true);
	}


    public void actionPerformed(ActionEvent e) {
        String text = this.modifAxiom.getText();
        String regle = this.rule.getText();
        String step = this.nbStep.getText();

        if (e.getSource() == this.defineLsystem) {
            if (!text.isEmpty()) {
                if (text.matches(".*\\d.*")) {
                    showError(
                        this.modifAxiom,
                        "L'axiome ne peut pas contenir de chiffres"
                    );
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
            } else if (!regle.isEmpty()) {
                String[] rules = regle.split(",");
                for (String r : rules) {
                    String[] parts = r.split(">");
                    if (parts.length != 2) {
                        showError(
                            this.rule,
                            "Regle invalide : \"" +
                                r +
                                "\". Format attendu : symbole>regle"
                        );
                        return;
                    }
                    if (parts[0].length() != 1) {
                        showError(
                            this.rule,
                            "Le symbole doit etre un seul caractère : \"" +
                                r +
                                "\""
                        );
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
			System.out.println(this.lsystem.toString());

			draw(step, this.lsystem, this.config, this.display, this.history);
        }

		// don't think this is necessary anymore
        else if (e.getSource() == this.settings) {		
            this.paramDialog.setVisible(true);
            this.config.setPas(this.paramDialog.getLongueur());
            this.config.setAngle(this.paramDialog.getAngle());
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
                case "Custom..." -> this.selectedColor =
                    JColorChooser.showDialog(
                        this,
                        "Selection de couleur",
                        selectedColor
                    );
                    
            }

            List<Segment> current = this.display.getSegments();
            if (current != null && !current.isEmpty()) {
                this.display.setDrawColor(selectedColor);
                this.display.repaint();
            }
            Platform.runLater(() -> {
				if (this.vue3D != null) {
					if (selectedColor == null) {
						this.vue3D.resetDrawColor();
					} else {
						this.vue3D.setDrawColor(awtToFxColor(selectedColor));
					}
				}
			});
        } 
        
        else if (e.getSource() == this.clear) {
            clear2D();
            clear3D();
        } 
        
        else if (e.getSource() == this.zoomP) {
            if (mode3D) {
                Platform.runLater(() -> {
                    if (this.camera3D != null) {
                        double z = this.camera3D.getTranslateZ() + 100;
                        if (z > -100) z = -100;
                        this.camera3D.setTranslateZ(z);
                    }
                });
            } else {
                this.display.zoomIn();
            }
        } 
        
        else if (e.getSource() == this.zoomM) {
            if (mode3D) {
                Platform.runLater(() -> {
                    if (this.camera3D != null) {
                        double z = this.camera3D.getTranslateZ() - 100;
                        if (z < -5000) z = -5000;
                        this.camera3D.setTranslateZ(z);
                    }
                });
            } else {
                this.display.zoomOut();
            }
        } 

        else if (e.getSource() == this.random) {
            JDialog loading = loadingDialog();
            history = new History();
            new Thread(() -> {
                int pos = (int) (Math.random() * this.preSet.size());
                LSystem chosen = this.preSet.get(pos);

                // Mets à jour les champs (UI) sur EDT
                SwingUtilities.invokeLater(() -> {
                    this.modifAxiom.setText(chosen.getAxiome().getContent());
                    String s = "";
                    for (GenericRule r : chosen.getRegles())
                        s += r.toString() + ",";
                    this.rule.setText(s);
                });

                ConfigLsystem cfg = this.preSetConfig.get(pos);
                
                ConfigTortue configT = new ConfigTortue(cfg.pas, cfg.angle);
       
                LSystem temp = copyLSystem(chosen);
                

                int n = getStepCount(step, 3);
				history.addState(new State(temp.getCurrentGeneration()));
                for (int i = 0; i < n; i++) {
                    temp.step();
                    history.addState(new State(temp.getCurrentGeneration()));
                }

                List<Segment> finalSegments = build2DSegments(
					temp.getCurrentGeneration(),
					cfg.startX,
					cfg.startY,
					configT
				);

                // --- Gestion3D pour random------------
                List<Segment3D> finalSegments3D = build3DSegments(
					temp.getCurrentGeneration(),
					configT
				);
                update3D(finalSegments3D);
                //-------------3D pour random------------

                SwingUtilities.invokeLater(() -> {
                    this.presetInfo.setText(cfg.info);
                    this.display.setLSystem(temp);
                    this.display.getLSystem().setAxiome(
                        new Axiom(chosen.getAxiome().getContent())
                    );
                    update2D(finalSegments);
                    this.historySlider.setMaximum(this.history.size());
                    this.historySlider.setValue(this.history.size());
                    loading.dispose();
                });
            })
                .start();
            loading.setVisible(true);
        } 


        else if (e.getSource() == this.play) {
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

            int n = getStepCount(step, 3);

            LSystem temp = copyLSystem(this.display.getLSystem());
            this.display.setLSystem(temp);
            for (int i = 0; i < n; i++) temp.step();

            ConfigTortue config = new ConfigTortue(this.config.getPas(), this.config.getAngle());
			List<Segment> finalSegments = build2DSegments(
				temp.getCurrentGeneration(),
				300,
				400,
				config
			);

			List<Segment3D> finalSegments3D = build3DSegments(
				temp.getCurrentGeneration(),
				config
			);

            clear2D();
            final int[] index = { 0 };

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

                List<Segment> current = new ArrayList<>(
                    this.display.getSegments()
                );

                int speed = 10;
                for (
                    int i = 0;
                    i < speed && index[0] < finalSegments.size();
                    i++
                ) {
                    current.add(finalSegments.get(index[0]));
                    index[0]++;
                }

                update2D(current);
                final int currentIndex = index[0];
                List<Segment3D> current3D = new ArrayList<>(
					finalSegments3D.subList(
						0,
						Math.min(currentIndex, finalSegments3D.size())
					)
				);
				update3D(current3D);
			});

            playTimer.start();
        }
    }

	public void loadPresets()
	{
		try 
        {
            String fileString = Files.readString(Path.of("src/main/lindenmeyer/ui/presets.json"));

            JSONObject root = new JSONObject(fileString);
            JSONArray presetsArray = root.getJSONArray("presets");


            for (int i=0; i<presetsArray.length(); i++)
            {
                JSONObject presetObj = presetsArray.getJSONObject(i);
                LSystem lSyst = new LSystem(presetObj);
                ConfigLsystem config = new ConfigLsystem(presetObj);

                this.presets.add(new Preset(presetObj.getString("name"), config, lSyst));
            }
        } 
        catch (IOException e) 
        {
            throw new RuntimeException("Failed to read presets.json", e);
        }
	}

	public ArrayList<Preset> getPresets() { return this.presets; }
    
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new InterfaceLsystem();
			}
		});
	}
}

// todo: check for null objects
// todo: update textfield