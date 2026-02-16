package lindenmeyer.lsystem;

import javax.swing.*;
import java.awt.*;
import java.util.Stack; // IMPORTANT : pour la pile
import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

public class LSystemDisplay extends JPanel {
    private SymbolList symbolsADessiner;

    public LSystemDisplay(SymbolList symbols) {
        this.symbolsADessiner = symbols;
        this.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // --- Paramètres de la Tortue ---
        double x = 400; // Position de départ centrée
        double y = getHeight() * 0.8; // Position de départ vers le bas
        double angle = Math.toRadians(-90); // Pointer vers le haut (mieux pour les arbres)
        double pas = 5.0; 
        double rotation = Math.toRadians(25); // 25° est standard pour les arbres

        // --- La Pile pour la mémoire ---
        // On stocke un tableau : [x, y, angle]
        Stack<double[]> pile = new Stack<>();

        // --- La Traduction ---
        for (Symbol s : symbolsADessiner.getSymbols()) {
            char c = s.getSymbol();

            switch (c) {
                case 'F': // AVANCER
                    double x2 = x + pas * Math.cos(angle);
                    double y2 = y + pas * Math.sin(angle);
                    g2d.drawLine((int)x, (int)y, (int)x2, (int)y2);
                    x = x2; y = y2;
                    break;
                case '+': // PIVOTER DROITE
                    angle += rotation;
                    break;
                case '-': // PIVOTER GAUCHE
                    angle -= rotation;
                    break;
                case '[': // SAUVEGARDER L'ÉTAT (Bifurcation)
                    pile.push(new double[]{x, y, angle});
                    break;
                case ']': // RESTAURER L'ÉTAT (Fin de branche)
                    if (!pile.isEmpty()) {
                        double[] etatSauve = pile.pop();
                        x = etatSauve[0];
                        y = etatSauve[1];
                        angle = etatSauve[2];
                    }
                    break;
            }
        }
    }
}


















+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++