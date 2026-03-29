package lindenmeyer.turtle;

import java.awt.Color;

/**
 * C'est le "Squelette" de la tortue.
 * Elle contient les maths (position, angles).
 */
public abstract class AbstractTurtle3D {
    // les attributs
    private ConfigTortue config;

    // LE CONSTRUCTEUR
    public AbstractTurtle3D(ConfigTortue config) {
        this.config = config;
    }

    // LES MÉTHODES concretes
    public ConfigTortue getConfig() {
        return config;
    }

    // LES MÉTHODES ABSTRAITES
    public abstract void changeColor(Color c);

    public abstract void forward();

    public abstract void backward();

    public abstract void changeAngleX(boolean clockwise);

    public abstract void changeAngleZ(boolean clockwise);

    public abstract void savePosition();

    public abstract void restorePosition();

    // Methode qui demande à l'interpréteur de lire les ordres
    public void interpret(Interpretable3D object) {
        object.interpret(this);
    }
}
