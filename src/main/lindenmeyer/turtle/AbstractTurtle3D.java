package lindenmeyer.turtle;

import java.util.List;
import javafx.scene.paint.Color;

/**
 * Classe abstraite d'une tortue 3D orientée par un repère (heading, left, up).
 * Correspondance symboles L-Système 
 */
public abstract class AbstractTurtle3D {

    private ConfigTortue config;

    /**
     * Initialise une Tortue avec ses configurations dans {@link lindenmeyer.turtle.ConfigTortue}
     * @param config configurations
     */
    public AbstractTurtle3D(ConfigTortue config) {
        this.config = config;
    }

    /**
     * Change la coleur des segments
     * @param c coleur en AWT
     */
    public abstract void changeColor(Color c);

    /**
     * Change la coleur d'un objet
     * @param o l'objet
     */
    public abstract void setColorOf(Object o);

    /**
     * Avancer la tortue
     */
    public abstract void forward();

    /**
     * Reculer la tortue
     */
    public abstract void backward();

    /** 
     * Yaw   RU(±alpha) — autour de Up
     * @param clockwise valeur booleanne qui indicate une rotation clockwise
     * */
    public abstract void rotateU(boolean clockwise);

    /** 
     * Pitch RL(±alpha) — autour de Left  
     * @param clockwise valeur booleanne qui indicate une rotation clockwise 
     * */
    public abstract void rotateL(boolean clockwise);

    /** 
     * Roll  RH(±alpha) — autour de Heading 
     * @param clockwise valeur booleanne qui indicate une rotation clockwise
     * */
    public abstract void rotateH(boolean clockwise);

    /**
     * Enregistrement de la position de la Tortue
     */
    public abstract void savePosition();

    /**
     * Restaurer la position de la Tortue
     */
    public abstract void restorePosition();

    /** 
     * + et - -> yaw (RU) 
     * @param clockwise valeur booleanne qui indicate une rotation clockwise
     * */
    public void changeAngleX(boolean clockwise) {
        rotateU(clockwise);
    }

    /** 
     * * et / -> pitch (RL) 
     * @param clockwise valeur booleanne qui indicate une rotation clockwise
     * */
    public void changeAngleZ(boolean clockwise) {
        rotateL(clockwise);
    }

    /**
     * Afficher un objet 3D
     * @param object    l'objet 3D
     */
    public void interpret(Interpretable3D object) {
        object.interpret(this);
    }

    /**
     * Afficher une liste d'objets 3D
     * @param objs      la liste des objets 3D
     */
    public void interpretAll(List<? extends Interpretable3D> objs) {
        for (Interpretable3D o : objs) interpret(o);
    }

    public ConfigTortue getConfig() {
        return config;
    }
}
