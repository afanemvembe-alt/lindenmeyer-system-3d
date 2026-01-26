package lindenmeyer.rules;

<<<<<<< HEAD
import lindenmeyer.symbols.*;

public abstract class GenericRule implements Applicable {

    private SymbolList successor;

    public GenericRule(SymbolList successor) {
        this.successor = successor;
    }

    @Override
    public SymbolList getSuccessor() {
=======
import java.util.ArrayList;
import java.util.List;

public abstract class GenericRule<T> implements Applicable<T> {

    private List<T> successor;

    public GenericRule(List<T> successor) {
        this.successor = new ArrayList<>(successor);
    }

    @Override
    public List<T> getSuccessor() {
>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
        return this.successor;
    }

    @Override
<<<<<<< HEAD
    public boolean isApplicable(SymbolList generation) {
=======
    public boolean isApplicable(List<T> generation) {
>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
        return generation.equals(this.getPredecessor());
    }
}
