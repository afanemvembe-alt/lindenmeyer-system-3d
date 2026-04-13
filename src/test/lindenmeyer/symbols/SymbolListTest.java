package lindenmeyer.symbols;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;
import org.junit.jupiter.api.Test;

public class SymbolListTest {

    // public static void main(String[] args) {
    // boolean res = true;

    // res &= newList();
    // res &= addItem();
    // res &= addCharacter();
    // res &= listsEqual();

    // System.out.println(SymbolListTest.class + " : " + (res ? "All tests for OK" :
    // "Test failed"));
    // }

    // public static boolean newList() {
    // boolean res = true;

    // SymbolList s = new SymbolList();

    // res &= s instanceof SymbolList;
    // res &= s instanceof List;
    // res &= s.isEmpty();

    // return res;
    // }

    @Test
    void newList() {
        SymbolList s = new SymbolList();

        assertTrue(s instanceof SymbolList);
        assertTrue(s instanceof List);
        assertTrue(s.isEmpty());
    }

    // public static boolean addItem() {
    // boolean res = true;

    // SymbolList s = new SymbolList();

    // Symbol a = new Symbol('A');
    // s.add(a);

    // res &= s.contains(a);

    // return res;
    // }

    @Test
    void addItem() {
        SymbolList s = new SymbolList();
        Symbol a = new Symbol('a');

        s.add(a);

        assertTrue(s.contains(a));
    }

    // public static boolean addCharacter() {
    // boolean res = true;

    // SymbolList s = new SymbolList();
    // SymbolFactory f = new SymbolFactory();

    // Symbol a = f.getSymbol('A');

    // s.add('A');

    // res &= s.contains(a);

    // return res;
    // }

    @Test
    void addCharacter() {
        SymbolList s = new SymbolList();
        SymbolFactory f = new SymbolFactory();

        Symbol a = f.getSymbol('A');

        s.add(a);

        assertTrue(s.contains(a));
    }

    // public static boolean listsEqual() {
    // boolean res = true;

    // SymbolFactory f = new SymbolFactory();

    // SymbolList la = new SymbolList(f);
    // SymbolList lb = new SymbolList(f);
    // SymbolList lc = new SymbolList(f);

    // la.add('A');
    // lb.add('A');
    // lc.add('A');

    // la.add('B');
    // lb.add('B');
    // lc.add('C');

    // res &= la.equals(lb);
    // res &= !la.equals(lc);

    // return res;
    // }

    @Test
    void listsEqual() {
        SymbolFactory f = new SymbolFactory();

        SymbolList la = new SymbolList(f);
        SymbolList lb = new SymbolList(f);
        SymbolList lc = new SymbolList(f);

        la.add('A');
        lb.add('A');
        lc.add('A');

        la.add('B');
        lb.add('B');
        lc.add('C');

        assertEquals(la, lb);
        assertNotEquals(la, lc);
    }
}
