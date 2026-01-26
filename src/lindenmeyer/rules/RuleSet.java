package lindenmeyer.rules;

import java.util.Set;
import java.util.HashSet;

public class RuleSet {
    private HashSet<Applicable> rules;

    public Set<Applicable> getRules() {
        return this.rules;
    }

    public RuleSet(Set<Applicable> rules) {
        this.rules = new HashSet<>(rules);
    }
}
