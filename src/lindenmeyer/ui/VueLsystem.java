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
    private double zoom = 0.6; // un peu plus gros qu'avant
    private Color drawColor = null;

    // Padding autour du dessin
    private final int paddingX = 10;
    private final int paddingY = 10;

    public VueLsystem(LSystem lsystem) {
        super();
        this.lsystem = lsystem;
        this.setPreferredSize(new Dimension(700, 500));
        this.lsystem.addListener(this);
    }

    public LSystem getLSystem() { return this.lsystem; }
    public List<Segment> getSegments() { return this.segments; }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
        // Pas de centrage, juste le zoom par défaut
        this.repaint();
    }

    public void clearSegments() {
        if (segments != null) segments.clear();
        this.repaint();
    }

    public void setDrawColor(Color c) { this.drawColor = c; this.repaint(); }
    public void resetDrawColor() { this.drawColor = null; this.repaint(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (segments == null || segments.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;

        // Limites du dessin
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        for (Segment s : segments) {
            minX = Math.min(minX, Math.min(s.getX1(), s.getX2()));
            minY = Math.min(minY, Math.min(s.getY1(), s.getY2()));
        }

        // Offset pour placer en haut à gauche
        double offsetX = paddingX - minX * zoom;
        double offsetY = paddingY - minY * zoom;

        // Dessin des segments
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

    // Zoom manuel
    public void zoomIn() { zoom *= 1.2; this.repaint(); }
    public void zoomOut() { zoom /= 1.2; this.repaint(); }

    @Override
    public void lsystemUpdated(Object source) {
        this.repaint();
    }
}