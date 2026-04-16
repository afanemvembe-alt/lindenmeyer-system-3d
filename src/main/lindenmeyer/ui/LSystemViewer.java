package lindenmeyer.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lindenmeyer.lsystem.LSystem;
import lindenmeyer.lsystem.LsystemListener;

public class LSystemViewer implements ActionListener, LsystemListener {
    
    private Vue3D vue3D;
    private VueLsystem vue2D;

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
            case "play":
                break;
            default:
                break;
        }
    }
    
    @Override
    public void lsystemUpdated(Object source) {
        LSystem lSystem = (LSystem) source;
        
        
    }
}
