package lindenmeyer.ui;

import lindenmeyer.lsystem.LSystem;

public class Preset 
{
    private String name;
    private ConfigLsystem config;
    private LSystem lSys;
    
    public Preset(String name, ConfigLsystem config, LSystem lSys)
    {
        this.name = name;
        this.config = config;
        this.lSys = lSys;
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public ConfigLsystem getConfig() { return this.config; }

    public void setConfig(ConfigLsystem config) { this.config = config; }

    public LSystem getLSys() { return this.lSys; }

    public void setLSystem(LSystem lSys) { this.lSys = lSys; }
}
