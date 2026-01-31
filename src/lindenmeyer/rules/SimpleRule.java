package lindenmeyer.rules;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

/**
 * Une regle ayant comme predecesseur un unique symbole.
 */
public class SimpleRule extends GenericRule {

    /**
     * Le symbole du predecesseur.
     */
    private Symbol predecessor;

    /**
     * Cree une nouvelle regle a partir du symbole donne en predecesseur et de la liste de symboles
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
