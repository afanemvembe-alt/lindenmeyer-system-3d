package lindenmeyer.rules;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;

/**
 * Permet de contruire un `RuleSet` a partir d'une chaîne de character donnee.
 */
public class RuleSetFactory {

    private char rule_separator;
    private char part_separator;
    private SymbolFactory factory;

    /**
     * Cree `RuleFactory` avec les séparateurs et la `SymbolFactory` donnes.
     *
     * @param rule_separator sépare chaque règle dans les chaines donnees
     * @param part_separator sépare le prédécesseur du successeur dans une chaîne
     * @param f              une source de `Symbol`
     */
    public RuleSetFactory(
        char rule_separator,
        char part_separator,
        SymbolFactory f
    ) {
        this.rule_separator = rule_separator;
        this.part_separator = part_separator;
        this.factory = f;
    }

    /**
     * Cree `RuleFactory` avec ',' comme séparateur de règles, et '>' comme
     * séparateur de partie de règle.
     *
     * @param f
     */
    public RuleSetFactory(SymbolFactory f) {
        this(',', '>', f);
    }

    /**
     * Retourne le séparateur de règles.
     *
     * @return un character
     */
    public char getRuleSeparator() {
        return this.rule_separator;
    }

    /**
     * Retourne le séparateur de prédécesseur et successeur.
     *
     * @return un character
     */
    public char getPartSeparator() {
        return this.part_separator;
    }

    /**
     * Retourne la source de symboles.
     *
     * @return une source de symboles
     */
    public SymbolFactory getFactory() {
        return this.factory;
    }

    /**
     * Cree un `RuleSet` a partir d'une chaîne de caractères s donnee en entree.
     * Cette chaîne sera
     * sous la forme de
     * [prédécesseur][part_separator][successeur][rule_separator][règle suivante].
     *
     * @param s une chaîne de caractères
     * @return un ensemble de règles
     */
    public RuleSet parseString(String s) {
    RuleSet res = new RuleSet();
    if (s == null || s.isEmpty()) return res;

    for (String ruleRaw : s.split(String.valueOf(rule_separator))) {
        ruleRaw = ruleRaw.trim();
        if (ruleRaw.isEmpty()) continue;

        // séparation safe
        String[] parts = ruleRaw.split(String.valueOf(part_separator), 2);
        if (parts.length < 2) continue;

        String leftPart = parts[0].trim();
        String rightPart = parts[1].trim();

        // --- POIDS ---
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

        // --- SUCCESSEUR ---
        SymbolList successor = new SymbolList(this.factory);
        for (char c : rightPart.toCharArray()) {
            successor.add(c);
        }

        // --- CONTEXTE ---
        if (leftPart.contains("<") && leftPart.contains(">")) {

            String[] contextParts = leftPart.trim().split("\\s*[<>]\\s*");

            if (contextParts.length != 3) continue;

            Symbol l = factory.getSymbol(contextParts[0].charAt(0));
            Symbol p = factory.getSymbol(contextParts[1].charAt(0));
            Symbol r = factory.getSymbol(contextParts[2].charAt(0));

            res.add(new ContextRule(
                p,
                successor,
                SymbolList.of(factory, l),
                SymbolList.of(factory, r),
                weight
            ));

        } else {
            // règle simple
            if (leftPart.length() != 1) continue;

            res.add(new SimpleRule(
                factory.getSymbol(leftPart.charAt(0)),
                successor,
                weight
            ));
        }
    }

    return res;
}
}
