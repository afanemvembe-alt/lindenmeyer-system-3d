package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolFactory;

public class RuleFactory {
    private char separator;
    private SymbolFactory factory;

    public RuleFactory(char separator, SymbolFactory f) {
        this.separator = separator;
        this.factory = f;
    }

    public RuleFactory(SymbolFactory f) {
        this('>', f);
    }

    public RuleSet parseString(String s) {
        return null;
    }
}
