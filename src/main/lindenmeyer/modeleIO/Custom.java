package lindenmeyer.modeleIO;

import java.time.LocalDateTime;

import org.json.JSONObject;

import lindenmeyer.lsystem.LSystem;
import lindenmeyer.rules.GenericRule;
import lindenmeyer.ui.ConfigLsystem;

/**
 * L'implémentation de la classe {@link ModeleIO} pour un modèle Lindenmeyer
 * défini par l'utlisateur
 */
public class Custom extends ModeleIO 
{
    /**
     * Date de création du modèle
     */
    private String timestamp;

    /**
     * Initialise un modèle et sauvegarde la date de création
     * @param name      le nom du système Lindenmeyer
     * @param config    les configurations de l'affichage
     * @param lSystem   le système Lindenmeyer
     */
    public Custom(String name, ConfigLsystem config, LSystem lSystem)
    {
        super(name, config, lSystem);
        this.timestamp = LocalDateTime.now().toString();
    }

    /**
     * Initialise un système à partir d'un objet JSON et sauvegarde
     * la date de création
     * @param object    l'objet JSON
     */
    public Custom(JSONObject object)
    {
        super(object);
        this.timestamp = LocalDateTime.now().toString();
    }

    /**
     * Transforme le modèle au format JSON pour être enregistré plus tard.
     * @return  l'objet JSON
     */
    public JSONObject toJSON()
    {
        JSONObject object = new JSONObject();
        JSONObject ruleObject = new JSONObject();
        JSONObject configObject = new JSONObject();

        object.put("date", this.timestamp);
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
        // configObject.put("maxSteps", 8);
        object.put("config", configObject);

        return object;
    }

    public String getTimestamp() { return this.timestamp; }
}
