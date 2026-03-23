package lindenmeyer.turtle;

import java.awt.Color;
import java.util.*;

/**
 * C'est le "Squelette" de la tortue. 
 * Elle contient les maths (position, angles).
 */
public abstract class AbstractTurtle3D {
    //les attributs 
    private Coord3D position;
    private double angle_x, angle_y;
    private ConfigTortue config;

      //  LE CONSTRUCTEUR 
 public AbstractTurtle3D(Coord3D position, double angle_x, double angle_y, ConfigTortue config) {
        this.position = position;
        this.angle_x = angle_x;
        this.angle_y = angle_y;
        this.config = config;
    }
      // LES MÉTHODES concretes
    public ConfigTortue getConfig() {
        return config;
    }

   //Version simplifiée du constructeur 
    public AbstractTurtle3D(ConfigTortue config) {
        this(new Coord3D(), 0, 0, config);
    }

    public Coord3D getPosition() {
        return position;
    }
        // LES MÉTHODES ABSTRAITES
    public abstract void changeColor(Color c);

    public abstract void forward();

    public abstract void backward();

    public abstract void changeAngleX();

    public abstract void changeAngleY();

    public abstract void savePosition();

    public abstract void restorePosition();

    public abstract List<Segment3D> getSegments();
  
  // Methode qui demande à l'interpréteur de lire les ordres
    public void interpret(Interpretable3D object) {
        object.interpret(this);
    }
   // Execute une liste complete d'ordres et renvoie le dessin
    public List<Segment3D> interpretAll(List<? extends Interpretable3D> list) {
        for (Interpretable3D object : list) {
            interpret(object);
        }

        return getSegments();
    }
}
