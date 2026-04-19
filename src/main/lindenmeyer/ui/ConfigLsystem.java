package lindenmeyer.ui;

import org.json.JSONObject;

public class ConfigLsystem {
    public int angle;
    public int pas;
    public double startX;
    public double startY;
	public String info;

    public ConfigLsystem(int angle, int pas, double startX, double startY, String info) {
        this.angle = angle;
        this.pas = pas;
        this.startX = startX;
        this.startY = startY;
        this.info = info;
    }

    public ConfigLsystem(JSONObject obj)
    {
        JSONObject config = obj.getJSONObject("config");
        this.angle = config.getInt("angle");
        this.pas = config.getInt("pas");
        this.startX = config.getInt("startX");
        this.startY = config.getInt("startY");

        // String description = config.getString("description");

        String info = obj.getString("name")
        // + "\nDescription: " + description
        + "\nAngle: " + angle
        + "\nLongueur: " + this.pas;

        this.info = info;
    }

    public String toString() { return this.info; }

    public int getAngle() { return this.angle; }

    public void setAngle(int angle) { this.angle = angle; }

    public int getPas() { return this.pas; }

    public void setPas(int pas) { this.pas = pas; }

    public double getStartX() { return this.startX; }

    public double getStartY() { return this.startY; }

    // public void setDescription(String description) { this.info = description; }
}
