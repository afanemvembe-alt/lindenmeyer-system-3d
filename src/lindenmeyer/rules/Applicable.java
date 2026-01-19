package lindenmeyer.rules;

import java.util.List;

public interface Applicable<T> {
    boolean isApplicable(List<T> generation);
    List<T> getPredecessor();
    List<T> getSuccessor();
}
