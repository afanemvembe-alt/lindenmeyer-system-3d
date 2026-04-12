package lindenmeyer.turtle;

import java.awt.Color;

/**
 * Un segment sur un plan 2-dimensionnel.
 */
public class Segment {

    /** Coordonnées de début et du fin du segment. */
    public double x1, y1, x2, y2;
    /** Couleur du segment. */
    public Color color;

    /**
     * Crée un nouveau segment avec les coordonnées et couleurs données.
     * @param x1 valeur x du point de depart
     * @param y1 valeur y du point de depart
     * @param x2 valeur x du point d'arrivée
     * @param y2 valeur y du point d'arrivée
     * @param c couleur du segment
     */
    public Segment(double x1, double y1, double x2, double y2, Color c) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = c;
    }

    /**
     * Retourne la valeur en x du point de départ.
     * @return un double
     */
    public double getX1() {
        return x1;
    }

    /**
     * Retourne la valeur en y du point de départ.
     * @return un double
     */
    public double getY1() {
        return y1;
    }

    /**
     * Retourne la valeur en x du point d'arrivée.
     * @return un double
     */
    public double getX2() {
        return x2;
    }

    /**
     * Retourne la valeur en y du point d'arrivée.
     * @return un double
     */
    public double getY2() {
        return y2;
    }

    /**
     * Retourne la couleur du segment.
     * @return une couleur
     */
    public Color getCouleur() {
        return color;
    }
}
