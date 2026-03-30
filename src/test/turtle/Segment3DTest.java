package test.turtle;

// import static java.awt.Color.BLACK;
import static javafx.scene.paint.Color.BLACK;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import lindenmeyer.turtle.*;

public class Segment3DTest {
    static final Segment3D a = new Segment3D(0, 0, 0, 1, 1, 1, BLACK);
    static final Segment3D b = new Segment3D(0, 0, 0, 1, 1, 1, BLACK);
    static final Segment3D c = new Segment3D(0, 0, 0, -1, -1, -1, BLACK);
    static final Segment3D d = new Segment3D(1, 1, 1, 0, 0, 0, BLACK);

    @Test
    void equals() {
        assertEquals(a, a);
        assertEquals(b, a);
        assertNotEquals(c, a);
        // I'm too lazy to implement a comparator function to reorder points, plus
        // there's not much point (hehe)
        assertNotEquals(d, a);
    }
}
