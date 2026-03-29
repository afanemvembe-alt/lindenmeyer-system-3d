package lindenmeyer.turtle;

import java.awt.Color;
import java.util.*;

public class Turtle3D extends AbstractTurtle3D {
    private Deque<Coord3D> storedPositions;
    private List<Segment3D> segments;
    private Coord3D position;
    private double angle_x, angle_z;
    private Color color;

    public Turtle3D(ConfigTortue config) {
        super(config);
        storedPositions = new ArrayDeque<>();
        segments = new ArrayList<>();
        position = new Coord3D();
        angle_x = 0;
        angle_z = 0;
        color = Color.BLACK;
    }

    private void move(boolean forwards) {
        Coord3D fst = new Coord3D(position);

        double mod = forwards ? 1 : -1;
        position.translateAngle(getConfig().getPas() * mod, angle_x, angle_z);

        segments.add(new Segment3D(fst, position, color));
    }

    @Override
    public void forward() {
        move(true);
    }

    @Override
    public void backward() {
        move(false);
    }

    @Override
    public void changeAngleX(boolean clockwise) {
        double mod = clockwise ? 1 : -1;

        angle_x += getConfig().getAngleRotation() * mod;
    }

    @Override
    public void changeAngleZ(boolean clockwise) {
        double mod = clockwise ? 1 : -1;

        angle_z += getConfig().getAngleRotation() * mod;
    }

    @Override
    public void savePosition() {
        storedPositions.add(new Coord3D(position));
    }

    @Override
    public void restorePosition() {
        position = storedPositions.pop();
    }

    @Override
    public void changeColor(Color c) {
        color = c;
    }
}
