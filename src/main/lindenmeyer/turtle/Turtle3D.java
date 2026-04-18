package lindenmeyer.turtle;

import java.util.*;
import javafx.scene.paint.Color;

/**
 * Tortue 3D orientee par un repere orthonorme (heading, left, up).
 * Avancer : pos += dist * heading
 */
public class Turtle3D extends AbstractTurtle3D {

    // ------------------------------------------------------------------ //
    //  Etat interne sauvegardable                                         //
    // ------------------------------------------------------------------ //
    private static class TurtleState {

        final double[] pos;
        final double[] heading;
        final double[] left;
        final double[] up;

        TurtleState(
            double[] pos,
            double[] heading,
            double[] left,
            double[] up
        ) {
            this.pos = pos.clone();
            this.heading = heading.clone();
            this.left = left.clone();
            this.up = up.clone();
        }
    }

    // ------------------------------------------------------------------ //
    //  Champs                                                           //
    // ------------------------------------------------------------------ //
    private double[] pos; // position courante
    private double[] heading; // axe H (direction de marche)
    private double[] left; // axe L (gauche)
    private double[] up; // axe U (haut)

    private final Deque<TurtleState> stack = new ArrayDeque<>();
    private final List<Segment3D> segments = new ArrayList<>();

    private Color color = Color.BLACK;
    private ColorFactory colorFactory;

    // Bornes pour le centrage de la vue
    private double xMin, xMax, yMin, yMax, zMin, zMax;

    // ------------------------------------------------------------------ //
    //  Constructeur                                                       //
    // ------------------------------------------------------------------ //
    public Turtle3D(ConfigTortue config) {
        super(config);
        colorFactory = config.getColorFactory();
        reset();
    }

    /** Remet la tortue à l'état initial (origine, dirigée vers le haut). */
    public void reset() {
        pos = new double[] { 0, 0, 0 };
        heading = new double[] { 0, 1, 0 }; // pointe vers +Y (haut écran)
        left = new double[] { -1, 0, 0 }; // pointe vers -X
        up = new double[] { 0, 0, 1 }; // pointe vers +Z (hors écran)
        xMin = yMin = zMin = Double.MAX_VALUE;
        xMax = yMax = zMax = -Double.MAX_VALUE;
    }

    // ------------------------------------------------------------------ //
    //  Mouvements                                                         //
    // ------------------------------------------------------------------ //

    /** Avance : pos += pas * heading  (trace un segment). */
    @Override
    public void forward() {
        move(true);
    }

    /** Recule : pos -= pas * heading  (trace un segment). */
    @Override
    public void backward() {
        move(false);
    }

    private void move(boolean forward) {
        double[] from = pos.clone();
        double d = getConfig().getPas() * (forward ? 1 : -1);
        pos[0] += d * heading[0];
        pos[1] += d * heading[1];
        pos[2] += d * heading[2];
        updateBounds();
        segments.add(
            new Segment3D(
                from[0],
                from[1],
                from[2],
                pos[0],
                pos[1],
                pos[2],
                color
            )
        );
    }

    // ------------------------------------------------------------------ //
    //  Rotations — matrices du tableau                                    //
    // ------------------------------------------------------------------ //

    /**
     * Yaw : RU(±α) — rotation autour de l'axe Up.
     * Fait tourner heading et left dans le plan (heading, left).
     *
     * RU(α) = |  cos  sin  0 |
     *         | -sin  cos  0 |
     *         |   0    0   1 |
     *
     * @param clockwise  true -> +α (symbole +), false -> −α (symbole -)
     */
    @Override
    public void rotateU(boolean clockwise) {
        double a = (getConfig().getAngleRotation() * Math.PI) / 180.0;
        if (!clockwise) a = -a;
        double c = Math.cos(a),
            s = Math.sin(a);
        double[] newH = {
            c * heading[0] + s * left[0],
            c * heading[1] + s * left[1],
            c * heading[2] + s * left[2],
        };
        double[] newL = {
            -s * heading[0] + c * left[0],
            -s * heading[1] + c * left[1],
            -s * heading[2] + c * left[2],
        };
        heading = normalize(newH);
        left = normalize(newL);
        // up reste inchangé
    }

    /**
     * Pitch : RL(±α) — rotation autour de l'axe Left.
     * Fait tourner heading et up dans le plan (heading, up).
     *
     * RL(α) = |  cos  0  -sin |
     *         |   0   1    0  |
     *         |  sin  0   cos |
     *
     * @param clockwise  true → +α (symbole &amp;), false → −α (symbole ^)
     */
    @Override
    public void rotateL(boolean clockwise) {
        double a = (getConfig().getAngleRotation() * Math.PI) / 180.0;
        if (!clockwise) a = -a;
        double c = Math.cos(a),
            s = Math.sin(a);
        double[] newH = {
            c * heading[0] - s * up[0],
            c * heading[1] - s * up[1],
            c * heading[2] - s * up[2],
        };
        double[] newU = {
            s * heading[0] + c * up[0],
            s * heading[1] + c * up[1],
            s * heading[2] + c * up[2],
        };
        heading = normalize(newH);
        up = normalize(newU);
        // left reste inchange
    }

    /**
     * Roll : RH(±α) - rotation autour de l'axe Heading.
     * Fait tourner left et up dans le plan (left, up).
     *
     * RH(α) = | 1    0      0   |
     *         | 0   cos   -sin  |
     *         | 0   sin    cos  |
     *
     * @param clockwise  true → +α (symbole \), false → −α (symbole /)
     */
    @Override
    public void rotateH(boolean clockwise) {
        double a = (getConfig().getAngleRotation() * Math.PI) / 180.0;
        if (!clockwise) a = -a;
        double c = Math.cos(a),
            s = Math.sin(a);
        double[] newL = {
            c * left[0] - s * up[0],
            c * left[1] - s * up[1],
            c * left[2] - s * up[2],
        };
        double[] newU = {
            s * left[0] + c * up[0],
            s * left[1] + c * up[1],
            s * left[2] + c * up[2],
        };
        left = normalize(newL);
        up = normalize(newU);
        // heading reste inchange
    }

    // ------------------------------------------------------------------ //
    //  Pile de positions                                                  //
    // ------------------------------------------------------------------ //

    @Override
    public void savePosition() {
        stack.push(new TurtleState(pos, heading, left, up));
    }

    @Override
    public void restorePosition() {
        TurtleState s = stack.pop();
        pos = s.pos.clone();
        heading = s.heading.clone();
        left = s.left.clone();
        up = s.up.clone();
    }

    // ------------------------------------------------------------------ //
    //  Couleur                                                            //
    // ------------------------------------------------------------------ //

    @Override
    public void changeColor(Color c) {
        this.color = c;
    }

    @Override
    public void setColorOf(Object o) {
        this.color = colorFactory.getColor(o);
    }

    // ------------------------------------------------------------------ //
    //  Accesseurs                                                         //
    // ------------------------------------------------------------------ //

    public List<Segment3D> getSegments() {
        return segments;
    }

    public class Bounds {

        public final double xMin, xMax, yMin, yMax, zMin, zMax;

        Bounds(
            double x0,
            double x1,
            double y0,
            double y1,
            double z0,
            double z1
        ) {
            xMin = x0;
            xMax = x1;
            yMin = y0;
            yMax = y1;
            zMin = z0;
            zMax = z1;
        }
    }

    public Bounds getBounds() {
        return new Bounds(xMin, xMax, yMin, yMax, zMin, zMax);
    }

    // ------------------------------------------------------------------ //
    //  Utilitaires prives                                                //
    // ------------------------------------------------------------------ //

    private static double[] normalize(double[] v) {
        double len = Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
        if (len < 1e-12) return v;
        return new double[] { v[0] / len, v[1] / len, v[2] / len };
    }

    private void updateBounds() {
        if (pos[0] < xMin) xMin = pos[0];
        if (pos[0] > xMax) xMax = pos[0];
        if (pos[1] < yMin) yMin = pos[1];
        if (pos[1] > yMax) yMax = pos[1];
        if (pos[2] < zMin) zMin = pos[2];
        if (pos[2] > zMax) zMax = pos[2];
    }
}
