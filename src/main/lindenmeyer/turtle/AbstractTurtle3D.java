package lindenmeyer.turtle;

import javafx.scene.paint.Color;
import java.util.List;

/**
 * Classe abstraite d'une tortue 3D orientée par un repère (heading, left, up).
 *
 * Correspondance symboles L-Système / méthodes :
 *   F   → forward()
 *   +   → rotateU(true)    yaw à gauche   (RU(+α))
 *   -   → rotateU(false)   yaw à droite   (RU(-α))
 *   &   → rotateL(true)    pitch vers bas (RL(+α))
 *   ^   → rotateL(false)   pitch vers haut(RL(-α))
 *   \   → rotateH(true)    roll droite    (RH(+α))
 *   /   → rotateH(false)   roll gauche    (RH(-α))
 *   [   → savePosition()
 *   ]   → restorePosition()
 */
public abstract class AbstractTurtle3D {

    private ConfigTortue config;

    public AbstractTurtle3D(ConfigTortue config) { this.config = config; }

    public ConfigTortue getConfig() { return config; }

    // ---- méthodes abstraites ----------------------------------------- //

    public abstract void changeColor(Color c);
    public abstract void setColorOf(Object o);
    public abstract void forward();
    public abstract void backward();

    /** Yaw   RU(±α) — autour de Up      */
    public abstract void rotateU(boolean clockwise);

    /** Pitch RL(±α) — autour de Left    */
    public abstract void rotateL(boolean clockwise);

    /** Roll  RH(±α) — autour de Heading */
    public abstract void rotateH(boolean clockwise);

    public abstract void savePosition();
    public abstract void restorePosition();


    /** + et - -> yaw (RU) */
    public void changeAngleX(boolean clockwise) { rotateU(clockwise); }

    /** * et / -> pitch (RL) */
    public void changeAngleZ(boolean clockwise) { rotateL(clockwise); }

    // ---- interpretation ----- //

    public void interpret(Interpretable3D object)                   { object.interpret(this); }
    public void interpretAll(List<? extends Interpretable3D> objs)  { for (Interpretable3D o : objs) interpret(o); }
}
