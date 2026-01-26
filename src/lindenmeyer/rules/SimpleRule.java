package lindenmeyer.rules;

import java.util.List;

public class SimpleRule<T> extends GenericRule<T> {

    private T predecessor;

    public SimpleRule(T predecessor, List<T> successor) {
        super(successor);
        this.predecessor = predecessor;
    }

    @Override
    public List<T> getPredecessor() {
        return List.of(this.predecessor);
    }

    
}
