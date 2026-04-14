package lindenmeyer.symbols;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ArbreMotTest {

    @Test
    void affiche() {
        NoeudMot a = new NoeudMot(new Symbol('a'));
        NoeudMot b = new NoeudMot(new Symbol('b'));
        NoeudMot plus = new NoeudMot(new Symbol('+'));

        a.setRemplacement(b);

        b.setRemplacement(a);
        b.setProchain(plus);
        b.getProchain().setProchain(b);

        ArbreMot arbre = new ArbreMot();
        arbre.setRacine(a);
        arbre.setProfondeurMax(1);

        List<Mot> mots = new ArrayList<>();

        Iterator<Mot> iter = arbre.iterator();

        while (iter.hasNext()) {
            mots.add(iter.next());
        }

        System.err.println(b);
        System.err.println(b.getProchain());
        System.err.println(b.getProchain().getProchain());

        assertEquals(
            List.of(b.getValeur(), plus.getValeur(), b.getValeur()),
            mots
        );
    }
}
