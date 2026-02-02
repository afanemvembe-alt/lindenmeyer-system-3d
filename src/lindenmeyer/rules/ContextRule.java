package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;

/**
 * Règle contenant une `SymbolList` comme prédécesseur.
 */
public class ContextRule extends GenericRule {

    /**
     * Le prédécesseur de la règle.
     */
    SymbolList predecessor;

    @Override
    public SymbolList getPredecessor() {
        return this.predecessor;
    }

    /**
     * Construit une règle a partir de deux `SymbolList`, un prédécesseur et un successeur.
     * @param predecessor une liste de symboles
     * @param successor une autre liste de symboles
     */
    public ContextRule(SymbolList predecessor, SymbolList successor) {
        super(successor);
        this.predecessor = predecessor;
    }
}
