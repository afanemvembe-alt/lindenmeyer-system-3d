package lindenmeyer.turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack; // gerer la memoire des branchess

public class Tortue {
    private double x, y;
    private double angle;
    private ConfigTortue config; // on remplace les valeurs fixes par la config

    public Tortue(double x, double y, double angle , ConfigTortue config) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.config = config ;
    }

    public List<Segment> interpreter(String sequence) {
        List<Segment> segments = new ArrayList<>();
 // pile pour sauvegarder l'etat (x, y, angle) lors d'un '['
          Stack <double []> pile = new Stack;
  // on utilise les valeurs de la configuration utilisateur
          double longueur = config.getPas();
          double deltaAngle = config.getAngleRotation();


// changer premier paragraphe 

        for (char c : sequence.toCharArray()) {
            if (c == 'F') {
        // calcul de la destination avec precision double 
                double nx = x + longueur * Math.cos(Math.toRadians(angle));
                double ny = y + longueur * Math.sin(Math.toRadians(angle));


// changer explication 2"me paragraphe 
    // on cree le segement en utilisant le constructeur 
                segments.add(new Segment(x, y, nx, ny));
                x = nx;
                y = ny;
            } else if (c == '+') {
                angle += deltaAngle;
            } else if (c == '-') {
                angle -= deltaAngle;
            
             else if (c == '[') {
    // on memorise ou on est avant de creer une branche 
                    pile.push (new double [] {x , y , angle});
        } else if (c == ']'){
    // on revient au dernier point memorisé 
    if (!pile. isEmpty()) {
        double [] etat = pile.pop();
                
                this.x = etat [0];
                this.y = etat [1];
                this.angle = etat [2];
    }
        }
}

        return segments;
    }
}
