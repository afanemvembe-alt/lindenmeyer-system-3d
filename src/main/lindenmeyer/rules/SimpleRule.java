package lindenmeyer.rules;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

public class SimpleRule extends GenericRule {

    public SimpleRule(Symbol predecessor, SymbolList successor, double weight) {
        // On passe le prédécesseur sous forme de liste à la classe mère
        super(SymbolList.of(predecessor), successor, weight);
    }

    public SimpleRule(Symbol predecessor, SymbolList successor) {
        this(predecessor, successor, 1.0);
    }

    @Override
    public boolean isApplicable(SymbolList symbol, SymbolList left, SymbolList right) {
        // Une règle simple ne regarde que si le symbole central correspond
        return this.predecessor.equals(symbol);
    }
}