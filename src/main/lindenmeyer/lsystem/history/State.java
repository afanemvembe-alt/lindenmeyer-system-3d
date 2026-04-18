package lindenmeyer.lsystem.history;

import lindenmeyer.lsystem.symbols.SymbolList;

/**
 * Un état individuel à stocker.
 */
public class State {
    private SymbolList state;

    /**
     * Crée un nouvel état à partir de l'état donné.
     * 
     * @param toSaveState l'état à sauvegarder
     */
    public State(SymbolList toSaveState) {
        state = toSaveState;
    }

    /**
     * Retourne l'état stocké.
     * 
     * @return l'état stocké
     */
    public SymbolList getState() {
        return state;
    }
}
