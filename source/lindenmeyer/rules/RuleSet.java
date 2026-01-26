package lindenmeyer.rules;

import java.util.Set;
import java.util.HashSet;

<<<<<<< HEAD
public class RuleSet {
    private HashSet<Applicable> rules;

    public Set<Applicable> getRules() {
        return this.rules;
    }

    public RuleSet(Set<Applicable> rules) {
=======
import lindenmeyer.symbols.Symbols;
import lindenmeyer.symbols.Symbol;

public class RuleSet {
    private HashSet<Applicable<Symbol>> rules;

    public Set<Applicable<Symbol>> getRules() {
        return this.rules;
    }

    public RuleSet(Set<Applicable<Symbol>> rules) {
>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
        this.rules = new HashSet<>(rules);
    }
}
