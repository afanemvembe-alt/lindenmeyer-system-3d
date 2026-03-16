package lindenmeyer.turtle;

public class Coord3D {
    public double x, y, z;

    public Coord3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coord3D() {
        this(0, 0, 0);
    }
}
