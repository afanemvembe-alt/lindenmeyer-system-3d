package lindenmeyer.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lindenmeyer.symbols.SymbolList;

public class RuleSet extends java.util.HashSet<GenericRule> {

    private final Random random = new Random();

    public RuleSet() {
        super();
    }

    public SymbolList successorOf(SymbolList symbol, SymbolList left, SymbolList right) {

        if (symbol == null) return null;

        List<GenericRule> applicableRules = new ArrayList<>();

        // 1. Trouver les règles applicables
        for (GenericRule rule : this) {
            if (rule.isApplicable(symbol, left, right)) {
                applicableRules.add(rule);
            }
        }

        if (applicableRules.isEmpty()) {
            return null;
        }

        // 2. Gestion stochastique
        double totalWeight = 0;
        for (GenericRule r : applicableRules) {
            totalWeight += r.getWeight();
        }

        // Sécurité si tous les poids sont nuls
        if (totalWeight == 0) {
            return applicableRules.get(0).getSuccessor();
        }

        double threshold = random.nextDouble() * totalWeight;
        double currentSum = 0;

        for (GenericRule rule : applicableRules) {
            currentSum += rule.getWeight();
            if (currentSum >= threshold) {
                return rule.getSuccessor();
            }
        }

        // fallback sécurité
        return applicableRules.get(0).getSuccessor();
    }

    public SymbolList successorOf(SymbolList symbol) {
        return successorOf(symbol, null, null);
    }
}