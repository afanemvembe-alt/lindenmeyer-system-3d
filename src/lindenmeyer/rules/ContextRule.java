package lindenmeyer.rules;

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
    }
}
