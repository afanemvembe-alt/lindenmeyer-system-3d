package lindenmeyer.lsystem;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.rules.*;
import lindenmeyer.symbols.*;

/**
 * Classe permettant de construire de manière simplifiée des {@link LSystem}.
 * 
 * Cette classe permet notamment de traiter des chaines de caractères
 * représentant les paramètres du système.
 */
public class LSystemFactory {
    protected final RuleSetFactory ruleSetFactory;

    public LSystemFactory(char ruleSep, char partSep, SymbolFactory sf) {
        ruleSetFactory = new RuleSetFactory(ruleSep, partSep, sf);
    }

    public LSystemFactory(char ruleSep, char partSep) {
        this(ruleSep, partSep, new SymbolFactory());
    }

    public LSystemFactory(SymbolFactory sf) {
        ruleSetFactory = new RuleSetFactory(sf);
    }

    public LSystemFactory() {
        this(new SymbolFactory());
    }

    /**
     * Crée un nouveau {@link LSystem} à partir des chaines données en entrée.
     * 
     * @param axiomString chaîne représentant le point de départ du système
     * @param ruleString  chaîne représentant les règles du système
     * @return le système à sa première génération avec les paramètres spécifiés
     */
    public LSystem parseString(String axiomString, String ruleString) {
        RuleSet rules = ruleSetFactory.parseString(ruleString);
        Axiom axiom = new Axiom(axiomString);

        return new LSystem(axiom, rules, ruleSetFactory.getFactory());
    }
}
