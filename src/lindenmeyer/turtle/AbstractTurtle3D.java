package lindenmeyer.turtle;

import java.util.*;

public abstract class AbstractTurtle3D {
    private Coord3D position;
    private double angle_x, angle_y;
    private ConfigTortue config;

    public AbstractTurtle3D(Coord3D position, double angle_x, double angle_y, ConfigTortue config) {
        this.position = position;
        this.angle_x = angle_x;
        this.angle_y = angle_y;
        this.config = config;
    }

    public AbstractTurtle3D(ConfigTortue config) {
        this(new Coord3D(), 0, 0, config);
    }

    public Coord3D getPosition() {
        return position;
    }

    public abstract void forward();

    public abstract void backward();

    public abstract void changeAngleX();

    public abstract void changeAngleY();

    public abstract void savePosition();

    public abstract void restorePosition();

    public abstract List<Segment3D> getSegments();

    public void interpret(Interpretable3D object) {
        object.interpret(this);
    }

    public List<Segment3D> interpretAll(List<? extends Interpretable3D> list) {
        for (Interpretable3D object : list) {
            interpret(object);
        }

        return getSegments();
    }
}
