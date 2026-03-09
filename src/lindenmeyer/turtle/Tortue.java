package lindenmeyer.turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import lindenmeyer.lsystem.Segment;

public class Tortue {
    private double x, y, angle;
    private ConfigTortue config;
    private Stack<double[]> pile = new Stack<>();
    
    // Un "mini-bibliothéque" de 10 couleurs
    private Map<Character, Color> nuancier = new HashMap<>();

    public Tortue(double x, double y, double angle, ConfigTortue config) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.config = config;
        
        // Initialisation des 10 couleurs par défaut
        nuancier.put('A', Color.BLACK);
        nuancier.put('B', Color.RED);
        nuancier.put('C', Color.GREEN);
        nuancier.put('D', Color.BLUE);
        nuancier.put('E', Color.YELLOW);
        nuancier.put('F', Color.MAGENTA); // 'F' dessine maintenant en Magenta par défaut
        nuancier.put('G', Color.CYAN);
        nuancier.put('H', Color.ORANGE);
        nuancier.put('I', Color.PINK);
        nuancier.put('J', Color.GRAY);
    }

    public List<Segment> interpreter(String sequence) {
        List<Segment> segments = new ArrayList<>();
        double pas = config.getPas();
        double deltaAngle = config.getAngleRotation();

        for (char c : sequence.toCharArray()) {
            //La tortue ne cherche plus la lettre 'F'. Elle regarde si le caractère c (peu importe lequel) existe dans ton nuancier de 10 couleurs.
            // Si le caractère est une de nos 10 lettres (A à J)
            if (nuancier.containsKey(c)) {
                Color couleurChoisie = nuancier.get(c);
                
                double nx = x + pas * Math.cos(Math.toRadians(angle));
                double ny = y + pas * Math.sin(Math.toRadians(angle));
                
                // On crée le segment avec la couleur de la lettre
                segments.add(new Segment(x, y, nx, ny, couleurChoisie));
                
                x = nx;
                y = ny;
            } 
            else if (c == '+') {
                // Les constantes restent uniquement pour les ACTIONS (tourner, pile)
                angle += deltaAngle;
            } 
            else if (c == '-') {
                angle -= deltaAngle;
            } 
            else if (c == '[') {
                pile.push(new double[]{x, y, angle});
            } 
            else if (c == ']') {
                if (!pile.isEmpty()) {
                    double[] etat = pile.pop();
                    x = etat[0]; 
                    y = etat[1]; 
                    angle = etat[2];
                }
            }
        }
        return segments;
    }
}