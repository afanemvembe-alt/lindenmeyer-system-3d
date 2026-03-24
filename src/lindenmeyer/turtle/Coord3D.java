package lindenmeyer.turtle;

/**
 * Une coordonnée 3-dimensionnelle.
 */
public class Coord3D {
    /**
     * Positions dans l'espace du point
     */
    public double x, y, z;

    /**
     * Crée un point à partir des coordonnées données.
     * 
     * @param x position x dans l'espace
     * @param y position y dans l'espace
     * @param z position z dans l'espace
     */
    public Coord3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Crée un point par défaut au centre de l'espace.
     * 
     * Ses coordonnées sont donc (0, 0, 0).
     */
    public Coord3D() {
        this(0, 0, 0);
    }

    public void moveX(int offset) {
        x += offset;
    }

    public void moveY(int offset) {
        y += offset;
    }

    public void moveZ(int offset) {
        x += offset;
    }

    public void translate(int x, int y, int z) {
        moveX(x);
        moveY(y);
        moveZ(z);
    }
}
