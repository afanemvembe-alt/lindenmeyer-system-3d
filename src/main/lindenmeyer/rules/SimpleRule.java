package lindenmeyer.rules;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

public class SimpleRule extends GenericRule {

    public SimpleRule(SymbolList predecessor, SymbolList successor) {
        super(predecessor, successor);
    }

    // Constructeur pratique pour un seul symbole
    public SimpleRule(Symbol predecessor, SymbolList successor) {
        super(SymbolList.of(predecessor), successor);
    }

    @Override
    public boolean isApplicable(SymbolList symbol, SymbolList left, SymbolList right) {
        return this.predecessor.equals(symbol);
    }
}