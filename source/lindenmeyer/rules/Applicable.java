package lindenmeyer.rules;

<<<<<<< HEAD
import lindenmeyer.symbols.SymbolList;

public interface Applicable {
    boolean isApplicable(SymbolList generation);
    SymbolList getPredecessor();
    SymbolList getSuccessor();
=======
import java.util.List;

public interface Applicable<T> {
    boolean isApplicable(List<T> generation);
    List<T> getPredecessor();
    List<T> getSuccessor();
>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
}
