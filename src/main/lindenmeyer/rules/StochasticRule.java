package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolList;

public class StochasticRule extends SimpleRule {
    private double weight;

    public StochasticRule(SymbolList predecessor, SymbolList successor, double weight) {
        super(predecessor, successor);
        this.weight = weight;
    }

    @Override
    public double getWeight() { return this.weight; }

    @Override
    public String toString() {
        return "(" + weight + ") " + super.toString();
    }
}