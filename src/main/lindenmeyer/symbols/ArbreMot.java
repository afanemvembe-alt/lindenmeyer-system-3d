package lindenmeyer.symbols;

import java.util.*;

public class ArbreMot implements Iterable<Mot> {

    private NoeudMot racine;
    private Integer profondeurMax;

    public ArbreMot(NoeudMot racine, int profondeurMax) {
        this.racine = racine;
        this.profondeurMax = profondeurMax;
    }

    public ArbreMot(NoeudMot racine) {
        this(racine, 0);
    }

    public void setProfondeurMax(int profondeurMax) {
        this.profondeurMax = profondeurMax;
    }

    public int getProfondeurMax() {
        return profondeurMax;
    }

    public ArbreMot() {
        this(null, 0);
    }

    public NoeudMot getRacine() {
        return racine;
    }

    public void setRacine(NoeudMot racine) {
        this.racine = racine;
    }

    @Override
    public Iterator<Mot> iterator() {
        return new ArbreMotIterator();
    }

    public class ArbreMotIterator implements Iterator<Mot> {

        private int profondeur = 0;
        private NoeudMot noeudCourant;
        private List<NoeudMot> stack;

        public ArbreMotIterator() {
            stack = new ArrayList<>();
            stack.add(racine);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Mot next() {
            noeudCourant = stack.removeLast();
            Mot res;

            while (
                noeudCourant.getRemplacement() != null &&
                profondeur < profondeurMax
            ) {
                stack.add(noeudCourant);
                noeudCourant = noeudCourant.getRemplacement();
                profondeur++;
            }

            res = noeudCourant.getValeur();

            while (!stack.isEmpty() && noeudCourant.getProchain() == null) {
                noeudCourant = stack.removeLast();
                profondeur--;
            }

            if (noeudCourant.getProchain() != null) {
                stack.add(noeudCourant.getProchain());
            }

            return res;
        }
    }
}
