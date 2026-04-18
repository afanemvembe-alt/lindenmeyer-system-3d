package lindenmeyer.lsystem.symbols;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant d'associer un ensemble de caractères à un nombre limité de {@link Symbol}.
 *
 * Cette classe permet une implémentation poid-plume.
 */
public class SymbolFactory {

    // Le dictionnaire pour stocker les symboles déjà créés
    private final Map<Character, Symbol> inventory = new HashMap<>();

    /**
     * Retourne le symbole associé au caractère donné en argument.
     *
     * <h4>Garanties</h4>
     *
     * Pour un même caractère donné en entrée, cette fonction retournera toujours le même objet.
     *
     * @param c un caractère
     * @return le symbole correspondant
     */
    public Symbol getSymbol(char c) {
        // 1. Si on ne l'a pas encore, on le crée
        if (!inventory.containsKey(c)) {
            if ("-+*/[]".contains(String.valueOf(c))) {
                inventory.put(c, new Constante(c));
            } else {
                inventory.put(c, new Symbol(c));
            }
        }

        // 2. On renvoie celui qui est dans le dictionnaire
        return inventory.get(c);
    }

    /**
     * Retourne le nombre de symboles stockés en mémoire.
     * @return un entier
     */
    public int size() {
        return inventory.size();
    }

    /**
     * Crée un liste de symboles à partir de la chaîne donnée en entrée.
     * @param str caractères à mettre dans la liste
     * @return liste de symboles
     */
    public static SymbolFactory fromString(String str) {
        SymbolFactory res = new SymbolFactory();

        for (int i = 0; i < str.length(); i++) {
            res.getSymbol(str.charAt(i)); // duplicate values are checked in getSymbol
        }

        return res;
    }
}
