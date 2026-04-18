package lindenmeyer.lsystem.rules;

import java.util.Set;

import lindenmeyer.lsystem.symbols.SymbolList;

import java.util.HashSet;

/**
 * Un ensemble de règles.
 */
public class RuleSet extends HashSet<GenericRule> {
    // private HashSet<GenericRule> rules;

    /**
     * Retourne l'ensemble des règles.
     * 
     * @return
     */
    public Set<GenericRule> getRules() {
        return this;
    }

    /**
     * Construit un ensemble de règles à partir de l'ensemble donné.
     * 
     * @param rules
     */
    public RuleSet(Set<GenericRule> rules) {
        // this.rules = new HashSet<>(rules);
        super(rules);
    }

    /**
     * Construit un ensemble de règles vide.
     */
    public RuleSet() {
        super();
    }

    /**
     * Retourne le successeur de la liste de symboles donnée.
     * 
     * @param s une liste de symboles
     * @return le successeur s'il existe, sinon la liste donnee en entree
     */
    public SymbolList successorOf(SymbolList s) {
        for (GenericRule rule : getRules()) {
            if (rule.isApplicable(s)) {
                return rule.getSuccessor();
            }
        }

        return s;
    }
}
