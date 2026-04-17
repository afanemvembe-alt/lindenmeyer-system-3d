package lindenmeyer.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class ColorPicker extends JFrame implements ActionListener {

    private List<Color> colors;
    private Color selectedColor = null;

    public ColorPicker() {
        super();
        LayoutManager layout = new BorderLayout();
        Button addButton = new Button("+");
    }

    public void addColor(Color color) {
        colors.add(color);
    }

    public void getUserColor() {
        selectedColor = JColorChooser.showDialog(this, "ajouter", null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "addColor":
                getUserColor();
                addColor(selectedColor);
                break;
            default:
                break;
        }
    }
}
