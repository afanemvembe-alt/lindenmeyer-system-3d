package lindenmeyer.rules;

import java.util.Objects;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

public abstract class GenericRule implements Applicable {

    private SymbolList successor;
    private double weight = 1.0;

    public GenericRule(SymbolList successor) {
        if (successor == null) {
            throw new IllegalArgumentException("Le successeur ne peut pas être null");
        }
        this.successor = successor;
    }

    public GenericRule(SymbolList successor, double weight) {
        if (successor == null) {
            throw new IllegalArgumentException("Le successeur ne peut pas être null");
        }
        this.successor = successor;
        this.weight = weight;
    }

    @Override
    public SymbolList getSuccessor() {
        return this.successor;
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public boolean isApplicable(SymbolList generation) {
        return isApplicable(generation, null, null);
    }

    public boolean isApplicable(SymbolList symbol, SymbolList left, SymbolList right) {
        if (symbol == null || getPredecessor() == null) return false;
        return symbol.equals(getPredecessor());
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        if (weight != 1.0) {
            res.append("(").append(weight).append(") ");
        }

        for (Symbol s : getPredecessor()) {
            res.append(s.getSymbol());
        }

        res.append(" -> ");

        for (Symbol s : getSuccessor()) {
            res.append(s.getSymbol());
        }

        return res.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        GenericRule tmp = (GenericRule) obj;

        return Double.compare(tmp.weight, weight) == 0 &&
               getPredecessor().equals(tmp.getPredecessor()) &&
               getSuccessor().equals(tmp.getSuccessor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPredecessor(), getSuccessor(), weight);
    }
}