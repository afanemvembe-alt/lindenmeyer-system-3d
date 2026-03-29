package lindenmeyer.turtle;

import static java.lang.Math.*;

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
        x += offset;
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

        double dx = distance * sin(angle_z) * cos(angle_x);
        double dy = distance * sin(angle_z) * cos(angle_x);
        double dz = distance * cos(angle_z);

        translate(dx, dy, dz);
    }
}
