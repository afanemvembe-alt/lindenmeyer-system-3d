package bench;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.lsystem.LSystem;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.rules.RuleSetFactory;
import lindenmeyer.symbols.SymbolFactory;

/**
 * Spécialisation d'un bencher de L-System permettant de simuler un flocon de
 * Koch.
 */
public class Koch extends LSystemBench {
    private static final String axiomString = "F++F++F";
    private static final String ruleString = "F>F-F++F-F";

    /**
     * Construit l'instance par défaut de la classe, un flocon de Koch avec 13
     * générations.
     */
    public Koch() {
        super(
            13,
            new LSystem(
                new Axiom(axiomString),
                new RuleSetFactory(new SymbolFactory()).parseString(ruleString),
                new SymbolFactory()
            )
        );
    }

    /**
     * Exécute le test.
     * 
     * @param args non-utilisé
     */
    public static void main(String[] args) {
        System.out.println(Bencher.bench(new Koch()));
    }
}