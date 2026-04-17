package lindenmeyer.ui.components;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

public class Button extends JButton {

    public static Font FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static Dimension DIMENSION = new Dimension(130, 30);
    public static List<Button> buttons = new ArrayList<>();

    public Button(String text) {
        super(text);
        buttons.add(this);

        setFont(FONT);
        setPreferredSize(DIMENSION);
    }

    public static void setBaseFont(Font font) {
        FONT = font;
    }

    public static void updateFont(Font font) {
        setBaseFont(font);
        for (Button b : buttons) {
            b.setFont(FONT);
        }
    }

    public static void setDefaultDimension(Dimension size) {
        DIMENSION = size;
    }

    public static void updateDimension(Dimension size) {
        setDefaultDimension(size);
        for (Button b : buttons) {
            b.setPreferredSize(DIMENSION);
        }
    }
}
