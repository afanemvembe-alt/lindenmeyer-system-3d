package lindenmeyer.modeleIO;

import org.json.JSONObject;

import lindenmeyer.lsystem.LSystem;
import lindenmeyer.ui.ConfigLsystem;

public class Preset extends ModeleIO
{   
    public Preset(String name, ConfigLsystem config, LSystem lsystem)
    {
        super(name, config, lsystem);
    }

    public Preset(JSONObject object)
    {
        super(object);
    }
}
