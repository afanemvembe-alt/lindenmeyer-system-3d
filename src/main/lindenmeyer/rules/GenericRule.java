package lindenmeyer.rules;

import java.util.Objects;
import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

public abstract class GenericRule implements Applicable {

    protected SymbolList predecessor; // Ajouté pour que les enfants y aient accès
    private SymbolList successor;
    private double weight = 1.0;

    public GenericRule(SymbolList predecessor, SymbolList successor, double weight) {
        if (predecessor == null || successor == null) {
            throw new IllegalArgumentException("Les listes ne peuvent pas être nulles");
        }
        this.predecessor = predecessor;
        this.successor = successor;
        this.weight = weight;
    }

    @Override
    public SymbolList getSuccessor() {
        return this.successor;
    }

    @Override
    public SymbolList getPredecessor() {
        return this.predecessor;
    }

    public double getWeight() {
        return this.weight;
    }

   
    @Override
    public abstract boolean isApplicable(SymbolList symbol, SymbolList left, SymbolList right);

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        if (weight != 1.0) res.append("(").append(weight).append(")");
        
        res.append(predecessor.toString())
           .append(" -> ")
           .append(successor.toString());
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