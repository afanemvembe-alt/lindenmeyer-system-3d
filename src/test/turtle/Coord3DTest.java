package turtle;

import static org.junit.jupiter.api.Assertions.*;

import lindenmeyer.turtle.Coord3D;
import org.junit.jupiter.api.Test;

public class Coord3DTest {

    static final Coord3D a = new Coord3D(0, 0, 0);
    static final Coord3D b = new Coord3D(1, 0, 0);
    static final Coord3D c = new Coord3D(0, 1, 0);
    static final Coord3D d = new Coord3D(0, 0, 1);
    static final Coord3D e = new Coord3D(0, 0, 0);
    static final Coord3D f = new Coord3D(1, 1, 1);

    @Test
    void testEquals() {
        assertEquals(a, a);
        assertEquals(e, a);
        assertNotEquals(b, c);
    }

    @Test
    void translate() {
        Coord3D tmp = new Coord3D(a);

        tmp.moveX(1);
        assertEquals(b, tmp);

        tmp.moveX(-1);
        tmp.moveY(1);
        assertEquals(c, tmp);
        tmp.moveY(-1);

        tmp.moveZ(1);
        assertEquals(d, tmp);
        tmp.moveZ(-1);

        tmp.translate(1, 1, 1);

        assertEquals(f, tmp);
    }

    @Test
    void translateAngle() {
        Coord3D tmp = new Coord3D(a);

        tmp.translateAngle(1, 0, 90);
        tmp.translateAngle(1, 90, 90);
        tmp.translateAngle(1, 0, 0);

        assertTrue(f.x - tmp.x <= 1e-5);
        assertTrue(f.y - tmp.y <= 1e-5);
        assertTrue(f.z - tmp.z <= 1e-5);
    }
}
