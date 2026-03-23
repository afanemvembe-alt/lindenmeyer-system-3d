package lindenmeyer.turtle;

import java.awt.Color;

public class Segment3D {
    public Coord3D start;
    public Coord3D end;
    public Color color;// couleur de la branche 

    public Segment3D(Coord3D start, Coord3D end, Color c) {
        this.start = start;
        this.end = end;
        this.Color = c;
    }

    public Segment3D(double x1, double y1, double z1, double x2, double y2, double z2, Color color) {
        this.start= new Coord3D(x1, y1, z1);
        this.end = new Coord3D(x2, y2, z2);
        this.Color= c;
    }
}
