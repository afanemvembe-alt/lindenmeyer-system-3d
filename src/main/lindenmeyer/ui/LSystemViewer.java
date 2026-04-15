package lindenmeyer.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LSystemViewer implements ActionListener {

    public static enum Mode {
        TwoDimensional,
        ThreeDimensional,
    }

    private Mode mode;

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    public void switchMode() {
        if (mode == Mode.TwoDimensional) {
            setMode(Mode.ThreeDimensional);
        } else {
            setMode(Mode.TwoDimensional);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "changeMode":
                switchMode();
                break;
            default:
                break;
        }
    }
}
