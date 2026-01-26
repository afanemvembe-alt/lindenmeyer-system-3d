package lindenmeyer.symbols;

import java.util.List;
import java.util.ArrayList;

public class SymbolList {
    private SymbolFactory factory;
    private ArrayList<Symbol> symbols;

    public SymbolList() {
        this.factory = new SymbolFactory();
    }

    public SymbolList(SymbolFactory factory) {
        this.factory = factory;
    }

    public void add(Symbol symbol) {
        this.symbols.add(symbol);
    }

    public void add(char c) {
        this.add(this.factory.getSymbol(c));
    }

    public static SymbolList fromString(String s, SymbolFactory f) {
        SymbolList res = new SymbolList(f);

        for (int i = 0; i < s.length(); i++) {
            res.add(s.charAt(i));
        }

        return res;
    }

    public List<Symbol> getSymbols() {
        return this.symbols;
    }
}
