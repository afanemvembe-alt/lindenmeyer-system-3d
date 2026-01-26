package lindenmeyer.rules;

<<<<<<< HEAD
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

    
=======
import java.util.List;
import lindenmeyer.symbols.Symbol;

public class SimpleRule implements Regle {
    private final Symbol predecessor;
    private final List<Symbol> successor;
    public SimpleRule(Symbol predecessor, List<Symbol> successor){
        this.predecessor = predecessor;
        this.successor = successor;
    }
    public Symbol getPredecesseur(){ return predecessor; }
    public List<Symbol> getSuccesseur(){ return successor; }
>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
}
