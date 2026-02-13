package test.lindenmeyer;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.lsystem.LSystem;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.rules.RuleSetFactory;
import lindenmeyer.symbols.SymbolFactory;

public class LSystemTest {
    public static void main(String[] args) {
        boolean res = true;

        res &= n_generations();

        System.out.println(LSystemTest.class + (res ? "Tests OK" : "KO"));
    }


    public static boolean n_generations() {
        boolean res = true;

        SymbolFactory sf = new SymbolFactory();
        RuleSetFactory rf = new RuleSetFactory(sf);

        RuleSet rs = rf.parseString("A>AB,B>A");

        LSystem ls = new LSystem(new Axiom("A"), rs, sf);

        return ls.generer(7).equals("ABAABABAABAABABAABABAABAABABAABAAB");
    }
}
