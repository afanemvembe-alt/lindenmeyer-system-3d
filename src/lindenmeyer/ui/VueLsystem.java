package lindenmeyer.ui;

import lindenmeyer.axiom.*;
import lindenmeyer.symbols.*;
import lindenmeyer.lsystem.*;
import lindenmeyer.rules.*;
import lindenmeyer.turtle.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class VueLsystem extends JPanel implements LsystemListener{
	private LSystem lsystem;
	private List<Segment> segments = new ArrayList<>();
	private double zoom = 1.0;

	

	
	public VueLsystem(LSystem lsystem){
		super();
		this.lsystem= lsystem;
		this.setPreferredSize(new Dimension(700, 500));
		this.lsystem.addListener(this);
	}
	
	public LSystem getLSystem(){
		return this.lsystem;
	}
	
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

	public void clearSegments() {
		if (segments != null) segments.clear();
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (segments == null || segments.isEmpty()) return;

    Graphics2D g2 = (Graphics2D) g;

    // 1. Calcul des limites
    double minX = Double.MAX_VALUE;
    double minY = Double.MAX_VALUE;
    double maxX = Double.MIN_VALUE;
    double maxY = Double.MIN_VALUE;

    for (Segment s : segments) {
        minX = Math.min(minX, Math.min(s.getX1(), s.getX2()));
        minY = Math.min(minY, Math.min(s.getY1(), s.getY2()));
        maxX = Math.max(maxX, Math.max(s.getX1(), s.getX2()));
        maxY = Math.max(maxY, Math.max(s.getY1(), s.getY2()));
    }

    double width = maxX - minX;
    double height = maxY - minY;

    double offsetX = (getWidth() - width) / 2 - minX;
    double offsetY = (getHeight() - height) / 2 - minY;

    // 2. Dessin des segments
    for (Segment s : segments) {
        int x1 = (int)((s.getX1() + offsetX) * zoom);
        int y1 = (int)((s.getY1() + offsetY) * zoom);
        int x2 = (int)((s.getX2() + offsetX) * zoom);
        int y2 = (int)((s.getY2() + offsetY) * zoom);

        g2.drawLine(x1, y1, x2, y2);
    }
}
	

        // Change le zoom en multipliant par un facteur
        public void zoomIn() {
            this.zoom *= 1.2; // zoom+ de 20%
            this.revalidate();
            this.repaint();
        }

        public void zoomOut() {
            this.zoom /= 1.2; // zoom- de 20%
            this.revalidate();
            this.repaint();
        }

        // Optionnel : setter pour un zoom précis
        public void setZoom(double zoom) {
            this.zoom = zoom;
            this.revalidate();
            this.repaint();
        }
	@Override	
	public void lsystemUpdated(Object source){
		this.repaint();
	}
}

		
