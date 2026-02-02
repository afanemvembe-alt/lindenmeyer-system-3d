package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;

/**
 * Un objet qui permet de transformer une `SymbolList` en une autre.
 */
public interface Applicable {
    /**
     * Determine si cette objet est applicable.
     * @param generation symbole a tester
     * @return true si la règle est applicable, false sinon
     */
    boolean isApplicable(SymbolList generation);
    /**
     * Retourne la liste de symboles avant transformation.
     * @return une liste de symboles
     */
    SymbolList getPredecessor();
    
    /**
     * Retourne la liste correspondante apres transformation.
     * 
     * @return une list de symboles
     */
    SymbolList getSuccessor();
}
