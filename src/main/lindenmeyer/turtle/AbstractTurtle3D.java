package lindenmeyer.turtle;

import java.util.List;
import javafx.scene.paint.Color;

/**
 * Classe abstraite d'une tortue 3D orientée par un repère (heading, left, up).
 * Correspondance symboles L-Système 
 */
public abstract class AbstractTurtle3D {

    private ConfigTortue config;

    public AbstractTurtle3D(ConfigTortue config) {
        this.config = config;
    }

    public ConfigTortue getConfig() {
        return config;
    }

    // ---- méthodes abstraites ----------------------------------------- //

    public abstract void changeColor(Color c);

    public abstract void setColorOf(Object o);

    public abstract void forward();

    public abstract void backward();

    /** Yaw   RU(±alpha) — autour de Up      */
    public abstract void rotateU(boolean clockwise);

    /** Pitch RL(±alpha) — autour de Left    */
    public abstract void rotateL(boolean clockwise);

    /** Roll  RH(±alpha) — autour de Heading */
    public abstract void rotateH(boolean clockwise);

    public abstract void savePosition();

    public abstract void restorePosition();

    /** + et - -> yaw (RU) */
    public void changeAngleX(boolean clockwise) {
        rotateU(clockwise);
    }

    /** * et / -> pitch (RL) */
    public void changeAngleZ(boolean clockwise) {
        rotateL(clockwise);
    }

    // ---- interpretation ----- //

    public void interpret(Interpretable3D object) {
        object.interpret(this);
    }

    public void interpretAll(List<? extends Interpretable3D> objs) {
        for (Interpretable3D o : objs) interpret(o);
    }
}
