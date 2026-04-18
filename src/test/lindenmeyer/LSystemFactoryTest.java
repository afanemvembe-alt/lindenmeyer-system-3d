package lindenmeyer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.lsystem.*;
import lindenmeyer.rules.GenericRule;
import lindenmeyer.rules.RuleSetFactory;
import lindenmeyer.symbols.SymbolFactory;
import org.junit.jupiter.api.Test;

public class LSystemFactoryTest {

    static final char partSep = '>';
    static final char ruleSep = ';';
    static final LSystemFactory lSystemFactory = new LSystemFactory(
        ruleSep,
        partSep
    );
    static final String axiomString = "F++F++F";
    static final String ruleString = "F>F-F++F-F";

    @Test
    void generation() {
        SymbolFactory sf = new SymbolFactory();
        RuleSetFactory ruleSetFactory = new RuleSetFactory(
            ruleSep,
            partSep,
            sf
        );

        LSystem expectedLSystem = new LSystem(
            new Axiom(axiomString),
            ruleSetFactory.parseString(ruleString),
            sf
        );
        LSystem actualLSystem = lSystemFactory.parseString(
            axiomString,
            ruleString
        );

        System.err.println(expectedLSystem.getRegles());
        System.err.println(actualLSystem.getRegles());

        for (GenericRule r : expectedLSystem.getRegles().getRules()) {
            System.err.println(r);
        }

        for (GenericRule r : actualLSystem.getRegles().getRules()) {
            System.err.println(r);
        }

        assertEquals(expectedLSystem.getRegles(), actualLSystem.getRegles());
    }
}
