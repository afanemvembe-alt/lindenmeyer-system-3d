package lindenmeyer.lsystem;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.rules.*;
import lindenmeyer.symbols.*;

public class Main {
    public static void main(String[] args) {
        // Création de la factory de symboles
        SymbolFactory factory = new SymbolFactory();

        // Symboles utilisés
        Symbol F = factory.getSymbol('F');
        Symbol plus = factory.getSymbol('+');
        Symbol minus = factory.getSymbol('-');

        // Axiome de départ (flocon de Koch)
        LSystem koch = new LSystem(new Axiom("F+F--F+F"), new RuleSet(), factory);

        // Définition du successeur pour F : F -> F+F--F+F
        SymbolList successor = new SymbolList(factory);
        String succStr = "F+F--F+F";
        for (char c : succStr.toCharArray()) {
            successor.add(c); // on ajoute chaque symbole à SymbolList
        }

        // Création de la règle et ajout au L-System
        //koch.ajouterRegle(new SimpleRule(F, successor));

        // Générer plusieurs itérations
        int iterations = 5;
        for (int i = 1; i <= iterations; i++) {
            String result = koch.generer(i);
            System.out.println("Itération " + i + ": " + result);
        }
    }
}
