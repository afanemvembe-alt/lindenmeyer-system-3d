package lindenmeyer.rules;

<<<<<<< HEAD
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
=======
import java.util.ArrayList;
import java.util.List;

public class ContextRule<T> extends GenericRule<T> {

    List<T> predecessor;

    @Override
    public List<T> getPredecessor() {
        return this.predecessor;
    }

    public ContextRule(List<T> predecessor, List<T> successor) {
        super(successor);
        this.predecessor = new ArrayList<>(predecessor);
>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
    }
}
