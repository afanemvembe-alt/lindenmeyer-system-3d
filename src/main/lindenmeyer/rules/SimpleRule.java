package lindenmeyer.rules;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

/**
 * Une règle ayant comme prédécesseur un unique symbole.
 */
public class SimpleRule extends GenericRule {

    /**
     * Le symbole du prédécesseur.
     */
    private Symbol predecessor;

    /**
     * Cree une nouvelle règle a partir du symbole donne en prédécesseur et de la liste de symboles
     * donnee en successeur.
     * @param predecessor un symbole
     * @param successor une liste de symboles
     */
    public SimpleRule(Symbol predecessor, SymbolList successor, double weight) {
    super(successor, weight);
    this.predecessor = predecessor;
}

    @Override
    public SymbolList getPredecessor() {
        return SymbolList.of(this.predecessor);
    }

    @Override
public boolean isApplicable(SymbolList symbol, SymbolList left, SymbolList right) {
    return symbol.equals(getPredecessor());
}
}
