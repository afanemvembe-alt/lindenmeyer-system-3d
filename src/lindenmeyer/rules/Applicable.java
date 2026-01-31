package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;

/**
 * Un objet qui permet de tranformer une `SymbolList` en une autre.
 */
public interface Applicable {
    /**
     * Determine si cette objet est applicable.
     * @param generation symbole a tester
     * @return true si la regle est applicable, false sinon
     */
    boolean isApplicable(SymbolList generation);
    /**
     * Retourne la liste de symboles avant tranformation.
     * @return une liste de symboles
     */
    SymbolList getPredecessor();
    /**
     * Retourne la liste correspondante apres tranformation.
     * @return une list de symboles
     */
    SymbolList getSuccessor();
}
