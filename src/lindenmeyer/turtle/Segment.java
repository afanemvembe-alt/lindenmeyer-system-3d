package lindenmeyer.turtle;

import java.awt.Color;
import java.awt.Graphics2D;

public class Segment {
    // Coordonnées en double pour la précision des calculs
    private double x1, y1;
    private double x2, y2;
    private Color couleur;

    public Segment(double x1, double y1, double x2, double y2, Color couleur) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.couleur = couleur;
    }

    /**
     * Permet au segment de se dessiner lui meme sur le graphique
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(this.couleur);
        // On cast en (int) car drawLine attend des entiers pour les pixels
        g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    // --Getters (si besoin de manipuler les données plus tard ? ) --
    public double getX1() { return x1; }
    public double getY1() { return y1; }
    public double getX2() { return x2; }
    public double getY2() { return y2; }
    public Color getCouleur() { return couleur; }
}