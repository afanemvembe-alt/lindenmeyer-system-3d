package lsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un système L.
 */
public class LSystem {

    private String axiome;               // La chaîne de départ
    private List<Regle> regles;          // Liste des règles de réécriture

    /**
     * Constructeur
     * @param axiome La chaîne initiale
     */
    public LSystem(String axiome) {
        this.axiome = axiome;
        this.regles = new ArrayList<>();
    }

    /**
     * Ajoute une règle au système
     * @param regle La règle à ajouter
     */
    public void ajouterRegle(Regle regle) {
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
                boolean remplace = false;

                // Applique chaque règle si applicable
                for (Regle r : regles) {
                    if (r.getPredecesseur() == c) {
                        prochain.append(r.getSuccesseur());
                        remplace = true;
                        break;
                    }
                }

                // Si aucune règle ne s'applique, on garde le symbole
                if (!remplace) {
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
