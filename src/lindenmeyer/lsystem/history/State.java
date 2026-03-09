package lindenmeyer.lsystem.history;

import lindenmeyer.symbols.SymbolList;

public class State {
    private SymbolList state;

    public State(SymbolList toSaveState) {
        state = toSaveState;
    }

    public SymbolList getState() {
        return state;
    }
}
