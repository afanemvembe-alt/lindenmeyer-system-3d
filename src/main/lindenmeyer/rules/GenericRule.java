package lindenmeyer.rules;

import java.util.Objects;

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
     * 
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
        res += ">";
        for (Symbol s : getSuccessor()) {
            res += s.getSymbol();
        }

        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GenericRule) {
            GenericRule tmp = (GenericRule) obj;
            return getPredecessor().equals(tmp.getPredecessor()) && getSuccessor().equals(tmp.getSuccessor());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPredecessor(), getSuccessor());
    }
}
