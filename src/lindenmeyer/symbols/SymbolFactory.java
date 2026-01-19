import java.util.HashMap;
import java.util.Map;

public class SymbolFactory {
    // La "Réserve" (ton stock de briques magiques)
    private final Map<Character, Symbol> inventory = new HashMap<>();

    // L'alphabet officiel que tu as défini
    private final String alphabetAutorise = "Ff+-[]XY";

    public Symbol getSymbol(char c) {
        // 1. Vérifier si la lettre fait partie de ton alphabet
        if (alphabetAutorise.indexOf(c) == -1) {
            throw new IllegalArgumentException("Erreur : La lettre '" + c + "' ne fait pas partie de l'alphabet autorisé !");
        }

        // 2. Le Poids-Mouche : on regarde si on a déjà créé cette brique
        if (!inventory.containsKey(c)) {
            // On ne la crée qu'une seule fois
            inventory.put(c, new Symbol(c));
        }
        
        // 3. On retourne toujours la même brique stockée
        return inventory.get(c);
    }

    // Pour prouver au prof que tu économises de la mémoire
    public int getNombreDeBriquesEnMemoire() {
        return inventory.size();
    }
}