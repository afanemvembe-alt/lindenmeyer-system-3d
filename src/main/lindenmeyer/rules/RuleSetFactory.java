package lindenmeyer.rules;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;

public class RuleSetFactory {

    private char rule_separator;
    private char part_separator;
    private SymbolFactory factory;

    public RuleSetFactory(char rule_separator, char part_separator, SymbolFactory f) {
        this.rule_separator = rule_separator;
        this.part_separator = part_separator;
        this.factory = f;
    }

    public RuleSetFactory(SymbolFactory f) {
        this(',', '>', f);
    }

    public RuleSet parseString(String s) {
        RuleSet res = new RuleSet();
        if (s == null || s.isEmpty()) return res;

        for (String ruleRaw : s.split(String.valueOf(rule_separator))) {
            ruleRaw = ruleRaw.trim();
            if (ruleRaw.isEmpty()) continue;

            String[] parts = ruleRaw.split(String.valueOf(part_separator), 2);
            if (parts.length < 2) continue;

            String leftPart = parts[0].trim();
            String rightPart = parts[1].trim();

            // 1. Extraction du POIDS
            double weight = 1.0;
            if (leftPart.startsWith("(")) {
                int endBracket = leftPart.indexOf(")");
                if (endBracket > 0) {
                    try {
                        weight = Double.parseDouble(leftPart.substring(1, endBracket));
                    } catch (NumberFormatException e) {
                        weight = 1.0;
                    }
                    leftPart = leftPart.substring(endBracket + 1).trim();
                }
            }

            // 2. Création du SUCCESSEUR
            SymbolList successor = new SymbolList(this.factory);
            for (char c : rightPart.toCharArray()) {
                successor.add(this.factory.getSymbol(c));
            }

            // 3. Gestion CONTEXTUELLE ou SIMPLE
            if (leftPart.contains("<") || leftPart.contains(">")) {
                // On utilise une regex pour découper proprement autour de < et >
                String[] contextParts = leftPart.split("[<>]");

                if (contextParts.length == 3) {
                    Symbol l = factory.getSymbol(contextParts[0].trim().charAt(0));
                    Symbol p = factory.getSymbol(contextParts[1].trim().charAt(0));
                    Symbol r = factory.getSymbol(contextParts[2].trim().charAt(0));

                    // Correction ici : on crée des SymbolList proprement
                    SymbolList leftContext = new SymbolList(factory);
                    leftContext.add(l);
                    SymbolList rightContext = new SymbolList(factory);
                    rightContext.add(r);

                    res.add(new ContextRule(p, successor, leftContext, rightContext, weight));
                }
            } else {
                // Règle simple (ex: F ou (0.5)F)
                if (leftPart.length() >= 1) {
                    res.add(new SimpleRule(
                        factory.getSymbol(leftPart.charAt(0)),
                        successor,
                        weight
                    ));
                }
            }
        }
        return res;
    }

    // Getters standard
    public char getRuleSeparator() { return this.rule_separator; }
    public char getPartSeparator() { return this.part_separator; }
    public SymbolFactory getFactory() { return this.factory; }
}