package lindenmeyer.symbols;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class MotTest {

    static final Symbol a = new Symbol('a');
    static final Symbol a1 = new Symbol(((Symbol) a).getSymbol());
    static final Symbol b = new Symbol('b');
    static final SymbolList list = SymbolList.of((Symbol) a, (Symbol) b);

    @Test
    void equals() {
        assertEquals(a, a);
        assertEquals(a, a1);
        assertFalse(a == a1);
        assertNotEquals(a, b);
        assertNotEquals(list, a);
    }

    @Test
    void affiche() {
        assertEquals(List.of(a, b), list.affiche());
        assertEquals(List.of(a1, b), list.affiche());
        assertNotEquals(List.of(b, a), list.affiche());
    }
}
