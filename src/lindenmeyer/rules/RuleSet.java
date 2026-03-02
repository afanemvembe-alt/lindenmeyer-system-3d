package lindenmeyer.rules;

import java.util.Set;

import lindenmeyer.symbols.SymbolList;

import java.util.HashSet;

/**
 * Un ensemble de règles.
 */
public class RuleSet {
    private HashSet<GenericRule> rules;

    /**
     * Retourne l'ensemble des règles.
     * 
     * @return
     */
    public Set<GenericRule> getRules() {
        return this.rules;
    }

    /**
     * Construit un ensemble de règles à partir de l'ensemble donné.
     * 
     * @param rules
     */
    public RuleSet(Set<GenericRule> rules) {
        this.rules = new HashSet<>(rules);
    }

    /**
     * Construit un ensemble de règles vide.
     */
    public RuleSet() {
        this(new HashSet<>());
    }

    /**
     * Ajoute une règle à l'ensemble.
     * 
     * @param e règle à ajouter
     */
    public void add(GenericRule e) {
        this.rules.add(e);
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

    public void addAll(Set<GenericRule> rules) {
        this.rules.addAll(rules);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof RuleSet) {
            RuleSet other = (RuleSet) obj;
            return other.getRules().equals(getRules());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return getRules().hashCode();
    }
}
