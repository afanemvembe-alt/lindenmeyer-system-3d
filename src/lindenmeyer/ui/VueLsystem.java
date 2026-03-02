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
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (segments != null) {
			g.setColor(Color.BLACK);
			for (Segment s : segments) {
				g.drawLine((int)s.x1, (int)s.y1, (int)s.x2, (int)s.y2);
			}
		}
	}
	
	@Override	
	public void lsystemUpdated(Object source){
		this.repaint();
	}
}
		
