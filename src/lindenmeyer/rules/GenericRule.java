package lindenmeyer.rules;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericRule<T> implements Applicable<T> {

    private List<T> successor;

    public GenericRule(List<T> successor) {
        this.successor = new ArrayList<>(successor);
    }

    @Override
    public List<T> getSuccessor() {
        return this.successor;
    }

    @Override
    public boolean isApplicable(List<T> generation) {
        return generation.equals(this.getPredecessor());
    }
}
