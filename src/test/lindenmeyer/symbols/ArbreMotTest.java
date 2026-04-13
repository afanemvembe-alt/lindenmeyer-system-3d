package lindenmeyer.symbols;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ArbreMotTest {

    static Symbol aMot = new Symbol('a');
    static Symbol bMot = new Symbol('b');
    static SymbolList lMot = SymbolList.of(aMot, bMot, aMot);

    @Test
    void equals() {
        ArbreMot a = new ArbreMot(aMot);
        ArbreMot b = new ArbreMot(bMot);
        ArbreMot l = new ArbreMot(lMot);

        ArbreMot arbreMot = new ArbreMot(a.getValue());

        assertEquals(a, arbreMot);

        arbreMot.setPremierEnfant(l);
        arbreMot.addFratrie(b);
        arbreMot.addFratrie(a);

        ArbreMot brbreMot = new ArbreMot(a.getValue());

        brbreMot.setPremierEnfant(l);
        brbreMot.addFratrie(b);
        brbreMot.addFratrie(a);

        assertEquals(arbreMot, brbreMot);
    }

    // @Test
    // void affiche() {
    //     ArbreMot a = new ArbreMot(aMot);
    //     ArbreMot b = new ArbreMot(bMot);
    //     ArbreMot l = new ArbreMot(lMot);

    //     ArbreMot arbreMot = new ArbreMot(a.getValue());

    //     arbreMot.setPremierEnfant(l);
    //     arbreMot.addFratrie(b);
    //     arbreMot.addFratrie(a);

    //     assertEquals(
    //         List.of(aMot, bMot, aMot, bMot, aMot),
    //         arbreMot.affiche(5)
    //     );
    // }
}
