package lindenmeyer.rules;

import java.util.Set;

import lindenmeyer.symbols.SymbolList;

import java.util.HashSet;

/**
 * Un ensemble de regles.
 */
public class RuleSet {
    private HashSet<GenericRule> rules;

    public Set<GenericRule> getRules() {
        return this.rules;
    }

    public RuleSet(Set<GenericRule> rules) {
        this.rules = new HashSet<>(rules);
    }

    public RuleSet() {
        this(new HashSet<>());
    }

    public void add(GenericRule e) {
        this.rules.add(e);
    }

    public SymbolList successorOf(SymbolList s) {
        return null;
    }
}
