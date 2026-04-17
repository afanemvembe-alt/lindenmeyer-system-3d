package lindenmeyer.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import lindenmeyer.lsystem.LSystem;
import lindenmeyer.lsystem.LsystemListener;
import lindenmeyer.turtle.Segment;
import lindenmeyer.turtle.Tortue;

//ajouter un updateusize dans le constructeur de vuelystem
public class VueLsystem extends JPanel implements LsystemListener {

    private LSystem lsystem;
    private List<Segment> segments = new ArrayList<>();
    private double zoom = 1; // taille raisonnable
    private Color drawColor = null;
    private Tortue tortue;

    // petit espace avec les bords
    private final int paddingX = 10;
    private final int paddingY = 10;

    public VueLsystem(LSystem lsystem) {
        super();
        this.lsystem = lsystem;
        updateViewSize();
        this.lsystem.addListener(this);
    }

    public LSystem getLSystem() {
        return this.lsystem;
    }

    public void setLSystem(LSystem lsystem) {
        this.lsystem = lsystem;
        this.repaint();
    }

    public List<Segment> getSegments() {
        return this.segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
        this.repaint();
    }

    public void clearSegments() {
        if (segments != null) segments.clear();
        this.repaint();
    }

    public void setDrawColor(Color c) {
        this.drawColor = c;
        this.repaint();
    }

    public void resetDrawColor() {
        this.drawColor = null;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (segments == null || segments.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        // Anti-aliasing pour un dessin plus propre
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // 1. Trouver les limites réelles du L-System pour le centrage
        double minX = Double.MAX_VALUE,
            maxX = -Double.MAX_VALUE;
        double minY = Double.MAX_VALUE,
            maxY = -Double.MAX_VALUE;

        for (Segment s : segments) {
            minX = Math.min(minX, Math.min(s.getX1(), s.getX2()));
            maxX = Math.max(maxX, Math.max(s.getX1(), s.getX2()));
            minY = Math.min(minY, Math.min(s.getY1(), s.getY2()));
            maxY = Math.max(maxY, Math.max(s.getY1(), s.getY2()));
        }

        // 2. Calculer la taille occupée par le L-System
        double systemWidth = (maxX - minX) * zoom;
        double systemHeight = (maxY - minY) * zoom;

        // 3. Calculer l'offset pour CENTRER dans le JPanel de 1000x1000
        // On ignore totalement le Viewport ici !
        double offsetX = (getWidth() - systemWidth) / 2 - (minX * zoom);
        double offsetY = (getHeight() - systemHeight) / 2 - (minY * zoom);

        // 4. Dessin
        g2.setStroke(new BasicStroke(1));

        for (Segment s : segments) {
            g2.setColor(drawColor != null ? drawColor : s.getCouleur());
            int x1 = (int) (s.getX1() * zoom + offsetX);
            int y1 = (int) (s.getY1() * zoom + offsetY);
            int x2 = (int) (s.getX2() * zoom + offsetX);
            int y2 = (int) (s.getY2() * zoom + offsetY);
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    // Zoom manuel - CORRIGÉ
    public void zoomIn() {
        zoom *= 1.2;
        updateViewSize();
    }

    public void zoomOut() {
        zoom /= 1.2;
        updateViewSize();
    }

    /**
     * Met à jour la taille du JPanel pour que le JScrollPane
     * sache qu'il doit agrandir/réduire les barres de défilement.
     */
    private void updateViewSize() {
        // On base la taille sur le zoom (ex: 1000 pixels de base)
        int newSize = (int) (1000 * zoom);
        this.setPreferredSize(new Dimension(newSize, newSize));

        // Revalidate force le JScrollPane à recalculer les barres de défilement
        this.revalidate();
        this.repaint();
    }

    @Override
    public void lsystemUpdated(Object source) {
        // Si le L-système change (nouvelle génération), on redessine
        repaint();
    }
}
