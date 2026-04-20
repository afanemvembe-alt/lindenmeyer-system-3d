package lindenmeyer.modeleIO;

import org.json.JSONObject;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.lsystem.LSystem;
import lindenmeyer.rules.GenericRule;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.ui.ConfigLsystem;

/**
 * La classe abstraite representante un système Lindenmeyer {@link LSystem}
 * et les configurations d'affichage {@link ConfigLsystem}dans l'interface utlisateur
 */
public abstract class ModeleIO
{
    protected String name;
    protected ConfigLsystem config;
    protected LSystem lSystem;

    /**
     * Initialise un modèle avec son nom, ses configuraitons et son système Lindenmyer
     * @param name      le nom du système Lindenmyer
     * @param config    les configurations d'affichage
     * @param lSystem   le système Lindenmyer
     */
    public ModeleIO(String name, ConfigLsystem config, LSystem lSystem)
    {
        this.name = name;
        this.config = config;
        this.lSystem = lSystem;
    }

    /**
     * Constructeur qui initialise un modèle à partir d'un objet JSON
     * @param object    l'objet JSON
     */
    public ModeleIO(JSONObject object)
    {
        // System.out.println(object.toString());
        this.name = object.getString("name");

        JSONObject configObject = object.getJSONObject("config");

        String info = object.getString("name")
        // + " - Max steps " + configObject.getInt("maxSteps")
        // + "\nDescription: " + configObject.getString("description")
        + "\nAngle: " + configObject.getInt("angle")
        + "\nLongueur: " + configObject.getInt("pas");

        this.config = new ConfigLsystem(
            configObject.getInt("angle"),
            configObject.getInt("pas"),
            configObject.getInt("startX"),
            configObject.getInt("startY"),
            info
        );

        this.lSystem = new LSystem(new Axiom(object.getString("axiom")), new RuleSet(), new SymbolFactory());

        JSONObject rulesObject = object.getJSONObject("rules");
        for (String key : rulesObject.keySet()) {
            if (key.length() != 1) {
                throw new IllegalArgumentException(
                    "Rule key must be a single character: " + key
                );
            }

            char symbol = key.charAt(0);
            String replacement = rulesObject.getString(key);
            this.lSystem.ajouterRegle(symbol, replacement);
        }
    }

    /**
     * Retourne un objet JSON representant le modèle Lindenmyer
     * @return  L'objet JSON
     */
    public JSONObject toJSON()
    {
        JSONObject object = new JSONObject();
        JSONObject ruleObject = new JSONObject();
        JSONObject configObject = new JSONObject();

        object.put("name", name);
        object.put("axiom", lSystem.getAxiome().toString());

        for (GenericRule rule : this.lSystem.getRegles())
        {
            ruleObject.put(rule.getPredecessor().toString(), rule.getSuccessor().toString());
        }
        object.put("rules", ruleObject);

        configObject.put("angle", this.config.getAngle());
        configObject.put("pas", this.config.getPas());
        configObject.put("startX", this.config.getStartX());
        configObject.put("startY", this.config.getStartY());
        // configObject.put("description", this.lSys)
        // configObject.put("maxSteps", 8);

        object.put("config", configObject);
        // System.out.println(object.toString());

        return object;
    }

    // Getters et setters

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public ConfigLsystem getConfig() { return this.config; }

    public void setConfig(ConfigLsystem config) { this.config = config; }

    public LSystem getLSystem() { return this.lSystem; }

    public void setLSystem(LSystem lSys) { this.lSystem = lSys; }
}
