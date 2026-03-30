package lindenmeyer.turtle;

// import java.awt.Color;
import javafx.scene.paint.Color;
import java.util.*;

public class Turtle3D extends AbstractTurtle3D {
    private Deque<Coord3D> storedPositions;
    private List<Segment3D> segments;
    private Coord3D position;
    private double angle_x, angle_z;
    private Color color;
    private double x_min, x_max, y_min, y_max, z_min, z_max;
    private ColorFactory colorFactory;

    @Override
    public void setColorOf(Object o) {
        color = colorFactory.getColor(o);
    }

    public Turtle3D(ConfigTortue config) {
        super(config);
        storedPositions = new ArrayDeque<>();
        segments = new ArrayList<>();
        position = new Coord3D();
        angle_x = 0;
        angle_z = 0;
        color = Color.BLACK;
        colorFactory = new ColorFactory();
    }

    private void updateBounds() {
        if (position.x < x_min) {
            x_min = position.x;
        } else if (position.x > x_max) {
            x_max = position.x;
        }

        if (position.y < y_min) {
            y_min = position.y;
        } else if (position.y > y_max) {
            y_max = position.y;
        }

        if (position.z < z_min) {
            z_min = position.z;
        } else if (position.z > z_max) {
            z_max = position.z;
        }
    }

    public class Bounds {
        public double x_min, x_max, y_min, y_max;

        public Bounds(double x_min, double x_max, double y_min, double y_max) {
            this.x_min = x_min;
            this.x_max = x_max;
            this.y_min = y_min;
            this.y_max = y_max;
        }
    }

    public Bounds getBounds() {
        return new Bounds(x_min, x_max, y_min, y_max);
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

        // if (angle_x > 360) {
        //     angle_x -= 360;
        // } else if (angle_x < 0) {
        //     angle_x += 360;
        // }
    }

    @Override
    public void changeAngleZ(boolean clockwise) {
        double mod = clockwise ? 1 : -1;

        angle_z += getConfig().getAngleRotation() * mod;

        if (angle_z > 360) {
            angle_z -= 360;
        } else if (angle_x < 0) {
            angle_z += 360;
        }
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

    public List<Segment3D> getSegments() {
        return segments;
    }
}
