package lindenmeyer.symbols;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Un L-Système, représenté sous la forme d'une foret.
 *
 * Cette structure facilite la création d'une structure récursive, moins couteuse en mémoire.
 */
public class ArbreMot {

    private Mot value;
    private ArbreMot premierEnfant;
    private List<ArbreMot> fratrie;

    public Mot getValue() {
        return value;
    }

    public ArbreMot getPremierEnfant() {
        return premierEnfant;
    }

    public List<ArbreMot> getFratrie() {
        return fratrie;
    }

    public ArbreMot(Mot value, ArbreMot premierEnfant, List<ArbreMot> fratrie) {
        this.value = value;
        this.premierEnfant = premierEnfant;
        this.fratrie = fratrie;
    }

    public ArbreMot(Mot value) {
        this(value, null, new ArrayList<>());
    }

    public void setValue(Mot value) {
        this.value = value;
    }

    public void setPremierEnfant(ArbreMot premierEnfant) {
        this.premierEnfant = premierEnfant;
    }

    public void setFratrie(List<ArbreMot> fratrie) {
        this.fratrie = fratrie;
    }

    public void addFratrie(ArbreMot fratrie) {
        this.fratrie.add(fratrie);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ArbreMot) {
            ArbreMot a = (ArbreMot) obj;

            return (
                value.equals(a.getValue()) && premierEnfant != null
                    ? premierEnfant.equals(a.getPremierEnfant())
                    : premierEnfant == a.getPremierEnfant() &&
                      fratrie.equals(a.getFratrie())
            );
        }

        return false;
    }
}
