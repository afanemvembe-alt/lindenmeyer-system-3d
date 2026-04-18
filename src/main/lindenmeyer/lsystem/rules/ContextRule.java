package lindenmeyer.lsystem.rules;

import lindenmeyer.lsystem.symbols.Symbol;
import lindenmeyer.lsystem.symbols.SymbolList;

/**
 * Règle contenant une `SymbolList` comme prédécesseur.
 */
public class ContextRule extends GenericRule {

    /**
     * Le prédécesseur de la règle.
     */
    Symbol predecessor;

    SymbolList leftContext;
    SymbolList rightContext;

    @Override
    public SymbolList getPredecessor() {
        return null;
    }

    /**
     * Construit une règle a partir de deux `SymbolList`, un prédécesseur et un successeur.
     * @param predecessor une liste de symboles
     * @param successor une autre liste de symboles
     */
    public ContextRule(Symbol predecessor, SymbolList successor, SymbolList leftContext, SymbolList rightContext) {
        super(successor);
        this.predecessor = predecessor;
        this.leftContext = leftContext;
        this.rightContext = rightContext;
    }
}
