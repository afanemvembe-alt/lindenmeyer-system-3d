package lindenmeyer.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

public class InterfaceLsystem extends JFrame implements ActionListener {

    public VueLsystem display;
    public LSystem lsystem;
	public ConfigLsystem config;

	//Panels de commande
    public JPanel commands, panelGeneration, panelZoom, panelLsystem;

    public JTextField modifAxiom, rule, nbStep;

	//Boutons de controle de l'interface
    public JButton defineLsystem, generate, random, clear, zoomP, zoomM, settings, play;
    
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
	public int maxStep=20;  
	
	//Pour play/pause du Lsystem
	private boolean playing = false;
	private Timer playTimer;
	
    public InterfaceLsystem() {
        super("LSystem");
		loadPresets();

		// not sure about this
        MenubarLsystem menuBar = new MenubarLsystem(this);
        this.setJMenuBar(menuBar);

        this.commands = new JPanel();
        Color bg = new Color(245,245,245);
		this.commands.setBackground(bg);
        //this.commands.setLayout(new GridLayout(4,1,5,5));
        this.commands.setLayout(new BoxLayout(this.commands, BoxLayout.Y_AXIS));
        this.commands.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Commandes"),BorderFactory.createEmptyBorder(10,10,10,10)));
        this.commands.setPreferredSize(new Dimension(300, 200));

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
		
		//Definition de la police des boutons
		this.defineLsystem.setFont(uiFont);
		this.generate.setFont(uiFont);
		this.random.setFont(uiFont);
		this.clear.setFont(uiFont);
		this.zoomP.setFont(uiFont);
		this.zoomM.setFont(uiFont);
		this.settings.setFont(uiFont);
		this.play.setFont(uiFont);

		//Action des boutons
        this.defineLsystem.addActionListener(this);
        this.generate.addActionListener(this);
        this.clear.addActionListener(this);
        this.zoomP.addActionListener(this);
        this.zoomM.addActionListener(this);
        this.random.addActionListener(this);
        this.settings.addActionListener(this);
        this.play.addActionListener(this);

		//Taille des JTextField
        Dimension taille = new Dimension(180, 30);
        this.modifAxiom.setPreferredSize(taille);
        this.rule.setPreferredSize(taille);
        this.nbStep.setPreferredSize(taille);

		//Creation de bordures pour chaque sous Panel
        this.panelLsystem = new JPanel(new FlowLayout());
        this.panelGeneration = new JPanel(new FlowLayout());
        this.panelZoom = new JPanel(new FlowLayout());
        this.panelLsystem.setBorder(BorderFactory.createTitledBorder("Definition du LSystem"));
		this.panelGeneration.setBorder(BorderFactory.createTitledBorder("Generation"));
		this.panelZoom.setBorder(BorderFactory.createTitledBorder("Affichage"));
		this.panelInfo = new JPanel(new BorderLayout());
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
				return;
			}
			index = index - 1;
			if (index >= this.history.size()) {
				index = this.history.size() - 1;
			}
			State s = (State) this.history.getState(index);
			SymbolList symbols = s.getState();
			ConfigTortue config = new ConfigTortue(this.config.getPas(), this.config.getAngle());
			Tortue tortue = new Tortue(300, 400, -90, config);
			List<Segment> segments = tortue.interpreter(symbols.toString());
			this.display.setSegments(segments);
			this.display.repaint();
		});
		
		
		//Ajout de composants dans le sous Panel correspondant
        this.panelLsystem.add(ligneAxiom);
        this.panelLsystem.add(ligneRegle);
        //this.panelLsystem.add(presetSelector);
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
        this.commands.add(this.panelGeneration);
        this.commands.add(this.panelZoom);
        this.commands.add(this.panelInfo);
        
		this.paramDialog= new ParamDialog(this);
        this.lsystem = new LSystem(new Axiom("F"));
        this.display = new VueLsystem(this.lsystem);

		// Panel pour le slider
		this.setLayout(new BorderLayout());
        this.display.setPreferredSize(new Dimension(2000, 2000));
        JScrollPane scroll = new JScrollPane(display);
		JPanel sliderPanel = new JPanel(new BorderLayout());
		sliderPanel.setBorder(BorderFactory.createTitledBorder("Historique des generations"));
		sliderPanel.add(this.historySlider, BorderLayout.CENTER);
        JPanel dessin = new JPanel(new BorderLayout());
		dessin.add(scroll, BorderLayout.CENTER);
		dessin.add(sliderPanel, BorderLayout.SOUTH);
		
		//Gestion de la fenetre et ajouts des somposants
        this.add(dessin, BorderLayout.CENTER);
        this.add(this.commands, BorderLayout.EAST);
        
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

	public ConfigLsystem getInterfaceConfig() { return this.config; }
    
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

	public void setLSystem(LSystem lSystem)
	{
		this.lsystem = lSystem;
	}

	public void setConfig(ConfigLsystem config) { this.config = config; }

	public VueLsystem getVueLsystem()
	{
		return this.display;
	}

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
			LSystem temp = new LSystem(
					new Axiom(lSystem.getAxiome().getContent()),
					lSystem.getRegles(),
					lSystem.getSymbolFactory()
			);

			// if we're running on a preset ??
			history.addState(new State(temp.getCurrentGeneration()));

			for (int i = 0; i<n ; i++)
			{
				temp.step();
				history.addState(new State(temp.getCurrentGeneration()));
			}

			List<Segment> finalSegments = tortue.interpreter(temp.getCurrentGeneration().toString());

			SwingUtilities.invokeLater(() -> {
					this.display.setSegments(finalSegments);
					this.display.getLSystem().setAxiome(new Axiom(lSystem.getAxiome().getContent()));
					this.display.repaint();
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
        }

        else if (e.getSource() == this.zoomP) {
            this.display.zoomIn();
        }

        else if (e.getSource() == this.zoomM) {
			this.display.zoomOut();
        }

        else if (e.getSource() == this.random) {
			int pos = (int)(Math.random() * this.presets.size());
			Preset chosen = this.presets.get(pos);
			draw(step, chosen.getLSys(), chosen.getConfig(), this.display, this.history);
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

			ConfigTortue config = new ConfigTortue(this.config.getPas(), this.config.getAngle());
			Tortue tortue = new Tortue(300, 400, -90, config);
			List<Segment> finalSegments = tortue.interpreter(temp.getCurrentGeneration().toString());
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

				// Ajouter plusieurs segments par tick pour accélérer
				int speed = 10; 
				for (int i = 0; i < speed && index[0] < finalSegments.size(); i++) {
					current.add(finalSegments.get(index[0]));
					index[0]++;
				}

				this.display.setSegments(current);
				this.display.repaint();
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
