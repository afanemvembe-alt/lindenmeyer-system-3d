package lindenmeyer.turtle;

import static java.lang.Math.*;
import java.util.Objects;

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

    /**
     * Crée un nouveau point à partir du point donné.
     * 
     * @param coord
     */
    public Coord3D(Coord3D coord) {
        this(coord.x, coord.y, coord.z);
    }

    /**
     * Déplace le point sur l'axe x.
     * 
     * @param offset longueur de déplacement
     */
    public void moveX(double offset) {
        x += offset;
    }

    /**
     * Déplace le point sur l'axe y.
     * 
     * @param offset longueur de déplacement
     */
    public void moveY(double offset) {
        y += offset;
    }

    /**
     * Déplace le point sur l'axe z.
     * 
     * @param offset longueur de déplacement
     */
    public void moveZ(double offset) {
        z += offset;
    }

    /**
     * Effectue une translation du point en utilisant dx, dy, et dz.
     * 
     * @param x dx
     * @param y dy
     * @param z dz
     */
    public void translate(double x, double y, double z) {
        moveX(x);
        moveY(y);
        moveZ(z);
    }

    /**
     * Effectue une translation du point en utilisant une distance, et deux valeurs
     * d'angle.
     * 
     * @param distance distance du déplacement
     * @param angle_x  angle sur le plan (x, y) du déplacement
     * @param angle_z  angle sur le plan z du déplacement
     */
    public void translateAngle(double distance, double angle_x, double angle_z) {
        System.err.println("angle x: " + angle_x + " angle z: " + angle_z);

        if (angle_z > 180) {
            angle_z = 180 - angle_z;
            angle_x += 180;
            // System.err.println("z superieur, on applique la transformation: " + angle_x);
        } else if (angle_z < 0) {
            angle_z += 180;
            angle_x += 180;
        }

        System.err.println("after mod: angle x: " + angle_x + " angle z: " + angle_z);

        angle_x = toRadians(angle_x);
        angle_z = toRadians(angle_z);

        double dx = distance * sin(angle_z) * cos(angle_x);
        double dy = distance * sin(angle_z) * sin(angle_x);
        double dz = distance * cos(angle_z);

        translate(dx, dy, dz);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Coord3D) {
            Coord3D other = (Coord3D) obj;

            return x == other.x && y == other.y && z == other.z;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return String.format("Coord3D { x: %f, y: %f, z: %f }", x, y, z);
    }
}
