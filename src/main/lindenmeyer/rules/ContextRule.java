package lindenmeyer.rules;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

public class ContextRule extends GenericRule {

    private Symbol centralSymbol; // Renommé pour clarté
    private SymbolList leftContext;
    private SymbolList rightContext;

    public ContextRule(Symbol predecessor, SymbolList successor,
                       SymbolList leftContext, SymbolList rightContext,
                       double weight) {
        // Transmission du prédécesseur central à la classe mère
        super(SymbolList.of(predecessor), successor, weight);
        this.centralSymbol = predecessor;
        this.leftContext = leftContext;
        this.rightContext = rightContext;
    }

    // Surcharge pour le cas non-stochastique
    public ContextRule(Symbol predecessor, SymbolList successor,
                       SymbolList leftContext, SymbolList rightContext) {
        this(predecessor, successor, leftContext, rightContext, 1.0);
    }

    @Override
    public boolean isApplicable(SymbolList symbol, SymbolList left, SymbolList right) {
        // 1. Vérifie le symbole central (via la méthode héritée de GenericRule)
        if (!symbol.equals(this.predecessor)) {
            return false;
        }

        // 2. Vérifie le contexte gauche (dernier symbole de la liste 'left')
        if (leftContext != null && !leftContext.isEmpty()) {
            if (left == null || left.isEmpty()) return false;
            
            Symbol lastLeft = left.get(left.size() - 1);
            if (!lastLeft.equals(leftContext.get(0))) return false;
        }

        // 3. Vérifie le contexte droit (premier symbole de la liste 'right')
        if (rightContext != null && !rightContext.isEmpty()) {
            if (right == null || right.isEmpty()) return false;

            Symbol firstRight = right.get(0);
            if (!firstRight.equals(rightContext.get(0))) return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String weightStr = (getWeight() != 1.0) ? "(" + getWeight() + ")" : "";
        String leftStr = (leftContext == null || leftContext.isEmpty()) ? "" : leftContext.toString() + " < ";
        String rightStr = (rightContext == null || rightContext.isEmpty()) ? "" : " > " + rightContext.toString();

        return weightStr + leftStr + centralSymbol + rightStr + " -> " + getSuccessor();
    }
}