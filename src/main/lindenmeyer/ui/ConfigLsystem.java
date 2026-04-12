package lindenmeyer.ui;

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
}
