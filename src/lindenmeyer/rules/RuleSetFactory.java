package lindenmeyer.rules;

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
     * @param rule_separator sépare chaque règle dans les chaines donnees
     * @param part_separator sépare le prédécesseur du successeur dans une chaîne
     * @param f une source de `Symbol`
     */
    public RuleSetFactory(char rule_separator, char part_separator, SymbolFactory f) {
        this.rule_separator = rule_separator;
        this.part_separator = part_separator;
        this.factory = f;
    }

    /**
     * Cree `RuleFactory` avec ',' comme séparateur de règles, et '>' comme séparateur de partie de règle.
     * @param f
     */
    public RuleSetFactory(SymbolFactory f) {
        this(',', '>', f);
    }

    /**
     * Retourne le séparateur de règles.
     * @return un character
     */
    public char getRuleSeparator() {
        return this.rule_separator;
    }

    /**
     * Retourne le séparateur de prédécesseur et successeur.
     * @return un character
     */
    public char getPartSeparator() {
        return this.part_separator;
    }

    /**
     * Retourne la source de symboles.
     * @return une source de symboles
     */
    public SymbolFactory getFactory() {
        return this.factory;
    }

    /**
     * Cree un `RuleSet` a partir d'une chaîne de caractères s donnee en entree. Cette chaîne sera
     * sous la forme de [prédécesseur][part_separator][successeur][rule_separator][règle suivante].
     * @param s une chaîne de caractères
     * @return un ensemble de règles
     */
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
