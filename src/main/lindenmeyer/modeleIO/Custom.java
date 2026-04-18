package lindenmeyer.modeleIO;

import java.time.LocalDateTime;

import org.json.JSONObject;

import lindenmeyer.lsystem.LSystem;
import lindenmeyer.rules.GenericRule;
import lindenmeyer.ui.ConfigLsystem;

public class Custom extends ModeleIO 
{
    private String timestamp;
    
    public Custom(String name, ConfigLsystem config, LSystem lsystem)
    {
        super(name, config, lsystem);
        this.timestamp = LocalDateTime.now().toString();
    }

    public Custom(JSONObject object)
    {
        super(object);
        this.timestamp = LocalDateTime.now().toString();
    }

    public String getTimestamp() { return this.timestamp; }

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
        configObject.put("maxSteps", 8);
        object.put("config", configObject);

        return object;
    }
}
