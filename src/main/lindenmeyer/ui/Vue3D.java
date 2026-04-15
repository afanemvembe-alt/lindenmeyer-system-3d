package lindenmeyer.ui;

import static java.lang.Math.acos;
import static java.lang.Math.toDegrees;

import java.util.List;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import lindenmeyer.turtle.Segment3D;

/**
 * Composant servant à effectuer le rendu 3D d'un L-Système.
 *
 * <h2>Attention</h2>
 *
 * Ceci est un composant JavaFX, il faut donc l'adapter pour l'utiliser dans un
 * contexte Swing.
 */
public class Vue3D extends Scene {

    private double lineThickness = 10;
    private double tilt = 0;
    private double rotation = 0;
    private List<Segment3D> segments;
    private Group root;

    /**
     * Retourne l'épaisseur du trait de dessin.
     *
     * @return un double
     */
    public double getLineThickness() {
        return lineThickness;
    }

    public void setSegments(List<Segment3D> segments) {
        this.segments = segments;
    }

    /**
     * Modifie l'épaisseur du trait de dessin
     *
     * @param lineThickness épaisseur de la ligne
     */
    public void setLineThickness(double lineThickness) {
        this.lineThickness = lineThickness;
    }

    public Vue3D(List<Segment3D> segments) {
        // Group root = new Group();
        super(new Group(), 720, 480);
        root = (Group) getRoot();
        root.setTranslateX(720.0 / 2);
        root.setTranslateY(480.0 / 2);
        // root.setScaleX(2);
        // root.setScaleY(2);
        this.segments = segments;
        renderGrid();

        for (Segment3D s : segments) {
            root.getChildren().add(segmentToCylinder(s));
        }
    }

    public void render() {
        renderGrid();
    }

    public void redraw() {
        root.getChildren().clear();

        for (Segment3D s : segments) {
            root.getChildren().add(segmentToCylinder(s));
        }
    }

    /**
     * Crée un nouveau cylindre à partir d'un segment.
     *
     * @param s le segment de base
     * @return cylindre résultant
     */
    Cylinder segmentToCylinder(Segment3D s) {
        // https://stackoverflow.com/questions/56259785/how-to-draw-a-3d-line-in-javafx
        // pas le plus efficace, mais aucune dépendance rajoutée
        Point3D start = new Point3D(s.start.x, s.start.y, s.start.z);
        Point3D end = new Point3D(s.end.x, s.end.y, s.end.z);
        Point3D seg = end.subtract(start);
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D rotationAxis = seg.crossProduct(yAxis);
        double angle = acos(seg.normalize().dotProduct(yAxis));
        Point3D midpoint = start.midpoint(end);

        double length = seg.magnitude();

        Translate moveToMidpoint = new Translate(
            midpoint.getX(),
            midpoint.getY(),
            midpoint.getZ()
        );
        Rotate rotateAroundCenter = new Rotate(-toDegrees(angle), rotationAxis);

        Cylinder c = new Cylinder(1, length);
        c.setMaterial(new PhongMaterial(s.color));
        c.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);

        return c;
    }

    protected void renderGrid() {
        for (int i = 0; i < 1000; i += 100) {
            Line line_x = new Line(i, 0, i, 1000);
            Line line_y = new Line(0, i, 1000, i);

            root.getChildren().addAll(line_x, line_y);
        }
    }
}
