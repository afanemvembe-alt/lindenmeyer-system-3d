package test.lindenmeyer;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.lsystem.LSystem;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.rules.RuleSetFactory;
import lindenmeyer.symbols.SymbolFactory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class LSystemTest {
    // public static void main(String[] args) {
    // boolean res = true;

    // res &= n_generations();

    // System.out.println(LSystemTest.class + (res ? "Tests OK" : "KO"));
    // }

    // chaine prise de: https://en.wikipedia.org/wiki/L-system#Examples_of_L-systems
    private static String inputString = "A\n" + //
            "AB\n" + //
            "ABA\n" + //
            "ABAAB\n" + //
            "ABAABABA\n" + //
            "ABAABABAABAAB\n" + //
            "ABAABABAABAABABAABABA\n" + //
            "ABAABABAABAABABAABABAABAABABAABAAB";

    private static List<String> getExpectedStrings() {
        return inputString.lines().collect(Collectors.toList());
    }

    @Test
    void n_generations() {

        List<String> expected = getExpectedStrings();

        System.err.println(expected.get(2));

        SymbolFactory sf = new SymbolFactory();
        RuleSetFactory rf = new RuleSetFactory(sf);

        RuleSet rs = rf.parseString("A>AB,B>A");

        LSystem ls = new LSystem(new Axiom("A"), rs, sf);

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), ls.getCurrentGeneration().toString());
            ls.step();
        }
    }
}
