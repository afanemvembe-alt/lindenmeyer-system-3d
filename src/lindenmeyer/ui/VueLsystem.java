package lindenmeyer.ui;

import lindenmeyer.lsystem.*;
import lindenmeyer.turtle.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class VueLsystem extends JPanel implements LsystemListener {

    private LSystem lsystem;
    private List<Segment> segments = new ArrayList<>();
    private double zoom =1; // taille raisonnable
    private Color drawColor = null;

    // petit espace avec les bords
    private final int paddingX = 10;
    private final int paddingY = 10;

    public VueLsystem(LSystem lsystem) {
        super();
        this.lsystem = lsystem;
        this.setPreferredSize(new Dimension(1000, 1000)); // grand espace scrollable
        this.lsystem.addListener(this);
    }

    public LSystem getLSystem() { return this.lsystem; }
    public void setLSystem(LSystem lsystem) {
    this.lsystem = lsystem;
    this.repaint();
}

    public List<Segment> getSegments() { return this.segments; }

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

        // 🔹 Calcul des limites
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;

        for (Segment s : segments) {
            minX = Math.min(minX, Math.min(s.getX1(), s.getX2()));
            minY = Math.min(minY, Math.min(s.getY1(), s.getY2()));
        }

        // Récupérer le cadre bleu (viewport)
        JViewport viewport = (JViewport) this.getParent();
        Rectangle viewRect = viewport.getViewRect();

        // Position en haut à gauche DU CADRE BLEU
        double offsetX = 1;
        double offsetY = viewRect.y + paddingY - minY * zoom;

        //Dessin
        g2.setStroke(new BasicStroke(1));

        for (Segment s : segments) {
            g2.setColor(drawColor != null ? drawColor : Color.BLACK);

            int x1 = (int) (s.getX1() * zoom + offsetX);
            int y1 = (int) (s.getY1() * zoom + offsetY);
            int x2 = (int) (s.getX2() * zoom + offsetX);
            int y2 = (int) (s.getY2() * zoom + offsetY);

            g2.drawLine(x1, y1, x2, y2);
        }
    }

    //Zoom manuel
    public void zoomIn() {
        zoom *= 1.2;
        repaint();
    }
    

    public void zoomOut() {
        zoom /= 1.2;
        repaint();
    }

    @Override
    public void lsystemUpdated(Object source) {
        repaint();
    }
}