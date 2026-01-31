package lindenmeyer.rules;

import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;

/**
 * Permet de contruire un `RuleSet` a partir d'une chaine de caractere donnee.
 */
public class RuleFactory {
    private char rule_separator;
    private char part_separator;
    private SymbolFactory factory;

    /**
     * Cree `RuleFactory` avec les separateurs et la `SymbolFactory` donnes.
     * @param rule_separator separe chaque regle dans les chaines donnees
     * @param part_separator separe le predecesseur du successeur dans une chaine
     * @param f une source de `Symbol`
     */
    public RuleFactory(char rule_separator, char part_separator, SymbolFactory f) {
        this.rule_separator = rule_separator;
        this.part_separator = part_separator;
        this.factory = f;
    }

    /**
     * Cree `RuleFactory` avec ',' comme separateur de regles, et '>' comme separateur de partie de regle.
     * @param f
     */
    public RuleFactory(SymbolFactory f) {
        this(',', '>', f);
    }

    /**
     * Retourne le separateur de regles.
     * @return un caractere
     */
    public char getRuleSeparator() {
        return this.rule_separator;
    }

    /**
     * Retourne le separateur de predecesseur et successeur.
     * @return un caractere
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
     * Cree un `RuleSet` a partir d'une chaine de caracteres s donnee en entree. Cette chaine sera
     * sous la forme de [predecesseur][part_separator][successeur][rule_separator][regle suivante].
     * @param s une chaine de caracteres
     * @return un ensemble de regles
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
