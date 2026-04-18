package lindenmeyer.lsystem.rules;

import lindenmeyer.lsystem.symbols.Symbol;
import lindenmeyer.lsystem.symbols.SymbolList;

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
    public SimpleRule(Symbol predecessor, SymbolList successor) {
        super(successor);
        this.predecessor = predecessor;
    }

    @Override
    public SymbolList getPredecessor() {
        return SymbolList.of(this.predecessor);
    }

    
}
