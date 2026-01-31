package lindenmeyer.rules;

import java.util.Set;

import lindenmeyer.symbols.SymbolList;

import java.util.HashSet;

/**
 * Un ensemble de regles.
 */
public class RuleSet {
    private HashSet<Applicable> rules;

    public Set<Applicable> getRules() {
        return this.rules;
    }

    public RuleSet(Set<Applicable> rules) {
        this.rules = new HashSet<>(rules);
    }

    public RuleSet() {
        this(new HashSet<>());
    }

    public void add(Applicable e) {
        this.rules.add(e);
    }

    public SymbolList apply(SymbolList s) {
        return null;
    }
}
