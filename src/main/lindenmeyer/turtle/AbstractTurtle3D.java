package lindenmeyer.turtle;

// import java.awt.Color;
import javafx.scene.paint.Color;
import java.util.List;

/**
 * Classe abstraite représantant les opérations de base d'une tortue.
 */
public abstract class AbstractTurtle3D {
    // les attributs
    private ConfigTortue config;

    /**
     * Crée une nouvelle tortue avec la configuration souhaitée.
     * 
     * @param config configuration
     */
    public AbstractTurtle3D(ConfigTortue config) {
        this.config = config;
    }

    /**
     * Retourne la configuration de la tortue.
     * 
     * @return configuration de la tortue
     */
    public ConfigTortue getConfig() {
        return config;
    }

    // LES MÉTHODES ABSTRAITES
    /**
     * Change la couleur du segment dessiné par la tortue.
     * 
     * @param c nouvelle couleur
     */
    public abstract void changeColor(Color c);

    /**
     * Change la couleur en fonction de l'object donné.
     * 
     * @param o un objet
     */
    public abstract void setColorOf(Object o);

    /**
     * Avance la tortue du pas fourni dans la configuration.
     */
    public abstract void forward();

    /**
     * Recule la tortue du pas fourni dans la configuration.
     */
    public abstract void backward();

    /**
     * Change la direction du la tortue de l'angle fourni dans la configuration sur
     * l'axe (x, y).
     * 
     * @param clockwise si la tortue doit tourner dans le sens des aiguilles d'une
     *                  montre
     */
    public abstract void changeAngleX(boolean clockwise);

    /**
     * Change la direction du la tortue de l'angle fourni dans la configuration sur
     * l'axe (z).
     * 
     * @param clockwise si la tortue doit tourner dans le sens des aiguilles d'une
     *                  montre
     */
    public abstract void changeAngleZ(boolean clockwise);

    /**
     * Enregistre la position actuelle de la tortue pour la restaurer plus tard.
     */
    public abstract void savePosition();

    /**
     * Replace la tortue à la dernière position sauvegardée si elle existe, sinon ne
     * fait rien.
     */
    public abstract void restorePosition();

    /**
     * Exécute les intructions correspondant à un objet.
     * 
     * @param object objet à interpéter
     */
    public void interpret(Interpretable3D object) {
        object.interpret(this);
    }

    /**
     * Exécute les instructions correspondant aux objets donnés.
     * 
     * @param objects objets à interpéter
     */
    public void interpretAll(List<? extends Interpretable3D> objects) {
        for (Interpretable3D o : objects) {
            interpret(o);
        }
    }
}
