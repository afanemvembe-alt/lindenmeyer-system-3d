package bench;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.lsystem.LSystem;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.rules.RuleSetFactory;
import lindenmeyer.symbols.SymbolFactory;

public class Koch extends LSystemBench {
    private static final String axiomString = "F++F++F";
    private static final String ruleString = "F>F-F++F-F";

    public Koch() {
        SymbolFactory sf = new SymbolFactory();
        RuleSetFactory rf = new RuleSetFactory(sf);
        RuleSet rs = rf.parseString(ruleString);
        super(8, new LSystem(new Axiom(axiomString), rs, sf));
    }

    public static void main(String[] args) {
        System.out.println(Bencher.bench(new Koch()));
    }
}
