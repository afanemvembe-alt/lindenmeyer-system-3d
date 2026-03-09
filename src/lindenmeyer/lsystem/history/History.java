package lindenmeyer.lsystem.history;

import java.util.ArrayList;
import java.util.List;

// PATTERN MEMENTO

public class History {
    private List<Object> inner;

    public History() {
        this.inner = new ArrayList<>();
    }

    public void addState(Object s) {
        inner.add(s);
    }

    public Object getState(int index) {
        return inner.get(index);
    }
}
