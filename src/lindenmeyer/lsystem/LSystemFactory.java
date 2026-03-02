package lindenmeyer.lsystem;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.rules.*;
import lindenmeyer.symbols.*;

public class LSystemFactory {
    protected final RuleSetFactory ruleSetFactory;

    public LSystemFactory(char ruleSep, char partSep, SymbolFactory sf) {
        ruleSetFactory = new RuleSetFactory(ruleSep, partSep, sf);
    }

    public LSystemFactory(char ruleSep, char partSep) {
        this(ruleSep, partSep, new SymbolFactory());
    }

    public LSystemFactory(SymbolFactory sf) {
        ruleSetFactory = new RuleSetFactory(sf);
    }

    public LSystemFactory() {
        this(new SymbolFactory());
    }

    public LSystem parseString(String axiomString, String ruleString) {
        RuleSet rules = ruleSetFactory.parseString(ruleString);
        Axiom axiom = new Axiom(axiomString);

        return new LSystem(axiom, rules, ruleSetFactory.getFactory());
    }
}
