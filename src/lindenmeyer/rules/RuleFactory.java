package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;

public class RuleFactory {
    private char rule_separator;
    private char part_separator;
    private SymbolFactory factory;

    public RuleFactory(char rule_separator, char part_separator, SymbolFactory f) {
        this.rule_separator = rule_separator;
        this.part_separator = part_separator;
        this.factory = f;
    }

    public RuleFactory(SymbolFactory f) {
        this(',', '>', f);
    }

    public char getRuleSeparator() {
        return this.rule_separator;
    }

    public char getPartSeparator() {
        return this.part_separator;
    }

    public SymbolFactory getFactory() {
        return this.factory;
    }

    public RuleSet parseString(String s) {
        RuleSet res = new RuleSet();
        for (String part : s.split("" + this.rule_separator)) {
            String[] parts = part.split("" + this.part_separator);

            SymbolList successor = new SymbolList(this.factory);

            for (int i = 0; i < parts[1].length(); i += 1) {
                successor.add(parts[1].charAt(i));
            }

            res.add(new SimpleRule(this.factory.getSymbol(parts[0].charAt(0)), successor));

        }
        return res;
    }
}
