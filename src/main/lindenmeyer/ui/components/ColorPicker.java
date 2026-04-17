package lindenmeyer.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class ColorPicker extends JDialog implements ActionListener {

    private List<Color> colors;
    private Color selectedColor = null;
    private JPanel colorPanel;

    protected class ColorButton extends JButton {

        Color color;

        public Color getColor() {
            return color;
        }

        public ColorButton(Color c) {
            super("BUTTON");
            setForeground(c);
        }
    }

    public ColorPicker() {
        super();
        colors = new ArrayList<>();
        LayoutManager layout = new BorderLayout();
        Button addButton = new Button("+");
        addButton.addActionListener(this);
        addButton.setActionCommand("addColor");
        setLayout(layout);
        add(addButton, BorderLayout.SOUTH);
        colorPanel = new JPanel();
        LayoutManager colorPanelLayout = new GridLayout(0, 1);
        colorPanel.setLayout(colorPanelLayout);
        add(colorPanel, BorderLayout.NORTH);
        pack();
    }

    protected void addColor(Color color) {
        colors.add(color);
    }

    protected void addColorButton(Color color) {
        ColorButton button = new ColorButton(color);
        colorPanel.add(button);
        pack();
        revalidate();
        repaint();
    }

    protected void getUserColor() {
        selectedColor = JColorChooser.showDialog(this, "ajouter", null);
    }

    protected void removeSelectedColor() {}

    public List<Color> getColors() {
        return colors;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "addColor":
                getUserColor();
                addColor(selectedColor);
                addColorButton(selectedColor);
                break;
            case "removeColor":
                removeSelectedColor();
                break;
            default:
                break;
        }
    }
}
