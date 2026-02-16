package lindenmeyer.rules;

import lindenmeyer.symbols.*;

/**
 * Règle abstraite, contenant un prédécesseur et un successeur.
 */
public abstract class GenericRule implements Applicable {

    /**
     * Le successeur de la règle.
     */
    private SymbolList successor;

    /**
     * Construit une règle contenant le successeur donne.
     * @param successor une SymbolList des successeurs
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

    @Override
    public String toString() {
        String res = "";
        for (Symbol s : getPredecessor()) {
            res += s.getSymbol();
        }
        res += "->";
        for (Symbol s : getSuccessor()) {
            res += s.getSymbol();
        }

        return res;
    }
}
