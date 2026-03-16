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
	private Color drawColor = null;

	

	
	public VueLsystem(LSystem lsystem){
		super();
		this.lsystem= lsystem;
		this.setPreferredSize(new Dimension(700, 500));
		this.lsystem.addListener(this);
	}
	
	public LSystem getLSystem(){
		return this.lsystem;
	}
	
	public List<Segment> getSegments() {
		return this.segments;
	}
	
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

	public void clearSegments() {
		if (segments != null) segments.clear();
	}
	
	public void setDrawColor(Color c) {
		this.drawColor = c;
	}

	public void resetDrawColor() {
		this.drawColor = null;
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		if (segments == null || segments.isEmpty()) return;

		Graphics2D g2 = (Graphics2D) g;
		g2.drawString("L-System Viewer", 10, 20);

		//Calcul des limites de tous les segments
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

		double drawingWidth = maxX - minX;
		double drawingHeight = maxY - minY;

		//Calcul des offsets pour centrer le dessin dans le JPanel
		double offsetX = (getWidth() - drawingWidth * zoom) / 2 - minX * zoom;
		double offsetY = (getHeight() - drawingHeight * zoom) / 2 - minY * zoom;

		//Dessin des segments avec le zoom et offsets appliqués
		for (Segment s : segments) {
			
			if (drawColor != null) {
				g2.setColor(drawColor);
			} else {
				g2.setColor(s.getCouleur());
			}
			
			int x1 = (int) ((s.getX1() * zoom) + offsetX);
			int y1 = (int) ((s.getY1() * zoom) + offsetY);
			int x2 = (int) ((s.getX2() * zoom) + offsetX);
			int y2 = (int) ((s.getY2() * zoom) + offsetY);

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

	//Setter pour un zoom précis
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

		
