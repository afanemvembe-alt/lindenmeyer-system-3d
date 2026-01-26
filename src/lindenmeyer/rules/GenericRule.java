package lindenmeyer.rules;

import java.util.ArrayList;
import java.util.List;

import lindenmeyer.symbols.*;

public abstract class GenericRule<T> implements Applicable<T> {

    private List<T> successor;

    public GenericRule(List<T> successor) {
        this.successor = new ArrayList<>(successor);
    }

    @Override
    public List<T> getSuccessor() {
        return this.successor;
    }

    @Override
    public boolean isApplicable(List<T> generation) {
        return generation.equals(this.getPredecessor());
    }

    public static abstract SymbolList parsePredecessor(String s, SymbolFactory f);

    public SymbolList parseSuccessor(String s, SymbolFactory f) {
        SymbolList.fromString(s, f);
    }

    public static GenericRule<Symbol> fromString(String s, SymbolFactory f) {

    }
}
