package lindenmeyer.lsystem.history;

import java.util.ArrayList;
import java.util.List;

// PATTERN MEMENTO

/**
 * Gère une liste d'états, sans pourvoir les modifier, et permet d'obtenir
 * l'état historique demandé.
 */
public class History {
    private List<Object> inner;

    /**
     * Génère un nouvel historique initialement vide.
     */
    public History() {
        this.inner = new ArrayList<>();
    }

    /**
     * Stocke un nouvel état dans l'historique
     * 
     * @param s l'état à stocker
     */
    public void addState(Object s) {
        inner.add(s);
    }

    /**
     * Retourne l'état de la génération {@code index}.
     * 
     * @param index index de la génération à retourner
     * @return la génération à retourner
     */
    public Object getState(int index) {
        return inner.get(index);
    }

    /**
     * Retourne la longueur de l'historique.
     * 
     * @return longueur de l'historique
     */
    public int size() {
        return inner.size();
    }

    public void clear()
    {
        this.inner.clear();
    }
}
