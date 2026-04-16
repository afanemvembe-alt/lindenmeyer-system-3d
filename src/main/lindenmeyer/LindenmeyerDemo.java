package lindenmeyer;

import lindenmeyer.axiom.*;
import lindenmeyer.symbols.*;
import lindenmeyer.rules.*;
import lindenmeyer.lsystem.LSystem;

public class LindenmeyerDemo {

    // Ton code de test que tu voulais garder (on le met ici)
    public static void DemoTests(SymbolFactory symbolFactory) {
        System.out.println("=== DEBUT DES TESTS UNITAIRES ===");
        Symbol a = symbolFactory.getSymbol('A');
        Symbol b = symbolFactory.getSymbol('B');
        Symbol c = symbolFactory.getSymbol('C');
        Symbol d = symbolFactory.getSymbol('D');

        SymbolList succD = new SymbolList(symbolFactory);
        succD.add(d);

        // Test règle contextuelle : A < B > C -> D
        ContextRule contextRule = new ContextRule(b, succD, SymbolList.of(a), SymbolList.of(c), 1.0);
        
        System.out.println("Applicable avec A < B > C ? " 
            + contextRule.isApplicable(SymbolList.of(b), SymbolList.of(a), SymbolList.of(c)));
        System.out.println("=== FIN DES TESTS UNITAIRES ===\n");
    }

    public static void main(String[] args) {
        SymbolFactory factory = new SymbolFactory();
        
        // 1. On lance tes tests d'abord
        DemoTests(factory);

        // 2. On lance la simulation du L-System
        Axiom axiom = new Axiom("F");
        RuleSetFactory rulefactory = new RuleSetFactory(',', '>', factory);
        
        // Test stochastique (aléatoire)
        RuleSet ruleset = rulefactory.parseString("(0.5)F>F[+F], (0.5)F>F[-F]");

        LSystem lsystem = new LSystem(axiom, ruleset, factory);

        System.out.println("--- Simulation L-System ---");
        for (int i = 0; i <= 3; i++) {
            System.out.println("Génération " + i + " : " + lsystem.getCurrentGeneration());
            lsystem.step();
        }
        
        // 3. Test contextuel final
        System.out.println("\n--- Test Contextuel Final ---");
        lsystem.setAxiome(new Axiom("ABC"));
        lsystem.getRegles().clear();
        lsystem.ajouterRegle(rulefactory.parseString("A<B>C>D").iterator().next());
        lsystem.step();
        System.out.println("Résultat (ABC -> ADC) : " + lsystem.getCurrentGeneration());
    }
}