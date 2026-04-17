package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;

public class ContextRule extends GenericRule {
    private SymbolList leftContext;
    private SymbolList rightContext;
    private double weight;

    public ContextRule(SymbolList predecessor, SymbolList successor, SymbolList left, SymbolList right, double weight) {
        super(predecessor, successor);
        this.leftContext = left;
        this.rightContext = right;
        this.weight = weight;
    }

    @Override
    public double getWeight() { return this.weight; }

    @Override
    public boolean isApplicable(SymbolList symbol, SymbolList left, SymbolList right) {
        if (!symbol.equals(predecessor)) return false;
        
        // Vérification sécurisée du contexte
        boolean leftMatch = (leftContext == null || (left != null && !left.isEmpty() && left.get(left.size()-1).equals(leftContext.get(0))));
        boolean rightMatch = (rightContext == null || (right != null && !right.isEmpty() && right.get(0).equals(rightContext.get(0))));
        
        return leftMatch && rightMatch;
    }

    @Override
    public String toString() {
        return "(" + weight + ") " + leftContext + " < " + predecessor + " > " + rightContext + " -> " + successor;
    }
}