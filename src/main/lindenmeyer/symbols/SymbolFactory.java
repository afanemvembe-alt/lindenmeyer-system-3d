package lindenmeyer.symbols;

import java.util.HashMap;
import java.util.Map;

public class SymbolFactory {
    // Le dictionnaire pour stocker les symboles déjà créés
    private final Map<Character, Symbol> inventory = new HashMap<>();

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

    public int size() {
        return inventory.size();
    }

    public static SymbolFactory fromString(String str) {
        SymbolFactory res = new SymbolFactory();

        for (int i = 0; i < str.length(); i++) {
            res.getSymbol(str.charAt(i)); // duplicate values are checked in getSymbol
        }

        return res;
    }
}