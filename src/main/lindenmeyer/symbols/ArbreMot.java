package lindenmeyer.symbols;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * Un L-Système, représenté sous la forme d'une foret.
 *
 * Cette structure facilite la création d'une structure récursive, moins couteuse en mémoire.
 */
public class ArbreMot implements Iterable<Mot> {

    private Mot value;
    private ArbreMot premierEnfant;
    private ArbreMot prochaineFratrie;
    private Integer depth;

    public Mot getValue() {
        return value;
    }

    public ArbreMot getPremierEnfant() {
        return premierEnfant;
    }

    public ArbreMot(
        Mot value,
        ArbreMot premierEnfant,
        ArbreMot prochaineFratrie,
        int profondeur
    ) {
        this.value = value;
        this.premierEnfant = premierEnfant;
        this.prochaineFratrie = prochaineFratrie;
        this.depth = profondeur;
    }

    public ArbreMot(Mot value) {
        this(value, null, null, 0);
    }

    public void setValue(Mot value) {
        this.value = value;
    }

    public void setPremierEnfant(ArbreMot premierEnfant) {
        this.premierEnfant = premierEnfant;
    }

    public void setProchaineFratrie(ArbreMot prochaineFratrie) {
        this.prochaineFratrie = prochaineFratrie;
    }

    public void addFratrie(ArbreMot prochaineFratrie) {
        ArbreMot tmp = this;

        while (tmp.getProchaineFratrie() != null) {
            tmp = tmp.getProchaineFratrie();
        }

        tmp.setProchaineFratrie(prochaineFratrie);
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
                      prochaineFratrie.equals(a.getProchaineFratrie())
            );
        }

        return false;
    }

    @Override
    public Iterator<Mot> iterator() {
        return new ArbreMotIterator();
    }

    public class ArbreMotIterator implements Iterator<Mot> {

        private int current_depth = 0;
        private Deque<ArbreMot> stack;

        @Override
        public boolean hasNext() {
            return stack.isEmpty();
        }

        @Override
        public Mot next() {
            // TODO Auto-generated method stub
            return null;
        }
    }

    public ArbreMot getProchaineFratrie() {
        return prochaineFratrie;
    }

    public Integer getDepth() {
        return depth;
    }
}
