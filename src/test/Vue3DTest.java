package test;

import java.util.*;

import javafx.application.Application;
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
        LSystem l = lf.parseString("A", "A>B+B*B+B,B>A");
        Turtle3D t = new Turtle3D(new ConfigTortue(10, 30));
        l.step(5);
        t.interpretAll(l.getCurrentGeneration());
        List<Segment3D> segments = t.getSegments();
        System.err.println(segments.size());
        for (Segment3D s : segments) {
            System.err.println(s.start + "; " + s.end);
        }
        Vue3D vue = new Vue3D(segments);
        arg0.setScene(vue);
        arg0.show();
    }
}
