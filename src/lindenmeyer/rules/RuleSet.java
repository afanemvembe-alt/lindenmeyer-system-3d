package lindenmeyer.rules;

import java.util.Set;
import java.util.HashSet;

import lindenmeyer.symbols.Symbols;
import lindenmeyer.symbols.Symbol;

public class RuleSet {
    private HashSet<Applicable<Symbol>> rules;

    public Set<Applicable<Symbol>> getRules() {
        return this.rules;
    }

    public RuleSet(Set<Applicable<Symbol>> rules) {
        this.rules = new HashSet<>(rules);
    }
}
