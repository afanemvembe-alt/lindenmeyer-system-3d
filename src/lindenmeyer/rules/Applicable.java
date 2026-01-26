package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;

public interface Applicable {
    boolean isApplicable(SymbolList generation);
    SymbolList getPredecessor();
    SymbolList getSuccessor();
}
