package test;

import java.util.*;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import lindenmeyer.lsystem.LSystem;
import lindenmeyer.lsystem.LSystemFactory;
import lindenmeyer.turtle.ConfigTortue;
import lindenmeyer.turtle.Segment3D;
import lindenmeyer.turtle.Turtle3D;
import lindenmeyer.ui.*;

public class Vue3DTest extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        LSystemFactory lf = new LSystemFactory();
        LSystem l = lf.parseString("A", "A>A*A*A*A+A+A+A");
        Turtle3D t = new Turtle3D(new ConfigTortue(25, 90));
        l.step(1);
        t.interpretAll(l.getCurrentGeneration());
        List<Segment3D> segments = t.getSegments();
        System.err.println(segments.size());
        for (Segment3D s : segments) {
            System.err.println(s.start + "; " + s.end);
        }
        Vue3D vue = new Vue3D(segments);
        vue.getRoot().getTransforms()
                .addAll(new Rotate(45, 0, 0, 0, new Point3D(1, 0, 0)),
                        new Rotate(45, 0, 0, 0, new Point3D(0, 1, 0)));
        arg0.setScene(vue);
        arg0.show();
    }
}
