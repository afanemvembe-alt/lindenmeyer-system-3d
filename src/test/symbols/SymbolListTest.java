package test.symbols;

import java.util.List;

import lindenmeyer.symbols.*;

public class SymbolListTest {
    public static void main(String[] args) {
        boolean res = true;

        res &= newList();
        res &= addItem();
        res &= addCharacter();
        res &= listsEqual();

        System.out.println(SymbolListTest.class + " : " + (res ? "All tests for OK" : "Test failed"));
    }

    public static boolean newList() {
        boolean res = true;

        SymbolList s = new SymbolList();

        res &= s instanceof SymbolList;
        res &= s.getSymbols() instanceof List;
        res &= s.getSymbols().isEmpty();

        return res;
    }

    public static boolean addItem() {
        boolean res = true;

        SymbolList s = new SymbolList();
        
        Symbol a = new Symbol('A');
        s.add(a);

        res &= s.getSymbols().contains(a);

        return res;
    }

    public static boolean addCharacter() {
        boolean res = true;

        SymbolList s = new SymbolList();
        SymbolFactory f = new SymbolFactory();

        Symbol a = f.getSymbol('A');

        s.add('A');

        res &= s.getSymbols().contains(a);

        return res;
    }

    public static boolean listsEqual() {
        boolean res = true;

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

        res &= la.equals(lb);
        res &= !la.equals(lc);

        return res;
    }
}
