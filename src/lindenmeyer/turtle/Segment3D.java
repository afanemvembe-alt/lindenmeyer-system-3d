package lindenmeyer.turtle;

import java.awt.Color;
import java.util.Objects;

/**
 * Repésente un segment dans un espace 3D, composé de deux {@link Coord3D}.
 */
public class Segment3D {
    /** début du segment */
    public Coord3D start;
    /** fin du segment */
    public Coord3D end;
    /** couleur du segment */
    public Color color; // couleur de la branche

    /**
     * Crée un segment à partir de deux points fournis et d'une couleur.
     * 
     * @param start début du segment
     * @param end   fin du segment
     * @param c     couleur du segment
     */
    public Segment3D(Coord3D start, Coord3D end, Color c) {
        this.start = new Coord3D(start);
        this.end = new Coord3D(end);
        this.color = c; // ✔ correction ici
    }

    /**
     * Crée un segment à partir de coordonnées fournies.
     * 
     * @param x1
     * @param y1
     * @param z1
     * @param x2
     * @param y2
     * @param z2
     * @param color couleur du segment
     */
    public Segment3D(double x1, double y1, double z1,
            double x2, double y2, double z2,
            Color color) {

        this.start = new Coord3D(x1, y1, z1);
        this.end = new Coord3D(x2, y2, z2);
        this.color = color; // ✔ correction ici
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Segment3D) {
            Segment3D other = (Segment3D) obj;

            return start.equals(other.start) && end.equals(other.end) && color.equals(other.color);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, color);
    }
}