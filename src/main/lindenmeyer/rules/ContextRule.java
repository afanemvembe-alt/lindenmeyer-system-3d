package lindenmeyer.rules;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

/**
 * Règle gérant le contexte (voisinage gauche et droit).
 * Syntaxe type : A < B > C -> Succession
 */
public class ContextRule extends GenericRule {

    private Symbol predecessor;
    private SymbolList leftContext;
    private SymbolList rightContext;

    /**
     * Constructeur classique
     */
    public ContextRule(Symbol predecessor, SymbolList successor,
                       SymbolList leftContext, SymbolList rightContext) {
        super(successor);
        this.predecessor = predecessor;
        this.leftContext = leftContext;
        this.rightContext = rightContext;
    }

    /**
     * Constructeur stochastique (avec poids)
     */
    public ContextRule(Symbol predecessor, SymbolList successor,
                       SymbolList leftContext, SymbolList rightContext,
                       double weight) {
        super(successor, weight);
        this.predecessor = predecessor;
        this.leftContext = leftContext;
        this.rightContext = rightContext;
    }

    @Override
    public SymbolList getPredecessor() {
        return SymbolList.of(predecessor);
    }

    /**
     * Vérifie si la règle est applicable en tenant compte du contexte.
     */
    @Override
    public boolean isApplicable(SymbolList symbol, SymbolList left, SymbolList right) {

        // 1. Vérifie le symbole central
        if (!symbol.equals(getPredecessor())) {
            return false;
        }

        // 2. Vérifie le contexte gauche (voisin immédiat)
        if (leftContext != null && !leftContext.isEmpty()) {
            if (left == null || left.isEmpty()) {
                return false;
            }

            Symbol lastLeft = left.get(left.size() - 1);
            if (!lastLeft.equals(leftContext.get(0))) {
                return false;
            }
        }

        // 3. Vérifie le contexte droit (voisin immédiat)
        if (rightContext != null && !rightContext.isEmpty()) {
            if (right == null || right.isEmpty()) {
                return false;
            }

            Symbol firstRight = right.get(0);
            if (!firstRight.equals(rightContext.get(0))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        String leftStr = (leftContext == null || leftContext.isEmpty()) ? "" : leftContext.toString();
        String rightStr = (rightContext == null || rightContext.isEmpty()) ? "" : rightContext.toString();

        return leftStr + " < " + predecessor + " > " + rightStr + " -> " + getSuccessor();
    }
}