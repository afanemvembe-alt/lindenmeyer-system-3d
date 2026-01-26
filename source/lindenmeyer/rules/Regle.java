package lindenmeyer.rules;

import lindenmeyer.symbols.Symbol;
import java.util.List;

public interface Regle {
    Symbol getPredecesseur();
    List<Symbol> getSuccesseur();
}
