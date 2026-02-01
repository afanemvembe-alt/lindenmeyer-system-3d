package lindenmeyer.lsystem;

import lindenmeyer.rules.*;
import lindenmeyer.symbols.*;

public class Main {
    public static void main(String[] args) {
        // Création des symboles
        SymbolFactory factory = new SymbolFactory();
        Symbol F = factory.getSymbol('F');
        Symbol plus = factory.getSymbol('+');
        Symbol minus = factory.getSymbol('-');

        // Axiome
        LSystem koch = new LSystem("F+F--F+F");

        // Règles : Exemple simple pour le flocon de Koch
        // Utilisation de fromString pour transformer la chaîne en SymbolList
        SymbolList successor = SymbolList.fromString("F+F--F+F", factory);
        koch.ajouterRegle(new SimpleRule(F, successor));

        // Générer 1, 2, 3 itérations
        for (int i = 1; i <= 3; i++) {
            String result = koch.generer(i);
            System.out.println("Itération " + i + ": " + result);
        }
    }
}
