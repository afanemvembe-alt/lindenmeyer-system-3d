package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;

/**
 * Regle contenant une `SymbolList` comme predecesseur.
 */
public class ContextRule extends GenericRule {

    /**
     * Le predecesseur de la regle.
     */
    SymbolList predecessor;

    @Override
    public SymbolList getPredecessor() {
        return this.predecessor;
    }

    /**
     * Construit une regle a partir de deux `SymbolList`, un predecesseur et un successeur.
     * @param predecessor une liste de symboles
     * @param successor une autre liste de symboles
     */
    public ContextRule(SymbolList predecessor, SymbolList successor) {
        super(successor);
        this.predecessor = predecessor;
    }
}
