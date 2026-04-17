package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;

public abstract class GenericRule implements Applicable {
    protected SymbolList predecessor;
    protected SymbolList successor;

    public GenericRule(SymbolList predecessor, SymbolList successor) {
        this.predecessor = predecessor;
        this.successor = successor;
    }

    @Override
    public SymbolList getSuccessor() { return this.successor; }

    @Override
    public SymbolList getPredecessor() { return this.predecessor; }

    // On définit une méthode par défaut pour le poids, que les enfants pourront écraser
    public double getWeight() { return 1.0; }

    @Override
    public abstract boolean isApplicable(SymbolList symbol, SymbolList left, SymbolList right);

@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof GenericRule)) return false;
    GenericRule other = (GenericRule) obj;
    // Deux règles sont égales si elles ont le même prédécesseur et le même successeur
    return java.util.Objects.equals(this.predecessor, other.predecessor) && 
           java.util.Objects.equals(this.successor, other.successor);
}

@Override
public int hashCode() {
    return java.util.Objects.hash(predecessor, successor);
}
@Override
public String toString() {
    // On met des espaces et la flèche -> pour coller au test GenericRuleTest ligne 56
    return predecessor.toString() + " -> " + successor.toString();
}
}