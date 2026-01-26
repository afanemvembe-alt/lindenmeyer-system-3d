package lindenmeyer.rules;

import java.util.List;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

public interface Applicable {
    boolean isApplicable(SymbolList generation);
    SymbolList getPredecessor();
    SymbolList getSuccessor();
}
