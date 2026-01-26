package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;

public class ContextRule extends GenericRule {

    SymbolList predecessor;

    @Override
    public SymbolList getPredecessor() {
        return this.predecessor;
    }

    public ContextRule(SymbolList predecessor, SymbolList successor) {
        super(successor);
        this.predecessor = predecessor;
    }
}
