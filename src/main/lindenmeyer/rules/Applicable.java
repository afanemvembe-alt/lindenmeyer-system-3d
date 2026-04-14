package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;

/**
 * Interface représentant une règle applicable dans un L-System.
 * Elle permet de vérifier si une règle peut s'appliquer à un symbole
 * (avec ou sans contexte), et de récupérer le prédécesseur et le successeur.
 */
public interface Applicable {

    /**
     * Détermine si la règle est applicable SANS contexte.
     * (Méthode par défaut qui appelle la version avec contexte)
     *
     * @param symbol symbole ou groupe de symboles à tester
     * @return true si la règle est applicable, false sinon
     */
    default boolean isApplicable(SymbolList symbol) {
        return isApplicable(symbol, null, null);
    }

    /**
     * Détermine si la règle est applicable AVEC contexte.
     *
     * @param symbol le symbole central à tester
     * @param left le contexte à gauche (peut être null)
     * @param right le contexte à droite (peut être null)
     * @return true si la règle est applicable, false sinon
     */
    boolean isApplicable(SymbolList symbol, SymbolList left, SymbolList right);

    /**
     * Retourne la liste de symboles avant transformation (prédécesseur).
     *
     * @return une liste de symboles
     */
    SymbolList getPredecessor();

    /**
     * Retourne la liste de symboles après transformation (successeur).
     *
     * @return une liste de symboles
     */
    SymbolList getSuccessor();
}