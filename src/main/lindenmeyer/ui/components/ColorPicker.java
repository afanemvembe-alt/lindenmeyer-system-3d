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
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPicker extends JDialog implements ActionListener {

    private List<Color> colors;
    private Color selectedColor = null;
    private JPanel colorPanel;
    private JPanel bottomPanel;
    private Boolean confirmed = false;

    protected class ColorButton extends JButton {

        Color color;

        public Color getColor() {
            return color;
        }

        public ColorButton(Color c, ActionListener listener) {
            super("BUTTON");
            setForeground(c);
            setActionCommand("removeColor");
            addActionListener(listener);
            this.color = c;
        }
    }

    public ColorPicker(JFrame parent, List<Color> colors) {
        super(parent, "Selection de couleurs", true);
        this.colors = new ArrayList<>(colors);
        LayoutManager layout = new BorderLayout();
        setLayout(layout);
        createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
        colorPanel = new JPanel();
        LayoutManager colorPanelLayout = new GridLayout(0, 1);
        colorPanel.setLayout(colorPanelLayout);
        add(colorPanel, BorderLayout.NORTH);
        generateButtons();
        pack();
    }

    public ColorPicker(JFrame parent) {
        this(parent, new ArrayList<>());
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        GridLayout layout = new GridLayout(1, 0, 10, 10);

        Button addButton = new Button("+");
        Button okButton = new Button("Ok");
        Button cancelButton = new Button("Cancel");

        bottomPanel.setLayout(layout);
        bottomPanel.add(addButton);
        bottomPanel.add(okButton);
        bottomPanel.add(cancelButton);

        addButton.setActionCommand("addColor");
        okButton.setActionCommand("valider");
        cancelButton.setActionCommand("annuler");

        addButton.addActionListener(this);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    protected void addColor(Color color) {
        colors.add(color);
    }

    protected void addColorButton(Color color) {
        ColorButton button = new ColorButton(color, this);
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

    protected void valider() {
        confirmed = true;
        dispose();
    }

    protected void cancel() {
        confirmed = false;
        dispose();
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    protected void generateButtons() {
        for (Color c : colors) {
            ColorButton b = new ColorButton(c, this);
            colorPanel.add(b);
        }
    }

    public void setColors(List<Color> colors) {
        colorPanel.removeAll();
        generateButtons();
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
                if (e.getSource() instanceof ColorButton) {
                    ColorButton cButton = (ColorButton) e.getSource();
                    if (!colors.contains(cButton.getColor())) {
                        System.err.println(
                            "LIST DID NOT HAVE " + cButton.getColor()
                        );
                    }
                    colors.remove(cButton.getColor());
                    colorPanel.remove(cButton);
                    pack();
                    revalidate();
                    repaint();
                }
                break;
            case "valider":
                valider();
                break;
            case "annuler":
                cancel();
                break;
            default:
                break;
        }
    }
}
