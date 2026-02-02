package lindenmeyer.turtle;

public class Turtle {
    private double x, y;
    private double angle;

    public Turtle(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void moveForward(double distance) {
        // à implémenter
    }

    public void turn(double deltaAngle) {
        angle += deltaAngle;
    }
}
