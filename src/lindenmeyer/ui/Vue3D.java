package lindenmeyer.ui;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.transform.*;
import lindenmeyer.turtle.Segment3D;
import javafx.scene.text.*;

public class Vue3D extends Scene {
    public Vue3D() {
        Group g = new Group();
        super(g, 720, 480);

        setFill(Color.LIGHTGRAY);

        Circle circle = new Circle(0, 0, 30, Color.GREEN);
        g.getChildren().add(circle);

        Box box = new Box(50, 50, 50);
        box.setTranslateX(200);
        box.setTranslateY(200);
        Rotate r = new Rotate(30, 20, 30, 20, new Point3D(1, 1, 1));
        box.getTransforms().add(r);

        Text text = new Text("This is a test");
        text.setX(10);
        text.setY(50);
        text.setFont(new Font(20));

        text.getTransforms().add(new Rotate(30, 50, 30));

        g.getChildren().add(box);
        g.getChildren().add(text);

        Cylinder c = new Cylinder(10, 100);
        c.setTranslateX(400);
        c.setTranslateY(400);
        c.getTransforms().add(r);

        for (int i = 0; i < 1000; i += 100) {
            Line line_x = new Line(i, 0, i, 1000);
            Line line_y = new Line(0, i, 1000, i);

            g.getChildren().addAll(line_x, line_y);
        }

        g.getChildren().add(c);
    }

    protected void drawSegment(Segment3D segment) {

    }
}
