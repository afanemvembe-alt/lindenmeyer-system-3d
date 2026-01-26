package lindenmeyer.rules;

import lindenmeyer.symbols.*;

public abstract class GenericRule implements Applicable {

    private SymbolList successor;

    public GenericRule(SymbolList successor) {
        this.successor = successor;
    }

    @Override
    public SymbolList getSuccessor() {
        return this.successor;
    }

    @Override
    public boolean isApplicable(SymbolList generation) {
        return generation.equals(this.getPredecessor());
    }
}
