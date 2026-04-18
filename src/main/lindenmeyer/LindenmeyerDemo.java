package lindenmeyer;

import lindenmeyer.axiom.*;
import lindenmeyer.symbols.*;
import lindenmeyer.rules.*;
import lindenmeyer.lsystem.LSystem;

public class LindenmeyerDemo {

    public static void DemoTests(SymbolFactory symbolFactory) {
        System.out.println("=== DEBUT DES TESTS UNITAIRES ===");
        Symbol a = symbolFactory.getSymbol('A');
        Symbol b = symbolFactory.getSymbol('B');
        Symbol c = symbolFactory.getSymbol('C');
        Symbol d = symbolFactory.getSymbol('D');

        SymbolList succD = new SymbolList(symbolFactory);
        succD.add(d);

        // CORRECTION : On transforme 'b' en SymbolList pour le constructeur
        SymbolList predB = new SymbolList(symbolFactory);
        predB.add(b);

        // Test règle contextuelle : A < B > C -> D avec poids 1.0
        ContextRule contextRule = new ContextRule(predB, succD, SymbolList.of(a), SymbolList.of(c), 1.0);
        
        System.out.println("Applicable avec A < B > C ? " 
            + contextRule.isApplicable(SymbolList.of(b), SymbolList.of(a), SymbolList.of(c)));
        System.out.println("=== FIN DES TESTS UNITAIRES ===\n");
    }

    public static void main(String[] args) {
        SymbolFactory factory = new SymbolFactory();
        
        DemoTests(factory);

        Axiom axiom = new Axiom("F");
        RuleSetFactory rulefactory = new RuleSetFactory(',', '>', factory);
        
        // Test stochastique
        RuleSet ruleset = rulefactory.parseString("(0.5)F>F[+F], (0.5)F>F[-F]");

        LSystem lsystem = new LSystem(axiom, ruleset, factory);

        System.out.println("--- Simulation L-System ---");
        for (int i = 0; i <= 3; i++) {
            System.out.println("Génération " + i + " : " + lsystem.getCurrentGeneration());
            lsystem.step();
        }
        
        System.out.println("\n--- Test Contextuel Final ---");
        lsystem.setAxiome(new Axiom("ABC"));
        
        // CORRECTION : On vide la liste manuellement si clear() n'existe pas
        lsystem.getRegles().getRules().clear(); 
        
        lsystem.ajouterRegle(rulefactory.parseString("A<B>C>D").iterator().next());
        lsystem.step();
        System.out.println("Résultat (ABC -> ADC) : " + lsystem.getCurrentGeneration());
    }
}