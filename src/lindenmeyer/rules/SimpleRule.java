package lindenmeyer.rules;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

public class SimpleRule extends GenericRule {

    private Symbol predecessor;

    public SimpleRule(Symbol predecessor, SymbolList successor) {
        super(successor);
        this.predecessor = predecessor;
    }

    @Override
    public SymbolList getPredecessor() {
        return SymbolList.of(this.predecessor);
    }

    
}
