package lindenmeyer.symbols;

import java.util.List;
import java.util.ArrayList;

public class SymbolList {
    private SymbolFactory factory;
    private List<Symbol> symbols;

    public SymbolList() {
        this(new SymbolFactory());
    }

    public SymbolList(SymbolFactory factory) {
        this(new ArrayList<>(), factory);
    }

    public SymbolList(List<Symbol> s, SymbolFactory f) {
        this.factory = f;
        this.symbols = new ArrayList<>(s);
    }

    public SymbolList(SymbolList s, SymbolFactory f) {
        this(s.getSymbols(), f);
    }

    public static SymbolList of(Symbol s) {
        SymbolList res = new SymbolList(null);
        res.symbols = List.of(s);

        return res;
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

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode() && obj instanceof SymbolList;
    }

    @Override
    public int hashCode() {
        return symbols.hashCode();
    }
}
