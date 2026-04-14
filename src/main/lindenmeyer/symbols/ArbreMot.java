package lindenmeyer.symbols;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Un L-Système, représenté sous la forme d'une foret.
 *
 * Cette structure facilite la création d'une structure récursive, moins couteuse en mémoire.
 */
public class ArbreMot implements Iterable<Mot>, Mot {

    private NoeudMot premierEnfant;
    // necessary to prevent infinitely recursive trees
    private Integer max_depth;

    @Override
    public List<Symbol> affiche() {
        ArrayList<Symbol> res = new ArrayList<>();

        Iterator<Mot> iter = iterator();

        while (iter.hasNext()) {
            res.addAll(iter.next().affiche());
        }

        return res;
    }

    @Override
    public Iterator<Mot> iterator() {
        return new ArbreMotIterator();
    }

    public class ArbreMotIterator implements Iterator<Mot> {

        private int current_depth = 0;
        private NoeudMot current_node = premierEnfant;
        private ArrayList<NoeudMot> stack;

        public ArbreMotIterator() {
            stack = new ArrayList<>(List.of(current_node));
            current_depth = 0;

            while (
                current_node.getPremierEnfant() != null &&
                current_depth < max_depth
            ) {
                stack.add(current_node);
                current_node = current_node.getPremierEnfant();
                current_depth += 1;
            }

            // current_node = a;
        }

        @Override
        public boolean hasNext() {
            return current_node != null;
        }

        @Override
        public Mot next() {
            Mot res = current_node.getValue();

            while (current_node.getProchaineFratrie() != null) {
                current_node = stack.removeLast();
                current_depth--;
            }

            current_node = current_node.getProchaineFratrie();

            if (current_node == null) {
                return res;
            }

            while (
                current_node.getPremierEnfant() != null &&
                current_depth < max_depth
            ) {
                current_depth++;
                stack.add(current_node);
                current_node = current_node.getPremierEnfant();
            }

            return res;
        }
    }
}
