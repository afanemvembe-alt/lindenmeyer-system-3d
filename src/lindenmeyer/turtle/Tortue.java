package lindenmeyer.turtle;

import java.util.ArrayList;
import java.util.List;

public class Tortue {
    private double x, y;
    private double angle;

    public Tortue(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public List<Segment> interpreter(String sequence) {
        List<Segment> segments = new ArrayList<>();
        double longueur = 10;

        for (char c : sequence.toCharArray()) {
            if (c == 'F') {
                double nx = x + longueur * Math.cos(Math.toRadians(angle));
                double ny = y + longueur * Math.sin(Math.toRadians(angle));
                segments.add(new Segment(x, y, nx, ny));
                x = nx;
                y = ny;
            } else if (c == '+') {
                angle += 60;
            } else if (c == '-') {
                angle -= 60;
            }
        }

        return segments;
    }
}
