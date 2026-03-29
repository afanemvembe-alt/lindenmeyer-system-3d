package test;

import javafx.application.Application;
import javafx.stage.Stage;

import lindenmeyer.ui.*;

public class Vue3DTest extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        Vue3D vue = new Vue3D();
        arg0.setScene(vue);
        arg0.show();
    }
}
