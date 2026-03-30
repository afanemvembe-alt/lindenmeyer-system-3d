package lindenmeyer.ui;

import static java.lang.Math.acos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.transform.*;
import lindenmeyer.turtle.Segment3D;
import javafx.scene.text.*;

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

    /**
     * Retourne l'épaisseur du trait de dessin.
     * 
     * @return un double
     */
    public double getLineThickness() {
        return lineThickness;
    }

    /**
     * Modifie l'épaisseur du trait de dessin
     * 
     * @param lineThickness épaisseur de la ligne
     */
    public void setLineThickness(double lineThickness) {
        this.lineThickness = lineThickness;
    }

    public Vue3D() {
        Group g = new Group();
        super(g, 720, 480);

        setFill(Color.LIGHTGRAY);

        // let's try (200, 200, 0) (400, 400, 0)
        // https://stackoverflow.com/questions/56259785/how-to-draw-a-3d-line-in-javafx
        Point3D start = new Point3D(200, 200, 0);
        Point3D end = new Point3D(400, 400, 0);
        Point3D seg = end.subtract(start);
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D rotationAxis = seg.crossProduct(yAxis);
        double angle = acos(seg.normalize().dotProduct(yAxis));
        Point3D midpoint = start.midpoint(end);

        double length = seg.magnitude();

        Translate moveToMidpoint = new Translate(midpoint.getX(), midpoint.getY(), midpoint.getZ());
        Rotate rotateAroundCenter = new Rotate(-toDegrees(angle), rotationAxis);

        Cylinder c = new Cylinder(1, length);
        c.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);

        // g.getChildren().add(c);

        for (int i = 0; i < 1000; i += 100) {
            Line line_x = new Line(i, 0, i, 1000);
            Line line_y = new Line(0, i, 1000, i);

            g.getChildren().addAll(line_x, line_y);
        }

        g.getChildren().add(c);
        g.getChildren().add(segmentToCylinder(new Segment3D(0, 0, 0, 100, 300, 50, null)));
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

        Translate moveToMidpoint = new Translate(midpoint.getX(), midpoint.getY(), midpoint.getZ());
        Rotate rotateAroundCenter = new Rotate(-toDegrees(angle), rotationAxis);

        Cylinder c = new Cylinder(1, length);
        c.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);

        return c;
    }
}
