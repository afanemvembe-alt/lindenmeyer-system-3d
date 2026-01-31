package lindenmeyer.rules;

import lindenmeyer.symbols.*;

/**
 * Regle abstraite, contenant un predecesseur et un successeur.
 */
public abstract class GenericRule implements Applicable {

    /**
     * Le successeur de la regle.
     */
    private SymbolList successor;

    /**
     * Construit une regle contenant le successeur donne.
     * @param successor une liste de symboles
     */
    public GenericRule(SymbolList successor) {
        this.successor = successor;
    }

    @Override
    public SymbolList getSuccessor() {
        return this.successor;
    }

    @Override
    public boolean isApplicable(SymbolList generation) {
        return generation.equals(this.getPredecessor());
    }
}
