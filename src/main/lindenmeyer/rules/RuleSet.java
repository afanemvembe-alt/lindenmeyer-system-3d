package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import java.util.Collection;

public class RuleSet implements Iterable<GenericRule> {
    private List<GenericRule> rules;

    public RuleSet() {
        this.rules = new ArrayList<>();
    }

    // Constructeur pour la compatibilité avec les tests profs
    public RuleSet(Collection<GenericRule> rules) {
        this.rules = new ArrayList<>(rules);
    }

    public List<GenericRule> getRules() {
        return this.rules;
    }

   
    public void clear() {
        this.rules.clear();
    }

    public void add(GenericRule rule) {
        this.rules.add(rule);
    }

    /**
     * Gère le choix de la règle en fonction du contexte et du poids (stochastique)
     */
    public SymbolList successorOf(SymbolList symbol, SymbolList left, SymbolList right) {
        List<GenericRule> applicableRules = new ArrayList<>();
        double totalWeight = 0;

        // 1. Filtrer les règles applicables selon le contexte
        for (GenericRule r : rules) {
            if (r.isApplicable(symbol, left, right)) {
                applicableRules.add(r);
                totalWeight += r.getWeight();
            }
        }

        if (applicableRules.isEmpty()) return null;

        // 2. Tirage au sort pondéré (Stochastique)
        double dice = Math.random() * totalWeight;
        double currentRange = 0;

        for (GenericRule r : applicableRules) {
            currentRange += r.getWeight();
            if (dice <= currentRange) return r.getSuccessor();
        }
        return applicableRules.get(0).getSuccessor();
    }

    public SymbolList successorOf(SymbolList symbol) {
        return successorOf(symbol, null, null);
    }

    @Override
    public Iterator<GenericRule> iterator() {
        return rules.iterator();
    }

    public boolean isEmpty() {
        return rules.isEmpty();
    }

}