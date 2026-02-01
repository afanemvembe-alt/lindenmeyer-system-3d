package lindenmeyer.lsystem;

import java.util.ArrayList;
import java.util.List;

import lindenmeyer.rules.GenericRule;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolList;

/**
 * Classe représentant un système L.
 */
public class LSystem {

    private String axiome;               // La chaîne de départ
    private RuleSet regles;          // Liste des règles de réécriture

    /**
     * Constructeur
     * @param axiome La chaîne initiale
     */
    public LSystem(String axiome) {
        this.axiome = axiome;
        this.regles = new RuleSet();
    }

    /**
     * Ajoute une règle au système
     * @param regle La règle à ajouter
     */
    public void ajouterRegle(GenericRule regle) {
        regles.add(regle);
    }

    /**
     * Génère la chaîne après n itérations
     * @param n Nombre d'itérations
     * @return La chaîne résultante
     */
   public String generer(int n) {
    String resultat = axiome;

    for (int i = 0; i < n; i++) {
        StringBuilder prochain = new StringBuilder();

        for (char c : resultat.toCharArray()) {
            Symbol s = new Symbol(c);
            SymbolList current = SymbolList.of(s);
            boolean applied = false;

            // Parcours des règles
            for (GenericRule r : regles.getRules()) {
                if (r.isApplicable(current)) {
                    for (Symbol sym : r.getSuccessor().getSymbols()) {
                        prochain.append(sym.getSymbol());
                    }
                    applied = true;
                    break;
                }
            }

            // Si aucune règle ne s'applique, on garde le symbole
            if (!applied) {
                prochain.append(c);
            }
        }

        resultat = prochain.toString();
    }

    return resultat;
}



    /**
     * Retourne l'axiome
     */
    public String getAxiome() {
        return axiome;
    }

    /**
     * Définit l'axiome
     */
    public void setAxiome(String axiome) {
        this.axiome = axiome;
    }
}
