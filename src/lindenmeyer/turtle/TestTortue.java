package lindenmeyer.turtle;

import java.util.List;

public class TestTortue {
    public static void main(String[] args) {
        Tortue t = new Tortue(0, 0, 0);
        List<Segment> segs = t.interpreter("F+F-F");

        for (Segment s : segs) {
            System.out.println("Segment: (" + s.x1 + "," + s.y1 + ") -> (" + s.x2 + "," + s.y2 + ")");
        }
    }
}
