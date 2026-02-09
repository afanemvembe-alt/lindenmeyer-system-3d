package lindenmeyer.lsystem;

import java.util.HashMap;
import java.util.Map;

import lindenmeyer.rules.GenericRule;

public class LSystem {

    private String axiome;
    private Map<Character, String> regles;

    public LSystem(String axiome) {
        this.axiome = axiome;
        this.regles = new HashMap<>();
    }

    public void ajouterRegle(char symbole, String remplacement) {
        regles.put(symbole, remplacement);
    }

    public void ajouterRegle(GenericRule regle) {
        ajouterRegle(regle.getPredecessor().getFirst().getSymbol(), regle.getSuccessor().toString());
    }

    public String generer(int n) {
        String resultat = axiome;

        for (int i = 0; i < n; i++) {
            String nouveau = "";

            for (int j = 0; j < resultat.length(); j++) {
                char c = resultat.charAt(j);

                if (regles.containsKey(c)) {
                    nouveau = nouveau + regles.get(c);
                } else {
                    nouveau = nouveau + c;
                }
            }

            resultat = nouveau;
        }

        return resultat;
    }

    public String getAxiome() {
        return axiome;
    }

    public void setAxiome(String axiome) {
        this.axiome = axiome;
    }
}
