package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;

public class RuleSet implements Iterable<GenericRule> {
    private List<GenericRule> rules = new ArrayList<>();

    public RuleSet() {}

    public RuleSet(Collection<GenericRule> rules) {
        this.rules = new ArrayList<>(rules);
    }

    public List<GenericRule> getRules() { return this.rules; }
    public void add(GenericRule rule) { this.rules.add(rule); }

    public SymbolList successorOf(SymbolList symbol, SymbolList left, SymbolList right) {
        List<GenericRule> applicableRules = new ArrayList<>();
        double totalWeight = 0;

        for (GenericRule r : rules) {
            if (r.isApplicable(symbol, left, right)) {
                applicableRules.add(r);
                totalWeight += r.getWeight(); // Marche car défini dans GenericRule
            }
        }

        if (applicableRules.isEmpty()) return null;

        double dice = Math.random() * totalWeight;
        double currentRange = 0;

        for (GenericRule r : applicableRules) {
            currentRange += r.getWeight();
            if (dice <= currentRange) return r.getSuccessor();
        }
        return applicableRules.get(0).getSuccessor();
    }

@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof RuleSet)) return false;
    RuleSet other = (RuleSet) obj;
    // Deux RuleSets sont égaux si leurs listes de règles sont identiques
    return java.util.Objects.equals(this.rules, other.getRules());
}

    @Override
    public Iterator<GenericRule> iterator() { return rules.iterator(); }
}