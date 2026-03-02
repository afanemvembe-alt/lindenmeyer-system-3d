package lindenmeyer.turtle;

import java.util.ArrayList;
import java.util.List;

public class Tortue {

    private double x;
    private double y;
    private double angle;

    private double longueur;
    private double angleRotation;

    public Tortue(double x, double y, double angle, double longueur, double angleRotation) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.longueur = longueur;
        this.angleRotation = angleRotation;
    }

    public List<Segment> interpreter(String sequence) {

        List<Segment> segments = new ArrayList<>();

        for (char c : sequence.toCharArray()) {

            if (c == 'F') {

                double nx = x + longueur * Math.cos(Math.toRadians(angle));
                double ny = y + longueur * Math.sin(Math.toRadians(angle));

                segments.add(new Segment(x, y, nx, ny));

                x = nx;
                y = ny;

            } else if (c == '+') {
                angle += angleRotation;

            } else if (c == '-') {
                angle -= angleRotation;
            }
        }

        return segments;
    }
}